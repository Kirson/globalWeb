package com.cashback.interfaces;

import java.util.List;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.Contacto;
import com.cashback.model.Persona;

public interface IContacto {
	void crearContacto(Contacto contacto) throws ExcGuardarRegistro;

	Contacto actualizarContacto(Contacto contacto) throws ExcGuardarRegistro;

	List<Contacto> recuperarContactoList(Persona persona);
}
