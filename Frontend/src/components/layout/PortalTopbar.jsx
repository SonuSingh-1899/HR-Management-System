function PortalTopbar({ activeSection, session, onLogout }) {
  const sectionTitle = activeSection.charAt(0).toUpperCase() + activeSection.slice(1)

  return (
    <header className="flex min-h-16 items-center justify-between border-b border-slate-200 bg-white px-5 lg:px-8">
      <div>
        <h1 className="text-lg font-bold text-slate-950">HRMS Portal</h1>
        <p className="text-xs font-medium text-slate-500">{sectionTitle}</p>
      </div>

      <div className="flex items-center gap-4">
        <div className="hidden text-right sm:block">
          <p className="text-sm font-semibold text-slate-900">{session.user.name}</p>
          <p className="text-xs text-slate-500">{session.user.role}</p>
        </div>
        <button
          type="button"
          onClick={onLogout}
          className="rounded-lg border border-slate-200 px-4 py-2 text-sm font-semibold text-slate-700 transition hover:bg-slate-100"
        >
          Logout
        </button>
      </div>
    </header>
  )
}

export default PortalTopbar
