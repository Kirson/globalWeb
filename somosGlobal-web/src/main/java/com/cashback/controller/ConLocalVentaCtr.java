package com.cashback.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.cashback.interfaces.Globales;
import com.cashback.interfaces.ILocalVenta;
import com.cashback.interfaces.IUbicacion;
import com.cashback.model.LocalPromocion;
import com.cashback.model.LocalVenta;
import com.cashback.model.Ubicacion;

@SessionScoped
@ManagedBean
public class ConLocalVentaCtr {
	private LocalVenta localVenta;
	
	private List<Ubicacion> ubicacionList;
	private MapModel model;
	private String centerMap, ubiFrameMapa = "", ubiFrameSv = "";
	private Marker marker;
	private Ubicacion ubicacion;
	@EJB
	private ILocalVenta sLocalVenta;
	@EJB
	private IUbicacion sUbicacion;

	public ConLocalVentaCtr() {
		model = new DefaultMapModel();
	}
	
	public String seleccionarLocalVenta() {
		List<LocalPromocion> localPromocionList= sLocalVenta.recuperarLocalPromocionList(
				localVenta, Globales.IMG_TIPO_PROMO, Globales.EST_OK);
		List<LocalPromocion> localPromocionBRList= sLocalVenta.recuperarLocalPromocionList(
				localVenta, Globales.IMG_TIPO_BARRA, Globales.EST_OK);
		localVenta.setLocalPromocionList(localPromocionList);
		localVenta.setLocalPromocionBRList(localPromocionBRList);
		if (localPromocionBRList.size() < 4) {
			for (int i = localPromocionBRList.size(); i < 4; i++) {
				LocalPromocion lp = new LocalPromocion();
				lp.setLpImagen("feliz.png");
				localPromocionBRList.add(lp);
			}
		}

		ubicacionList = sUbicacion.recuperarUbicacionList(localVenta);
		model = new DefaultMapModel();

		for (Ubicacion ubicacion : ubicacionList) {
			double latitud = Double.parseDouble(ubicacion.getUbiLatitud());
			double longitud = Double.parseDouble(ubicacion.getUbiLongitud());
			centerMap = String.valueOf(latitud) + ", "
					+ String.valueOf(longitud);
			Marker marker = new Marker(new LatLng(latitud, longitud),
					localVenta.getLvNombreCom());
			marker.setCursor(Integer.toString(ubicacion.getUbiId()));
			model.addOverlay(marker);
		}
		if (centerMap == null) {
			centerMap = "-1.241541009574931, -78.61058816313744";
		}
		return "conLocalPromociones";
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		this.marker = (Marker) event.getOverlay();
		ubicacion = sUbicacion.recuperarUbicacion(Integer.parseInt(marker
				.getCursor()));
		ubiFrameMapa = ubicacion.getUbiFrameMapa();
		ubiFrameSv = ubicacion.getUbiFrameSv();
	}

	public String verLocalVenta(){
		seleccionarLocalVenta();
		return "conLocal?faces-redirect=true";
	}
	
	public String verLocalImagenes(){
		return "conLocalImagenes?faces-redirect=true";
	}
	
	public LocalVenta getLocalVenta() {
		return localVenta;
	}

	public void setLocalVenta(LocalVenta localVenta) {
		this.localVenta = localVenta;
	}

	public MapModel getModel() {
		return model;
	}

	public void setModel(MapModel model) {
		this.model = model;
	}

	public List<Ubicacion> getUbicacionList() {
		return ubicacionList;
	}

	public void setUbicacionList(List<Ubicacion> ubicacionList) {
		this.ubicacionList = ubicacionList;
	}

	public String getCenterMap() {
		return centerMap;
	}

	public void setCenterMap(String centerMap) {
		this.centerMap = centerMap;
	}

	public String getUbiFrameMapa() {
		return ubiFrameMapa;
	}

	public void setUbiFrameMapa(String ubiFrameMapa) {
		this.ubiFrameMapa = ubiFrameMapa;
	}

	public String getUbiFrameSv() {
		return ubiFrameSv;
	}

	public void setUbiFrameSv(String ubiFrameSv) {
		this.ubiFrameSv = ubiFrameSv;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
}
