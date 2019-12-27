package com.wex.gateway.dev_evaluation_project.graphql.scalar

import graphql.schema.GraphQLScalarType
import org.springframework.stereotype.Component


@Component
class DateScalar (): GraphQLScalarType("Date", "The Date to be sent to the DeuceService", DateCoercing("yyyy-MM-dd HH:mm:ssZ"))
