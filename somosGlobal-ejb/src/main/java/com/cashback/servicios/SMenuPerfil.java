package com.cashback.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.cashback.interfaces.IMenuPerfil;
import com.cashback.model.Menu;
import com.cashback.model.Perfil;

@Stateless
public class SMenuPerfil extends AbstractService implements IMenuPerfil {

	@Override
	public List<Menu> recuperarMenuList(Perfil perfil) {
		String jql = "SELECT mp.menu FROM MenuPerfil mp WHERE mp.perfil =:perfil";
		Query q = emCashback.createQuery(jql);
		q.setParameter("perfil", perfil);
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>) q.getResultList();
		return menuList;
	}

	@Override
	public Menu recuperarMenu(Perfil perfil, Menu menu) {
		String jql="SELECT mp.menu FROM MenuPerfil mp WHERE mp.perfil =:perfil AND mp.menu =:menu";
		Query q = emCashback.createQuery(jql);
		q.setParameter("perfil", perfil);
		q.setParameter("menu", menu);
		try {
			return (Menu) q.getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}
}
