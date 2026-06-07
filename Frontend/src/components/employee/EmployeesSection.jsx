import FeedbackMessage from '../common/FeedbackMessage'
import EmployeeProfile from '../dashboard/EmployeeProfile'
import UserProfile from '../dashboard/UserProfile'
import EmployeeForm from './EmployeeForm'
import EmployeeList from './EmployeeList'

function EmployeesSection({
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
  return (
    <section className="space-y-6 px-5 py-6 lg:px-8">
      <FeedbackMessage error={error} message={message} />

      <div className="grid gap-6 xl:grid-cols-2">
        <div className="rounded-xl border border-slate-200 bg-white p-5 shadow-sm">
          <UserProfile user={session.user} />
        </div>
        <div className="rounded-xl border border-slate-200 bg-white p-5 shadow-sm">
          <EmployeeProfile employee={myEmployee} />
        </div>
      </div>

      {isManagementUser ? (
        <>
          <div className="rounded-xl border border-slate-200 bg-white p-5 shadow-sm">
            <EmployeeForm
              employeeForm={employeeForm}
              employeeLoading={employeeLoading}
              selectedEmployeeId={selectedEmployeeId}
              onCancelEdit={onCancelEdit}
              onFieldChange={onEmployeeFieldChange}
              onSubmit={onEmployeeSubmit}
            />
          </div>
          <div className="rounded-xl border border-slate-200 bg-white p-5 shadow-sm">
            <EmployeeList
              employees={employees}
              employeeLoading={employeeLoading}
              onDelete={onDeleteEmployee}
              onEdit={onEditEmployee}
            />
          </div>
        </>
      ) : null}
    </section>
  )
}

export default EmployeesSection
