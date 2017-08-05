package com.finger.birds.ucontroller.validationext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.finger.birds.ucontroller.authkey.AuthkeyUtil;

public class AuthkeyCanNullValidator implements ConstraintValidator<AuthkeyCanNull, String>{

	@Override
	public void initialize(AuthkeyCanNull constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) {
			return true;
		} else {
			if(AuthkeyUtil.checkKey(value).equals(-1l)){
				return false;
			} else {
				return true;
			}
		}
	}

}
