package com.cashback.interfaces;

import java.util.List;

import com.cashback.model.Persona;
import com.cashback.model.Puntos;

public interface IPuntos {
	void crearPuntos(Puntos punto);

	List<Puntos> recuperarPuntos(Persona persona);
}
