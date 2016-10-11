package com.cashback.interfaces;

import java.util.List;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.Hobbie;

public interface IHobbie {
	void crearHobbie(Hobbie hobbie) throws ExcGuardarRegistro;

	List<Hobbie> recuperarHobbieList(String estadoHobbie);

	Hobbie actualizar(Hobbie hobbie) throws ExcGuardarRegistro;

	List<Hobbie> recuperarHobbieList();

	Hobbie recuperarHobbie(int codigo);
}
