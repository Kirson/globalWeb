package com.cashback.servicios;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.cashback.interfaces.IAppmensajes;
import com.cashback.model.Appmensaje;

@Stateless
public class SAppmensaje extends AbstractService implements IAppmensajes {

	@Override
	public Appmensaje recuperarAppmensaje(String menCodigo) {
		Appmensaje appmensaje= new Appmensaje();
		try {
			String jpql = "SELECT a FROM Appmensaje a WHERE a.menCodigo =:menCodigo";
			Query q = emCashback.createQuery(jpql);
			q.setParameter("menCodigo", menCodigo);
			return (Appmensaje) q.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return appmensaje;
		}
	}
}
