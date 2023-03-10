package com.example.exprwebdemo.dao;

import com.example.exprwebdemo.entities.Expression;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ExpressionDao {
    @PersistenceContext
    private EntityManager em;

    public List<Expression> findAll() {
        return em.createNamedQuery("Expression.findAll", Expression.class).getResultList();
    }

    public Expression save(Expression expression) {
        em.persist(expression);
        return expression;
    }

    public void update(Expression expression) {
        em.merge(expression);
    }

    public List<Expression> findLessThan(double value) {
        return em.createNamedQuery("Expression.findByResultLessThan", Expression.class).setParameter("result", value).getResultList();
    }
}
