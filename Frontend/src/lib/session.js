import { sessionStorageKey } from '../constants/app'

export function readStoredSession() {
  const savedSession = window.localStorage.getItem(sessionStorageKey)

  if (!savedSession) {
    return null
  }

  try {
    return JSON.parse(savedSession)
  } catch {
    window.localStorage.removeItem(sessionStorageKey)
    return null
  }
}

export function saveSession(session) {
  window.localStorage.setItem(sessionStorageKey, JSON.stringify(session))
}

export function removeSession() {
  window.localStorage.removeItem(sessionStorageKey)
}
