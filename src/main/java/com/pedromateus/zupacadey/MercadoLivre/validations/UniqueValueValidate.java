package com.pedromateus.zupacadey.MercadoLivre.validations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidate implements ConstraintValidator<UniqueValueValid,Object> {

    private String field;
    private Class<?> klass;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValueValid constraintAnnotation) {
        this.field=constraintAnnotation.field();
        this.klass=constraintAnnotation.domainValue();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        String jpql= "select 1 from "+klass.getName()+" where "+field+" = :obj";
        Query query=entityManager.createQuery(jpql);
        query.setParameter("obj",obj);
        return query.getResultList().isEmpty();
    }
}
