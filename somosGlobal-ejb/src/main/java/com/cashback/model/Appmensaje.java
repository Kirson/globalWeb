package com.cashback.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the APPMENSAJES database table.
 * 
 */
@Entity
@Table(name="appmensajes")
@NamedQuery(name="Appmensaje.findAll", query="SELECT a FROM Appmensaje a")
public class Appmensaje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="men_id")
	private int menId;

	@Column(name="men_codigo")
	private String menCodigo;

	@Column(name="men_texto")
	private String menTexto;

	@Column(name="men_tipo")
	private String menTipo;

	public Appmensaje() {
	}

	public int getMenId() {
		return this.menId;
	}

	public void setMenId(int menId) {
		this.menId = menId;
	}

	public String getMenCodigo() {
		return this.menCodigo;
	}

	public void setMenCodigo(String menCodigo) {
		this.menCodigo = menCodigo;
	}

	public String getMenTexto() {
		return this.menTexto;
	}

	public void setMenTexto(String menTexto) {
		this.menTexto = menTexto;
	}

	public String getMenTipo() {
		return this.menTipo;
	}

	public void setMenTipo(String menTipo) {
		this.menTipo = menTipo;
	}

}