package com.example.messenger_api.services

import com.example.messenger_api.models.Conversation
import com.example.messenger_api.models.Message
import com.example.messenger_api.models.User
import com.example.messenger_api.repositaries.ConversationRepository
import com.example.messenger_api.repositaries.MessageRepository
import com.example.messenger_api.repositaries.UserRepository
import org.springframework.core.convert.ConversionService
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(
    val repository: MessageRepository,
    val conversationRepository: ConversationRepository,
    val conversionService: ConversionService,
    val serRepository: UserRepository) : MessageService {

        @Throws(MessageEmptyException::class,
            MessageRecipientInvalidException::class)
        override fun sendMessage(sender: User, recipientId: Long,
                                 messageText: String) : Message {
            val optional = userRepository.findById(recipientId)

            if (optional.isPresent) {
                val recipient = optional.get()

                if (!messageText.isEmpty()) {
                    val conversation: Conversation = if (conversationService.conversationExists(sender, recipient)) {
                        conversionService.getConversation(sender, recipient) as Conversation
                    }else {
                        conversationService.createConversation(sender, recipient)
                    }
                    conversationRepository.save(conversation)

                    val message = Message(sender, recipient, messageText, conversation)
                    repository.save(message)
                    return message
                }
            } else{
                throw MessageRecipientInvalidException("tHE RECIPIENT ID'$recipientId' is invalid")
            }
            throw MessageEmptyException()
        }
    }
