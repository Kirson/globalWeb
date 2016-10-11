package com.cashback.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@Table(name="menu")
@NamedQuery(name="Menu.findAll", query="SELECT m FROM Menu m")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="men_id")
	private int menId;

	@Column(name="men_estado")
	private String menEstado;

	@Column(name="men_nombre")
	private String menNombre;

	@Column(name="men_tipo")
	private String menTipo;

	@Column(name="men_url")
	private String menUrl;

	//bi-directional many-to-one association to MenuPerfil
	@OneToMany(mappedBy="menu")
	private List<MenuPerfil> menuPerfils;

	public Menu() {
	}

	public int getMenId() {
		return this.menId;
	}

	public void setMenId(int menId) {
		this.menId = menId;
	}

	public String getMenEstado() {
		return this.menEstado;
	}

	public void setMenEstado(String menEstado) {
		this.menEstado = menEstado;
	}

	public String getMenNombre() {
		return this.menNombre;
	}

	public void setMenNombre(String menNombre) {
		this.menNombre = menNombre;
	}

	public String getMenTipo() {
		return this.menTipo;
	}

	public void setMenTipo(String menTipo) {
		this.menTipo = menTipo;
	}

	public String getMenUrl() {
		return this.menUrl;
	}

	public void setMenUrl(String menUrl) {
		this.menUrl = menUrl;
	}

	public List<MenuPerfil> getMenuPerfils() {
		return this.menuPerfils;
	}

	public void setMenuPerfils(List<MenuPerfil> menuPerfils) {
		this.menuPerfils = menuPerfils;
	}

	public MenuPerfil addMenuPerfil(MenuPerfil menuPerfil) {
		getMenuPerfils().add(menuPerfil);
		menuPerfil.setMenu(this);

		return menuPerfil;
	}

	public MenuPerfil removeMenuPerfil(MenuPerfil menuPerfil) {
		getMenuPerfils().remove(menuPerfil);
		menuPerfil.setMenu(null);

		return menuPerfil;
	}

}