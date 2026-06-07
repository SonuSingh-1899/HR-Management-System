const trendData = [
  { month: 'Jan', value: 38 },
  { month: 'Feb', value: 52 },
  { month: 'Mar', value: 70 },
  { month: 'Apr', value: 34 },
  { month: 'May', value: 86 },
  { month: 'Jun', value: 55 },
]

function WorkforceTrend() {
  return (
    <section className="rounded-xl border border-slate-200 bg-white p-5 shadow-sm">
      <div className="mb-8 flex flex-col gap-3 sm:flex-row sm:items-start sm:justify-between">
        <div>
          <h2 className="text-lg font-bold text-slate-950">Workforce Trend</h2>
          <p className="text-xs text-slate-500">Monthly hiring growth vs attrition</p>
        </div>
        <div className="flex gap-2">
          <button
            type="button"
            className="rounded-lg border border-slate-200 px-3 py-2 text-xs font-semibold text-slate-600"
          >
            6 Months
          </button>
          <button
            type="button"
            className="rounded-lg bg-indigo-600 px-3 py-2 text-xs font-semibold text-white"
          >
            1 Year
          </button>
        </div>
      </div>

      <div className="flex h-56 items-end gap-4 border-b border-slate-200 px-2">
        {trendData.map((item) => (
          <div key={item.month} className="flex flex-1 flex-col items-center gap-2">
            <div className="flex h-44 w-full items-end rounded-t-lg bg-indigo-100">
              <div
                className="w-full rounded-t-lg bg-indigo-700"
                style={{ height: `${item.value}%` }}
              />
            </div>
            <span className="text-xs font-medium text-slate-500">{item.month}</span>
          </div>
        ))}
      </div>
    </section>
  )
}

export default WorkforceTrend
