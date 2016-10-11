/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashback.servicios;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.ITransaccionesConsumidor;
import com.cashback.model.Persona;
import com.cashback.model.PuntosConsumidor;
import com.cashback.model.TransaccionesConsumidor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author somosGlobal
 */
@Stateless
public class STransaccionesConsumidor extends AbstractService implements ITransaccionesConsumidor{

    @Override
    public void crearTransaccion(TransaccionesConsumidor transaccion) throws ExcGuardarRegistro {
        try {
            emCashback.persist(transaccion);
	} catch (PersistenceException e) {
            e.printStackTrace();
            throw new ExcGuardarRegistro(AppMensajes.ERR_CREAR_REGISTRO);
	}
    }

    @Override
    public List<TransaccionesConsumidor> recuperarHistorialTransaccion(Persona persona) {
        String jpql = "SELECT p FROM TransaccionesConsumidor p WHERE p.consumidor =:persona";
	Query q = emCashback.createQuery(jpql);
	q.setParameter("consumidor", persona);
	@SuppressWarnings("unchecked")
	List<TransaccionesConsumidor> puntosList = (List<TransaccionesConsumidor>) q.getResultList();
	return puntosList;
    }
    
}
