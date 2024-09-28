@Component
class AppUserDetailsService(val userRepository: UserRepository) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)?:
        throw UsernameNotFoundException("User with name $username not found. Please check your username and try again. 404")
        return User(user.username, user.password,ArrayList<GrantedAuthority>())
    }
}