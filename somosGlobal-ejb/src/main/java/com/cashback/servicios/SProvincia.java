package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.IProvincia;
import com.cashback.model.Provincia;

@Stateless
public class SProvincia extends AbstractService implements IProvincia {

	@Override
	public List<Provincia> recuperarProvinciaList() {
		String jpql = "SELECT p FROM Provincia p ORDER BY p.prvNombre";
		Query q = emCashback.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Provincia> provinciaList = (List<Provincia>) q.getResultList();
		return provinciaList;
	}

	@Override
	public void crearProvincia(Provincia provincia) throws ExcGuardarRegistro {
		try {
			emCashback.persist(provincia);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_CREAR_REGISTRO);
		}
	}

	@Override
	public Provincia actualizarProvincia(Provincia provincia)
			throws ExcGuardarRegistro {
		try {
			if (emCashback.find(Provincia.class, provincia.getPrvId()) == null) {
				throw new ExcGuardarRegistro(AppMensajes.ERR_REGISTRO_NO_EXISTE);
			}
			return emCashback.merge(provincia);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_GUARDAR_REGISTRO);
		}
	}

}
