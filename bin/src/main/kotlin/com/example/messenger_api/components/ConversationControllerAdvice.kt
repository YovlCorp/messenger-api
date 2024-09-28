package com.example.messenger_api.components

import com.example.messenger_api.constants.ErrorResponse
import com.example.messenger_api.exceptions.ConversationIdInvalidException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ConversationControllerAdvice {
    @ExceptionHandler
    fun conversationIdInvalidException(conversationIdInvalidWxception:
    ConversationIdInvalidException): ResponseEntity<ErrorResponse>{
        val res = ErrorResponse("", conversationIdInvalidException.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }

}