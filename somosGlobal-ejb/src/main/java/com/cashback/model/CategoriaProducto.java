package com.cashback.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the categoria_producto database table.
 * 
 */
@Entity
@Table(name="categoria_producto")
@NamedQuery(name="CategoriaProducto.findAll", query="SELECT c FROM CategoriaProducto c")
public class CategoriaProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cp_id")
	private int cpId;

	@Column(name="cp_descripcion")
	private String cpDescripcion;

	@Column(name="cp_estado")
	private int cpEstado;

	@Column(name="cp_id_padre")
	private int cpIdPadre;

	@Column(name="cp_nombre")
	private String cpNombre;

	public CategoriaProducto() {
	}

	public int getCpId() {
		return this.cpId;
	}

	public void setCpId(int cpId) {
		this.cpId = cpId;
	}

	public String getCpDescripcion() {
		return this.cpDescripcion;
	}

	public void setCpDescripcion(String cpDescripcion) {
		this.cpDescripcion = cpDescripcion;
	}

	public int getCpEstado() {
		return this.cpEstado;
	}

	public void setCpEstado(int cpEstado) {
		this.cpEstado = cpEstado;
	}

	public int getCpIdPadre() {
		return this.cpIdPadre;
	}

	public void setCpIdPadre(int cpIdPadre) {
		this.cpIdPadre = cpIdPadre;
	}

	public String getCpNombre() {
		return this.cpNombre;
	}

	public void setCpNombre(String cpNombre) {
		this.cpNombre = cpNombre;
	}

}