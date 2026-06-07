import InfoField from '../common/InfoField'

function EmployeeProfile({ employee }) {
  return (
    <section>
      <h2 className="mb-5 text-2xl font-semibold text-blue-700">Employee profile</h2>
      {employee ? (
        <div className="grid gap-6 md:grid-cols-3">
          <InfoField label="Employee code" value={employee.employeeCode} />
          <InfoField label="Department" value={employee.department} />
          <InfoField label="Designation" value={employee.designation} />
          <InfoField label="Joining date" value={employee.joiningDate} />
          <InfoField label="Employment type" value={employee.employmentType} />
          <InfoField label="Status" value={employee.status} />
        </div>
      ) : (
        <p className="max-w-3xl leading-7 text-blue-900/80">
          No employee profile is linked to this login yet. This is expected for a freshly
          registered account.
        </p>
      )}
    </section>
  )
}

export default EmployeeProfile
