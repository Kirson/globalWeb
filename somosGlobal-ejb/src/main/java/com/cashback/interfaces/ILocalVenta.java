package com.cashback.interfaces;

import java.util.List;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.model.HorarioAtencion;
import com.cashback.model.LocalPromocion;
import com.cashback.model.LocalVenta;
import com.cashback.model.Persona;

public interface ILocalVenta {
	List<LocalVenta> recuperarLocalVenta();

	void crearLocarVenta(LocalVenta localVenta) throws ExcGuardarRegistro;

	LocalVenta actualizarLocalVenta(LocalVenta localVenta)
			throws ExcGuardarRegistro;

	List<LocalVenta> recuperarLocalVentaList(String lvRuc, String lvNombreCom,
			String lvRazonSocial);

	List<HorarioAtencion> recuperarHorarioAtencionList(LocalVenta localVenta);

	LocalVenta recuperarLocalVenta(int id);

	List<LocalVenta> recuperaLocalVentaList(String catId, boolean cargarPromocionList, boolean cargarSucursalesList);

	List<LocalPromocion> recuperarLocalPromocionList(LocalVenta localVenta,
			String lpTipo, String lpEstado);

	List<LocalVenta> recuperarLocalVentaList(Persona persona);
	
	List<LocalVenta> recuperarLocalVentaList(String lvPalabrasClave, String ubiPalabrasClave);

}
