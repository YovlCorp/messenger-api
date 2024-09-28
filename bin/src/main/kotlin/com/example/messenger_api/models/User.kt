package com.example.messenger_api.models

import com.example.messenger_api.listeners.UserListener
import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.intellij.lang.annotations.Pattern
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import jakarta.persistence.*
import java.util.Date




@Entity
@Table(name = "user")
@EntityListeners(UserListener::class)
class User (
    @Column(unique = true)
    @Size(min = 2)
    var username: String = "",
    @Size(min = 8, max = 15)
    @Column(unique = true)
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[-]?(\\d{3})[-]?(\\d{4})$")
    var phoneNumber: String = "",
    @Size(min = 60, max = 60)
    var password: String = "",
    var status: String = "available",
    @Pattern(regexp = "\\A(activated|deactivated)\\z")
    var accountStatus: String = "activated",
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var createdAt: Date = Date.from(Instant.now())
){
    //collection message
    @OneToMany(mappedBy = "sender", targetEntity = Message::class)
    private var sentMessages: Collection<Message>? = null

    @OneToMany(mappedBy = "recipient", targetEntity = Message::class)
    private var receivedMessages: Collection<Message>? = null
}