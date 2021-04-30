package br.com.zup.ecommerce.validatores;

import br.com.zup.ecommerce.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class EmailDuplicadoValidator implements ConstraintValidator<EmailDuplicado, String> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> tabela;
    private String campo;

    @Override
    public void initialize(EmailDuplicado constraintAnnotation) {
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
