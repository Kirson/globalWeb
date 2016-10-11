package com.cashback.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.cashback.enums.AppMensajes;
import com.cashback.interfaces.Globales;
import com.cashback.interfaces.IActorRol;
import com.cashback.model.ActorRol;

@SessionScoped
@ManagedBean
public class ActorRolCtr extends Controladores {

	private ActorRol actorRol;
	private List<ActorRol> actorRolList;
	private String cedrucpasAct;
	private BigDecimal nuevoPrcArol;

	@EJB
	private IActorRol sActorRol;

	public void crearActorRol() {
		ActorRol cloneActorRol = (ActorRol) actorRol.clone();
		cloneActorRol.setIdArol(null);
		cloneActorRol.setPrcArol(nuevoPrcArol);
		cloneActorRol.setEstadoArol(Globales.EST_OK);
		if (sActorRol.crearActorRol(cloneActorRol)) {
			mostrarInfo("Crear Porcentaje Rol", AppMensajes.INF_OPERACION_EXITO);
			actorRolList.add(cloneActorRol);
		} else {
			mostrarErrorSummary("Crear Porcentaje Rol",
					"Proceso no realizado, compruebe que no exista un porcentaje igual ya creado");
		}

	}

	public void recuperarActorRolList() {
		actorRolList = sActorRol.recuperarActorRolList(cedrucpasAct,
				usuario.getActor());
	}

	public ActorRol getActorRol() {
		return actorRol;
	}

	public void setActorRol(ActorRol actorRol) {
		this.actorRol = actorRol;
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

	public BigDecimal getNuevoPrcArol() {
		return nuevoPrcArol;
	}

	public void setNuevoPrcArol(BigDecimal nuevoPrcArol) {
		this.nuevoPrcArol = nuevoPrcArol;
	}

}
