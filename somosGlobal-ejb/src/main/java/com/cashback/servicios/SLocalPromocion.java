package com.cashback.servicios;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.ILocalPromocion;
import com.cashback.model.LocalPromocion;
import com.cashback.model.LocalVenta;

@Stateless
public class SLocalPromocion extends AbstractService implements ILocalPromocion {

	@Override
	public List<LocalPromocion> recuperarLocalPromocionList(
			LocalVenta localVenta, String lpTipo, String lpEstado) {
		String jql = "SELECT lp FROM LocalPromocion lp"
				+ " WHERE lp.localVenta =:localVenta"
				+ " AND lp.lpEstado LIKE :lpEstado"
				+ " AND lp.lpTipo LIKE :lpTipo"
				+ " ORDER BY lp.lpTipo, lp.lpImagen";
		Query q = emCashback.createQuery(jql);
		q.setParameter("localVenta", localVenta);
		q.setParameter("lpTipo", lpTipo + "%");
		q.setParameter("lpEstado", lpEstado + "%");
		@SuppressWarnings("unchecked")
		List<LocalPromocion> localPromocionList = (List<LocalPromocion>) q
				.getResultList();
		return localPromocionList;
	}

	@Override
	public LocalPromocion actualizarLocalPromocion(LocalPromocion localPromocion)
			throws ExcGuardarRegistro {
		if (emCashback.find(LocalPromocion.class, localPromocion.getLpId()) == null) {
			throw new ExcGuardarRegistro(AppMensajes.ERR_REGISTRO_NO_EXISTE,
					"Local Promocion");
		}
		try {
			return emCashback.merge(localPromocion);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_GUARDAR_REGISTRO,
					"Local Promocion");
		}

	}

	@Override
	public void crearLocalPromocion(LocalPromocion localPromocion)
			throws ExcGuardarRegistro {
		if (emCashback.find(LocalPromocion.class, localPromocion.getLpId()) != null) {
			throw new ExcGuardarRegistro(AppMensajes.ERR_REGISTRO_YA_EXISTE,
					"Local Promocion");
		}
		try {
			emCashback.persist(localPromocion);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_CREAR_REGISTRO,
					"Local Promocion");
		}

	}

	@Override
	public void eliminarLocalPromocion(LocalPromocion localPromocion) {
		emCashback.remove(emCashback.merge(localPromocion));
	}

}
