import { gatewayDisplayUrl } from '../../constants/app'

function AppHeader({ session, onLogout }) {
  return (
    <header className="flex flex-col gap-6 bg-blue-700 px-5 py-8 text-white md:flex-row md:items-start md:justify-between md:px-16 md:py-12">
      <div>
        <p className="mb-2 text-xs font-semibold uppercase tracking-widest text-blue-100">
          HR management
        </p>
        <h1 className="text-3xl font-semibold leading-tight md:text-5xl">Minimal gateway frontend</h1>
      </div>

      {session ? (
        <div className="flex flex-col gap-4 md:flex-row md:items-center">
          <p className="text-sm">
            {session.user.name}
            <span className="mt-1 block text-blue-100">{session.user.role}</span>
          </p>
          <button
            type="button"
            onClick={onLogout}
            className="min-h-11 bg-blue-100 px-5 py-3 text-sm font-semibold text-blue-700 transition hover:bg-white"
          >
            Logout
          </button>
        </div>
      ) : (
        <p className="text-sm text-blue-100">Connected to {gatewayDisplayUrl}</p>
      )}
    </header>
  )
}

export default AppHeader
