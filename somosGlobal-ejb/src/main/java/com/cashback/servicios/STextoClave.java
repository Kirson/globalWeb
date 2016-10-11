package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.ITextoClave;
import com.cashback.model.TextoClave;

@Stateless
public class STextoClave extends AbstractService implements ITextoClave {

	@Override
	public List<TextoClave> recuperarTextoClaveList(String tcTexto,
			String tcTipo) {
		String jpql = "SELECT tc FROM TextoClave tc WHERE tc.tcTexto LIKE :tcTexto AND tc.tcTipo LIKE :tcTipo";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("tcTexto", "%" + tcTexto + "%");
		q.setParameter("tcTipo", tcTipo + "%");
		@SuppressWarnings("unchecked")
		List<TextoClave> textoClaveList = (List<TextoClave>) q.getResultList();
		return textoClaveList;
	}

	public void crearTextoClave(TextoClave textoClave)
			throws ExcGuardarRegistro {
		TextoClave tc = emCashback.find(TextoClave.class, textoClave.getTcId());
		if (tc != null) {
			throw new ExcGuardarRegistro(AppMensajes.ERR_REGISTRO_YA_EXISTE,
					"Texto Clave");
		}
		String jpql = "SELECT tc FROM TextoClave tc WHERE tc.tcTexto =:tcTexto AND tc.tcTipo =:tcTipo";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("tcTexto", textoClave.getTcTexto());
		q.setParameter("tcTipo", textoClave.getTcTipo());
		try {
			q.getSingleResult();
		} catch (NoResultException e) {
			textoClave.setTcTexto(textoClave.getTcTexto().trim());
			emCashback.persist(textoClave);
		}

	}

}
