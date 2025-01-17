package com.example.messenger_api.components

import com.example.messenger_api.constants.ErrorResponse
import com.example.messenger_api.constants.ResponseConstants
import com.example.messenger_api.exceptions.MessageEmptyException
import com.example.messenger_api.exceptions.MessageRecipientInvalidException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class MessageControllerAdvice {
    @ExceptionHandler(MessageEmptyException::class)
    fun messageEmpty(messageEmptyException: MessageEmptyException):
            ResponseEntity<ErrorResponse>{
        //erroe object
        val res = ErrorResponse(ResponseConstants.MESSAGE_EMPTY.value,messageEmptyException.message)

        //возврат error response
        return ResponseEntity.unprocessableEntity().body(res)
    }

    @ExceptionHandler(MessageRecipientInvalidException::class)
        fun messageRecipientInvalid(messageRecipientInvalidException: MessageRecipientInvalidException):
            ResponseEntity<ErrorResponse> {
            val res = ErrorResponse(ResponseConstants.MESSAGE_RECIPIENT_INVALID.value,messageRecipientInvalidException.message)
        return ResponseEntity.unprocessableEntity().body(res)
        }
}