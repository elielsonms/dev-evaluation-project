package com.wex.gateway.dev_evaluation_project.graphql.scalar

import graphql.language.StringValue
import graphql.schema.Coercing
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

class DateCoercing (pattern:String?): Coercing<XMLGregorianCalendar, String> {
    private val formatter by lazy {
        SimpleDateFormat(pattern)
    }

    override fun parseValue(value: Any?): XMLGregorianCalendar {
        val gregorianCalendar = GregorianCalendar().apply {
            time = formatter.parse(value.toString())
        }
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar)
    }

    override fun parseLiteral(literal: Any?): XMLGregorianCalendar {
        return parseValue( if (literal is StringValue)  literal.value else literal.toString())
    }

    override fun serialize(value: Any?): String {
        val valueXml : XMLGregorianCalendar = value as XMLGregorianCalendar
        return formatter.format(valueXml.toGregorianCalendar().time)
    }

}
