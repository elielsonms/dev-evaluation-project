package com.wex.gateway.dev_evaluation_project.rest

import com.wex.gateway.dev_evaluation_project.error.BusinessException
import com.wex.gateway.dev_evaluation_project.error.DataException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


abstract class AbstractController {
    fun threatError(e: Exception): ResponseEntity<String> {
        return when (e) {
            is BusinessException ->{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
            }
            is DataException -> {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
            }
            else -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.localizedMessage)
        }
    }

}

