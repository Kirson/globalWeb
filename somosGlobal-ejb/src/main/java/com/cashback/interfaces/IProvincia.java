package com.cashback.interfaces;

import java.util.List;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.Provincia;

public interface IProvincia {
	List<Provincia> recuperarProvinciaList();

	void crearProvincia(Provincia provincia) throws ExcGuardarRegistro;

	Provincia actualizarProvincia(Provincia provincia) 
			throws ExcGuardarRegistro;

}
