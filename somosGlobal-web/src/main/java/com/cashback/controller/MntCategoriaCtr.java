package com.cashback.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.ICategoria;
import com.cashback.model.Categoria;

@SessionScoped
@ManagedBean
public class MntCategoriaCtr extends Controladores {

	@EJB
	private ICategoria sCategoria;

	private Categoria categoria;
	private List<Categoria> categoriaList;
	private boolean crearNuevo, actualizar;

	public MntCategoriaCtr() {
		categoria = new Categoria();
		categoriaList = new ArrayList<Categoria>();
	}

	@PostConstruct
	public void inicio(){
		categoriaList = sCategoria.recuperarCategoriaList();
	}
	
	public String crearCategoria() {
		try {
			sCategoria.crearCategoria(categoria);
			categoriaList.add(categoria);
			categoria = new Categoria();
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode());
		}
		return null;
	}

	public String actualizarCategoria() {
		try {
			sCategoria.actualizarCategoria(categoria);
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode());
		}
		return null;
	}

	public String nuevaCategoria() {
		categoria = new Categoria();
		crearNuevo = true;
		actualizar = false;
		return null;
	}

	public String seleccionarCategoria() {
		crearNuevo = false;
		actualizar = true;
		return null;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategoriaList() {
		return categoriaList;
	}

	public void setCategoriaList(List<Categoria> categoriaList) {
		this.categoriaList = categoriaList;
	}

	public boolean isCrearNuevo() {
		return crearNuevo;
	}

	public void setCrearNuevo(boolean crearNuevo) {
		this.crearNuevo = crearNuevo;
	}

	public boolean isActualizar() {
		return actualizar;
	}

	public void setActualizar(boolean actualizar) {
		this.actualizar = actualizar;
	}
}
