@RestController
@RequestMapping("/conversations")
class ConversationController (
    val conversationService: ConversationServiceImpl,
    val conversationAssembler: ConversationAssembler,
    val userRepository: userRepository
){
    @GetMapping
    fun list(reqouest: HttpServletRequest):
            responseEntity<ConversationListVO>
    {
        val user = userRepository.findByUsername(request.userPrincipal.name) as User
        val conversations = conversationService.listConversations(user.id)
        return ResponseEntity.ok(conversationAssembler.toConversationListVO(conversations, user.id))
    }
    @GetMapping("/{conversation_Id}")
    fun show(
        @PathVariable(name = "conversation_id") conversationId: Long,
        request: HttpServletRequest): ResponseEntity<ConversationVO> {
        val user = userRepository.findByUsername(request.userPrincipal.name) as User
        val conversationThread = conversationService.retrieveThread(conversationId)
        return ResponseEntity.ok(conversationAssembler.toConversationVO(conversationThread, user.id))
    }
}