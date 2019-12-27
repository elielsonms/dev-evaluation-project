package com.wex.gateway.dev_evaluation_project.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.wex.gateway.dev_evaluation_project.model.Contact
import com.wex.gateway.dev_evaluation_project.repo.ContactRepo
import org.springframework.stereotype.Component

@Component
class ContactMutationResolver (private val contactRepo: ContactRepo): GraphQLMutationResolver{

    fun insert(contact:Contact): Contact{
        return contactRepo.save(contact)
    }

    fun update(contact:Contact): Contact{
        return contactRepo.save(contact)
    }

    fun delete(contactId: Int):Boolean{
        try {
            contactRepo.deleteById(contactId)
            return true
        }catch (e:Exception){
            return false
        }
    }
}