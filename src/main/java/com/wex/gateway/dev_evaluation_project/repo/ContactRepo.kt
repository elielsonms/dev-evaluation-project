package com.wex.gateway.dev_evaluation_project.repo

import com.wex.gateway.dev_evaluation_project.model.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Component
interface ContactRepo : JpaRepository<Contact, Int> {
}