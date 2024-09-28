package com.example.messenger_api.components

@Component
class ConversationAssembler (
    val conversationService:
    ConversationServiceImpl,
    val messageAssembler: MessageAssembler) {

    fun toConversationVO(conversation: Conversation, userId: Long):
    ConversationVO {

        val conversationMessage: ArrayList<MessageVO> = ArrayList()
        conversation.message.mapTo(conversationMessage) {
            messageAssembler.toMessageVO(it)
        }
        return ConversationVO(conversations.id, conversationService.nameSecondParty(conversation,userId),
            conversationMessage)

    }
    fun toConversationListVO(conversations: ArrayList<Conversation>,
                             userId: Long): ConversationListVO {
        val conversationVOList = conversation.map {
            toConversationVO(it, userId) }
        return ConversationListVO(conversationVOList)
    }
}