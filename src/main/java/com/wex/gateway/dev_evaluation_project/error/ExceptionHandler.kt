package com.wex.gateway.dev_evaluation_project.error

import graphql.GraphQLError
import graphql.servlet.core.GraphQLErrorHandler
import org.springframework.stereotype.Component


@Component
class ExceptionHandler : GraphQLErrorHandler {

    //To the GraphQL response
    //Processing all GraphQL errors and adapting to be our CustomError
    override fun processErrors(errors: MutableList<GraphQLError>?): MutableList<GraphQLError> {
        return errors?.map{
            if(it is CustomError) it else CustomError(it)
        }?.
                toMutableList()!!
    }
}