export const gatewayBaseUrl = import.meta.env.VITE_BACKEND_URL ?? import.meta.env.VITE_GATEWAY_URL ?? ''

export const gatewayDisplayUrl = gatewayBaseUrl || 'Vite proxy -> http://localhost:8080'

export const sessionStorageKey = 'hr-management-session'

export const emptyAuthForm = {
  name: '',
  email: '',
  password: '',
}

export const emptyEmployeeForm = {
  employeeCode: '',
  firstName: '',
  lastName: '',
  workEmail: '',
  phoneNumber: '',
  department: '',
  designation: '',
  employmentType: 'FULL_TIME',
  joiningDate: '',
  salary: '',
  managerName: '',
  emergencyContactName: '',
  emergencyContactPhone: '',
  address: '',
  status: 'ACTIVE',
}

export const employmentTypes = ['FULL_TIME', 'PART_TIME', 'CONTRACT', 'INTERN']

export const employeeStatuses = ['ACTIVE', 'INACTIVE', 'ON_LEAVE', 'TERMINATED']
