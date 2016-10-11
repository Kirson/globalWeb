package com.cashback.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.cashback.model.Actor;
import com.cashback.model.ActorRol;
import com.cashback.model.CatalogoGen;

public interface IActorRol {

	boolean crearActorRol(ActorRol actorRol);

	ActorRol recuperarActorRol(int idArol);

	/**
	 * Recupera un ActorRol por el Actor y el Rol de Negocio
	 * 
	 * @param actor
	 * @param catalogoGen
	 *            Rol de negocio
	 * @param estadoArol
	 * @return
	 */
	ActorRol recuperarActorRol(Actor actor, CatalogoGen catalogoGen,
			String estadoArol);

	boolean tieneRolNegocio(Actor actor, String refCg, String estadoArol);

	List<ActorRol> recuperarActorRolList(Actor actor, CatalogoGen rolNegocio,
			String estadoArol);

	ActorRol actualizarActorRol(ActorRol actorRol);

	List<ActorRol> recuperarHijosActorRol(Actor padreActor);

	boolean existeActorRol(Actor actor, CatalogoGen rolNegocioCatalogoGen,
			BigDecimal prcArol);

	List<ActorRol> recuperarActorRolList(String cedrucpasAct, Actor padreActor);

	List<Actor> recuperarActorByRolNegocioAndCategoria(CatalogoGen rolNegocio,
			String catId);

	List<Actor> findAllByPalabraClaveAndRolNegocio(String palabraClaveAct,
			CatalogoGen rolNegocio);
	 
}
