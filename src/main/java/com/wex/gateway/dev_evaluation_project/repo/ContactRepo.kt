package com.wex.gateway.dev_evaluation_project.repo

import com.wex.gateway.dev_evaluation_project.model.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Component

@Component
interface ContactRepo : JpaRepository<Contact, Int> {
    @Query("select c from Contact c where c.name like :name and c.nickname like :nickname and c.email like :email ")
    fun findByFilter(@Param("name") name: String,@Param("nickname") nickname: String,@Param("email") email: String): List<Contact>

}