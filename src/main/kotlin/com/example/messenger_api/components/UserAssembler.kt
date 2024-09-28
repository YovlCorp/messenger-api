package com.example.messenger_api.components

@Component
class UserAssembler {
    fun toUserVO(user: User): UserVO {
        return UserVO(user.id, user.username, user.phoneNumber, user.status, user.createdAt.toString())
    }
    fun toUserListVO(users: List<User>): UserListVO {
        val userVOList = users.map {toUserVO(it) }
        return UserListVO(userVOList)
    }
}