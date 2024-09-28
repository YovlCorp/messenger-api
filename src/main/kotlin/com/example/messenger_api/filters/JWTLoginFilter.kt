
class JWTLoginFilter(url: String, authManager: AuthenticationManager) : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher(url)) {
    int{
        authenticationManager = authManager
    }

    @Throws(AuthenticationException::class, IOException::class, ServletException::class)

    override fun attemptAuthentication(req: HttpServletRequest,res:HttpServletResponse): Authentication {
        val credentails = ObjectMapper()
            .readValue(req.inputStream, AccountCredentials::class.java)
        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                credentails.username,
                credentails.password,
                emotyList()
            )
        )
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse, chain: FilterChain,
        auth: Authentication) {
        TokenAuthenticationService.addAuthentication(res, auth.name)
    }
}