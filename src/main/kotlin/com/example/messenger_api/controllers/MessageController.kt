@RestController
@RequestMapping("/messages")
class MessageController(
    val messageService: MessageServiceImpl,
    val userRepository: UserRepository,
    val messageAssembler: MessageAssembler) {

    @PostMapping
    fun create(@RequestBody messageDetails: MessageRequest,
               request: HttpServletRequest): ResponseEntity<MessageVO> {
        val principal = request.userPrincipal
        val sender = userRepository.findByUsername(principal.name) as User
        val message = messageService.sendMessage(sender, messageDetails.recipientId, messageDetails.message)
        return ResponseEntity.ok(messageAssembler.toMessage(message))
    }
    data class MessageRequest(val recipientId: Long, val message: String)
}