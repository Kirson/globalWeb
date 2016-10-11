package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.IUbicacion;
import com.cashback.model.LocalVenta;
import com.cashback.model.Ubicacion;

@Stateless
public class SUbicacion extends AbstractService implements IUbicacion {

	@SuppressWarnings("unchecked")
	@Override
	public List<Ubicacion> recuperarUbicacionList(LocalVenta localVenta) {
		String jpql = "SELECT u FROM Ubicacion u WHERE u.localVenta =:localVenta";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("localVenta", localVenta);
		return (List<Ubicacion>) q.getResultList();
	}

	@Override
	public Ubicacion actualizarUbicacion(Ubicacion ubicacion)
			throws ExcGuardarRegistro {
		Ubicacion u = emCashback.find(Ubicacion.class, ubicacion.getUbiId());
		if (u == null) {
			throw new ExcGuardarRegistro(AppMensajes.ERR_REGISTRO_NO_EXISTE,
					"Ubicacion");
		}
		// ubicacion.setVersion(u.getVersion());
		try {
			return emCashback.merge(ubicacion);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_GUARDAR_REGISTRO,
					"Ubicacion");
		}
	}

	@Override
	public void crearUbicacion(Ubicacion ubicacion) throws ExcGuardarRegistro {
		try {
			emCashback.persist(ubicacion);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_CREAR_REGISTRO,
					"Ubicacion");
		}
	}

	@Override
	public Ubicacion recuperarUbicacion(int ubiId) {
		return emCashback.find(Ubicacion.class, ubiId);
	}

	@Override
	public void eliminarUbicacion(Ubicacion ubicacion) {
		emCashback.remove(emCashback.merge(ubicacion));

	}

}
