package com.wex.gateway.dev_evaluation_project.error

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component


@Component
class BusinessException : Exception() {

    override var message: String? = null
    var httpStatus: HttpStatus? = null

    fun BusinessException(value: String, httpStatus: HttpStatus) {
        message = value
        this.httpStatus = httpStatus
    }
}