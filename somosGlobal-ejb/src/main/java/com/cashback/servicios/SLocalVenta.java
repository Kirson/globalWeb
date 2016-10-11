package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.Globales;
import com.cashback.interfaces.ILocalVenta;
import com.cashback.model.HorarioAtencion;
import com.cashback.model.LocalPromocion;
import com.cashback.model.LocalVenta;
import com.cashback.model.Persona;

@Stateless
public class SLocalVenta extends AbstractService implements ILocalVenta {

	@Override
	public List<LocalVenta> recuperarLocalVenta() {
		String jpql = "SELECT lv FROM LocalVenta lv ORDER BY lv.razonSocial";
		Query q = emCashback.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<LocalVenta> localVentaList = (List<LocalVenta>) q.getResultList();
		return localVentaList;
	}

	@Override
	public void crearLocarVenta(LocalVenta localVenta)
			throws ExcGuardarRegistro {
		try {
			localVenta.setLvActividadPri(localVenta.getLvActividadPri().trim()
					.toUpperCase());
			localVenta.setLvNombreCom(localVenta.getLvNombreCom().trim()
					.toUpperCase());
			localVenta.setLvRazonSocial(localVenta.getLvRazonSocial().trim()
					.toUpperCase());
			emCashback.persist(localVenta);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_CREAR_REGISTRO);
		}

	}

	@Override
	public LocalVenta actualizarLocalVenta(LocalVenta localVenta)
			throws ExcGuardarRegistro {
		try {
			return emCashback.merge(localVenta);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_GUARDAR_REGISTRO);
		}

	}

	@Override
	public List<LocalVenta> recuperarLocalVentaList(String lvRuc,
			String lvNombreCom, String lvRazonSocial) {
		String jpql = "SELECT lv FROM LocalVenta lv"
				+ " WHERE lv.lvRuc LIKE :lvRuc"
				+ " AND lv.lvNombreCom LIKE :lvNombreCom"
				+ " AND lv.lvRazonSocial LIKE :lvRazonSocial"
				+ " ORDER BY lv.lvRazonSocial";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("lvRuc", "%" + lvRuc + "%");
		q.setParameter("lvNombreCom", "%" + lvNombreCom + "%");
		q.setParameter("lvRazonSocial", "%" + lvRazonSocial + "%");
		@SuppressWarnings("unchecked")
		List<LocalVenta> localVentaList = (List<LocalVenta>) q.getResultList();
		return localVentaList;
	}

	@Override
	public List<HorarioAtencion> recuperarHorarioAtencionList(
			LocalVenta localVenta) {
		String jpql = "SELECT ha FROM HorarioAtencion ha WHERE ha.codigoLocal =:localVenta";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("localVenta", localVenta);
		@SuppressWarnings("unchecked")
		List<HorarioAtencion> horarioAtencionList = (List<HorarioAtencion>) q
				.getResultList();
		return horarioAtencionList;
	}

	@Override
	public LocalVenta recuperarLocalVenta(int id) {
		return emCashback.find(LocalVenta.class, id);
	}

	@Override
	public List<LocalVenta> recuperaLocalVentaList(String catId,
			boolean cargarPromocionList, boolean cargarSucursalesList) {
		String jpql = "SELECT lv FROM LocalVenta lv WHERE lv.categoria.catId LIKE :catId";
		emCashback.flush();
		Query q = emCashback.createQuery(jpql);
		q.setParameter("catId", catId + "%");
		@SuppressWarnings("unchecked")
		List<LocalVenta> localVentaList = (List<LocalVenta>) q.getResultList();
		if (cargarPromocionList) {

			int i = 0;
			for (LocalVenta lv : localVentaList) {
				q = emCashback.createNamedQuery("LocalPromocion.findByTipo");
				q.setParameter("localVenta", lv);
				q.setParameter("lpTipo", Globales.IMG_TIPO_PROMO);
				@SuppressWarnings("unchecked")
				List<LocalPromocion> localPromocionPR = (List<LocalPromocion>) q
						.getResultList();

				q = emCashback.createNamedQuery("LocalPromocion.findByTipo");
				q.setParameter("localVenta", lv);
				q.setParameter("lpTipo", Globales.IMG_TIPO_BARRA);
				@SuppressWarnings("unchecked")
				List<LocalPromocion> localPromocionBR = (List<LocalPromocion>) q
						.getResultList();

				lv.setLocalPromocionList(localPromocionPR);
				lv.setLocalPromocionBRList(localPromocionBR);

				localVentaList.set(i, lv);
				i++;
			}
		}
		if (cargarSucursalesList) {
			for (LocalVenta lv : localVentaList) {
				@SuppressWarnings("unused")
				int t = lv.getUbicacions().size();
			}
		}
		return localVentaList;
	}

	@Override
	public List<LocalPromocion> recuperarLocalPromocionList(
			LocalVenta localVenta, String lpTipo, String lpEstado) {
		String jql = "SELECT lp FROM LocalPromocion lp"
				+ " WHERE lp.localVenta =:localVenta"
				+ " AND lp.lpEstado LIKE :lpEstado"
				+ " AND lp.lpTipo LIKE :lpTipo";
		Query q = emCashback.createQuery(jql);
		q.setParameter("localVenta", localVenta);
		q.setParameter("lpEstado", lpEstado + "%");
		q.setParameter("lpTipo", lpTipo + "%");
		@SuppressWarnings("unchecked")
		List<LocalPromocion> localPromocionList = (List<LocalPromocion>) q
				.getResultList();
		return localPromocionList;
	}

	@Override
	public List<LocalVenta> recuperarLocalVentaList(Persona persona) {
		String jql = "SELECT lv FROM LocalVenta lv WHERE lv.persona =:persona";
		Query q = emCashback.createQuery(jql);
		q.setParameter("persona", persona);
		@SuppressWarnings("unchecked")
		List<LocalVenta> localVentaList = (List<LocalVenta>) q.getResultList();
		return localVentaList;
	}

	@Override
	public List<LocalVenta> recuperarLocalVentaList(String lvPalabrasClave,
			String ubiPalabrasClave) {
		String jpql = "SELECT DISTINCT u.localVenta FROM Ubicacion u"
				+ " WHERE u.ubiPalabrasClave LIKE :ubiPalabrasClave"
				+ " AND u.localVenta.lvPalabrasClave LIKE :lvPalabrasClave";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("lvPalabrasClave", "%" + lvPalabrasClave + "%");
		q.setParameter("ubiPalabrasClave", "%" + ubiPalabrasClave + "%");
		@SuppressWarnings("unchecked")
		List<LocalVenta> localVentaList = (List<LocalVenta>) q.getResultList();
		int i = 0;
		for (LocalVenta lv : localVentaList) {
			q = emCashback.createNamedQuery("LocalPromocion.findByTipo");
			q.setParameter("localVenta", lv);
			q.setParameter("lpTipo", Globales.IMG_TIPO_PROMO);
			@SuppressWarnings("unchecked")
			List<LocalPromocion> localPromocionPR = (List<LocalPromocion>) q
					.getResultList();

			q = emCashback.createNamedQuery("LocalPromocion.findByTipo");
			q.setParameter("localVenta", lv);
			q.setParameter("lpTipo", Globales.IMG_TIPO_BARRA);
			@SuppressWarnings("unchecked")
			List<LocalPromocion> localPromocionBR = (List<LocalPromocion>) q
					.getResultList();

			lv.setLocalPromocionList(localPromocionPR);
			lv.setLocalPromocionBRList(localPromocionBR);

			localVentaList.set(i, lv);
			i++;
		}

		for (LocalVenta lv : localVentaList) {
			@SuppressWarnings("unused")
			int t = lv.getUbicacions().size();
		}

		return localVentaList;
	}

}
