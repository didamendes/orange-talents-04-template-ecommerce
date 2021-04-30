package br.com.zup.ecommerce.validatores;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidValidator implements ConstraintValidator<UniqueValid, String> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> tabela;
    private String campo;

    @Override
    public void initialize(UniqueValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.campo = constraintAnnotation.campo();
        this.tabela = constraintAnnotation.tabela();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Boolean valido = entityManager.createQuery("SELECT COUNT(1) > 0 FROM " + tabela.getName() + " WHERE lower( " + campo + " ) = :value", Boolean.class)
                .setParameter("value", value)
                .getSingleResult();

        return !valido;
    }
}
