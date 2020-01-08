package com.wex.gateway.dev_evaluation_project.rest

import com.google.gson.Gson
import com.wex.gateway.dev_evaluation_project.model.Contact
import com.wex.gateway.dev_evaluation_project.model.ContactFilterRequst
import com.wex.gateway.dev_evaluation_project.repo.ContactRepo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/contacts")
class ContactController(val contactRepo: ContactRepo) : AbstractController() {
    @GetMapping("/{name}/{nickname}/{email}")
    fun findContactsByFilter(@PathVariable name: String, @PathVariable nickname: String, @PathVariable email: String): ResponseEntity<String> {
        return try {
            if ((name != null && !name.isEmpty())
                    || (nickname != null && !nickname.isEmpty())
                    || (email != null && !email.isEmpty())
            ) {
                ResponseEntity.status(HttpStatus.OK).body(Gson().toJson(contactRepo.findByFilter(name, nickname, email)))
            } else {
                ResponseEntity.status(HttpStatus.OK).body(Gson().toJson(contactRepo.findAll()))
            }
        } catch (e: Exception) {
            threatError(e)
        }
    }

    @PostMapping("/")
    fun insert(@RequestBody contact: Contact): ResponseEntity<String> {
        return try {
            contact.insertDate = Date()
            contact.updateUser = SecurityContextHolder.getContext().authentication.name
            ResponseEntity.status(HttpStatus.OK).body(Gson().toJson(contactRepo.save(contact)));
        } catch (e: Exception) {
            threatError(e)
        }
    }

    @PutMapping("/{contactId}")
    fun update(@PathVariable contactId: Int, @RequestBody contact: Contact): ResponseEntity<String> {
        return try {
            contact.id = contactId
            contact.insertDate = Date()
            contact.updateUser = SecurityContextHolder.getContext().authentication.name
            ResponseEntity.status(HttpStatus.OK).body(Gson().toJson(contactRepo.findById(contactId).let { contactRepo.save(contact) }));
        } catch (e: Exception) {
            threatError(e)
        }
    }

    @DeleteMapping("/{contactId}")
    fun delete(@PathVariable contactId: Int): ResponseEntity<String> {
        return try {
            ResponseEntity.status(HttpStatus.OK).body(Gson().toJson(contactRepo.deleteById(contactId).let { true }))
        } catch (e: Exception) {
            threatError(e)
        }

    }
}

