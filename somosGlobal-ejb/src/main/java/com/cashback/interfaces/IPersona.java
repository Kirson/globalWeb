package com.cashback.interfaces;

import java.util.List;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.HobbiePersona;
import com.cashback.model.Persona;

public interface IPersona {
	List<Persona> recuperarPersonaList(String perNombres, String perApellidos, String perCedRucPas);
	List<Persona> recuperarPersonaList(String perEstado);

	void crearPersona(Persona persona) throws ExcGuardarRegistro;

	Persona actualizarPersona(Persona persona) throws ExcGuardarRegistro;

	Persona recuperarPersona(int id);

	void crearHobbiePersona(HobbiePersona hobbiePersona);

	List<HobbiePersona> recuperarHobbiePersonaList(Persona persona);
	
	/**
	 * 
	 */
	HobbiePersona actualizarHobbiePersona(HobbiePersona hobbiePersona);
	

}
