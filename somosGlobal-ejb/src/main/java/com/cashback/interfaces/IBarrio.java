package com.cashback.interfaces;

import java.util.List;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.Barrio;
import com.cashback.model.Ciudad;

public interface IBarrio {
	List<Barrio> recuperarBarrioList(Ciudad ciudad);

	void crearBarrio(Barrio barrio) throws ExcGuardarRegistro;

	void actualizarBarrio(Barrio barrio) throws ExcGuardarRegistro;
	
	Barrio recuperarBarrio(int barId);
}
