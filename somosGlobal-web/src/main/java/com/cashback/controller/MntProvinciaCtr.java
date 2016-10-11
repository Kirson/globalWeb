package com.cashback.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.ICiudad;
import com.cashback.interfaces.IProvincia;
import com.cashback.model.Ciudad;
import com.cashback.model.Provincia;

@SessionScoped
@ManagedBean
public class MntProvinciaCtr extends Controladores {
	private Provincia provincia;
	private List<Provincia> provinciaList;
	private Ciudad ciudad;
	private List<Ciudad> ciudadList;
	private boolean crearNuevo, actualizar, crearNuevaCiudad, actualizarCiudadRender;
	@EJB
	private IProvincia sProvincia;
	@EJB
	private ICiudad sCiudad;

	public MntProvinciaCtr() {
		provincia = new Provincia();
		provinciaList = new ArrayList<Provincia>();
		ciudad = new Ciudad();
		ciudadList = new ArrayList<Ciudad>();
	}

	@PostConstruct
	void inicio() {
		provinciaList = sProvincia.recuperarProvinciaList();
	}

	public String nuevaProvincia() {
		provincia = new Provincia();
		crearNuevo = true;
		actualizar = false;
		return null;
	}

	public String seleccionarProvincia() {
		crearNuevo = false;
		actualizar = true;
		return null;
	}

	public String nuevaCiudad() {
		ciudad = new Ciudad();
		crearNuevaCiudad = true;
		actualizarCiudadRender = false;
		return null;
	}

	public String seleccionarCiudad() {
		crearNuevaCiudad = false;
		actualizarCiudadRender = true;
		return null;
	}

	public String crearProvincia() {
		try {
			sProvincia.crearProvincia(provincia);
			provinciaList.add(provincia);
			provincia = new Provincia();
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode());
		}
		return null;
	}

	public String actualizarProvincia() {
		try {
			sProvincia.actualizarProvincia(provincia);
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode());
		}
		return null;
	}

	public String recuperarCiudadList(){
		ciudadList= sCiudad.recuperarCiudadList(provincia);
		return null;
	}
	
	public String crearCiudad() {
		try {
			ciudad.setProvincia(provincia);
			sCiudad.crearCiudad(ciudad);
			ciudadList.add(ciudad);
			ciudad = new Ciudad();
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode());
		}
		return null;
	}

	public String actualizarCiudad() {
		try {
			sCiudad.actualizarCiudad(ciudad);
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode());
		}
		return null;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public List<Provincia> getProvinciaList() {
		return provinciaList;
	}

	public void setProvinciaList(List<Provincia> provinciaList) {
		this.provinciaList = provinciaList;
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

	public boolean isCrearNuevaCiudad() {
		return crearNuevaCiudad;
	}

	public void setCrearNuevaCiudad(boolean crearNuevaCiudad) {
		this.crearNuevaCiudad = crearNuevaCiudad;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public List<Ciudad> getCiudadList() {
		return ciudadList;
	}

	public void setCiudadList(List<Ciudad> ciudadList) {
		this.ciudadList = ciudadList;
	}

	public boolean isActualizarCiudadRender() {
		return actualizarCiudadRender;
	}

	public void setActualizarCiudadRender(boolean actualizarCiudadRender) {
		this.actualizarCiudadRender = actualizarCiudadRender;
	}
}
