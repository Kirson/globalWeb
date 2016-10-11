package com.cashback.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.cashback.utilidades.Util;

public class ValidadorCedula implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		boolean cedulaValida = false;
		try {
			String cadenaOriginal = arg2.toString().trim();
			String valorIngresado = arg2.toString().trim().substring(0, 10);
			Integer intPos3 = Integer.parseInt(valorIngresado.substring(2, 3));
			if (intPos3 < 6) {
				cedulaValida = Util.esCedulaValida(valorIngresado);
			}

			if (intPos3 >= 6) {
				cedulaValida = Util.esRucValidoPublico(cadenaOriginal);
			}

			if (!cedulaValida) {
				FacesMessage message = new FacesMessage("Cédula/RUC incorrecto");
				throw new ValidatorException(message);
			}
			
		} 
		catch (StringIndexOutOfBoundsException e) {
			FacesMessage message = new FacesMessage("Cédula/RUC incorrecto");
			throw new ValidatorException(message);
		} catch (NumberFormatException e) {
			FacesMessage message = new FacesMessage("Cédula/RUC incorrecto");
			throw new ValidatorException(message);
		}
	}
}
