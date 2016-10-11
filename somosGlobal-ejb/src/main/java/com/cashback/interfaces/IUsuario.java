package com.cashback.interfaces;

import java.util.List;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.Usuario;

public interface IUsuario {
	Usuario actualizarUsuario(Usuario usuario) throws ExcGuardarRegistro;

	boolean crearUsuario(Usuario usuario) throws ExcGuardarRegistro;

	List<Usuario> recuperarUsuarioList(String usrNombre);

	Usuario recuperarUsuario(String usrNombre, String usrPassword);

	Usuario recuperarUsuario(String usrNombre);
}
