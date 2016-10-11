package com.cashback.interfaces;

import java.util.List;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.TextoClave;

public interface ITextoClave {
	List<TextoClave> recuperarTextoClaveList(String tcTexto, String tcTipo);

	public void crearTextoClave(TextoClave textoClave)
			throws ExcGuardarRegistro;
}
