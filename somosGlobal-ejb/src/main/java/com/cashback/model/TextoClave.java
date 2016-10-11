package com.cashback.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the texto_clave database table.
 * 
 */
@Entity
@Table(name="texto_clave")
@NamedQuery(name="TextoClave.findAll", query="SELECT t FROM TextoClave t")
public class TextoClave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tc_id")
	private int tcId;

	@Column(name="tc_estado")
	private String tcEstado;

	@Column(name="tc_texto")
	private String tcTexto;

	@Column(name="tc_tipo")
	private String tcTipo;

	public TextoClave() {
	}

	public int getTcId() {
		return this.tcId;
	}

	public void setTcId(int tcId) {
		this.tcId = tcId;
	}

	public String getTcEstado() {
		return this.tcEstado;
	}

	public void setTcEstado(String tcEstado) {
		this.tcEstado = tcEstado;
	}

	public String getTcTexto() {
		return this.tcTexto;
	}

	public void setTcTexto(String tcTexto) {
		this.tcTexto = tcTexto;
	}

	public String getTcTipo() {
		return this.tcTipo;
	}

	public void setTcTipo(String tcTipo) {
		this.tcTipo = tcTipo;
	}

}