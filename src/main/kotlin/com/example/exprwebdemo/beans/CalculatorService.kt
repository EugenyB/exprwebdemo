package com.example.exprwebdemo.beans

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Named
import net.objecthunter.exp4j.ExpressionBuilder

@Named
@ApplicationScoped
open class CalculatorService {
    open fun calculate(expression: String?): Double {
        return ExpressionBuilder(expression).build().evaluate()
    }
}