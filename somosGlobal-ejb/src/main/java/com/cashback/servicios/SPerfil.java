package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.IPerfil;
import com.cashback.model.Perfil;

@Stateless
public class SPerfil extends AbstractService implements IPerfil {

	@Override
	public List<Perfil> recuperarPerfilList(String prfNombre) {
		String jpql = "SELECT p FROM Perfil p WHERE p.prfNombre LIKE :prfNombre ORDER BY p.prfNombre";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("prfNombre", "%" + prfNombre + "%");
		@SuppressWarnings("unchecked")
		List<Perfil> perfilList = (List<Perfil>) q.getResultList();
		return perfilList;
	}

	@Override
	public void crearPerfil(Perfil perfil) throws ExcGuardarRegistro {
		try {
			emCashback.persist(perfil);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_GUARDAR_REGISTRO,
					"Perfil");
		}
	}

	@Override
	public Perfil actualizarPerfil(Perfil perfil) throws ExcGuardarRegistro {
		if (recuperarPerfil(perfil.getPrfId()) == null) {
			throw new ExcGuardarRegistro(AppMensajes.ERR_GUARDAR_REGISTRO,
					perfil.toString());
		}
		try {
			return emCashback.merge(perfil);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_GUARDAR_REGISTRO,
					"Perfil");
		}
	}

	@Override
	public Perfil recuperarPerfil(int prfId) {
		return emCashback.find(Perfil.class, prfId);
	}

	@Override
	public List<Perfil> recuperarPerfilList2(String prfEstado) {
		String jql = "SELECT p FROM Perfil p WHERE p.prfEstado LIKE :prfEstado";
		Query q = emCashback.createQuery(jql);
		q.setParameter("prfEstado", prfEstado + "%");
		@SuppressWarnings("unchecked")
		List<Perfil> perfilList = (List<Perfil>) q.getResultList();
		return perfilList;
	}

	@Override
	public Perfil recuperarPerfil(String prfCodigo) {
		Query q = emCashback.createNamedQuery("Perfil.findByPrfCodigo");
		q.setParameter("prfCodigo", prfCodigo);
		try {
			return (Perfil) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

}
