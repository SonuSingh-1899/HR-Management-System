import Sidebar from './Sidebar'
import PortalTopbar from './PortalTopbar'

function PortalLayout({ activeSection, children, onLogout, onSectionChange, session }) {
  return (
    <div className="min-h-screen bg-slate-50 text-slate-950 lg:flex">
      <div className="lg:sticky lg:top-0 lg:h-screen">
        <Sidebar
          activeSection={activeSection}
          onSectionChange={onSectionChange}
          session={session}
        />
      </div>

      <div className="min-w-0 flex-1">
        <PortalTopbar activeSection={activeSection} session={session} onLogout={onLogout} />
        {children}
      </div>
    </div>
  )
}

export default PortalLayout
