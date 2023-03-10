package com.example.exprwebdemo.beans;

import com.example.exprwebdemo.dao.ExpressionDao;
import com.example.exprwebdemo.entities.Expression;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class ExpressionBean implements Serializable {
    @EJB
    ExpressionDao expressionDao;

    @Inject
    CalculatorService calculatorService;

    private String current;

    private Expression editExpression;

    private String message;

    private double value;

    private List<Expression> found = Collections.emptyList();

    public List<Expression> getFound() {
        return found;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public Expression getEditExpression() {
        return editExpression;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public List<Expression> getExpressions() {
        return expressionDao.findAll();
    }

    public String add() {
        try {
            double v = calculatorService.calculate(current);
            Expression expression = new Expression();
            expression.setExpression(current);
            expression.setResult(v);
            expressionDao.save(expression);
            current = "";
            message = "";
            return "index";
        } catch (Exception e) {
            return "error";
        }
    }

    public String edit(Expression e) {
        editExpression = e;
        return "edit";
    }

    public String finishEdit() {
        try {
            double v = calculatorService.calculate(editExpression.getExpression());
            editExpression.setResult(v);
            expressionDao.update(editExpression);
            return "index";
        } catch (Exception e) {
            message = "Please correct errors!";
            return "edit";
        }
    }

    public String findLessThan() {
        found = expressionDao.findLessThan(value);
        return "searchresult";
    }
}
