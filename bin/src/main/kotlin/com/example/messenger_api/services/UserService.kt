package com.example.messenger_api.services

import com.example.messenger_api.models.User

interface UserService {
    fun attemptRegistration(userDetails: User): User
    fun listUsers(currentUser): List<User>
    fun retrieveUserData(username: String): User?
    fun retrieveUserData(id: Long): User?
    fun usernameExists(username: String): Boolean
}