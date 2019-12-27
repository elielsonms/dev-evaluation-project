package com.wex.gateway.dev_evaluation_project.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.wex.gateway.dev_evaluation_project.model.Contact
import com.wex.gateway.dev_evaluation_project.model.OrderInput
import com.wex.gateway.dev_evaluation_project.model.PageInput
import com.wex.gateway.dev_evaluation_project.repo.ContactRepo
import org.springframework.stereotype.Component


@Component
class ContactQueryResolver(private val contactRepo: ContactRepo): GraphQLQueryResolver {

    fun findContacts(contactFilter:Contact, orderInput: OrderInput, pageInput: PageInput):List<Contact>{
        return contactRepo.findAll()
    }

    fun getContact(contactId:Int):Contact{
        return contactRepo.findById(contactId).get()
    }
}