import { gatewayDisplayUrl } from '../../constants/app'
import InfoField from '../common/InfoField'

function StatusStrip({ session, isManagementUser }) {
  return (
    <section className="grid gap-4 pb-8 pt-2 md:grid-cols-3">
      <InfoField label="Gateway URL" value={gatewayDisplayUrl} />
      <InfoField label="Current user" value={session.user.email} />
      <InfoField
        label="Access"
        value={isManagementUser ? 'Management dashboard' : 'Self profile only'}
      />
    </section>
  )
}

export default StatusStrip
