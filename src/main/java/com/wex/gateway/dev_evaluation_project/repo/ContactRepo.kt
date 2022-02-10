package com.wex.gateway.dev_evaluation_project.repo

import com.wex.gateway.dev_evaluation_project.model.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Component

// Foi necessário extender a JpaSpecificationExecutor para funcionar a busca por criteria
@Component
interface ContactRepo : JpaRepository<Contact, Int>, JpaSpecificationExecutor<Contact> {
}
