package com.cashback.servicios;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.cashback.interfaces.Globales;
import com.cashback.interfaces.IActorReferencia;
import com.cashback.model.Actor;
import com.cashback.model.ActorReferencia;
import com.cashback.model.CatalogoGen;

@Stateless
public class SActorReferencia extends AbstractService implements
		IActorReferencia {

	@Override
	public boolean crearActorReferencia(ActorReferencia actorReferencia) {
		actorReferencia.setFecCreaAr(new Date());
		actorReferencia.setVal1Ar(actorReferencia.getVal1Ar().trim());
		actorReferencia.setEstado_ar(Globales.EST_OK);
		emCashback.persist(actorReferencia);
		Actor actor = actorReferencia.getActor();
		if (actorReferencia.getCatalogoGen().getCatalogoGen().getTipoCg()
				.compareTo(Globales.IMAGEN_TIPO_CATALOGO) == 0) {
			if (actorReferencia.getCatalogoGen().getRefCg()
					.compareTo(Globales.IMG_TIPO_FOTO_ESTABLECIMIENTO) == 0) {
				actor.setFotoAct(actorReferencia.getVal1Ar());
				emCashback.merge(actor);
			}
			if (actorReferencia.getCatalogoGen().getRefCg()
					.compareTo(Globales.IMG_TIPO_LOGO_ESTABLECIMIENTO) == 0) {
				actor.setLogoAct(actorReferencia.getVal1Ar());
				emCashback.merge(actor);
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ActorReferencia recuperarActorReferencia(Actor actor, String tipoCg,
			String refCg) {
		Query q = emCashback
				.createNamedQuery("ActorReferencia.findByActorReferencia");
		q.setParameter("actor", actor);
		q.setParameter("tipoCg", tipoCg);
		q.setParameter("refCg", refCg);
		List<ActorReferencia> actorReferenciaList = (List<ActorReferencia>) q
				.getResultList();
		if (actorReferenciaList.size() > 0) {
			return actorReferenciaList.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActorReferencia> findAllByActorAndPadreCatalogoGen(Actor actor,
			CatalogoGen padreCatalogoGen) {
		Query q = emCashback
				.createNamedQuery("ActorReferencia.findAllByActorAndPadreCatalogoGen");
		q.setParameter("actor", actor);
		q.setParameter("padreCatalogoGen", padreCatalogoGen);
		return q.getResultList();
	}

	@Override
	public ActorReferencia findById(int idAr) {
		return emCashback.find(ActorReferencia.class, idAr);
	}
}
