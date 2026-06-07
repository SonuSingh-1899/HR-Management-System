const activities = [
  {
    title: 'Leave request approved',
    description: 'Michael Scott for 3 days starting Monday.',
    time: '2 hours ago',
  },
  {
    title: 'New login',
    description: 'Admin detected from San Francisco, CA.',
    time: '4 hours ago',
  },
  {
    title: 'New employee onboarding',
    description: 'Pam Beesly joined the Marketing team.',
    time: '6 hours ago',
  },
]

function RecentActivity() {
  return (
    <section className="rounded-xl border border-slate-200 bg-white p-5 shadow-sm">
      <h2 className="mb-6 text-lg font-bold text-slate-950">Recent Activity</h2>
      <div className="grid gap-6">
        {activities.map((activity) => (
          <article key={activity.title} className="flex gap-4">
            <span className="mt-1 grid size-8 shrink-0 place-items-center rounded-full bg-indigo-100 text-xs font-bold text-indigo-700">
              {activity.title.slice(0, 1)}
            </span>
            <div>
              <h3 className="text-sm font-semibold text-slate-950">{activity.title}</h3>
              <p className="mt-1 text-sm leading-6 text-slate-500">{activity.description}</p>
              <p className="mt-1 text-xs font-medium text-slate-400">{activity.time}</p>
            </div>
          </article>
        ))}
      </div>
      <button type="button" className="mt-6 text-sm font-semibold text-indigo-700">
        View all logs
      </button>
    </section>
  )
}

export default RecentActivity
