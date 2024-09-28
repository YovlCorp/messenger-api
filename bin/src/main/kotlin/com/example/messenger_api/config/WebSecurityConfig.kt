package com.example.messenger_api.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.WebSecurityConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import kotlin.jvm.Throws

@Configuration
@EnableWebSecurity
class WebSecurityConfig(val userDetailsService: AppUserDetailsService) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.Post, "/users/registrations")
            .permitAll()
            .antMatchers(HttpMethod.Post, "/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(JWTLoginFilter("/Login",
                authenticationManager()),
                UsernamePasswordAuthenticationFilter::class.java
                    .addFilterBefore(JWTAuthenticationFilter(),UsernamePasswordAuthenticationFilter::class.java)
    }
    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.defaultUserDetailsService<UserDetailsService>(userDetailsService)
            .passwordEncoder(BCryptPasswordEncoder())
    }
}