package com.example.exprwebdemo.beans

import com.example.exprwebdemo.dao.ExpressionDao
import com.example.exprwebdemo.entities.Expression
import jakarta.ejb.EJB
import jakarta.enterprise.context.SessionScoped
import jakarta.inject.Inject
import jakarta.inject.Named
import java.io.Serializable

@Named
@SessionScoped
open class ExpressionBean : Serializable {
    @EJB
    var expressionDao: ExpressionDao? = null

    @Inject
    private lateinit var calculatorService: CalculatorService

    var current: String? = null
    var editExpression: Expression? = null
        private set
    var message: String? = null
        private set
    var value = 0.0
    var found = emptyList<Expression>()
        private set
    val expressions: List<Expression>
        get() = expressionDao!!.findAll()

    open fun add(): String {
        return try {
            val v = calculatorService!!.calculate(current)
            val expression = Expression()
            expression.expression = current
            expression.result = v
            expressionDao!!.save(expression)
            current = ""
            message = ""
            "index"
        } catch (e: Exception) {
            "error"
        }
    }

    open fun edit(e: Expression?): String {
        editExpression = e
        return "edit"
    }

    open fun finishEdit(): String {
        return try {
            val v = calculatorService!!.calculate(editExpression!!.expression)
            editExpression!!.result = v
            expressionDao!!.update(editExpression!!)
            "index"
        } catch (e: Exception) {
            message = "Please correct errors!"
            "edit"
        }
    }

    open fun findLessThan(): String {
        found = expressionDao!!.findLessThan(value)
        return "searchresult"
    }
}