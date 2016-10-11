package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.IMenu;
import com.cashback.model.Menu;
import com.cashback.model.Perfil;

@Stateless
public class SMenu extends AbstractService implements IMenu {

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> recuperarMenu(Perfil perfil) {
		String jpql = "SELECT m FROM Menu m WHERE m.perfil =:perfil";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("perfil", perfil);
		List<Menu> menuList = (List<Menu>) q.getResultList();
		return menuList;
	}

	@Override
	public void crearMenu(Menu menu) throws ExcGuardarRegistro {
		try {
			emCashback.persist(menu);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_CREAR_REGISTRO, "Menu");
		}
	}

	@Override
	public Menu actualizarMenu(Menu menu) throws ExcGuardarRegistro {
		if (emCashback.find(Menu.class, menu.getMenId()) == null) {
			throw new ExcGuardarRegistro(AppMensajes.ERR_REGISTRO_NO_EXISTE,
					"Menu");
		}
		try {
			return emCashback.merge(menu);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_GUARDAR_REGISTRO,
					"Menu");
		}
	}

	@Override
	public Menu recuperarMenu(String menNombre) {
		String jql = "SELECT m FROM Menu m WHERE m.menNombre =:menNombre";
		Query q = emCashback.createQuery(jql);
		q.setParameter("menNombre", menNombre);
		try {
			return (Menu) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
