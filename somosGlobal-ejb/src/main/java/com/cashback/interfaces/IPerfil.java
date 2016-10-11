package com.cashback.interfaces;

import java.util.List;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.Perfil;

public interface IPerfil {
	List<Perfil> recuperarPerfilList(String prfNombre);

	List<Perfil> recuperarPerfilList2(String prfEstado);

	Perfil recuperarPerfil(int prfId);
	
	Perfil recuperarPerfil(String prfCodigo);

	void crearPerfil(Perfil perfil) throws ExcGuardarRegistro;

	Perfil actualizarPerfil(Perfil perfil) throws ExcGuardarRegistro;
}
