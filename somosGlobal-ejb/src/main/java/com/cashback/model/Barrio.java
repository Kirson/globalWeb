package com.cashback.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the barrio database table.
 * 
 */
@Entity
@Table(name = "barrio")
@NamedQuery(name = "Barrio.findAll", query = "SELECT b FROM Barrio b")
public class Barrio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bar_id")
	private int barId;

	@Column(name = "bar_nombre")
	private String barNombre;

	@ManyToOne
	@JoinColumn(name = "ciu_id")
	private Ciudad ciudad;

	// bi-directional many-to-one association to Ubicacion
	@OneToMany(mappedBy = "barrio")
	private List<Ubicacion> ubicacions;

	public Barrio() {
	}

	public int getBarId() {
		return this.barId;
	}

	public void setBarId(int barId) {
		this.barId = barId;
	}

	public String getBarNombre() {
		return this.barNombre;
	}

	public void setBarNombre(String barNombre) {
		this.barNombre = barNombre;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

}