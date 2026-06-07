import { gatewayBaseUrl, gatewayDisplayUrl } from '../constants/app'

const fallbackMessages = {
  400: 'Request data is not valid. Please check the highlighted details.',
  401: 'Your session has expired. Please login again.',
  403: 'You do not have permission to perform this action.',
  404: 'The requested resource was not found.',
  409: 'This request conflicts with existing data.',
  500: 'Server error. Please try again after a moment.',
}

export class ApiError extends Error {
  constructor({ message, status, details, path, error, cause }) {
    super(message, { cause })
    this.name = 'ApiError'
    this.status = status
    this.details = details
    this.path = path
    this.error = error
  }
}

function parseErrorDetails(details) {
  if (!details) {
    return []
  }

  if (typeof details === 'string') {
    return [details]
  }

  if (Array.isArray(details)) {
    return details.map(String)
  }

  if (typeof details === 'object') {
    return Object.entries(details).map(([field, value]) => `${field}: ${value}`)
  }

  return [String(details)]
}

export function toErrorState(error) {
  if (error instanceof ApiError) {
    return {
      message: error.message,
      details: parseErrorDetails(error.details),
      status: error.status,
      path: error.path,
    }
  }

  return {
    message: error?.message || 'Something went wrong. Please try again.',
    details: [],
    status: null,
    path: null,
  }
}

function getFriendlyMessage(status, data) {
  return data?.message || fallbackMessages[status] || 'Request failed. Please try again.'
}

async function parseResponseBody(response) {
  const rawText = await response.text()

  if (!rawText) {
    return null
  }

  try {
    return JSON.parse(rawText)
  } catch (parseError) {
    throw new ApiError({
      message: 'Server returned an invalid response.',
      status: response.status,
      details: rawText,
      path: response.url,
      error: response.statusText,
      cause: parseError,
    })
  }
}

export async function request(path, options = {}) {
  let response

  try {
    response = await fetch(`${gatewayBaseUrl}${path}`, {
      method: options.method ?? 'GET',
      headers: {
        'Content-Type': 'application/json',
        ...(options.token
          ? {
              Authorization: `${options.tokenType ?? 'Bearer'} ${options.token}`,
            }
          : {}),
      },
      body: options.body ? JSON.stringify(options.body) : undefined,
    })
  } catch (networkError) {
    throw new ApiError({
      message: `Cannot connect to backend at ${gatewayDisplayUrl}. Please make sure the monolith is running.`,
      status: 0,
      details: networkError.message,
      path,
      error: 'Network Error',
      cause: networkError,
    })
  }

  const data = await parseResponseBody(response)

  if (!response.ok) {
    throw new ApiError({
      message: getFriendlyMessage(response.status, data),
      status: response.status,
      details: data?.details,
      path: data?.path ?? path,
      error: data?.error ?? response.statusText,
    })
  }

  return data
}
