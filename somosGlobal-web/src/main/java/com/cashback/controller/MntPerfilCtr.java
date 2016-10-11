package com.cashback.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.IPerfil;
import com.cashback.model.Perfil;

public class MntPerfilCtr extends Controladores {
	private Perfil perfil;
	private List<Perfil> perfilList;
	private Boolean crearPerfilRender, actualizarPerfilRender;

	@EJB
	private IPerfil sPerfil;

	public MntPerfilCtr() {

	}

	@PostConstruct
	private void inicio() {
		recuperarPerfilList();
	}

	public String recuperarPerfilList() {
		perfilList = sPerfil.recuperarPerfilList("");
		return null;
	}

	public String nuevoPerfil() {
		perfil = new Perfil();
		crearPerfilRender = true;
		actualizarPerfilRender = false;
		return null;
	}

	public String seleccionarPerfil() {
		crearPerfilRender = false;
		actualizarPerfilRender = true;
		return null;
	}

	public String crearPerfil() {
		try {
			sPerfil.crearPerfil(perfil);
			nuevoPerfil();
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode());
		}
		return null;
	}

	public String actualizarPerfil() {
		try {
			sPerfil.actualizarPerfil(perfil);
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
			nuevoPerfil();
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode());
		}
		return null;
	}

	public String recuperarMenu(){
		
		return null;
	}
	
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getPerfilList() {
		return perfilList;
	}

	public void setPerfilList(List<Perfil> perfilList) {
		this.perfilList = perfilList;
	}

	public Boolean getCrearPerfilRender() {
		return crearPerfilRender;
	}

	public void setCrearPerfilRender(Boolean crearPerfilRender) {
		this.crearPerfilRender = crearPerfilRender;
	}

	public Boolean getActualizarPerfilRender() {
		return actualizarPerfilRender;
	}

	public void setActualizarPerfilRender(Boolean actualizarPerfilRender) {
		this.actualizarPerfilRender = actualizarPerfilRender;
	}
}
