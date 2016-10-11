package com.cashback.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the nota database table.
 * 
 */
@Entity
@Table(name="nota")
@NamedQuery(name="Nota.findAll", query="SELECT n FROM Nota n")
public class Nota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="not_id")
	private int notId;

	@Column(name="cat_id")
	private int catId;

	@Column(name="not_comentario_admin")
	private String notComentarioAdmin;

	@Column(name="not_cuerpo")
	private String notCuerpo;

	@Column(name="not_encabezado")
	private String notEncabezado;

	@Temporal(TemporalType.DATE)
	@Column(name="not_fecha")
	private Date notFecha;

	@Column(name="not_valorizacion")
	private int notValorizacion;

	@Column(name="per_id")
	private int perId;

	public Nota() {
	}

	public int getNotId() {
		return this.notId;
	}

	public void setNotId(int notId) {
		this.notId = notId;
	}

	public int getCatId() {
		return this.catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getNotComentarioAdmin() {
		return this.notComentarioAdmin;
	}

	public void setNotComentarioAdmin(String notComentarioAdmin) {
		this.notComentarioAdmin = notComentarioAdmin;
	}

	public String getNotCuerpo() {
		return this.notCuerpo;
	}

	public void setNotCuerpo(String notCuerpo) {
		this.notCuerpo = notCuerpo;
	}

	public String getNotEncabezado() {
		return this.notEncabezado;
	}

	public void setNotEncabezado(String notEncabezado) {
		this.notEncabezado = notEncabezado;
	}

	public Date getNotFecha() {
		return this.notFecha;
	}

	public void setNotFecha(Date notFecha) {
		this.notFecha = notFecha;
	}

	public int getNotValorizacion() {
		return this.notValorizacion;
	}

	public void setNotValorizacion(int notValorizacion) {
		this.notValorizacion = notValorizacion;
	}

	public int getPerId() {
		return this.perId;
	}

	public void setPerId(int perId) {
		this.perId = perId;
	}

}