/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashback.interfaces;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.Persona;
import com.cashback.model.TransaccionesConsumidor;
import java.util.List;

/**
 *
 * @author somosGlobal
 */
public interface ITransaccionesConsumidor {
    
    void crearTransaccion(TransaccionesConsumidor transaccion) throws ExcGuardarRegistro;

    List<TransaccionesConsumidor> recuperarHistorialTransaccion(Persona persona);
}
