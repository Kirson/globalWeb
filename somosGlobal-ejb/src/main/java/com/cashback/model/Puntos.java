package com.cashback.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the puntos database table.
 * 
 */
@Entity
@Table(name="puntos")
@NamedQuery(name="Puntos.findAll", query="SELECT p FROM Puntos p")
public class Puntos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pto_id")
	private int ptoId;

	@Column(name="pto_cantidad")
	private int ptoCantidad;

	@Column(name="pto_valor")
	private BigDecimal ptoValor;

	@Temporal(TemporalType.DATE)
	@Column(name="pto_vig_desde")
	private Date ptoVigDesde;

	@Temporal(TemporalType.DATE)
	@Column(name="pto_vig_hasta")
	private Date ptoVigHasta;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="per_id")
	private Persona persona;

	//bi-directional many-to-one association to LocalVenta
	@ManyToOne
	@JoinColumn(name="lv_id")
	private LocalVenta localVenta;

	public Puntos() {
	}

	public int getPtoId() {
		return this.ptoId;
	}

	public void setPtoId(int ptoId) {
		this.ptoId = ptoId;
	}

	public int getPtoCantidad() {
		return this.ptoCantidad;
	}

	public void setPtoCantidad(int ptoCantidad) {
		this.ptoCantidad = ptoCantidad;
	}

	public BigDecimal getPtoValor() {
		return this.ptoValor;
	}

	public void setPtoValor(BigDecimal ptoValor) {
		this.ptoValor = ptoValor;
	}

	public Date getPtoVigDesde() {
		return this.ptoVigDesde;
	}

	public void setPtoVigDesde(Date ptoVigDesde) {
		this.ptoVigDesde = ptoVigDesde;
	}

	public Date getPtoVigHasta() {
		return this.ptoVigHasta;
	}

	public void setPtoVigHasta(Date ptoVigHasta) {
		this.ptoVigHasta = ptoVigHasta;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public LocalVenta getLocalVenta() {
		return this.localVenta;
	}

	public void setLocalVenta(LocalVenta localVenta) {
		this.localVenta = localVenta;
	}

}