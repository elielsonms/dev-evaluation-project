package com.wex.gateway.dev_evaluation_project.directive.constraint

import java.math.BigInteger

/**
 * This class is the heart of the validation.  Any new validator functionality needs to be added to this following
 * a similar pattern.  Two primary types that we're validating is String and Int/BigInteger.
 */
class ConstraintValidator(val config: Map<String, Any>) {
    private val max: BigInteger?
        get() = if(config.containsKey("max")) config.get("max").toString().toBigInteger() else null
    private val maxLength: Int?
        get() = if(config.containsKey("maxLength")) config.get("maxLength").toString().toInt() else null
    private val match: Regex?
        get() = if(config.containsKey("match")) Regex(config.get("match").toString()) else null

    fun validate(input: String) {
        maxLength?.let {
            require(input.length <= it) {
                "String length of $input.length exceeded maximum allowed of $it"
            }
        }
        match?.let {
            require(it.matches(input)) {
                "Input does match required regex format of $it"
            }
        }
    }

    fun validate(input: BigInteger) {
        max?.let {
            require(input <= it) {
                "Integer value of $input is too large for this input, max allowed is $it"
            }
        }
    }

    fun validate(input: Int) = validate(input.toBigInteger())
}