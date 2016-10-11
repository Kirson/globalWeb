package com.cashback.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@Table(name = "categoria")
@NamedQueries({
		@NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
		@NamedQuery(name = "Categoria.findByIdList", query = "SELECT c FROM Categoria c"
				+ " WHERE c.catId LIKE :catId"
				+ " AND c.catEstado LIKE :catEstado ORDER BY c.catNombre") })
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cat_id")
	private String catId;

	@Column(name = "cat_estado")
	private String catEstado;

	@Column(name = "cat_nombre")
	private String catNombre;
	
	@Column(name = "cat_imagen")
	private String catImagen;

	// bi-directional many-to-one association to LocalVenta
	@OneToMany(mappedBy = "categoria")
	private List<LocalVenta> localVentas;

	public Categoria() {
	}

	public String getCatId() {
		return this.catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getCatEstado() {
		return this.catEstado;
	}

	public void setCatEstado(String catEstado) {
		this.catEstado = catEstado;
	}

	public String getCatNombre() {
		return this.catNombre;
	}

	public void setCatNombre(String catNombre) {
		this.catNombre = catNombre;
	}

	public String getCatImagen() {
		return catImagen;
	}

	public void setCatImagen(String catImagen) {
		this.catImagen = catImagen;
	}

	public List<LocalVenta> getLocalVentas() {
		return localVentas;
	}

	public void setLocalVentas(List<LocalVenta> localVentas) {
		this.localVentas = localVentas;
	}

}