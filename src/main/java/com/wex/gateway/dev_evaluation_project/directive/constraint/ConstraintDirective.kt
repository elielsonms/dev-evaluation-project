package com.wex.gateway.dev_evaluation_project.directive.constraint

import com.wex.gateway.dev_evaluation_project.util.LoggerDelegate
import graphql.language.FloatValue
import graphql.language.IntValue
import graphql.language.StringValue
import graphql.schema.GraphQLInputObjectField
import graphql.schema.GraphQLNonNull
import graphql.schema.GraphQLScalarType
import graphql.schema.idl.SchemaDirectiveWiring
import graphql.schema.idl.SchemaDirectiveWiringEnvironment

/**
 * Handles the schema building wiring required to set up our validation.  This only runs during build time and converts
 * fields with the constraint directive to a ConstraintScalar.  This is how the bridge from schema build to runtime validation
 * is built.
 */
class ConstraintDirective: SchemaDirectiveWiring {
    private val logger by LoggerDelegate()

    override fun onInputObjectField(environment: SchemaDirectiveWiringEnvironment<GraphQLInputObjectField>?): GraphQLInputObjectField {
        val ele = environment!!.element //Not happy about the possible NPE but given the argument is nullable and the return is not
        //If the field definition doesn't have a constraint directive, then do not modify this field.
        val args = ele.definition.getDirective("constraint")?.arguments?.map {
            it.name to when(val type = it.value) {
                is IntValue -> type.value
                is StringValue -> type.value
                is FloatValue -> type.value
                else -> type
            }
        }?.toMap() ?: return ele

        return ele.transform { builder ->
            when(ele.type) {
                is GraphQLNonNull -> builder.type(GraphQLNonNull(ConstraintScalar(args)))
                is GraphQLScalarType -> builder.type(ConstraintScalar(args))
                else -> {
                    logger.warn("Unable to properly transform type with constraint directive $ele.type")
                }
            }
        }
    }
}