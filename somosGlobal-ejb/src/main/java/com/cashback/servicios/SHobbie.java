package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.IHobbie;
import com.cashback.model.Hobbie;

@Stateless
public class SHobbie extends AbstractService implements IHobbie {

	@Override
	public void crearHobbie(Hobbie hobbie) throws ExcGuardarRegistro {
		try {
			emCashback.persist(hobbie); 
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_CREAR_REGISTRO);
		}
	}

	@Override
	public List<Hobbie> recuperarHobbieList(String hobEstado) {
		String jpql = "SELECT h FROM Hobbie h WHERE h.hobEstado =:hobEstado ORDER BY h.hobEstado";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("hobEstado", hobEstado);
		@SuppressWarnings({ "unchecked" })
		List<Hobbie> hobbieList = (List<Hobbie>) q.getResultList();
		return hobbieList;
	}

	@Override
	public Hobbie actualizar(Hobbie hobbie) throws ExcGuardarRegistro {
		try {
			if (emCashback.find(Hobbie.class, hobbie.getHobId()) == null) {
				throw new ExcGuardarRegistro(AppMensajes.ERR_REGISTRO_NO_EXISTE);
			}
			return emCashback.merge(hobbie);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_GUARDAR_REGISTRO);
		}
	}

	@Override
	public List<Hobbie> recuperarHobbieList() {
		String jpql = "SELECT h FROM Hobbie h WHERE h.hobEstado ORDER BY h.hobNombre";
		Query q = emCashback.createQuery(jpql);
		@SuppressWarnings({ "unchecked" })
		List<Hobbie> hobbieList = (List<Hobbie>) q.getResultList();
		return hobbieList;
	}

	@Override
	public Hobbie recuperarHobbie(int codigo) {
		return emCashback.find(Hobbie.class, codigo);
	}

}
