package com.cashback.interfaces;

import java.util.List;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.Menu;
import com.cashback.model.Perfil;

public interface IMenu {
	List<Menu> recuperarMenu(Perfil perfil);

	void crearMenu(Menu menu) throws ExcGuardarRegistro;

	Menu actualizarMenu(Menu menu) throws ExcGuardarRegistro;

	Menu recuperarMenu(String menNombre);

}
