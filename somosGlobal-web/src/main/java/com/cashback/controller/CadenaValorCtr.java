package com.cashback.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.cashback.enums.AppMensajes;
import com.cashback.interfaces.Globales;
import com.cashback.interfaces.IActor;
import com.cashback.interfaces.IActorRol;
import com.cashback.model.Actor;
import com.cashback.model.ActorRol;
import com.cashback.model.CatalogoGen;
import com.cashback.model.ICatalogoGen;

@SessionScoped
@ManagedBean
public class CadenaValorCtr extends Controladores {

	private String cedrucpasAct;
	private ActorRol actorRol;
	@EJB
	private IActorRol sActorRol;
	@EJB
	private ICatalogoGen sCatalogoGen;
	@EJB
	private IActor sActor;

	public CadenaValorCtr() {

	}

	private Actor consumidor;
	private List<ActorRol> actorRolList;

	public void recuperarActorRolList() {
		Actor actor = sActor.recuperarActorByCedRucPas(cedrucpasAct);
		CatalogoGen catalogoGen = sCatalogoGen.recuperarCatalogoGen(
				Globales.ROL_NEGOCIO, Globales.NIVEL_CONSUMIDOR);

		actorRolList = new ArrayList<ActorRol>();
		ActorRol consumidorRol = sActorRol.recuperarActorRol(actor,
				catalogoGen, "");
		Actor consumidor = consumidorRol.getActor();
		actorRolList.add(consumidorRol);

		ActorRol localRol = consumidorRol.getActorRol();
		Actor local = localRol.getActor();
		actorRolList.add(localRol);

		ActorRol grupoEmpresarialRol = localRol.getActorRol();
		Actor grupoEmpresarial = grupoEmpresarialRol.getActor();
		actorRolList.add(grupoEmpresarialRol);

		ActorRol holdingRol = grupoEmpresarialRol.getActorRol();
		Actor holding = holdingRol.getActor();
		actorRolList.add(holdingRol);

		ActorRol supervisorHoldingRol = holdingRol.getActorRol();
		Actor supervisorHolding = supervisorHoldingRol.getActor();
		actorRolList.add(supervisorHoldingRol);

		ActorRol globalRol = supervisorHoldingRol.getActorRol();
		Actor global = globalRol.getActor();
		actorRolList.add(globalRol);
	}

	public String actualizarActorRol() {
		sActorRol.actualizarActorRol(actorRol);
		mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		return null;
	}

	public Actor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Actor consumidor) {
		this.consumidor = consumidor;
	}

	public List<ActorRol> getActorRolList() {
		return actorRolList;
	}

	public void setActorRolList(List<ActorRol> actorRolList) {
		this.actorRolList = actorRolList;
	}

	public String getCedrucpasAct() {
		return cedrucpasAct;
	}

	public void setCedrucpasAct(String cedrucpasAct) {
		this.cedrucpasAct = cedrucpasAct;
	}

	public ActorRol getActorRol() {
		return actorRol;
	}

	public void setActorRol(ActorRol actorRol) {
		this.actorRol = actorRol;
	}

}
