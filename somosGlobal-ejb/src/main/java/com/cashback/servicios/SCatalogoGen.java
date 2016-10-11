package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.cashback.interfaces.Globales;
import com.cashback.model.CatalogoGen;
import com.cashback.model.ICatalogoGen;

@Stateless
public class SCatalogoGen extends AbstractService implements ICatalogoGen {

	@SuppressWarnings("unchecked")
	@Override
	public List<CatalogoGen> recuperarCatalogoGenList(String tipoCg) {
		Query q = null;
		if (tipoCg.compareTo(Globales.DIAS_TIPO_CATALOGO) == 0) {
			q = emCashback
					.createNamedQuery("CatalogoGen.findByTipoCgOrderRefCg");
		} else {
			q = emCashback
					.createQuery("SELECT cg FROM CatalogoGen cg WHERE cg.tipoCg LIKE :tipoCg ORDER BY cg.nombreCg");
		}
		q.setParameter("tipoCg", tipoCg + "%");
		return q.getResultList();
	}

	@Override
	public CatalogoGen recuperarCatalogoGen(String tipoCg, String refCg) {
		Query q = emCashback.createNamedQuery("CatalogoGen.findByTipoRef");
		q.setParameter("tipoCg", tipoCg);
		q.setParameter("refCg", refCg);
		@SuppressWarnings("unchecked")
		List<CatalogoGen> catalogoGen = (List<CatalogoGen>) q.getResultList();
		if (catalogoGen.size() > 0) {
			return catalogoGen.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CatalogoGen> recuperarCatalogoGenList(String tipoCg,
			String ref02Cg) {
		Query q = emCashback.createNamedQuery("CatalogoGen.findByTipoRef02");
		q.setParameter("tipoCg", tipoCg);
		q.setParameter("ref02Cg", ref02Cg);
		return (List<CatalogoGen>) q.getResultList();
	}

	@Override
	public CatalogoGen recuperarCatalogoGen(int idCg) {
		return emCashback.find(CatalogoGen.class, idCg);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CatalogoGen> recuperarCatalogoGenList02(
			CatalogoGen catalogoGen, String tipoCg) {
		Query q = null;
		if (catalogoGen == null) {
			q = emCashback.createNamedQuery("CatalogoGen.findOnlyFather");
			q.setParameter("tipoCg", tipoCg);
		} else {
			q = emCashback.createNamedQuery("CatalogoGen.findByFather");
			q.setParameter("catalogoGen", catalogoGen);
			q.setParameter("tipoCg", tipoCg + "%");
		}
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CatalogoGen> findAllByFather(CatalogoGen padreCatalogoGen) {
		Query q = emCashback.createNamedQuery("CatalogoGen.findAllByFather");
		q.setParameter("padreCatalogoGen", padreCatalogoGen);
		return q.getResultList();
	}

	@Override
	public CatalogoGen findByTipoCg(String tipoCg) {
		Query q = emCashback.createNamedQuery("CatalogoGen.findByTipoCg");
		q.setParameter("tipoCg", tipoCg);
		try {
			return (CatalogoGen) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
