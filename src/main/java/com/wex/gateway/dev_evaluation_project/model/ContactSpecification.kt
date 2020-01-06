package com.wex.gateway.dev_evaluation_project.model

import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

// Necessário a implementação de uma classe Specification para tratar a busca por criteria
class ContactSpecification(var criteria: Contact?) : Specification<Contact> {

    override fun toPredicate(root: Root<Contact>, query: CriteriaQuery<*>, builder: CriteriaBuilder): Predicate? {
        return builder.or(
                builder.like(root.get<String>("name"), "%" + this.criteria?.name + "%"),
                builder.like(root.get<String>("nickname"), "%" + this.criteria?.nickname + "%"),
                builder.like(root.get<String>("email"), "%" + this.criteria?.email + "%"),
                builder.like(root.get<String>("address"), "%" + this.criteria?.address + "%"),
                builder.equal(root.get<Contact>("id"), this.criteria?.id)
        )
    }

}
