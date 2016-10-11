package com.cashback.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the provincia database table.
 * 
 */
@Entity
@Table(name="provincia")
@NamedQuery(name="Provincia.findAll", query="SELECT p FROM Provincia p")
public class Provincia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="prv_id")
	private int prvId;

	@Column(name="prv_nombre")
	private String prvNombre;

	//bi-directional many-to-one association to Ciudad
	@OneToMany(mappedBy="provincia")
	private List<Ciudad> ciudads;

	public Provincia() {
	}

	public int getPrvId() {
		return this.prvId;
	}

	public void setPrvId(int prvId) {
		this.prvId = prvId;
	}

	public String getPrvNombre() {
		return this.prvNombre;
	}

	public void setPrvNombre(String prvNombre) {
		this.prvNombre = prvNombre;
	}

	public List<Ciudad> getCiudads() {
		return this.ciudads;
	}

	public void setCiudads(List<Ciudad> ciudads) {
		this.ciudads = ciudads;
	}

	public Ciudad addCiudad(Ciudad ciudad) {
		getCiudads().add(ciudad);
		ciudad.setProvincia(this);

		return ciudad;
	}

	public Ciudad removeCiudad(Ciudad ciudad) {
		getCiudads().remove(ciudad);
		ciudad.setProvincia(null);

		return ciudad;
	}

}