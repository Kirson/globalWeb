package com.cashback.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.Globales;
import com.cashback.interfaces.IHobbie;
import com.cashback.model.Hobbie;

@SessionScoped
@ManagedBean
public class CtrMntHobbie extends Controladores {

	@EJB
	private IHobbie sHobbie;
	private Hobbie hobbie;
	private List<Hobbie> hobbieList;
	private boolean crearNuevo, actualizar;

	public CtrMntHobbie() {
		hobbie = new Hobbie();
		hobbieList = new ArrayList<Hobbie>();
	}

	@PostConstruct
	public void inicio() {
		hobbieList = sHobbie.recuperarHobbieList(Globales.EST_OK);
	}

	public String crearHobbie() {
		try {
			sHobbie.crearHobbie(hobbie);
			hobbieList.add(hobbie);
			hobbie = new Hobbie();
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode());
		}
		return null;
	}

	public String actualizarHobbie() {
		try {
			sHobbie.actualizar(hobbie);
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode());
		}
		return null;
	}

	public String nuevoHobbie() {
		hobbie = new Hobbie();
		crearNuevo = true;
		actualizar = false;
		return null;
	}

	public String seleccionarHobbie() {
		crearNuevo = false;
		actualizar = true;
		return null;
	}

	public Hobbie getHobbie() {
		return hobbie;
	}

	public void setHobbie(Hobbie hobbie) {
		this.hobbie = hobbie;
	}

	public List<Hobbie> getHobbieList() {
		return hobbieList;
	}

	public void setHobbieList(List<Hobbie> hobbieList) {
		this.hobbieList = hobbieList;
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
