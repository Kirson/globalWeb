package com.cashback.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the localidad database table.
 * 
 */
@Entity
@Table(name = "localidad")
@NamedQueries({
		@NamedQuery(name = "Localidad.findAll", query = "SELECT l FROM Localidad l"),
		@NamedQuery(name = "Localidad.findByLocalidadPadre", query = "SELECT l FROM Localidad l WHERE l.localidad =:localidadPadre ORDER BY l.locNombre"),
		@NamedQuery(name="Localidad.findByLocalidadOnlyPadre", query="SELECT l FROM Localidad l WHERE (l.localidad IS NULL OR l = l.localidad) ORDER BY l.locNombre")})
public class Localidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loc_id")
	private int locId;

	@Column(name = "loc_estado")
	private String locEstado;

	@Column(name = "loc_nombre")
	private String locNombre;

	// bi-directional many-to-one association to Localidad
	@ManyToOne
	@JoinColumn(name = "loc_id_padre")
	private Localidad localidad;

	// bi-directional many-to-one association to Localidad
	@OneToMany(mappedBy = "localidad")
	private List<Localidad> localidads;
	
	@OneToMany(mappedBy = "localidad")
	private List<Actor> actors;

	public Localidad() {
	}

	public int getLocId() {
		return this.locId;
	}

	public void setLocId(int locId) {
		this.locId = locId;
	}

	public String getLocEstado() {
		return this.locEstado;
	}

	public void setLocEstado(String locEstado) {
		this.locEstado = locEstado;
	}

	public String getLocNombre() {
		return this.locNombre;
	}

	public void setLocNombre(String locNombre) {
		this.locNombre = locNombre;
	}

	public Localidad getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public List<Localidad> getLocalidads() {
		return this.localidads;
	}

	public void setLocalidads(List<Localidad> localidads) {
		this.localidads = localidads;
	}

	public Localidad addLocalidad(Localidad localidad) {
		getLocalidads().add(localidad);
		localidad.setLocalidad(this);

		return localidad;
	}

	public Localidad removeLocalidad(Localidad localidad) {
		getLocalidads().remove(localidad);
		localidad.setLocalidad(null);

		return localidad;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

}