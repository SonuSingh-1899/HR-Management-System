const anniversaries = [
  { name: 'Jim Halpert', role: 'Sales Specialist', meta: '3 Years · Sept 15' },
  { name: 'Angela Martin', role: 'Accountant', meta: 'Birthday · Sept 18' },
  { name: 'Dwight Schrute', role: 'Regional Manager', meta: '10 Years · Sept 21' },
]

function AnniversaryList() {
  return (
    <section>
      <div className="mb-4 flex items-center justify-between">
        <h2 className="text-lg font-bold text-slate-950">Upcoming Anniversaries</h2>
        <button type="button" className="text-sm font-semibold text-indigo-700">
          View Calendar
        </button>
      </div>
      <div className="grid gap-4 lg:grid-cols-3">
        {anniversaries.map((person) => (
          <article
            key={person.name}
            className="flex items-center gap-4 rounded-xl border border-slate-200 bg-white p-5 shadow-sm"
          >
            <div className="grid size-12 place-items-center rounded-full bg-slate-900 text-sm font-bold text-white">
              {person.name
                .split(' ')
                .map((part) => part[0])
                .join('')}
            </div>
            <div>
              <h3 className="text-sm font-bold text-slate-950">{person.name}</h3>
              <p className="text-xs text-slate-500">{person.role}</p>
              <p className="mt-2 text-xs font-semibold text-indigo-700">{person.meta}</p>
            </div>
          </article>
        ))}
      </div>
    </section>
  )
}

export default AnniversaryList
