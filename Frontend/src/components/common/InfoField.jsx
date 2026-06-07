function InfoField({ label, value }) {
  return (
    <div>
      <p className="mb-1 text-xs font-semibold uppercase tracking-wider text-blue-500">{label}</p>
      <strong className="break-words text-base font-semibold text-slate-900">{value}</strong>
    </div>
  )
}

export default InfoField
