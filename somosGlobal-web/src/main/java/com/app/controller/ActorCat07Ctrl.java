package com.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.cashback.controller.Controladores;
import com.cashback.enums.AppMensajes;
import com.cashback.interfaces.Globales;
import com.cashback.interfaces.IActor;
import com.cashback.interfaces.IActorReferencia;
import com.cashback.interfaces.IActorRol;
import com.cashback.interfaces.ICategoria;
import com.cashback.model.Actor;
import com.cashback.model.ActorReferencia;
import com.cashback.model.CatalogoGen;
import com.cashback.model.Categoria;
import com.cashback.model.ICatalogoGen;
import com.cashback.model.ILocalidad;

@SessionScoped
@ManagedBean
public class ActorCat07Ctrl extends Controladores {
	private String catIdSeleccionada = "07";
	private List<Actor> actorList;
	private CatalogoGen rolNegocio;
	private List<SelectItem> categoriaListItem;
	private Categoria categoria;

	@EJB
	private ICategoria sCategoria;
	@EJB
	private IActorRol sActorRol;
	@EJB
	private IActor sActor;
	@EJB
	private ICatalogoGen sCatalogoGen;
	@EJB
	private IActorReferencia sActoReferencia;
	@EJB
	private ILocalidad sLocalidad;

	@PostConstruct
	public void inicio() {
		categoria = sCategoria.recuperarCategoria(catIdSeleccionada);
		rolNegocio = sCatalogoGen.recuperarCatalogoGen(Globales.ROL_NEGOCIO,
				Globales.NIVEL_GRUPO_EMPRESARIAL);
		recuperarActorList();
		categoriaListItem = recuperarCategoriaListItem(catIdSeleccionada);
	}

	public void recuperarActorList() {
		List<Actor> actoresPadre = sActor
				.findAllByCategoriaInHijosFromRolNegocio(rolNegocio,
						catIdSeleccionada, Globales.EST_OK, Globales.EST_OK);
		actorList = new ArrayList<Actor>();
		for (Actor actorPadre : actoresPadre) {
			List<Actor> actoresHijos = new ArrayList<Actor>();
			for (Actor actorHijo : actorPadre.getActoresHijos()) {
				List<ActorReferencia> telefonosActor = new ArrayList<ActorReferencia>();
				List<ActorReferencia> direccionesActor = new ArrayList<ActorReferencia>();
				List<ActorReferencia> horariosActor = new ArrayList<ActorReferencia>();
				List<ActorReferencia> correosActor = new ArrayList<ActorReferencia>();
				List<ActorReferencia> galeriaImgActor = new ArrayList<ActorReferencia>();
				List<ActorReferencia> promocionImgActor = new ArrayList<ActorReferencia>();
				for (ActorReferencia ar : actorHijo.getActorReferencias()) {
					String tipoCatalogo = ar.getCatalogoGen().getCatalogoGen()
							.getTipoCg();
					if (tipoCatalogo.compareTo(Globales.TELEFONO) == 0) {
						telefonosActor.add(ar);
					}
					if (tipoCatalogo.compareTo(Globales.DIRECCION) == 0) {
						ar.setLocalidad(sLocalidad.recuperarLocalidad(Integer
								.parseInt(ar.getVal1Ar())));
						direccionesActor.add(ar);
					}
					if (tipoCatalogo.compareTo(Globales.DIAS_TIPO_CATALOGO) == 0) {
						horariosActor.add(ar);
					}
					if (tipoCatalogo.compareTo(Globales.CORREO_ELECTRONICO) == 0) {
						correosActor.add(ar);
					}

					if (tipoCatalogo.compareTo(Globales.IMAGEN_TIPO_CATALOGO) == 0) {
						if (ar.getCatalogoGen().getRefCg()
								.compareTo(Globales.IMAGEN_PROMOCION) == 0) {
							promocionImgActor.add(ar);
						}
						if (ar.getCatalogoGen().getRefCg()
								.compareTo(Globales.IMAGEN_GALERIA) == 0) {
							galeriaImgActor.add(ar);
						}
					}
				}
				actorHijo.setTelefonosActor(telefonosActor);
				actorHijo.setDireccionesActor(direccionesActor);
				actorHijo.setHorariosActor(horariosActor);
				actorHijo.setCorreosActor(correosActor);
				actorHijo.setGaleriaImgActor(galeriaImgActor);
				actorHijo.setPromocionImgActor(promocionImgActor);
				actoresHijos.add(actorHijo);
			}
			actorPadre.setActoresHijos(actoresHijos);
			actorList.add(actorPadre);
		}
		Collections.shuffle(actorList);
	}

	public String recuperarLocalVentaList3() {
		if (palabraClaveAct.trim().length() == 0) {
			mostrarInfo(AppMensajes.INF_NO_RESULTADOS);
			return null;
		}

		actorList = new ArrayList<Actor>();
		List<Actor> actores = sActorRol.findAllByPalabraClaveAndRolNegocio(
				palabraClaveAct, rolNegocio);
		for (Actor actor : actores) {
			List<ActorReferencia> telefonosActor = new ArrayList<ActorReferencia>();
			List<ActorReferencia> direccionesActor = new ArrayList<ActorReferencia>();
			List<ActorReferencia> horariosActor = new ArrayList<ActorReferencia>();
			List<ActorReferencia> correosActor = new ArrayList<ActorReferencia>();
			List<ActorReferencia> galeriaImgActor = new ArrayList<ActorReferencia>();
			List<ActorReferencia> promocionImgActor = new ArrayList<ActorReferencia>();
			for (ActorReferencia ar : actor.getActorReferencias()) {
				String tipoCatalogo = ar.getCatalogoGen().getCatalogoGen()
						.getTipoCg();
				if (tipoCatalogo.compareTo(Globales.TELEFONO) == 0) {
					telefonosActor.add(ar);
				}
				if (tipoCatalogo.compareTo(Globales.DIRECCION) == 0) {
					direccionesActor.add(ar);
				}
				if (tipoCatalogo.compareTo(Globales.DIAS_TIPO_CATALOGO) == 0) {
					horariosActor.add(ar);
				}
				if (tipoCatalogo.compareTo(Globales.CORREO_ELECTRONICO) == 0) {
					correosActor.add(ar);
				}

				if (tipoCatalogo.compareTo(Globales.IMAGEN_TIPO_CATALOGO) == 0) {
					if (ar.getCatalogoGen().getRefCg()
							.compareTo(Globales.IMAGEN_PROMOCION) == 0) {
						promocionImgActor.add(ar);
					}
					if (ar.getCatalogoGen().getRefCg()
							.compareTo(Globales.IMAGEN_GALERIA) == 0) {
						galeriaImgActor.add(ar);
					}
				}
			}
			actor.setTelefonosActor(telefonosActor);
			actor.setDireccionesActor(direccionesActor);
			actor.setHorariosActor(horariosActor);
			actor.setCorreosActor(correosActor);
			actor.setGaleriaImgActor(galeriaImgActor);
			actor.setPromocionImgActor(promocionImgActor);
			actorList.add(actor);
		}
		return null;
	}

	public List<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	public List<SelectItem> getCategoriaListItem() {
		return categoriaListItem;
	}

	public void setCategoriaListItem(List<SelectItem> categoriaListItem) {
		this.categoriaListItem = categoriaListItem;
	}

	public String getCatIdSeleccionada() {
		return catIdSeleccionada;
	}

	public void setCatIdSeleccionada(String catIdSeleccionada) {
		this.catIdSeleccionada = catIdSeleccionada;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}