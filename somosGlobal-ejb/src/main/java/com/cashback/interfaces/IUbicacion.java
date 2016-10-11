package com.cashback.interfaces;

import java.util.List;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.LocalVenta;
import com.cashback.model.Ubicacion;

public interface IUbicacion {
	List<Ubicacion> recuperarUbicacionList(LocalVenta localVenta);

	Ubicacion actualizarUbicacion(Ubicacion ubicacion)
			throws ExcGuardarRegistro;

	void crearUbicacion(Ubicacion ubicacion) throws ExcGuardarRegistro;
	
	Ubicacion recuperarUbicacion(int ubiId);
	
	void eliminarUbicacion(Ubicacion ubicacion);

}
