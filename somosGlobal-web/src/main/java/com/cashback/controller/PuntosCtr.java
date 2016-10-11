/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashback.controller;

import com.cashback.interfaces.ILocalVenta;
import com.cashback.interfaces.IPersona;
import com.cashback.interfaces.IPuntosConsumidor;
import com.cashback.interfaces.ITransaccionesConsumidor;
import com.cashback.model.CatalogoGen;
import com.cashback.model.ICatalogoGen;
import com.cashback.model.LocalVenta;
import com.cashback.model.Persona;
import com.cashback.model.PuntosConsumidor;
import com.cashback.model.TransaccionesConsumidor;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author somosGlobal
 */
@SessionScoped
@ManagedBean
public class PuntosCtr extends Controladores {
    
    private LocalVenta localVenta;
    private List<Persona> personas;
    private List<CatalogoGen> catalogo;
    private Double valorCompra;
    private PuntosConsumidor puntosConsumidor;
    private List<TransaccionesConsumidor> transacciones;
    private TransaccionesConsumidor transaccion;
    
    @EJB
    ITransaccionesConsumidor transaccionesEJB;
    
    @EJB
    IPersona personaEJB;
    
    @EJB
    ICatalogoGen catalogoEJB;
    
    @EJB
    IPuntosConsumidor puntosEJB;
    
    @EJB
    ILocalVenta localEJB;

    public LocalVenta getLocalVenta() {
        return localVenta;
    }

    public void setLocalVenta(LocalVenta localVenta) {
        this.localVenta = localVenta;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public List<CatalogoGen> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(List<CatalogoGen> catalogo) {
        this.catalogo = catalogo;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public PuntosConsumidor getPuntosConsumidor() {
        return puntosConsumidor;
    }

    public void setPuntosConsumidor(PuntosConsumidor puntosConsumidor) {
        this.puntosConsumidor = puntosConsumidor;
    }

    public List<TransaccionesConsumidor> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<TransaccionesConsumidor> transacciones) {
        this.transacciones = transacciones;
    }

    public TransaccionesConsumidor getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(TransaccionesConsumidor transaccion) {
        this.transaccion = transaccion;
    }
    
    
}
