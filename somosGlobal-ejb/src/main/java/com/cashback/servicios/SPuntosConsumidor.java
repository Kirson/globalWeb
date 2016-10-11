/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashback.servicios;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.IPuntosConsumidor;
import com.cashback.model.Persona;
import com.cashback.model.PuntosConsumidor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author somosGlobal
 */
@Stateless
public class SPuntosConsumidor extends AbstractService implements IPuntosConsumidor{

    @Override
    public void crearPuntos(PuntosConsumidor puntos) throws ExcGuardarRegistro {
        try {
            emCashback.persist(puntos);
	} catch (PersistenceException e) {
            e.printStackTrace();
            throw new ExcGuardarRegistro(AppMensajes.ERR_CREAR_REGISTRO);
	}
    }

    @Override
    public List<PuntosConsumidor> recuperarPuntos(Persona persona) {
       String jpql = "SELECT p FROM PuntosConsumidor p WHERE p.persona =:persona";
	Query q = emCashback.createQuery(jpql);
	q.setParameter("persona", persona);
	@SuppressWarnings("unchecked")
	List<PuntosConsumidor> puntosList = (List<PuntosConsumidor>) q.getResultList();
	return puntosList;
    }
    
}
