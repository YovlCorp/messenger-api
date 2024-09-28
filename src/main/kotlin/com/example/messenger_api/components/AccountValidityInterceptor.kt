package com.example.messenger_api.components



@Component
class AccountValidityInterceptor(val userRepository: UserRepository) : HandlerInterceptorAdapter() {
    @Throws(UserDeactivatedException::class)
    override fun preHandle(request: HttpServletRequest,
                             response: HttpServletResponse, handler: Any?): Boolean {
        val principal = Principal? = request.userPrincipal
        if(principal != null) {
            val user = userRepository.findByUsername(principal.name) as User
            if (user.accountStatus == "deactivated") {
                throw UserDeactivatedException("Your account has been deactivated.")
            }
        }
        RETURN SUPER.PREhANDLER(request, response, handler)
    }
}