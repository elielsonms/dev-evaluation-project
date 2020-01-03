package com.wex.gateway.dev_evaluation_project.rest

import com.wex.gateway.dev_evaluation_project.model.Contact
import com.wex.gateway.dev_evaluation_project.model.ContactFilterRequst
import com.wex.gateway.dev_evaluation_project.repo.ContactRepo
import org.bouncycastle.asn1.x500.style.RFC4519Style.o
import org.springframework.security.core.context.SecurityContext
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

    // Foi necessário criar o método para buscar um contato específico
    @GetMapping("/{contactId}")
    fun getContact(@PathVariable contactId:Int):Contact {
        return contactRepo.findById(contactId).get()
    }

    @PostMapping("/")
    fun insert(@RequestBody contact: Contact):Contact{
        contact.insertDate = Date()
//        Preenchendo o atributo incorreto, deveria ser contact.insertUser
//        contact.updateUser= SecurityContextHolder.getContext().authentication.name
        contact.insertUser = SecurityContextHolder.getContext().authentication.name
        return contactRepo.save(contact)
    }

    @PostMapping("/{contactId}")
    fun update(@PathVariable contactId:Int,@RequestBody contact: Contact):Contact{
//        Não deveria substituir o id do contact
//        contact.id = contactId
//        Preenchendo o atributo incorreto, deveria ser contact.updateDate
//        contact.insertDate = Date()
//        Deveria modificar o contact que retorna do repositório
//        contact.updateDate = Date()
//        contact.updateUser = SecurityContextHolder.getContext().authentication.name

        val contactFromRepo: Contact = contactRepo.findById(contactId).get()
        contactFromRepo.updateDate = Date()
        contactFromRepo.updateUser = SecurityContextHolder.getContext().authentication.name
        contactFromRepo.address = if (contact.address != null) contact.address else contactFromRepo.address
        contactFromRepo.email = if (contact.email != null) contact.email else contactFromRepo.email
        contactFromRepo.name = if (contact.name != null) contact.name else contactFromRepo.name
        contactFromRepo.nickname = if (contact.nickname != null) contact.nickname else contactFromRepo.nickname
        return contactRepo.save(contactFromRepo)
    }

    @DeleteMapping("/{contactId}")
    fun delete(@PathVariable contactId:Int):Boolean{
        return contactRepo.deleteById(contactId).let { true }
    }
}

