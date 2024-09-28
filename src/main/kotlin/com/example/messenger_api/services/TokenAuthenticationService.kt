internal object TokenAuthenticationService {
    private val TOKEN_EXPIRY: Long = 864000000
    private val SECRET = "$78GR43G7G8FEB8E"
    private val TOKEN_PREFIX = "Bearer "
    private val AUTHORIZATION_HEADER_KEY = "Authorization"

    fun addAuthentication(res: HttpServletResponse, username: String) {
        val JWT = Jwts.builder()
            .setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + TOKEN_EXPIRY))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact()
        res.addHeader(AUTHORIZATION_HEADER_KEY, TOKEN_PREFIX  $JWT)
    }
    fun getAuthentication(request: HttpServletRequest): Authentication? {
        val token = request.getHeader(AUTHORIZATION_HEADER_KEY)
        if(token != null) {
            val user = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .body.subject
            if (user!= null)
                return UsernamePasswordAuthenticationToken(user, null,
                    emptyList<GrantedAuthority>())
        }
        return null
    }
}