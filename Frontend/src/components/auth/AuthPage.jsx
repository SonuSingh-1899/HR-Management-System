import AuthForm from './AuthForm'
import AuthIntro from './AuthIntro'
import FeedbackMessage from '../common/FeedbackMessage'

function AuthPage({ error, message, ...authFormProps }) {
  return (
    <section className="grid gap-10 px-5 py-10 md:grid-cols-[minmax(0,1fr)_minmax(18rem,28rem)] md:px-16 md:py-12">
      <AuthIntro />
      <div className="space-y-4">
        <FeedbackMessage error={error} message={message} />
        <AuthForm {...authFormProps} />
      </div>
    </section>
  )
}

export default AuthPage
