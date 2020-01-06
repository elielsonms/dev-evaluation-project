package com.wex.gateway.dev_evaluation_project.rest

import com.wex.gateway.dev_evaluation_project.model.Contact
import com.wex.gateway.dev_evaluation_project.model.ContactFilterRequst
import com.wex.gateway.dev_evaluation_project.model.ContactSpecification
import com.wex.gateway.dev_evaluation_project.repo.ContactRepo
import org.bouncycastle.asn1.x500.style.RFC4519Style.o
import org.springframework.data.domain.Example
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/contact")
class ContactController(val contactRepo: ContactRepo){
    @PostMapping("/filter")
    // Faltando a tag RequestBody no parâmetro
    // Foi necessário criar um Specification para tratar a buscar por criteria
    // Implementação do PageRequest para a paginação
    // Implementação do Sort para a ordenação
    fun findContacts(@RequestBody contactFilterRequst: ContactFilterRequst):List<Contact>{
        val spec: Specification<Contact> = ContactSpecification(contactFilterRequst.filter)
        return contactRepo.findAll(
                spec,
                PageRequest.of(
                        contactFilterRequst.page!!.page,
                        contactFilterRequst.page!!.size,
                        Sort.by(if (contactFilterRequst.order!!.orderDescending) Sort.Direction.DESC else Sort.Direction.ASC, contactFilterRequst.order!!.orderBy))
        ).content
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
        return contactRepo.saveAndFlush(contact)
    }

//    @PostMapping("/update/{contactId}")
//    fun update(@PathVariable contactId:Int,@RequestBody contact: Contact):Contact{
//        Não deveria substituir o id do contact
//        Deveria modificar o contact que retorna do repositório
//        contact.id = contactId
//        Preenchendo o atributo incorreto, deveria ser contact.updateDate
//        contact.insertDate = Date()
//        contact.updateUser = SecurityContextHolder.getContext().authentication.name
//        return contactRepo.findById(contactId).let{ contactRepo.save(contact) }
//    }

    @PostMapping("/{contactId}")
    fun update(@PathVariable contactId:Int,@RequestBody contact: Contact):Contact{
        return contactRepo.findById(contactId).get().let {
            it.updateDate = Date()
            it.updateUser = SecurityContextHolder.getContext().authentication.name
            it.updateContact(contact)
            contactRepo.save(it)
        }
    }

    @DeleteMapping("/{contactId}")
    fun delete(@PathVariable contactId:Int):Boolean{
        return contactRepo.deleteById(contactId).let { true }
    }
}

