package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


import com.cashback.interfaces.IPuntos;
import com.cashback.model.Persona;
import com.cashback.model.Puntos;


@Stateless
public class SPuntos extends AbstractService implements IPuntos {

	@Override
	public List<Puntos> recuperarPuntos(Persona persona) {
		String jpql = "SELECT p FROM Puntos p WHERE p.persona =:persona";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("persona", persona);
		@SuppressWarnings("unchecked")
		List<Puntos> puntosList = (List<Puntos>) q.getResultList();
		return puntosList;
	}

	@Override
	public void crearPuntos(Puntos puntos) {
		// TODO Auto-generated method stub
	}

}
