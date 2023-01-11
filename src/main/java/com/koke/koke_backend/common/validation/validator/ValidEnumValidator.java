package com.koke.koke_backend.common.validation.validator;


import com.koke.koke_backend.common.validation.annotation.ValidEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ValidEnumValidator implements ConstraintValidator<ValidEnum, Enum<?>> {
	private ValidEnum annotation;

	@Override
	public void initialize(ValidEnum constraintAnnotation) {
		this.annotation = constraintAnnotation;
	}

	@Override
	public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
		boolean result = false;

		if (value == null) return true;

		Object[] enumValues = this.annotation.enumClass().getEnumConstants();
		if (enumValues != null) {
			for (Object enumValue : enumValues) {
				if (value == enumValue) {
					result = true;
					break;
				}
			}
		}
		return result;
	}
}
