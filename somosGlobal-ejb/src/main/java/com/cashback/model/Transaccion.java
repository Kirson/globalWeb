package com.cashback.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the transaccion database table.
 * 
 */
@Entity
@Table(name="transaccion")
@NamedQuery(name="Transaccion.findAll", query="SELECT t FROM Transaccion t")
public class Transaccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tra_id")
	private int traId;

	@Column(name="tra_estado")
	private String traEstado;

	@Column(name="tra_factura")
	private String traFactura;

	@Column(name="tra_ptos_acreditados")
	private int traPtosAcreditados;

	@Column(name="tra_tipo")
	private int traTipo;

	@Column(name="tra_valor_fac")
	private BigDecimal traValorFac;

	//bi-directional many-to-one association to LocalVenta
	@ManyToOne
	@JoinColumn(name="lv_id")
	private LocalVenta localVenta;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="per_id")
	private Persona persona;

	public Transaccion() {
	}

	public int getTraId() {
		return this.traId;
	}

	public void setTraId(int traId) {
		this.traId = traId;
	}

	public String getTraEstado() {
		return this.traEstado;
	}

	public void setTraEstado(String traEstado) {
		this.traEstado = traEstado;
	}

	public String getTraFactura() {
		return this.traFactura;
	}

	public void setTraFactura(String traFactura) {
		this.traFactura = traFactura;
	}

	public int getTraPtosAcreditados() {
		return this.traPtosAcreditados;
	}

	public void setTraPtosAcreditados(int traPtosAcreditados) {
		this.traPtosAcreditados = traPtosAcreditados;
	}

	public int getTraTipo() {
		return this.traTipo;
	}

	public void setTraTipo(int traTipo) {
		this.traTipo = traTipo;
	}

	public BigDecimal getTraValorFac() {
		return this.traValorFac;
	}

	public void setTraValorFac(BigDecimal traValorFac) {
		this.traValorFac = traValorFac;
	}

	public LocalVenta getLocalVenta() {
		return this.localVenta;
	}

	public void setLocalVenta(LocalVenta localVenta) {
		this.localVenta = localVenta;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}