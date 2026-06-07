import { useCallback, useEffect, useState } from 'react'
import AuthPage from './components/auth/AuthPage'
import Dashboard from './components/dashboard/Dashboard'
import AppHeader from './components/layout/AppHeader'
import PortalLayout from './components/layout/PortalLayout'
import { emptyAuthForm, emptyEmployeeForm } from './constants/app'
import { request, toErrorState } from './lib/api'
import { readStoredSession, removeSession, saveSession } from './lib/session'

function App() {
  const [authMode, setAuthMode] = useState('login')
  const [authForm, setAuthForm] = useState(emptyAuthForm)
  const [employeeForm, setEmployeeForm] = useState(emptyEmployeeForm)
  const [session, setSession] = useState(readStoredSession)
  const [employees, setEmployees] = useState([])
  const [myEmployee, setMyEmployee] = useState(null)
  const [selectedEmployeeId, setSelectedEmployeeId] = useState(null)
  const [message, setMessage] = useState('')
  const [error, setError] = useState(null)
  const [authLoading, setAuthLoading] = useState(false)
  const [employeeLoading, setEmployeeLoading] = useState(false)
  const [activeSection, setActiveSection] = useState('dashboard')

  const isManagementUser =
    session?.user?.role === 'ROLE_HR' || session?.user?.role === 'ROLE_MANAGER'

  const clearSession = useCallback(() => {
    setSession(null)
    setEmployees([])
    setMyEmployee(null)
    setSelectedEmployeeId(null)
    setActiveSection('dashboard')
    setEmployeeForm(emptyEmployeeForm)
    removeSession()
  }, [])

  useEffect(() => {
    if (!session?.accessToken) {
      return
    }

    let isCancelled = false
    const accessToken = session.accessToken

    async function syncDashboard() {
      setError(null)

      try {
        const currentUser = await request('/api/users/me', {
          token: accessToken,
        })

        if (isCancelled) {
          return
        }

        setSession((currentSession) => {
          if (!currentSession) {
            return currentSession
          }

          const nextSession = {
            ...currentSession,
            user: currentUser,
          }

          saveSession(nextSession)
          return nextSession
        })

        try {
          const employeeProfile = await request('/api/employees/me', {
            token: accessToken,
          })

          if (!isCancelled) {
            setMyEmployee(employeeProfile)
          }
        } catch (employeeError) {
          if (!isCancelled) {
            setMyEmployee(null)
            setMessage(toErrorState(employeeError).message)
          }
        }

        if (currentUser.role === 'ROLE_HR' || currentUser.role === 'ROLE_MANAGER') {
          const employeeList = await request('/api/employees', {
            token: accessToken,
          })

          if (!isCancelled) {
            setEmployees(employeeList)
          }
        }
      } catch (dashboardError) {
        if (!isCancelled) {
          clearSession()
          setError(toErrorState(dashboardError))
        }
      }
    }

    void syncDashboard()

    return () => {
      isCancelled = true
    }
  }, [clearSession, session?.accessToken])

  function resetMessages() {
    setError(null)
    setMessage('')
  }

  function handleRequestError(error, fallbackMessage) {
    const nextError = toErrorState(error)

    setError({
      ...nextError,
      message: nextError.message || fallbackMessage,
    })

    if (nextError.status === 401) {
      clearSession()
    }
  }

  function updateAuthField(field, value) {
    setAuthForm((currentForm) => ({
      ...currentForm,
      [field]: value,
    }))
  }

  function updateEmployeeField(field, value) {
    setEmployeeForm((currentForm) => ({
      ...currentForm,
      [field]: value,
    }))
  }

  function fillEmployeeForm(employee) {
    setEmployeeForm({
      employeeCode: employee.employeeCode ?? '',
      firstName: employee.firstName ?? '',
      lastName: employee.lastName ?? '',
      workEmail: employee.workEmail ?? '',
      phoneNumber: employee.phoneNumber ?? '',
      department: employee.department ?? '',
      designation: employee.designation ?? '',
      employmentType: employee.employmentType ?? 'FULL_TIME',
      joiningDate: employee.joiningDate ?? '',
      salary: employee.salary?.toString() ?? '',
      managerName: employee.managerName ?? '',
      emergencyContactName: employee.emergencyContactName ?? '',
      emergencyContactPhone: employee.emergencyContactPhone ?? '',
      address: employee.address ?? '',
      status: employee.status ?? 'ACTIVE',
    })
  }

  async function refreshEmployees() {
    const employeeList = await request('/api/employees', {
      token: session.accessToken,
    })

    setEmployees(employeeList)
  }

  async function handleAuthSubmit(event) {
    event.preventDefault()
    resetMessages()
    setAuthLoading(true)

    try {
      const payload =
        authMode === 'register'
          ? authForm
          : {
              email: authForm.email,
              password: authForm.password,
            }

      const response = await request(
        authMode === 'register' ? '/api/auth/register' : '/api/auth/login',
        {
          method: 'POST',
          body: payload,
        },
      )

      setSession(response)
      saveSession(response)
      setAuthForm(emptyAuthForm)
      setMessage(authMode === 'register' ? 'Account created successfully.' : 'Login successful.')
    } catch (authError) {
      handleRequestError(authError, 'Authentication failed. Please try again.')
    } finally {
      setAuthLoading(false)
    }
  }

  async function handleEmployeeSubmit(event) {
    event.preventDefault()
    resetMessages()
    setEmployeeLoading(true)

    try {
      const payload = {
        ...employeeForm,
        salary: Number(employeeForm.salary),
      }

      const path = selectedEmployeeId ? `/api/employees/${selectedEmployeeId}` : '/api/employees'
      const method = selectedEmployeeId ? 'PUT' : 'POST'

      await request(path, {
        method,
        body: payload,
        token: session.accessToken,
      })

      setEmployeeForm(emptyEmployeeForm)
      setSelectedEmployeeId(null)
      setMessage(selectedEmployeeId ? 'Employee updated successfully.' : 'Employee created successfully.')
      await refreshEmployees()
    } catch (employeeError) {
      handleRequestError(employeeError, 'Employee could not be saved. Please try again.')
    } finally {
      setEmployeeLoading(false)
    }
  }

  async function handleDeleteEmployee(employeeId) {
    resetMessages()
    setEmployeeLoading(true)

    try {
      await request(`/api/employees/${employeeId}`, {
        method: 'DELETE',
        token: session.accessToken,
      })

      await refreshEmployees()
      if (selectedEmployeeId === employeeId) {
        setSelectedEmployeeId(null)
        setEmployeeForm(emptyEmployeeForm)
      }

      setMessage('Employee deleted successfully.')
    } catch (employeeError) {
      handleRequestError(employeeError, 'Employee could not be deleted. Please try again.')
    } finally {
      setEmployeeLoading(false)
    }
  }

  function switchMode(nextMode) {
    resetMessages()
    setAuthMode(nextMode)
  }

  function startEditEmployee(employee) {
    setSelectedEmployeeId(employee.id)
    fillEmployeeForm(employee)
    resetMessages()
  }

  function cancelEditEmployee() {
    setSelectedEmployeeId(null)
    setEmployeeForm(emptyEmployeeForm)
    resetMessages()
  }

  return (
    <main className={session ? 'min-h-screen bg-slate-50' : 'min-h-screen bg-gradient-to-b from-white to-blue-50 text-blue-950'}>
      {!session ? (
        <>
          <AppHeader session={session} onLogout={clearSession} />
          <AuthPage
            authForm={authForm}
            authLoading={authLoading}
            authMode={authMode}
            error={error}
            message={message}
            onFieldChange={updateAuthField}
            onModeChange={switchMode}
            onSubmit={handleAuthSubmit}
          />
        </>
      ) : (
        <PortalLayout
          activeSection={activeSection}
          session={session}
          onLogout={clearSession}
          onSectionChange={setActiveSection}
        >
          <Dashboard
            activeSection={activeSection}
            employeeForm={employeeForm}
            employeeLoading={employeeLoading}
            employees={employees}
            error={error}
            isManagementUser={isManagementUser}
            message={message}
            myEmployee={myEmployee}
            selectedEmployeeId={selectedEmployeeId}
            session={session}
            onCancelEdit={cancelEditEmployee}
            onDeleteEmployee={handleDeleteEmployee}
            onEditEmployee={startEditEmployee}
            onEmployeeFieldChange={updateEmployeeField}
            onEmployeeSubmit={handleEmployeeSubmit}
          />
        </PortalLayout>
      )}
    </main>
  )
}

export default App
