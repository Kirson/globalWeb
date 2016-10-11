package com.cashback.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the perfil database table.
 * 
 */
@Entity
@Table(name = "perfil")
@NamedQueries({
		@NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p"),
		@NamedQuery(name = "Perfil.findByPrfCodigo", query = "SELECT p FROM Perfil p WHERE p.prfCodigo =:prfCodigo")

})
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prf_id")
	private int prfId;

	@Column(name = "prf_estado")
	private String prfEstado;

	@Column(name = "prf_nombre")
	private String prfNombre;

	@Column(name = "prf_codigo")
	private String prfCodigo;

	// bi-directional many-to-one association to MenuPerfil
	@OneToMany(mappedBy = "perfil")
	private List<MenuPerfil> menuPerfils;

	// bi-directional many-to-one association to LocalVenta
	@OneToMany(mappedBy = "perfil")
	private List<Usuario> usuarioList;

	public Perfil() {
	}

	public int getPrfId() {
		return this.prfId;
	}

	public void setPrfId(int prfId) {
		this.prfId = prfId;
	}

	public String getPrfEstado() {
		return this.prfEstado;
	}

	public void setPrfEstado(String prfEstado) {
		this.prfEstado = prfEstado;
	}

	public String getPrfNombre() {
		return this.prfNombre;
	}

	public void setPrfNombre(String prfNombre) {
		this.prfNombre = prfNombre;
	}

	public List<MenuPerfil> getMenuPerfils() {
		return this.menuPerfils;
	}

	public void setMenuPerfils(List<MenuPerfil> menuPerfils) {
		this.menuPerfils = menuPerfils;
	}

	public MenuPerfil addMenuPerfil(MenuPerfil menuPerfil) {
		getMenuPerfils().add(menuPerfil);
		menuPerfil.setPerfil(this);

		return menuPerfil;
	}

	public MenuPerfil removeMenuPerfil(MenuPerfil menuPerfil) {
		getMenuPerfils().remove(menuPerfil);
		menuPerfil.setPerfil(null);

		return menuPerfil;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public String getPrfCodigo() {
		return prfCodigo;
	}

	public void setPrfCodigo(String prfCodigo) {
		this.prfCodigo = prfCodigo;
	}

}