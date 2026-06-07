import { Component } from 'react'

class ErrorBoundary extends Component {
  constructor(props) {
    super(props)
    this.state = { hasError: false }
  }

  static getDerivedStateFromError() {
    return { hasError: true }
  }

  componentDidCatch(error, errorInfo) {
    console.error('Frontend render error:', error, errorInfo)
  }

  render() {
    if (this.state.hasError) {
      return (
        <main className="grid min-h-screen place-items-center bg-blue-50 px-5 text-blue-950">
          <section className="max-w-xl border-l-4 border-red-600 bg-white px-6 py-5 shadow-sm">
            <p className="mb-2 text-sm font-semibold uppercase tracking-wider text-red-700">
              Application error
            </p>
            <h1 className="mb-3 text-2xl font-semibold">Something went wrong</h1>
            <p className="leading-7 text-blue-900/80">
              Please refresh the page. If the issue continues, check the browser console and backend
              logs for the failed request.
            </p>
          </section>
        </main>
      )
    }

    return this.props.children
  }
}

export default ErrorBoundary
