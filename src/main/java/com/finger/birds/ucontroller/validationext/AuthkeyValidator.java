package com.finger.birds.ucontroller.validationext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.finger.birds.ucontroller.authkey.AuthkeyUtil;

public class AuthkeyValidator implements ConstraintValidator<Authkey, String>{

	@Override
	public void initialize(Authkey constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) {
			return false;
		} else {
			if(AuthkeyUtil.checkKey(value) == null || AuthkeyUtil.checkKey(value).equals(-1l)){
				return false;
			} else {
				return true;
			}
		}
	}

}
