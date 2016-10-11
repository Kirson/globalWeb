package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.IBarrio;
import com.cashback.model.Barrio;
import com.cashback.model.Ciudad;

@Stateless
public class SBarrio extends AbstractService implements IBarrio {

	@Override
	public List<Barrio> recuperarBarrioList(Ciudad ciudad) {
		String jpql = "SELECT b FROM Barrio b WHERE b.ciudad =:ciudad ORDER BY b.barNombre";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("ciudad", ciudad);
		@SuppressWarnings("unchecked")
		List<Barrio> barrioList = (List<Barrio>) q.getResultList();
		return barrioList;
	}

	@Override
	public void crearBarrio(Barrio barrio) throws ExcGuardarRegistro {
		try {
			emCashback.persist(barrio);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_CREAR_REGISTRO);
		}

	}

	@Override
	public void actualizarBarrio(Barrio barrio) throws ExcGuardarRegistro {
		try {
			if (emCashback.find(Ciudad.class, barrio.getBarId()) == null) {
				throw new ExcGuardarRegistro(AppMensajes.ERR_REGISTRO_NO_EXISTE);
			}
			emCashback.merge(barrio);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_GUARDAR_REGISTRO);
		}
	}

	
	@Override
	public Barrio recuperarBarrio(int barId) {
		return emCashback.find(Barrio.class, barId);
	}

}
