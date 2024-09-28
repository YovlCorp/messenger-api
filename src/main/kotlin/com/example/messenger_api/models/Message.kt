package com.example.messenger_api.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.aspectj.runtime.internal.Conversions
import org.hibernate.query.sqm.tree.expression.Conversion
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*

@Entity
class Message (
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var sender: User? = null,
    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    var recipient: User? = null,
    var body: String? = "",
    @ManyToOne(optional = false)
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    var conversation: Conversion? = null,
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
    @DateTimeFormat
    var createdAt: Date = Date.from(Instant.now())
)