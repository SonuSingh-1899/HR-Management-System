function AuthForm({
  authMode,
  authForm,
  authLoading,
  onFieldChange,
  onModeChange,
  onSubmit,
}) {
  return (
    <form className="grid gap-4" onSubmit={onSubmit}>
      <div className="mb-1 flex gap-2">
        <button
          type="button"
          className={`min-h-11 px-5 py-3 text-sm font-semibold transition ${
            authMode === 'login' ? 'bg-blue-700 text-white' : 'bg-blue-100 text-blue-700'
          }`}
          onClick={() => onModeChange('login')}
        >
          Login
        </button>
        <button
          type="button"
          className={`min-h-11 px-5 py-3 text-sm font-semibold transition ${
            authMode === 'register' ? 'bg-blue-700 text-white' : 'bg-blue-100 text-blue-700'
          }`}
          onClick={() => onModeChange('register')}
        >
          Register
        </button>
      </div>

      {authMode === 'register' ? (
        <label className="grid gap-2 text-sm font-medium text-blue-900/80">
          Name
          <input
            type="text"
            value={authForm.name}
            onChange={(event) => onFieldChange('name', event.target.value)}
            placeholder="Enter your name"
            required
            className="min-h-12 bg-blue-50 px-4 py-3 text-slate-900 outline-none transition focus:bg-blue-100"
          />
        </label>
      ) : null}

      <label className="grid gap-2 text-sm font-medium text-blue-900/80">
        Email
        <input
          type="email"
          value={authForm.email}
          onChange={(event) => onFieldChange('email', event.target.value)}
          placeholder="Enter your email"
          required
          className="min-h-12 bg-blue-50 px-4 py-3 text-slate-900 outline-none transition focus:bg-blue-100"
        />
      </label>

      <label className="grid gap-2 text-sm font-medium text-blue-900/80">
        Password
        <input
          type="password"
          value={authForm.password}
          onChange={(event) => onFieldChange('password', event.target.value)}
          placeholder="Enter your password"
          required
          className="min-h-12 bg-blue-50 px-4 py-3 text-slate-900 outline-none transition focus:bg-blue-100"
        />
      </label>

      <button
        type="submit"
        className="min-h-12 bg-blue-700 px-5 py-3 font-semibold text-white transition hover:bg-blue-800 disabled:cursor-not-allowed disabled:opacity-60"
        disabled={authLoading}
      >
        {authLoading ? 'Please wait...' : authMode === 'register' ? 'Create account' : 'Login'}
      </button>
    </form>
  )
}

export default AuthForm
