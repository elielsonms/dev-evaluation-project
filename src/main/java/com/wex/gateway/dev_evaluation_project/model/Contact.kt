package com.wex.gateway.dev_evaluation_project.model

import java.util.*
import javax.persistence.metamodel.SingularAttribute
import javax.persistence.metamodel.StaticMetamodel


class Contact {
    var id:Int?=null
    var name:String?=null
    var nickname:String?=null
    var email:String?=null
    var address:String?=null
    var insertDate:Date?=null
    var updateDate:Date?=null
    var insertUser:String?=null
    var updateUser:String?=null

    fun updateContact(contact: Contact) {
        this.address = if (contact.address != null) contact.address else this.address
        this.email = if (contact.email != null) contact.email else this.email
        this.name = if (contact.name != null) contact.name else this.name
        this.nickname = if (contact.nickname != null) contact.nickname else this.nickname
    }
}
