const navigationItems = [
  { id: 'dashboard', label: 'Dashboard', icon: 'DB' },
  { id: 'employees', label: 'Employees', icon: 'EM' },
  { id: 'payroll', label: 'Payroll', icon: 'PR' },
  { id: 'attendance', label: 'Attendance', icon: 'AT' },
  { id: 'performance', label: 'Performance', icon: 'PF' },
  { id: 'settings', label: 'Settings', icon: 'ST' },
]

function Sidebar({ activeSection, onSectionChange, session }) {
  return (
    <aside className="flex min-h-screen w-full flex-col border-r border-slate-200 bg-white px-4 py-5 lg:w-64">
      <div className="mb-8 flex items-center gap-3 px-2">
        <div className="grid size-11 place-items-center rounded-full bg-slate-950 text-sm font-bold text-white">
          HR
        </div>
        <div>
          <p className="text-sm font-bold text-indigo-700">HR Admin</p>
          <p className="text-xs text-slate-500">Enterprise Plan</p>
        </div>
      </div>

      <nav className="grid gap-1">
        {navigationItems.map((item) => {
          const isActive = activeSection === item.id

          return (
            <button
              key={item.id}
              type="button"
              onClick={() => onSectionChange(item.id)}
              className={`flex min-h-11 items-center gap-3 rounded-lg px-3 text-left text-sm font-medium transition ${
                isActive
                  ? 'bg-indigo-100 text-indigo-700'
                  : 'text-slate-700 hover:bg-slate-100 hover:text-slate-950'
              }`}
            >
              <span
                className={`grid size-7 place-items-center rounded-md text-[10px] font-bold ${
                  isActive ? 'bg-indigo-600 text-white' : 'bg-slate-100 text-slate-500'
                }`}
              >
                {item.icon}
              </span>
              {item.label}
            </button>
          )
        })}
      </nav>

      <div className="mt-auto border-t border-slate-200 px-2 pt-4">
        <p className="truncate text-xs font-semibold text-slate-800">{session.user.name}</p>
        <p className="truncate text-xs text-slate-500">{session.user.role}</p>
        <p className="mt-4 text-xs text-slate-400">v2.4.0</p>
      </div>
    </aside>
  )
}

export default Sidebar
