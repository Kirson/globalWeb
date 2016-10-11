package com.cashback.interfaces;

import java.util.List;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.LocalPromocion;
import com.cashback.model.LocalVenta;

public interface ILocalPromocion {
	List<LocalPromocion> recuperarLocalPromocionList(LocalVenta localVenta,
			String lpTipo, String lpEstado);

	LocalPromocion actualizarLocalPromocion(LocalPromocion localPromocion)
			throws ExcGuardarRegistro;

	void crearLocalPromocion(LocalPromocion localPromocion)
			throws ExcGuardarRegistro;

	void eliminarLocalPromocion(LocalPromocion localPromocion);
}
