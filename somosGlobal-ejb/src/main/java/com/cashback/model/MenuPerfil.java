package com.cashback.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the menu_perfil database table.
 * 
 */
@Entity
@Table(name="menu_perfil")
@NamedQuery(name="MenuPerfil.findAll", query="SELECT m FROM MenuPerfil m")
public class MenuPerfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mp_id")
	private int mpId;

	//bi-directional many-to-one association to Menu
	@ManyToOne
	@JoinColumn(name="men_id")
	private Menu menu;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn(name="prf_id")
	private Perfil perfil;

	public MenuPerfil() {
	}

	public int getMpId() {
		return this.mpId;
	}

	public void setMpId(int mpId) {
		this.mpId = mpId;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}