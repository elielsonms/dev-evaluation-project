package com.wex.gateway.dev_evaluation_project.directive.constraint

import graphql.language.IntValue
import graphql.language.StringValue
import graphql.schema.Coercing
import graphql.schema.CoercingParseLiteralException
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException
import java.lang.IllegalArgumentException
import java.math.BigInteger

/**
 * Coercing class manages the conversion from one type to another, however in our case it's the same
 * output as input.  This is where the validation is executed during runtime and will throw a "validation"
 * error to the log and properly in the response to the client.  Some exception management was required
 * due to the GraphQL service expecting very specific exception classes to handle it properly.
 */
class ConstraintCoercing(private val validator: ConstraintValidator): Coercing<String, String> {

    override fun parseValue(input: Any?): String =
        try {
            parse(input)
        } catch(e: IllegalArgumentException) {
            throw CoercingParseValueException(e.message)
        }


    override fun parseLiteral(input: Any?): String =
        try {
            parse(input)
        } catch(e: IllegalArgumentException) {
            throw CoercingParseLiteralException(e.message)
        }


    override fun serialize(input: Any?): String =
        try {
            parse(input)
        } catch(e: IllegalArgumentException) {
            throw CoercingSerializeException(e.message)
        }


    private fun parse(input: Any?): String =
        when(input) {
            is StringValue -> {
                validator.validate(input.value)
                input.value.toString()
            }
            is IntValue -> {
                validator.validate(input.value as BigInteger)
                input.value.toString()
            }
            else -> throw IllegalArgumentException("Sent unknown type to parse")
        }
}