/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashback.interfaces;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.Persona;
import com.cashback.model.PuntosConsumidor;
import java.util.List;

/**
 *
 * @author somosGlobal
 */
public interface IPuntosConsumidor {
    
    void crearPuntos(PuntosConsumidor puntos) throws ExcGuardarRegistro;

    List<PuntosConsumidor> recuperarPuntos(Persona persona);
}
