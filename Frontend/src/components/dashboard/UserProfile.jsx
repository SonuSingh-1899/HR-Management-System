import InfoField from '../common/InfoField'

function UserProfile({ user }) {
  return (
    <section>
      <h2 className="mb-5 text-2xl font-semibold text-blue-700">User profile</h2>
      <div className="grid gap-6 md:grid-cols-2">
        <InfoField label="Name" value={user.name} />
        <InfoField label="Role" value={user.role} />
        <InfoField label="Email" value={user.email} />
        <InfoField label="Status" value={user.active ? 'Active' : 'Inactive'} />
      </div>
    </section>
  )
}

export default UserProfile
