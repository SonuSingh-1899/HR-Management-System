import ComingSoon from '../common/ComingSoon'
import EmployeesSection from '../employee/EmployeesSection'
import DashboardOverview from './DashboardOverview'

const sectionTitles = {
  payroll: 'Payroll',
  attendance: 'Attendance',
  performance: 'Performance',
  settings: 'Settings',
}

function Dashboard({
  activeSection,
  employeeForm,
  employeeLoading,
  employees,
  error,
  isManagementUser,
  message,
  myEmployee,
  selectedEmployeeId,
  session,
  onCancelEdit,
  onDeleteEmployee,
  onEditEmployee,
  onEmployeeFieldChange,
  onEmployeeSubmit,
}) {
  if (activeSection === 'employees') {
    return (
      <EmployeesSection
        employeeForm={employeeForm}
        employeeLoading={employeeLoading}
        employees={employees}
        error={error}
        isManagementUser={isManagementUser}
        message={message}
        myEmployee={myEmployee}
        selectedEmployeeId={selectedEmployeeId}
        session={session}
        onCancelEdit={onCancelEdit}
        onDeleteEmployee={onDeleteEmployee}
        onEditEmployee={onEditEmployee}
        onEmployeeFieldChange={onEmployeeFieldChange}
        onEmployeeSubmit={onEmployeeSubmit}
      />
    )
  }

  if (activeSection !== 'dashboard') {
    return <ComingSoon title={sectionTitles[activeSection] ?? 'Section'} />
  }

  return (
    <DashboardOverview
      employeeCount={employees.length}
      error={error}
      isManagementUser={isManagementUser}
      message={message}
      session={session}
    />
  )
}

export default Dashboard
