package com.cashback.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the hobbie_persona database table.
 * 
 */
@Entity
@Table(name="hobbie_persona")
@NamedQuery(name="HobbiePersona.findAll", query="SELECT h FROM HobbiePersona h")
public class HobbiePersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="hp_id")
	private int hpId;

	@Column(name="hp_estado")
	private String hpEstado;

	//bi-directional many-to-one association to Hobbie
	@ManyToOne
	@JoinColumn(name="hob_id")
	private Hobbie hobbie;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="per_id")
	private Persona persona;

	public HobbiePersona() {
	}

	public int getHpId() {
		return this.hpId;
	}

	public void setHpId(int hpId) {
		this.hpId = hpId;
	}

	public String getHpEstado() {
		return this.hpEstado;
	}

	public void setHpEstado(String hpEstado) {
		this.hpEstado = hpEstado;
	}

	public Hobbie getHobbie() {
		return this.hobbie;
	}

	public void setHobbie(Hobbie hobbie) {
		this.hobbie = hobbie;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}