function AuthIntro() {
  return (
    <div>
      <h2 className="mb-4 text-2xl font-semibold text-blue-700">Single entry through the gateway</h2>
      <p className="mb-4 leading-7 text-blue-900/80">
        This frontend talks only to <strong>gateway-service</strong>. The gateway forwards auth
        requests to the auth service and employee requests to the employee service.
      </p>
      <p className="leading-7 text-blue-900/80">
        Theme is intentionally plain: white surface, blue actions, straight layout, no cards.
      </p>
    </div>
  )
}

export default AuthIntro
