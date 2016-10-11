package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.cashback.interfaces.ILicencia;
import com.cashback.model.Licencia;
@Stateless
public class SLicencia extends AbstractService implements ILicencia {

	@SuppressWarnings("unchecked")
	@Override
	public List<Licencia> recuperarLicenciaList() {
		String jpql = "SELECT lic FROM Licencia lic WHERE lic.estadoLicencia =1 ORDER BY lic.nombreLicencia";
		Query q = emCashback.createQuery(jpql);
		return (List<Licencia>) q.getResultList();
	}

	@Override
	public Licencia recuperarLicencia(int id) {
		return emCashback.find(Licencia.class, id);
	}

}
