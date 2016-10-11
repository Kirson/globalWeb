package com.cashback.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the hobbie database table.
 * 
 */
@Entity
@Table(name="hobbie")
@NamedQuery(name="Hobbie.findAll", query="SELECT h FROM Hobbie h")
public class Hobbie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="hob_id")
	private int hobId;

	@Column(name="hob_estado")
	private String hobEstado;

	@Column(name="hob_nombre")
	private String hobNombre;

	//bi-directional many-to-one association to HobbiePersona
	@OneToMany(mappedBy="hobbie")
	private List<HobbiePersona> hobbiePersonas;

	public Hobbie() {
	}

	public int getHobId() {
		return this.hobId;
	}

	public void setHobId(int hobId) {
		this.hobId = hobId;
	}

	public String getHobEstado() {
		return this.hobEstado;
	}

	public void setHobEstado(String hobEstado) {
		this.hobEstado = hobEstado;
	}

	public String getHobNombre() {
		return this.hobNombre;
	}

	public void setHobNombre(String hobNombre) {
		this.hobNombre = hobNombre;
	}

	public List<HobbiePersona> getHobbiePersonas() {
		return this.hobbiePersonas;
	}

	public void setHobbiePersonas(List<HobbiePersona> hobbiePersonas) {
		this.hobbiePersonas = hobbiePersonas;
	}

	public HobbiePersona addHobbiePersona(HobbiePersona hobbiePersona) {
		getHobbiePersonas().add(hobbiePersona);
		hobbiePersona.setHobbie(this);

		return hobbiePersona;
	}

	public HobbiePersona removeHobbiePersona(HobbiePersona hobbiePersona) {
		getHobbiePersonas().remove(hobbiePersona);
		hobbiePersona.setHobbie(null);

		return hobbiePersona;
	}

}