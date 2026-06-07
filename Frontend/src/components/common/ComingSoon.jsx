function ComingSoon({ title }) {
  return (
    <section className="grid min-h-[28rem] place-items-center px-5 py-10">
      <div className="max-w-xl rounded-xl border border-slate-200 bg-white px-8 py-10 text-center shadow-sm">
        <p className="mb-3 text-xs font-bold uppercase tracking-widest text-indigo-600">
          Module in progress
        </p>
        <h2 className="mb-3 text-2xl font-bold text-slate-950">{title}</h2>
        <p className="leading-7 text-slate-500">
          Coming soon. This section is reserved for the next phase of the HRMS portal.
        </p>
      </div>
    </section>
  )
}

export default ComingSoon
