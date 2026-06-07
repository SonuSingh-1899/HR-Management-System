import { employeeStatuses, employmentTypes } from '../../constants/app'
import FormField from './FormField'

const inputClass =
  'min-h-12 bg-blue-50 px-4 py-3 text-slate-900 outline-none transition focus:bg-blue-100'

function EmployeeForm({
  employeeForm,
  employeeLoading,
  selectedEmployeeId,
  onCancelEdit,
  onFieldChange,
  onSubmit,
}) {
  return (
    <section>
      <div className="mb-5 flex flex-col gap-4 md:flex-row md:items-center md:justify-between">
        <h2 className="text-2xl font-semibold text-blue-700">
          {selectedEmployeeId ? 'Update employee' : 'Create employee'}
        </h2>
        {selectedEmployeeId ? (
          <button
            type="button"
            onClick={onCancelEdit}
            className="min-h-11 bg-blue-100 px-5 py-3 text-sm font-semibold text-blue-700 transition hover:bg-blue-200"
          >
            Cancel edit
          </button>
        ) : null}
      </div>

      <form className="grid gap-4 md:grid-cols-2" onSubmit={onSubmit}>
        <FormField label="Employee code">
          <input
            type="text"
            value={employeeForm.employeeCode}
            onChange={(event) => onFieldChange('employeeCode', event.target.value)}
            required
            className={inputClass}
          />
        </FormField>
        <FormField label="First name">
          <input
            type="text"
            value={employeeForm.firstName}
            onChange={(event) => onFieldChange('firstName', event.target.value)}
            required
            className={inputClass}
          />
        </FormField>
        <FormField label="Last name">
          <input
            type="text"
            value={employeeForm.lastName}
            onChange={(event) => onFieldChange('lastName', event.target.value)}
            required
            className={inputClass}
          />
        </FormField>
        <FormField label="Work email">
          <input
            type="email"
            value={employeeForm.workEmail}
            onChange={(event) => onFieldChange('workEmail', event.target.value)}
            required
            className={inputClass}
          />
        </FormField>
        <FormField label="Phone number">
          <input
            type="text"
            value={employeeForm.phoneNumber}
            onChange={(event) => onFieldChange('phoneNumber', event.target.value)}
            required
            className={inputClass}
          />
        </FormField>
        <FormField label="Department">
          <input
            type="text"
            value={employeeForm.department}
            onChange={(event) => onFieldChange('department', event.target.value)}
            required
            className={inputClass}
          />
        </FormField>
        <FormField label="Designation">
          <input
            type="text"
            value={employeeForm.designation}
            onChange={(event) => onFieldChange('designation', event.target.value)}
            required
            className={inputClass}
          />
        </FormField>
        <FormField label="Employment type">
          <select
            value={employeeForm.employmentType}
            onChange={(event) => onFieldChange('employmentType', event.target.value)}
            className={inputClass}
          >
            {employmentTypes.map((type) => (
              <option key={type} value={type}>
                {type}
              </option>
            ))}
          </select>
        </FormField>
        <FormField label="Joining date">
          <input
            type="date"
            value={employeeForm.joiningDate}
            onChange={(event) => onFieldChange('joiningDate', event.target.value)}
            required
            className={inputClass}
          />
        </FormField>
        <FormField label="Salary">
          <input
            type="number"
            min="0"
            step="0.01"
            value={employeeForm.salary}
            onChange={(event) => onFieldChange('salary', event.target.value)}
            required
            className={inputClass}
          />
        </FormField>
        <FormField label="Manager name">
          <input
            type="text"
            value={employeeForm.managerName}
            onChange={(event) => onFieldChange('managerName', event.target.value)}
            required
            className={inputClass}
          />
        </FormField>
        <FormField label="Emergency contact name">
          <input
            type="text"
            value={employeeForm.emergencyContactName}
            onChange={(event) => onFieldChange('emergencyContactName', event.target.value)}
            required
            className={inputClass}
          />
        </FormField>
        <FormField label="Emergency contact phone">
          <input
            type="text"
            value={employeeForm.emergencyContactPhone}
            onChange={(event) => onFieldChange('emergencyContactPhone', event.target.value)}
            required
            className={inputClass}
          />
        </FormField>
        <FormField label="Address" wide>
          <textarea
            value={employeeForm.address}
            onChange={(event) => onFieldChange('address', event.target.value)}
            required
            className={`${inputClass} min-h-28 resize-y`}
          />
        </FormField>
        <FormField label="Status">
          <select
            value={employeeForm.status}
            onChange={(event) => onFieldChange('status', event.target.value)}
            className={inputClass}
          >
            {employeeStatuses.map((status) => (
              <option key={status} value={status}>
                {status}
              </option>
            ))}
          </select>
        </FormField>

        <button
          type="submit"
          className="min-h-12 bg-blue-700 px-5 py-3 font-semibold text-white transition hover:bg-blue-800 disabled:cursor-not-allowed disabled:opacity-60"
          disabled={employeeLoading}
        >
          {employeeLoading
            ? 'Saving...'
            : selectedEmployeeId
              ? 'Update employee'
              : 'Create employee'}
        </button>
      </form>
    </section>
  )
}

export default EmployeeForm
