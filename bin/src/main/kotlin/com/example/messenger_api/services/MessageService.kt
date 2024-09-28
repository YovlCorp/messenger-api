package com.example.messenger_api.services

import com.example.messenger_api.models.User
import org.aspectj.bridge.Message

interface MessageService {
    fun sendMessage(sender: User, recipientId: Long, messageText: String): Message
}