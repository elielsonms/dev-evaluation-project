package com.wex.gateway.dev_evaluation_project.graphql.resolver.field

import com.coxautodev.graphql.tools.GraphQLResolver
import com.wex.gateway.dev_evaluation_project.model.Contact
import com.wex.gateway.dev_evaluation_project.model.User
import com.wex.gateway.dev_evaluation_project.repo.ContactRepo
import org.springframework.stereotype.Component

@Component
class ContactResolver (private val contactRepo: ContactRepo): GraphQLResolver<Contact> {

    fun getInsertUser(contact:Contact):User{
        return User().apply{
            username = contact.updateUser
        }
    }

    fun getUpdateUser(contact:Contact):User{
        return User().apply{
            username = contact.updateUser
        }
    }

}