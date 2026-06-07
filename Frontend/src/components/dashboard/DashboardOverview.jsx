import FeedbackMessage from '../common/FeedbackMessage'
import MetricCard from './MetricCard'
import RecentActivity from './RecentActivity'
import AnniversaryList from './AnniversaryList'
import WorkforceTrend from './WorkforceTrend'

function DashboardOverview({ employeeCount, error, isManagementUser, message, session }) {
  return (
    <section className="space-y-6 px-5 py-6 lg:px-8">
      <FeedbackMessage error={error} message={message} />

      <div className="grid gap-4 md:grid-cols-2 xl:grid-cols-4">
        <MetricCard label="Total Employees" value={employeeCount || '0'} badge="+4.2%" />
        <MetricCard label="Attendance Rate" value="94%" badge="Optimal" tone="blue" />
        <MetricCard label="Pending Leaves" value="12" badge="High" tone="rose" />
        <MetricCard
          label="Retention Rate"
          value={isManagementUser ? '88.2%' : session.user.active ? 'Active' : 'Inactive'}
          badge="Avg 4.5"
          tone="emerald"
        />
      </div>

      <div className="grid gap-6 xl:grid-cols-[minmax(0,2fr)_minmax(20rem,1fr)]">
        <WorkforceTrend />
        <RecentActivity />
      </div>

      <AnniversaryList />
    </section>
  )
}

export default DashboardOverview
