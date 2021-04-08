package com.pedromateus.zupacadey.MercadoLivre.validations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistIdValidator implements ConstraintValidator<ExistIdValid,Long> {

    private Class<?> clazz;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistIdValid constraintAnnotation) {
        clazz=constraintAnnotation.clazz();
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
       String jpql="select 1 from "+clazz.getName()+" where id=:aLong";
       Query query=entityManager.createQuery(jpql).setParameter("aLong", aLong);
       return query.getResultList().size()==1;
    }
}
