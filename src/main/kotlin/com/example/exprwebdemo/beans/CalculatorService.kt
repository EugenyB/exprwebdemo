package com.example.exprwebdemo.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import net.objecthunter.exp4j.ExpressionBuilder;

@Named
@ApplicationScoped
public class CalculatorService {
    public double calculate(String expression) {
        return new ExpressionBuilder(expression).build().evaluate();
    }
}
