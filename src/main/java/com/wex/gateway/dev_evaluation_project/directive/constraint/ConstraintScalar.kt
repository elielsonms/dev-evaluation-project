package com.wex.gateway.dev_evaluation_project.directive.constraint

import graphql.schema.GraphQLScalarType
import graphql.schema.GraphQLType
import graphql.schema.GraphQLTypeVisitor
import graphql.util.TraversalControl
import graphql.util.TraverserContext


/**
 * This class defines the type is assigned to any input field definition that has the
 * @constraint directive applied to it.  This allows us runtime validation for the field.
 * Responsible for creating the Coercing and Validator classes.
 */
class ConstraintScalar(config: Map<String, Any>): GraphQLScalarType(
        "ConstraintScalar",
        "Scalar type that has validation applied to it",
        ConstraintCoercing(ConstraintValidator(config))) {
    companion object {
       var count = 0
    }
    /**
     * This is kind of black magic to override this function as it has been.  There is a mechanism in the super class
     * to ensure uniqueness amongst all types, including scalar, such that a string is a string everywhere.
     * However given the dynamic nature of the ConstraintScalar being augmented with arguments one cannot rule
     * them all but must be its own instantiation on each field, therefore cannot be limited to one instance
     * per the entire schema registry.
     */
    override fun accept(context: TraverserContext<GraphQLType>?, visitor: GraphQLTypeVisitor): TraversalControl {
        return if(count++ == 0)
             visitor.visitGraphQLScalarType(this, context)
        else
            TraversalControl.CONTINUE
    }

}