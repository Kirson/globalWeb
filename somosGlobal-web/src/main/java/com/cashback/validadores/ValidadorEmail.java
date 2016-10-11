package com.cashback.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ValidadorEmail implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {

		boolean emailValido = true;
		String email = arg2.toString().trim();

		if (-1 == email.indexOf("@")) {
			emailValido = false;
		}

		if (!emailValido) {
			FacesMessage message = new FacesMessage("Dirección de correo no válida");
			throw new ValidatorException(message);
		}

	}
}
