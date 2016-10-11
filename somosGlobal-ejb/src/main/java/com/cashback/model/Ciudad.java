package com.cashback.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the ciudad database table.
 * 
 */
@Entity
@Table(name = "ciudad")
@NamedQuery(name = "Ciudad.findAll", query = "SELECT c FROM Ciudad c")
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ciu_id")
	private int ciuId;

	@Column(name = "ciu_nombre")
	private String ciuNombre;

	// bi-directional many-to-one association to Provincia
	@ManyToOne
	@JoinColumn(name = "prv_id")
	private Provincia provincia;

	// bi-directional many-to-one association to Ciudad
	@OneToMany(mappedBy = "ciudad")
	private List<Barrio> barrioList;

	public Ciudad() {
	}

	public int getCiuId() {
		return this.ciuId;
	}

	public void setCiuId(int ciuId) {
		this.ciuId = ciuId;
	}

	public String getCiuNombre() {
		return this.ciuNombre;
	}

	public void setCiuNombre(String ciuNombre) {
		this.ciuNombre = ciuNombre;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}