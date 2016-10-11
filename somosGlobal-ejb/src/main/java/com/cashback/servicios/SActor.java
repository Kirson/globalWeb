package com.cashback.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.Globales;
import com.cashback.interfaces.IActor;
import com.cashback.interfaces.IActorRol;
import com.cashback.interfaces.IPerfil;
import com.cashback.interfaces.IUsuario;
import com.cashback.model.Actor;
import com.cashback.model.ActorRol;
import com.cashback.model.CatalogoGen;
import com.cashback.model.Perfil;
import com.cashback.model.Usuario;

@Stateless
public class SActor extends AbstractService implements IActor {

	@EJB
	private IPerfil SPerfil;
	@EJB
	private IUsuario sUsuario;
	@EJB
	private IActorRol sActorRol;

	@Override
	public Actor guardarActor(Actor actor) {
		actor.setFecModAct(new Date());
		return emCashback.merge(actor);
	}

	@Override
	public boolean crearActor(Actor actor) throws ExcGuardarRegistro {
		if (recuperarActorByCedRucPas(actor.getCedrucpasAct()) != null) {
			throw new ExcGuardarRegistro(AppMensajes.ERR_REGISTRO_YA_EXISTE,
					"Actor");
		}
		actor.setFecCreaAct(new Date());
		if (actor.getRazonSocialAct() == null) {
			actor.setRazonSocialAct("");
		}
		if (actor.getNombresAct() == null) {
			actor.setNombresAct("");
		}
		if (actor.getApellidosAct() == null) {
			actor.setApellidosAct("");
		}
		emCashback.persist(actor);
		return true;
	}

	@Override
	public List<Actor> recuperarActorList(String tipoAct, String nombresAct,
			String apellidosAct, String cedrucpasAct, String estadoAct) {
		Query q = emCashback
				.createNamedQuery("Actor.findByTipoNombresApellidosCedula");
		q.setParameter("tipoAct", tipoAct + "%");
		q.setParameter("nombresAct", "%" + nombresAct + "%");
		q.setParameter("apellidosAct", "%" + apellidosAct + "%");
		q.setParameter("cedrucpasAct", cedrucpasAct + "%");
		q.setParameter("estadoAct", estadoAct + "%");
		@SuppressWarnings("unchecked")
		List<Actor> actorList = (List<Actor>) q.getResultList();
		return actorList;
	}

	@Override
	public Actor recuperarActor(int idAct) {
		return emCashback.find(Actor.class, idAct);
	}

	@Override
	public Actor recuperarActorByCedRucPas(String cedrucpasAct) {
		Query q = emCashback.createNamedQuery("Actor.findByCedrucpasAct");
		q.setParameter("cedrucpasAct", cedrucpasAct);
		try {
			return (Actor) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public boolean crearActorActorRolUsuairo(Actor actor, ActorRol actorRol)
			throws ExcGuardarRegistro {
		crearActor(actor);
		actorRol.setFecCreaArol(new Date());
		emCashback.persist(actorRol);

		Usuario usuario = new Usuario();
		usuario.setActor(actor);
		Perfil perfil = null;
		if (actorRol.getCatalogoGen().getRefCg()
				.compareTo(Globales.NIVEL_CONSUMIDOR) == 0) {
			perfil = SPerfil.recuperarPerfil(Globales.CODIGO_PERFIL_CONSUMIDOR);
		} else {
			perfil = SPerfil.recuperarPerfil(Globales.CODIGO_PERFIL_ACTOR);
		}
		usuario.setPerfil(perfil);
		usuario.setUsrEstado(Globales.EST_OK);
		usuario.setUsrCreadoPor(actor.getUsrCreaAct());
		usuario.setUsrNombre(actor.getCedrucpasAct());
		usuario.setUsrCambiaPassword("S");
		String iden = actor.getCedrucpasAct();
		String pwd = iden.substring(iden.length() - 4, iden.length());
		usuario.setUsrPassword(usuario.passwordToMd5(pwd));
		sUsuario.crearUsuario(usuario);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Actor> findAllByRolNegocioAndCategoria(CatalogoGen rolNegocio,
			String estadoArol, String catId, boolean recuperarHijos) {
		Query q = emCashback.createNamedQuery("Actor.findAllByRolNegocio");
		q.setParameter("rolNegocio", rolNegocio);
		q.setParameter("estadoArol", estadoArol + "%");
		List<Actor> actorList = (List<Actor>) q.getResultList();
		if (recuperarHijos) {
			List<Actor> actores = new ArrayList<Actor>();
			for (Actor actorPadre : actorList) {
				Query q2 = emCashback
						.createNamedQuery("Actor.findAllByPadreInRolNegocioAndCategoria");
				q2.setParameter("actor", actorPadre);
				q2.setParameter("catId", catId + "%");
				q2.setParameter("estadoArol", estadoArol + "%");
				List<Actor> actoresHijos = new ArrayList<Actor>();
				for (Actor actorHijo : (List<Actor>) q2.getResultList()) {
					actorHijo.getActorReferencias().size();
					actoresHijos.add(actorHijo);
				}
				actorPadre.setActoresHijos(actoresHijos);
				actores.add(actorPadre);

			}
			return actores;
		} else {
			return actorList;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Actor> findAllByCategoriaInHijosFromRolNegocio(
			CatalogoGen rolNegocio, String catId, String estadoAct,
			String estadoArol) {
		Query q = emCashback
				.createNamedQuery("Actor.findAllByCategoriaInHijosFromRolNegocio");
		q.setParameter("rolNegocio", rolNegocio);
		q.setParameter("catId", catId + "%");
		q.setParameter("estadoAct", estadoAct + "%");
		q.setParameter("estadoArol", estadoArol);
		List<Actor> actorList = (List<Actor>) q.getResultList();
		List<Actor> actores = new ArrayList<Actor>();
		for (Actor actorPadre : actorList) {
			Query q2 = emCashback
					.createNamedQuery("Actor.findAllByPadreInRolNegocioAndCategoria");
			q2.setParameter("actor", actorPadre);
			q2.setParameter("catId", catId + "%");
			q2.setParameter("estadoArol", estadoArol + "%");
			List<Actor> actoresHijos = new ArrayList<Actor>();
			for (Actor actorHijo : (List<Actor>) q2.getResultList()) {
				actorHijo.getActorReferencias().size();
				actoresHijos.add(actorHijo);
			}
			actorPadre.setActoresHijos(actoresHijos);
			actores.add(actorPadre);
		}
		return actores;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Actor> findAllByCedRucPasAndRazonSocialNombre(String cedRucPas,
			String razonSocialAct, String nombresAct, String apellidosAct,
			String estadoAct) {
		Query q = emCashback
				.createNamedQuery("Actor.findAllByCedRucPasAndRazonSocialNombre");
		q.setParameter("cedRucPas", cedRucPas + "%");
		q.setParameter("razonSocialAct", "%" + razonSocialAct + "%");
		q.setParameter("nombresAct", "%" + nombresAct + "%");
		q.setParameter("apellidosAct", "%" + apellidosAct + "%");
		q.setParameter("estadoAct", estadoAct + "%");
		return q.getResultList();
	}

	@Override
	public Actor findByIdAct(int idAct) {
		Actor actor = recuperarActor(idAct);
		if (actor != null) {
			actor.getActorReferencias().size();
		}
		return actor;
	}
}
