package com.cashback.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the licencia database table.
 * 
 */
@Entity
@Table(name="licencia")
@NamedQuery(name="Licencia.findAll", query="SELECT l FROM Licencia l")
public class Licencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="lic_id")
	private int licId;

	@Column(name="lic_agregar_persona")
	private int licAgregarPersona;

	@Column(name="lic_cambiar_pto_dinero")
	private int licCambiarPtoDinero;

	@Column(name="lic_cambiar_pto_servicio")
	private int licCambiarPtoServicio;

	@Column(name="lic_compra_puntos")
	private int licCompraPuntos;

	@Column(name="lic_estado")
	private String licEstado;

	@Column(name="lic_nombre")
	private String licNombre;

	@Column(name="lic_vender_pto")
	private int licVenderPto;

	public Licencia() {
	}

	public int getLicId() {
		return this.licId;
	}

	public void setLicId(int licId) {
		this.licId = licId;
	}

	public int getLicAgregarPersona() {
		return this.licAgregarPersona;
	}

	public void setLicAgregarPersona(int licAgregarPersona) {
		this.licAgregarPersona = licAgregarPersona;
	}

	public int getLicCambiarPtoDinero() {
		return this.licCambiarPtoDinero;
	}

	public void setLicCambiarPtoDinero(int licCambiarPtoDinero) {
		this.licCambiarPtoDinero = licCambiarPtoDinero;
	}

	public int getLicCambiarPtoServicio() {
		return this.licCambiarPtoServicio;
	}

	public void setLicCambiarPtoServicio(int licCambiarPtoServicio) {
		this.licCambiarPtoServicio = licCambiarPtoServicio;
	}

	public int getLicCompraPuntos() {
		return this.licCompraPuntos;
	}

	public void setLicCompraPuntos(int licCompraPuntos) {
		this.licCompraPuntos = licCompraPuntos;
	}

	public String getLicEstado() {
		return this.licEstado;
	}

	public void setLicEstado(String licEstado) {
		this.licEstado = licEstado;
	}

	public String getLicNombre() {
		return this.licNombre;
	}

	public void setLicNombre(String licNombre) {
		this.licNombre = licNombre;
	}

	public int getLicVenderPto() {
		return this.licVenderPto;
	}

	public void setLicVenderPto(int licVenderPto) {
		this.licVenderPto = licVenderPto;
	}

}