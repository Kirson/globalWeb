package com.cashback.interfaces;

import java.util.List;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.Ciudad;
import com.cashback.model.Provincia;

public interface ICiudad {
	List<Ciudad> recuperarCiudadList(Provincia provincia);

	void crearCiudad(Ciudad ciudad) throws ExcGuardarRegistro;

	void actualizarCiudad(Ciudad ciudad) throws ExcGuardarRegistro;
	
	List<Ciudad> recuperarCiudadList();
	
	Ciudad recuperarCiudad(int id);
}
