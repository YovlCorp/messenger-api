package com.example.messenger_api.components

@Component
class MessageAssembler {
    fun toMessageVO(message: Message): MessageVO {
        return MessageVO(message.id, message.sender?.id,
            message.recipient?.Id, message.conversation?.id, message.body, message.createdAt.toString())
    }
}