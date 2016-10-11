package com.cashback.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the contacto database table.
 * 
 */
@Entity
@Table(name="contacto")
@NamedQuery(name="Contacto.findAll", query="SELECT c FROM Contacto c")
public class Contacto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="con_id")
	private int conId;

	@Column(name="con_descripcion")
	private String conDescripcion;

	@Column(name="con_tipo")
	private int conTipo;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="per_id")
	private Persona persona;

	public Contacto() {
	}

	public int getConId() {
		return this.conId;
	}

	public void setConId(int conId) {
		this.conId = conId;
	}

	public String getConDescripcion() {
		return this.conDescripcion;
	}

	public void setConDescripcion(String conDescripcion) {
		this.conDescripcion = conDescripcion;
	}

	public int getConTipo() {
		return this.conTipo;
	}

	public void setConTipo(int conTipo) {
		this.conTipo = conTipo;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}