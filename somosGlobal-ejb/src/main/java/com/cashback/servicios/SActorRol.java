package com.cashback.servicios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.cashback.interfaces.Globales;
import com.cashback.interfaces.IActorReferencia;
import com.cashback.interfaces.IActorRol;
import com.cashback.model.Actor;
import com.cashback.model.ActorReferencia;
import com.cashback.model.ActorRol;
import com.cashback.model.CatalogoGen;
import com.cashback.model.ICatalogoGen;
import com.cashback.model.Localidad;

@Stateless
public class SActorRol extends AbstractService implements IActorRol {

	@EJB
	private IActorReferencia sActorReferencia;
	@EJB
	private ICatalogoGen sCatalogoGen;

	@Override
	public boolean crearActorRol(ActorRol actorRol) {
		if (!existeActorRol(actorRol.getActor(), actorRol.getCatalogoGen(),
				actorRol.getPrcArol())) {
			actorRol.setFecCreaArol(new Date());
			emCashback.persist(actorRol);
			return true;
		}
		return false;
	}

	@Override
	public ActorRol recuperarActorRol(int idArol) {
		return emCashback.find(ActorRol.class, idArol);
	}

	@Override
	public ActorRol recuperarActorRol(Actor actor, CatalogoGen catalogoGen,
			String estadoArol) {
		Query q = emCashback.createNamedQuery("ActorRol.findByActorAndRolNegocio");
		q.setParameter("actor", actor);
		q.setParameter("catalogoGen", catalogoGen);
		q.setParameter("estadoArol", estadoArol + "%");

		@SuppressWarnings("unchecked")
		List<ActorRol> actorRols = (List<ActorRol>) q.getResultList();

		if (actorRols.size() > 0) {
			return actorRols.get(0);
		}
		return null;
	}

	@Override
	public boolean tieneRolNegocio(Actor actor, String refCg, String estadoArol) {
		Query q = emCashback
				.createNamedQuery("ActorRol.findByActortTipoCuenta");
		q.setParameter("actor", actor);
		q.setParameter("refCg", refCg);
		q.setParameter("estadoArol", estadoArol + "%");
		Long a = (Long) q.getSingleResult();
		if (a > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActorRol> recuperarActorRolList(Actor actor,
			CatalogoGen rolNegocio, String estadoArol) {
		Query q = emCashback.createNamedQuery("ActorRol.findByActorAndRolNegocio");
		q.setParameter("actor", actor);
		q.setParameter("rolNegocio", rolNegocio);
		q.setParameter("estadoArol", estadoArol + "%");
		return q.getResultList();
	}

	@Override
	public ActorRol actualizarActorRol(ActorRol actorRol) {
		actorRol.setFecModArol(new Date());
		return emCashback.merge(actorRol);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActorRol> recuperarHijosActorRol(Actor padreActor) {
		Query q = emCashback.createNamedQuery("ActorRol.findByPadre");
		q.setParameter("padreActor", padreActor);
		return q.getResultList();
	}

	@Override
	public boolean existeActorRol(Actor actor, CatalogoGen rolNegocio,
			BigDecimal prcArol) {
		Query q = emCashback
				.createNamedQuery("ActorRol.findByActorAndRolNegocioAndPorcentaje");
		q.setParameter("actor", actor);
		q.setParameter("rolNegocio", rolNegocio);
		q.setParameter("prcArol", prcArol);

		Long count = (Long) q.getSingleResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActorRol> recuperarActorRolList(String cedrucpasAct,
			Actor padreActor) {
		Query q = emCashback
				.createNamedQuery("ActorRol.findByCedrucpasActAndPadreActor");
		q.setParameter("cedrucpasAct", cedrucpasAct + "%");
		q.setParameter("padreActor", padreActor);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Actor> recuperarActorByRolNegocioAndCategoria(
			CatalogoGen rolNegocio, String catId) {
		Query q = emCashback
				.createNamedQuery("ActorRol.findAllByRolNegocioAndCategoria");
		q.setParameter("rolNegocio", rolNegocio);
		q.setParameter("catId", catId + "%");

		List<Actor> actorList = (List<Actor>) q.getResultList();

		CatalogoGen telefono = sCatalogoGen.findByTipoCg(Globales.TELEFONO);
		CatalogoGen correo = sCatalogoGen
				.findByTipoCg(Globales.CORREO_ELECTRONICO);
		CatalogoGen direccion = sCatalogoGen.findByTipoCg(Globales.DIRECCION);
		CatalogoGen horario = sCatalogoGen
				.findByTipoCg(Globales.DIAS_TIPO_CATALOGO);
		CatalogoGen imagen = sCatalogoGen
				.findByTipoCg(Globales.IMAGEN_TIPO_CATALOGO);

		List<Actor> actores = new ArrayList<Actor>();
		for (Actor actor : actorList) {
			actor.setTelefonosActor(sActorReferencia
					.findAllByActorAndPadreCatalogoGen(actor, telefono));
			actor.setCorreosActor(sActorReferencia
					.findAllByActorAndPadreCatalogoGen(actor, correo));
			actor.setDireccionesActor(sActorReferencia
					.findAllByActorAndPadreCatalogoGen(actor, direccion));
			for (ActorReferencia ar : actor.getDireccionesActor()) {
				ar.setLocalidad(emCashback.find(Localidad.class,
						Integer.parseInt(ar.getVal1Ar())));
			}
			actor.setHorariosActor(sActorReferencia
					.findAllByActorAndPadreCatalogoGen(actor, horario));

			List<ActorReferencia> imagenList = sActorReferencia
					.findAllByActorAndPadreCatalogoGen(actor, imagen);
			List<ActorReferencia> galeriaImgActor = new ArrayList<ActorReferencia>();
			List<ActorReferencia> promocionImgActor = new ArrayList<ActorReferencia>();
			for (ActorReferencia ar : imagenList) {
				if (ar.getCatalogoGen().getRefCg()
						.compareTo(Globales.IMAGEN_PROMOCION) == 0) {
					promocionImgActor.add(ar);
				}
				if (ar.getCatalogoGen().getRefCg()
						.compareTo(Globales.IMAGEN_GALERIA) == 0) {
					galeriaImgActor.add(ar);
				}
			}
			actor.setGaleriaImgActor(galeriaImgActor);
			actor.setPromocionImgActor(promocionImgActor);
			actores.add(actor);
		}
		return actores;
	}

	@SuppressWarnings("unchecked")
	public List<Actor> findAllByPalabraClaveAndRolNegocio(
			String palabraClaveAct, CatalogoGen rolNegocio) {
		Query q = emCashback
				.createNamedQuery("ActorRol.findAllByPalabraClaveAndRolNegocio");
		q.setParameter("palabraClaveAct", "%" + palabraClaveAct + "%");
		q.setParameter("rolNegocio", rolNegocio);
		List<Actor> actorList = (List<Actor>) q.getResultList();
		for (Actor actor : actorList) {
			int i = actor.getActorReferencias().size();
		}
		return q.getResultList();

	}
}
