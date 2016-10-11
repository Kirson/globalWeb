package com.cashback.interfaces;

import java.util.List;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.Actor;
import com.cashback.model.ActorRol;
import com.cashback.model.CatalogoGen;

public interface IActor {

	Actor guardarActor(Actor actor);

	boolean crearActor(Actor actor) throws ExcGuardarRegistro;

	List<Actor> recuperarActorList(String tipoAct, String nombresAct,
			String apellidosAct, String cedrucpasAct, String estadoAct);

	Actor recuperarActor(int idAct);
	
	Actor findByIdAct(int idAct);

	Actor recuperarActorByCedRucPas(String cedrucpasAct);

	boolean crearActorActorRolUsuairo(Actor actor, ActorRol actorRol)
			throws ExcGuardarRegistro;

	List<Actor> findAllByRolNegocioAndCategoria(CatalogoGen rolNegocio,
			String estadoArol, String catId, boolean recuperarHijos);

	List<Actor> findAllByCategoriaInHijosFromRolNegocio(CatalogoGen rolNegocio,
			String catId, String estadoAct, String estadoArol);

	List<Actor> findAllByCedRucPasAndRazonSocialNombre(String cedRucPas,
			String razonSocialAct, String nombresAct, String apellidosAct, String estadoAct);

}
