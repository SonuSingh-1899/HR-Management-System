function FeedbackMessage({ error, message }) {
  if (!error && !message) {
    return null
  }

  const errorMessage = typeof error === 'string' ? error : error?.message
  const errorDetails = typeof error === 'string' ? [] : (error?.details ?? [])

  return (
    <div className="space-y-2 py-2">
      {errorMessage ? (
        <div className="border-l-4 border-red-600 bg-red-50 px-4 py-3 text-red-800">
          <p className="text-sm font-semibold">{errorMessage}</p>
          {errorDetails.length > 0 ? (
            <ul className="mt-2 list-disc space-y-1 pl-5 text-sm">
              {errorDetails.map((detail) => (
                <li key={detail}>{detail}</li>
              ))}
            </ul>
          ) : null}
        </div>
      ) : null}
      {message ? <p className="text-sm font-medium text-blue-700">{message}</p> : null}
    </div>
  )
}

export default FeedbackMessage
