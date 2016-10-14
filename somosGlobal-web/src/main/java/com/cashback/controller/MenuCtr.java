package com.cashback.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.cashback.interfaces.IMenu;
import com.cashback.interfaces.IMenuPerfil;
import com.cashback.model.Menu;
import com.cashback.model.Usuario;

@SessionScoped
@ManagedBean
public class MenuCtr {
	private MenuModel menuModel;
	private Usuario usuario;
	@EJB
	private IMenuPerfil sMenuPerfil;
	@EJB
	private IMenu sMenu;

	public MenuCtr() {
		usuario = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuario");
	}

	@PostConstruct
	public void inicio() {
		recuperarMenuList();
	}

	public String recuperarMenuList() {
		menuModel = new DefaultMenuModel();
		// First submenu
		DefaultSubMenu firstSubmenu = new DefaultSubMenu("Mantenimiento");

		Menu menu01 = sMenu.recuperarMenu("MANTENIMIENTO:PERSONAS");
		DefaultMenuItem item01 = new DefaultMenuItem("Personas");
		item01.setUrl(menu01.getMenUrl());
		item01.setIcon("ui-icon-person");
		item01.setRendered(sMenuPerfil.recuperarMenu(usuario.getPerfil(),
				menu01) != null);
		firstSubmenu.addElement(item01);

		Menu menu02 = sMenu.recuperarMenu("MANTENIMIENTO:CATEGORIA");
		DefaultMenuItem item02 = new DefaultMenuItem("Categoria");
		item02.setUrl(sMenu.recuperarMenu("MANTENIMIENTO:CATEGORIA")
				.getMenUrl());
		item02.setIcon("ui-icon-lightbulb");
		item02.setRendered(sMenuPerfil.recuperarMenu(usuario.getPerfil(),
				menu02) != null);
		firstSubmenu.addElement(item02);

		Menu menu03 = sMenu.recuperarMenu("MANTENIMIENTO:LOCALVENTA");
		DefaultMenuItem item03 = new DefaultMenuItem("Locales");
		item03.setUrl(sMenu.recuperarMenu("MANTENIMIENTO:LOCALVENTA")
				.getMenUrl());
		item03.setRendered(sMenuPerfil.recuperarMenu(usuario.getPerfil(),
				menu03) != null);
		item03.setIcon("ui-icon-home");
		firstSubmenu.addElement(item03);

		Menu menu04 = sMenu.recuperarMenu("MANTENIMIENTO:HOBBIE");
		DefaultMenuItem item04 = new DefaultMenuItem("Hobbie");
		item04.setUrl(sMenu.recuperarMenu("MANTENIMIENTO:HOBBIE").getMenUrl());
		item04.setIcon("ui-icon-video");
		item04.setRendered(sMenuPerfil.recuperarMenu(usuario.getPerfil(),
				menu04) != null);
		firstSubmenu.addElement(item04);

		Menu menu05 = sMenu.recuperarMenu("MANTENIMIENTO:PROVINCIA");
		DefaultMenuItem item05 = new DefaultMenuItem("Provincia");
		item05.setUrl(sMenu.recuperarMenu("MANTENIMIENTO:PROVINCIA")
				.getMenUrl());
		item05.setIcon("ui-icon-image");
		item05.setRendered(sMenuPerfil.recuperarMenu(usuario.getPerfil(),
				menu05) != null);
		firstSubmenu.addElement(item05);

		Menu menu06 = sMenu.recuperarMenu("MANTENIMIENTO:USUARIO");
		DefaultMenuItem item06 = new DefaultMenuItem("Usuario");
		item06.setUrl(sMenu.recuperarMenu("MANTENIMIENTO:USUARIO").getMenUrl());
		item06.setIcon("ui-icon-image");
		item06.setRendered(sMenuPerfil.recuperarMenu(usuario.getPerfil(),
				menu06) != null);
		firstSubmenu.addElement(item06);

		Menu menu07 = sMenu.recuperarMenu("MANTENIMIENTO:ACTOR");
		DefaultMenuItem item07 = new DefaultMenuItem("Actor");
		item07.setUrl(menu07.getMenUrl());
		item07.setIcon("ui-icon-image");
		item07.setRendered(sMenuPerfil.recuperarMenu(usuario.getPerfil(),
				menu07) != null);
		firstSubmenu.addElement(item07);

		DefaultSubMenu consultasSubmenu = new DefaultSubMenu("Consultas");
		
		Menu men08 = sMenu.recuperarMenu("CONSULTAS:CADENAVALOR");
		DefaultMenuItem item09 = new DefaultMenuItem("Cadena de Valor");
		item09.setUrl(men08.getMenUrl());
		item09.setIcon("ui-icon-person");
		item09.setRendered(sMenuPerfil.recuperarMenu(usuario.getPerfil(),
				men08) != null);
		consultasSubmenu.addElement(item09);
		
		Menu men10 = sMenu.recuperarMenu("CONSULTAS:ACTOR");
		DefaultMenuItem item10 = new DefaultMenuItem("Actor");
		item10.setUrl(men10.getMenUrl());
		item10.setIcon("ui-icon-person");
		item10.setRendered(sMenuPerfil.recuperarMenu(usuario.getPerfil(),
				men10) != null);
		consultasSubmenu.addElement(item10);
                
                Menu men11 = sMenu.recuperarMenu("CONSULTAS:PUNTOS");
		DefaultMenuItem item11 = new DefaultMenuItem("Actor");
		item11.setUrl(men10.getMenUrl());
		item11.setIcon("ui-icon-person");
		item11.setRendered(sMenuPerfil.recuperarMenu(usuario.getPerfil(),
				men11) != null);
		consultasSubmenu.addElement(item11);
                
                Menu men12 = sMenu.recuperarMenu("CONSULTAS:PUNTOSDEMO");
		DefaultMenuItem item12 = new DefaultMenuItem("Actor");
		item12.setUrl(men10.getMenUrl());
		item12.setIcon("ui-icon-person");
		item12.setRendered(sMenuPerfil.recuperarMenu(usuario.getPerfil(),
				men12) != null);
		consultasSubmenu.addElement(item12);
		
		menuModel.addElement(firstSubmenu);
		menuModel.addElement(consultasSubmenu);
		return null;
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}
}
