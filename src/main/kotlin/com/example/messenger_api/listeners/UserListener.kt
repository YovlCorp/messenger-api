package com.example.messenger_api.listeners

import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import org.springframework.boot.autoconfigure.security.SecurityProperties.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserListener {
    @PrePersist
    @PreUpdate
    fun hashPassword(user: User){
        user.password = BCryptPasswordEncoder().encode(user.password)
    }
}