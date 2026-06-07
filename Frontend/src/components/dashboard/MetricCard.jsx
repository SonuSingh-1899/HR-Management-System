function MetricCard({ label, value, badge, tone = 'indigo' }) {
  const toneClasses = {
    indigo: 'bg-indigo-100 text-indigo-700',
    emerald: 'bg-emerald-100 text-emerald-700',
    rose: 'bg-rose-100 text-rose-700',
    blue: 'bg-blue-100 text-blue-700',
  }

  return (
    <article className="rounded-xl border border-slate-200 bg-white p-5 shadow-sm">
      <div className="mb-5 flex items-center justify-between">
        <span className={`grid size-10 place-items-center rounded-lg text-xs font-bold ${toneClasses[tone]}`}>
          {label.slice(0, 2).toUpperCase()}
        </span>
        {badge ? (
          <span className="rounded-full bg-emerald-50 px-3 py-1 text-xs font-semibold text-emerald-700">
            {badge}
          </span>
        ) : null}
      </div>
      <p className="mb-1 text-xs font-medium text-slate-500">{label}</p>
      <strong className="text-2xl font-bold text-slate-950">{value}</strong>
    </article>
  )
}

export default MetricCard
