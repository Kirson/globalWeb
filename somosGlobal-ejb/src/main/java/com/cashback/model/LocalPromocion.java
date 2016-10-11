package com.cashback.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the local_promocion database table.
 * 
 */
@Entity
@Table(name = "local_promocion")
@NamedQueries({
		@NamedQuery(name = "LocalPromocion.findAll", query = "SELECT lp FROM LocalPromocion lp"),
		@NamedQuery(name = "LocalPromocion.findByTipo", query = "SELECT lp FROM LocalPromocion lp"
				+ " WHERE lp.lpTipo =:lpTipo AND lp.localVenta =:localVenta") })
public class LocalPromocion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lp_id")
	private int lpId;

	@Column(name = "lp_detalle")
	private String lpDetalle;

	@Column(name = "lp_estado")
	private String lpEstado;

	@Column(name = "lp_tipo")
	private String lpTipo;

	@Temporal(TemporalType.DATE)
	@Column(name = "lp_fec_crea")
	private Date lpFecCrea;

	@Column(name = "lp_imagen")
	private String lpImagen;

	@Column(name = "lp_usr_crea")
	private String lpUsrCrea;

	@Column(name = "lp_usr_modif")
	private String lpUsrModif;

	@ManyToOne
	@JoinColumn(name = "lv_id")
	private LocalVenta localVenta;

	public LocalPromocion() {
	}

	public int getLpId() {
		return this.lpId;
	}

	public void setLpId(int lpId) {
		this.lpId = lpId;
	}

	public String getLpDetalle() {
		return this.lpDetalle;
	}

	public void setLpDetalle(String lpDetalle) {
		this.lpDetalle = lpDetalle;
	}

	public String getLpEstado() {
		return this.lpEstado;
	}

	public void setLpEstado(String lpEstado) {
		this.lpEstado = lpEstado;
	}

	public Date getLpFecCrea() {
		return this.lpFecCrea;
	}

	public void setLpFecCrea(Date lpFecCrea) {
		this.lpFecCrea = lpFecCrea;
	}

	public String getLpImagen() {
		return this.lpImagen;
	}

	public void setLpImagen(String lpImagen) {
		this.lpImagen = lpImagen;
	}

	public String getLpUsrCrea() {
		return this.lpUsrCrea;
	}

	public void setLpUsrCrea(String lpUsrCrea) {
		this.lpUsrCrea = lpUsrCrea;
	}

	public String getLpUsrModif() {
		return this.lpUsrModif;
	}

	public void setLpUsrModif(String lpUsrModif) {
		this.lpUsrModif = lpUsrModif;
	}

	public LocalVenta getLocalVenta() {
		return localVenta;
	}

	public void setLocalVenta(LocalVenta localVenta) {
		this.localVenta = localVenta;
	}

	public String getLpTipo() {
		return lpTipo;
	}

	public void setLpTipo(String lpTipo) {
		this.lpTipo = lpTipo;
	}

}