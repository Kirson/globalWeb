package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.ICategoria;
import com.cashback.model.Categoria;

@Stateless
public class SCategoria extends AbstractService implements ICategoria {

	@Override
	public List<Categoria> recuperarCategoriaList(String catEstado) {
		String jpql = "SELECT c FROM Categoria c WHERE c.catEstado =:catEstado ORDER BY c.catId, c.catNombre";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("catEstado", catEstado);
		@SuppressWarnings("unchecked")
		List<Categoria> categoriaList = (List<Categoria>) q.getResultList();
		return categoriaList;
	}

	@Override
	public void crearCategoria(Categoria categoria) throws ExcGuardarRegistro {
		if (emCashback.find(Categoria.class, categoria.getCatId()) != null) {
			throw new ExcGuardarRegistro(AppMensajes.ERR_REGISTRO_YA_EXISTE,
					"Categoria" + categoria.getCatNombre());
		}
		try {
			emCashback.persist(categoria);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_GUARDAR_REGISTRO,
					"Categoria");
		}
	}

	@Override
	public Categoria actualizarCategoria(Categoria categoria)
			throws ExcGuardarRegistro {
		try {
			return emCashback.merge(categoria);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_GUARDAR_REGISTRO,
					"Categoria");
		}

	}

	@Override
	public List<Categoria> recuperarCategoriaList() {
		String jpql = "SELECT c FROM Categoria c ORDER BY c.catId, c.catNombre";
		Query q = emCashback.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Categoria> categoriaList = (List<Categoria>) q.getResultList();
		return categoriaList;

	}

	@Override
	public Categoria recuperarCategoria(String catId) {
		return emCashback.find(Categoria.class, catId);
	}

	@Override
	public List<Categoria> recuperarCategoriaList(String catId, String catEstado) {
		Query q = emCashback.createNamedQuery("Categoria.findByIdList");
		q.setParameter("catId", catId + "%");
		q.setParameter("catEstado", catEstado + "%");
		@SuppressWarnings("unchecked")
		List<Categoria> categoriaList = (List<Categoria>) q.getResultList();
		return categoriaList;
	}
}
