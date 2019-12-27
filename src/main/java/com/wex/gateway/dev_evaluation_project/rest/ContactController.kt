package com.wex.gateway.dev_evaluation_project.rest

import com.wex.gateway.dev_evaluation_project.model.Contact
import com.wex.gateway.dev_evaluation_project.model.ContactFilterRequst
import com.wex.gateway.dev_evaluation_project.repo.ContactRepo
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/contact")
class ContactController(val contactRepo: ContactRepo){
    @PostMapping("/filter")
    fun findContacts(contactFilterRequst: ContactFilterRequst):List<Contact>{
        return contactRepo.findAll()
    }

    @PostMapping("/")
    fun insert(@RequestBody contact: Contact):Contact{
        contact.insertDate = Date()
        contact.updateUser= SecurityContextHolder.getContext().authentication.name
        return contactRepo.save(contact)
    }

    @PostMapping("/{contactId}")
    fun update(@PathVariable contactId:Int,@RequestBody contact: Contact):Contact{
        contact.id = contactId
        contact.insertDate = Date()
        contact.updateUser = SecurityContextHolder.getContext().authentication.name
        return contactRepo.findById(contactId).let{ contactRepo.save(contact) }
    }

    @DeleteMapping("/{contactId}")
    fun delete(@PathVariable contactId:Int):Boolean{
        return contactRepo.deleteById(contactId).let { true }
    }
}

