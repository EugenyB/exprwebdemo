package com.example.exprwebdemo.entities

import jakarta.persistence.*

@Entity
@Table(name = "expression")
@NamedQueries(
    value = [
        NamedQuery(name = "Expression.findAll", query = "select e from Expression e"),
        NamedQuery(
            name = "Expression.findByResultLessThan",
            query = "select e from Expression e where e.result < :result"
        )
    ]
)
open class Expression {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "expression", nullable = false)
    open var expression: String? = null

    @Column(name = "result")
    open var result: Double? = null
}