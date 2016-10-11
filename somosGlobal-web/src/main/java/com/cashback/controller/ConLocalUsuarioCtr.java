package com.cashback.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.cashback.interfaces.ILocalPromocion;
import com.cashback.interfaces.ILocalVenta;
import com.cashback.interfaces.IUbicacion;
import com.cashback.model.LocalPromocion;
import com.cashback.model.LocalVenta;
import com.cashback.model.Ubicacion;

@SessionScoped
@ManagedBean
public class ConLocalUsuarioCtr extends Controladores {
	private LocalVenta localVenta;
	private Ubicacion ubicacion;
	private LocalPromocion localPromocion;
	private List<LocalVenta> localVentaList;
	private List<Ubicacion> ubicacionList;
	private List<LocalPromocion> localPromocionList, localPromocionBarraList;

	@EJB
	private ILocalVenta sLocalVenta;
	@EJB
	private IUbicacion sUbicacion;
	@EJB
	private ILocalPromocion sLocalPromocion;

	public ConLocalUsuarioCtr() {
		super();
	}

	@PostConstruct
	public void inicio() {
		// localVentaList = sLocalVenta.recuperarLocalVentaList(usuario
		// .getPersona());
	}

	public String seleccionarLocalVenta() {
		ubicacionList = sUbicacion.recuperarUbicacionList(localVenta);
		localPromocionList = sLocalPromocion.recuperarLocalPromocionList(
				localVenta, "", "");
		return "mntUbicacion";

	}

	public LocalVenta getLocalVenta() {
		return localVenta;
	}

	public void setLocalVenta(LocalVenta localVenta) {
		this.localVenta = localVenta;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public LocalPromocion getLocalPromocion() {
		return localPromocion;
	}

	public void setLocalPromocion(LocalPromocion localPromocion) {
		this.localPromocion = localPromocion;
	}

	public List<LocalVenta> getLocalVentaList() {
		return localVentaList;
	}

	public void setLocalVentaList(List<LocalVenta> localVentaList) {
		this.localVentaList = localVentaList;
	}

	public List<Ubicacion> getUbicacionList() {
		return ubicacionList;
	}

	public void setUbicacionList(List<Ubicacion> ubicacionList) {
		this.ubicacionList = ubicacionList;
	}

	public List<LocalPromocion> getLocalPromocionList() {
		return localPromocionList;
	}

	public void setLocalPromocionList(List<LocalPromocion> localPromocionList) {
		this.localPromocionList = localPromocionList;
	}

	public List<LocalPromocion> getLocalPromocionBarraList() {
		return localPromocionBarraList;
	}

	public void setLocalPromocionBarraList(
			List<LocalPromocion> localPromocionBarraList) {
		this.localPromocionBarraList = localPromocionBarraList;
	}
}
