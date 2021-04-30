package br.com.zup.ecommerce.validatores;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistIdValidator implements ConstraintValidator<ExistId, Object> {

    private String campo;
    private Class<?> tabela;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        tabela = constraintAnnotation.tabela();
        campo = constraintAnnotation.campo();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Boolean valid = true;

        if (value != null) {
            valid = entityManager.createQuery("SELECT COUNT(1) > 0 FROM " + tabela.getName() + " WHERE " + campo + " = :value ", Boolean.class)
                    .setParameter("value", value)
                    .getSingleResult();
        }

        return valid;
    }
}
