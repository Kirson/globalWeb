/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.global;

import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class PuntosDemoCtrl {
    
    int puntos = 0;
    String seleccion = null;
    Map<String, String> usuarios = null;
    
    
    public PuntosDemoCtrl(){
        usuarios = new HashMap<String, String>();
        usuarios.put("juan","1");
        usuarios.put("pedro","2");
        usuarios.put("pablo","3");
        usuarios.put("andres","4");
    }
    
    public void anadirPuntos(){
        System.err.println("selected: "+seleccion +" - puntoos:"+puntos);
    }

    public int getPuntos(){
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }

    public Map<String, String> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Map<String, String> usuarios) {
        this.usuarios = usuarios;
    }
    
    
}
