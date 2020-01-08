package com.wex.gateway.dev_evaluation_project.model

import java.util.*
import javax.persistence.Entity

@Entity
public class Contact {
    var id: Int?=null
    var name:String?=null
    var nickname:String?=null
    var email:String?=null
    var address:String?=null
    var insertDate:Date?=null
    var updateDate:Date?=null
    var insertUser:String?=null
    var updateUser:String?=null
}