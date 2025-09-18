package ufpb.project.acervodigital.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValueValidator implements ConstraintValidator<EnumValue, CharSequence> {

    private Set<String> valoresAceitos;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        valoresAceitos = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if(charSequence == null) {
            return true;
        }

        return valoresAceitos.contains(charSequence.toString());
    }
}
