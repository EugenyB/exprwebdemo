package com.example.exprwebdemo.dao

import com.example.exprwebdemo.entities.Expression
import jakarta.ejb.Stateless
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

@Stateless
open class ExpressionDao {
    @PersistenceContext
    val em: EntityManager? = null
    open fun findAll(): List<Expression> {
        return em!!.createNamedQuery("Expression.findAll", Expression::class.java).resultList
    }

    open fun save(expression: Expression): Expression {
        em!!.persist(expression)
        return expression
    }

    open fun update(expression: Expression) {
        em!!.merge(expression)
    }

    open fun findLessThan(value: Double): List<Expression> {
        return em!!.createNamedQuery("Expression.findByResultLessThan", Expression::class.java)
            .setParameter("result", value).resultList
    }
}