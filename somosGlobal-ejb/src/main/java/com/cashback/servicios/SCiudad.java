package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.ICiudad;
import com.cashback.model.Ciudad;
import com.cashback.model.Provincia;

@Stateless
public class SCiudad extends AbstractService implements ICiudad {

	@Override
	public List<Ciudad> recuperarCiudadList(Provincia provincia) {
		String jpql = "SELECT c FROM Ciudad c WHERE c.provincia =:provincia ORDER BY c.ciuNombre";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("provincia", provincia);
		@SuppressWarnings("unchecked")
		List<Ciudad> ciudadList = (List<Ciudad>) q.getResultList();
		return ciudadList;
	}

	@Override
	public void crearCiudad(Ciudad ciudad) throws ExcGuardarRegistro {
		try {
			emCashback.persist(ciudad);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_CREAR_REGISTRO);
		}

	}

	@Override
	public void actualizarCiudad(Ciudad ciudad) throws ExcGuardarRegistro {
		try {
			if (emCashback.find(Ciudad.class, ciudad.getCiuId()) == null) {
				throw new ExcGuardarRegistro(AppMensajes.ERR_REGISTRO_NO_EXISTE);
			}
			emCashback.merge(ciudad);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_GUARDAR_REGISTRO);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ciudad> recuperarCiudadList() {
		String jpql = "SELECT c FROM Ciudad c ORDER BY c.ciuNombre";
		Query q = emCashback.createQuery(jpql);

		return (List<Ciudad>) q.getResultList();
	}

	@Override
	public Ciudad recuperarCiudad(int ciuId) {
		return emCashback.find(Ciudad.class, ciuId);
	}

}
