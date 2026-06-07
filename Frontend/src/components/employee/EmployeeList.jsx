function EmployeeList({ employees, employeeLoading, onDelete, onEdit }) {
  return (
    <section>
      <h2 className="mb-5 text-2xl font-semibold text-blue-700">Employee list</h2>
      {employees.length > 0 ? (
        <div className="grid gap-3">
          {employees.map((employee) => (
            <div
              key={employee.id}
              className="grid gap-4 bg-blue-50 px-4 py-4 md:grid-cols-[minmax(0,1fr)_auto] md:items-center"
            >
              <div className="flex flex-wrap items-center gap-x-5 gap-y-2">
                <strong className="text-slate-900">
                  {employee.firstName} {employee.lastName}
                </strong>
                <span className="text-sm text-blue-900/80">{employee.designation}</span>
                <span className="text-sm text-blue-900/80">{employee.department}</span>
                <span className="break-all text-sm text-blue-900/80">{employee.workEmail}</span>
              </div>
              <div className="flex gap-3">
                <button
                  type="button"
                  onClick={() => onEdit(employee)}
                  className="min-h-11 flex-1 bg-blue-100 px-5 py-3 text-sm font-semibold text-blue-700 transition hover:bg-blue-200 md:flex-none"
                >
                  Edit
                </button>
                <button
                  type="button"
                  onClick={() => onDelete(employee.id)}
                  disabled={employeeLoading}
                  className="min-h-11 flex-1 bg-blue-100 px-5 py-3 text-sm font-semibold text-blue-700 transition hover:bg-blue-200 disabled:cursor-not-allowed disabled:opacity-60 md:flex-none"
                >
                  Delete
                </button>
              </div>
            </div>
          ))}
        </div>
      ) : (
        <p className="leading-7 text-blue-900/80">No employees found yet.</p>
      )}
    </section>
  )
}

export default EmployeeList
