@Configuration
class AppConfig : WebMvcConfigurer {
    @Autowired
    lateinit var accountValidityInterceptor: AccountValidityInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(accountValidityInterceptor)
        super.addInterceptors(registry)
    }
}