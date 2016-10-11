package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.IContacto;
import com.cashback.model.Contacto;
import com.cashback.model.Persona;

@Stateless
public class SContacto extends AbstractService implements IContacto {

	@Override
	public void crearContacto(Contacto contacto) throws ExcGuardarRegistro {
		try {
			emCashback.persist(contacto);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_CREAR_REGISTRO,
					"Contacto");
		}
	}

	@Override
	public Contacto actualizarContacto(Contacto contacto)
			throws ExcGuardarRegistro {
		if (emCashback.find(Contacto.class, contacto.getConId()) == null) {
			throw new ExcGuardarRegistro(AppMensajes.ERR_REGISTRO_YA_EXISTE);
		}
		try {
			return emCashback.merge(contacto);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_GUARDAR_REGISTRO,
					"Contacto");
		}
	}

	@Override
	public List<Contacto> recuperarContactoList(Persona persona) {
		String jpql = "SELECT c FROM Contacto c WHERE c.persona =:persona";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("persona", persona);
		@SuppressWarnings("unchecked")
		List<Contacto> contactoList = (List<Contacto>) q.getResultList();
		return contactoList;
	}

}
