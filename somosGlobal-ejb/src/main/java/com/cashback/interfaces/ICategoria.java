package com.cashback.interfaces;

import java.util.List;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.Categoria;

public interface ICategoria {
	List<Categoria> recuperarCategoriaList(String catEstado);

	List<Categoria> recuperarCategoriaList();

	void crearCategoria(Categoria categoria) throws ExcGuardarRegistro;

	Categoria actualizarCategoria(Categoria categoria)
			throws ExcGuardarRegistro;

	Categoria recuperarCategoria(String catId);

	List<Categoria> recuperarCategoriaList(String catId, String catEstado);
}
