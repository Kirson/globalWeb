package com.cashback.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the horario_atencion database table.
 * 
 */
@Entity
@Table(name="horario_atencion")
@NamedQuery(name="HorarioAtencion.findAll", query="SELECT h FROM HorarioAtencion h")
public class HorarioAtencion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ha_id")
	private int haId;

	@Column(name="ha_apertura")
	private String haApertura;

	@Column(name="ha_cierre")
	private String haCierre;

	@Column(name="ha_dia")
	private String haDia;

	@Column(name="ha_estado")
	private String haEstado;

	//bi-directional many-to-one association to LocalVenta
	@ManyToOne
	@JoinColumn(name="lv_id")
	private LocalVenta localVenta;

	public HorarioAtencion() {
	}

	public int getHaId() {
		return this.haId;
	}

	public void setHaId(int haId) {
		this.haId = haId;
	}

	public String getHaApertura() {
		return this.haApertura;
	}

	public void setHaApertura(String haApertura) {
		this.haApertura = haApertura;
	}

	public String getHaCierre() {
		return this.haCierre;
	}

	public void setHaCierre(String haCierre) {
		this.haCierre = haCierre;
	}

	public String getHaDia() {
		return this.haDia;
	}

	public void setHaDia(String haDia) {
		this.haDia = haDia;
	}

	public String getHaEstado() {
		return this.haEstado;
	}

	public void setHaEstado(String haEstado) {
		this.haEstado = haEstado;
	}

	public LocalVenta getLocalVenta() {
		return this.localVenta;
	}

	public void setLocalVenta(LocalVenta localVenta) {
		this.localVenta = localVenta;
	}

}