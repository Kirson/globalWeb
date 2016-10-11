package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.IPersona;
import com.cashback.model.HobbiePersona;
import com.cashback.model.Persona;

@Stateless
public class SPersona extends AbstractService implements IPersona {

	@Override
	public List<Persona> recuperarPersonaList(String perNombres,
			String perApellidos, String perCedRucPas) {
		String jpql = "SELECT p FROM Persona p"
				+ " WHERE p.perNombres LIKE :perNombres"
				+ " AND p.perApellidos LIKE :perApellidos"
				+ " AND p.perCedRucPas LIKE :perCedRucPas"
				+ " ORDER BY p.perApellidos, p.perNombres";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("perNombres", "%" + perNombres + "%");
		q.setParameter("perApellidos", "%" + perApellidos + "%");
		q.setParameter("perCedRucPas", perCedRucPas + "%");
		@SuppressWarnings("unchecked")
		List<Persona> personaList = (List<Persona>) q.getResultList();
		return personaList;
	}

	@Override
	public void crearPersona(Persona persona) throws ExcGuardarRegistro {
		try {
			emCashback.persist(persona);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_CREAR_REGISTRO);
		}

	}

	@Override
	public Persona actualizarPersona(Persona persona) throws ExcGuardarRegistro {
		if (emCashback.find(Persona.class, persona.getPerId()) == null) {
			throw new ExcGuardarRegistro(AppMensajes.ERR_REGISTRO_NO_EXISTE);
		}
		try {
			emCashback.merge(persona);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_CREAR_REGISTRO);
		}
		return null;
	}

	@Override
	public Persona recuperarPersona(int id) {
		return emCashback.find(Persona.class, id);
	}

	@Override
	public void crearHobbiePersona(HobbiePersona hobbiePersona) {

	}

	@Override
	public List<HobbiePersona> recuperarHobbiePersonaList(Persona persona) {
		String jpql = "SELECT hp FROM HobbiePersona hp WHERE hp.persona =:persona"
				+ " ORDER BY hp.hobbie.hobNombre";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("persona", persona);
		@SuppressWarnings("unchecked")
		List<HobbiePersona> hobbiePersonaList = (List<HobbiePersona>) q
				.getResultList();
		return hobbiePersonaList;
	}

	@Override
	public HobbiePersona actualizarHobbiePersona(HobbiePersona hobbiePersona) {
		return emCashback.merge(hobbiePersona);
	}

	@Override
	public List<Persona> recuperarPersonaList(String perEstado) {
		String jql="SELECT p FROM Persona p WHERE p.perEstado =:perEstado ORDER BY p.perNombres";
		Query q = emCashback.createQuery(jql);
		q.setParameter("perEstado", perEstado);
		@SuppressWarnings("unchecked")
		List<Persona> personaList = (List<Persona>) q.getResultList();
		return personaList;
	}

}
