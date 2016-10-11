package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.cashback.model.ILocalidad;
import com.cashback.model.Localidad;

@Stateless
public class SLocalidad extends AbstractService implements ILocalidad {

	@SuppressWarnings("unchecked")
	@Override
	public List<Localidad> recuperarLocalidad(Localidad localidad) {
		Query q = null;
		if (localidad == null) {
			q = emCashback.createNamedQuery("Localidad.findByLocalidadOnlyPadre");
		} else {
			q = emCashback.createNamedQuery("Localidad.findByLocalidadPadre");
			q.setParameter("localidadPadre", localidad);
		}
		return q.getResultList();
	}

	@Override
	public Localidad recuperarLocalidad(int locId) {
		return emCashback.find(Localidad.class, locId);
	}

}
