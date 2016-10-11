package com.app.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.cashback.interfaces.IActorReferencia;
import com.cashback.model.Actor;
import com.cashback.model.ActorReferencia;
import com.cashback.model.ICatalogoGen;

@SessionScoped
@ManagedBean
public class ActorLocalCtrl {

	private Actor localActor;
	private boolean verGaleria, verPromociones;
	private MapModel model;
	private String centerMap, ubiFrameMapa = "", ubiFrameSv = "";
	private Marker marker;
	private ActorReferencia direccion;

	@EJB
	private IActorReferencia sActorReferencia;

	@EJB
	private ICatalogoGen sCatalogoGen;

	public ActorLocalCtrl() {
		model = new DefaultMapModel();
		centerMap = "-1.241541009574931, -78.61058816313744";
	}

	public String verActorLocal() {
		model = new DefaultMapModel();
		if (localActor.getDireccionesActor() != null) {
			for (ActorReferencia direccion : localActor.getDireccionesActor()) {
				double latitud = Double.parseDouble(direccion.getLatitudAr());
				double longitud = Double.parseDouble(direccion.getLongitudAr());
				centerMap = String.valueOf(latitud) + ", "
						+ String.valueOf(longitud);
				Marker marker = new Marker(new LatLng(latitud, longitud),
						localActor.getRazonSocialAct());
				marker.setCursor(Integer.toString(direccion.getIdAr()));
				model.addOverlay(marker);
			}
		}
		if (centerMap == null) {
			centerMap = "-1.241541009574931, -78.61058816313744";
		}
		return "verActorLocal?faces-redirect=true";

	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		this.marker = (Marker) event.getOverlay();
		direccion = sActorReferencia.findById(Integer.parseInt(marker
				.getCursor()));
		ubiFrameMapa = direccion.getUrlGmapAr();
		ubiFrameSv = direccion.getUrlSviewAr();
	}

	public String verGaleriaLocal() {
		verGaleria = true;
		verPromociones = false;
		return "verGaleriaLocal";
	}

	public String verPromocionLocal() {
		verGaleria = false;
		verPromociones = true;
		return "verGaleriaLocal";
	}

	public Actor getLocalActor() {
		return localActor;
	}

	public void setLocalActor(Actor localActor) {
		this.localActor = localActor;
	}

	public boolean isVerGaleria() {
		return verGaleria;
	}

	public void setVerGaleria(boolean verGaleria) {
		this.verGaleria = verGaleria;
	}

	public boolean isVerPromociones() {
		return verPromociones;
	}

	public void setVerPromociones(boolean verPromociones) {
		this.verPromociones = verPromociones;
	}

	public MapModel getModel() {
		return model;
	}

	public void setModel(MapModel model) {
		this.model = model;
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

	public ActorReferencia getDireccion() {
		return direccion;
	}

	public void setDireccion(ActorReferencia direccion) {
		this.direccion = direccion;
	}

}
