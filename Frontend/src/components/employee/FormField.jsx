function FormField({ label, children, wide = false }) {
  return (
    <label className={`grid gap-2 text-sm font-medium text-blue-900/80 ${wide ? 'md:col-span-2' : ''}`}>
      {label}
      {children}
    </label>
  )
}

export default FormField
