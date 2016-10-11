package com.cashback.interfaces;

import java.util.List;

import com.cashback.model.Actor;
import com.cashback.model.ActorReferencia;
import com.cashback.model.CatalogoGen;

public interface IActorReferencia {
	boolean crearActorReferencia(ActorReferencia actorReferencia);

	ActorReferencia recuperarActorReferencia(Actor actor, String tipoCg,
			String refCg);
	
	List<ActorReferencia> findAllByActorAndPadreCatalogoGen(Actor actor, CatalogoGen padreCatalogoGen);
	
	ActorReferencia findById(int idAr);
	

}
