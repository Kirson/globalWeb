package com.cashback.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parametros_gen database table.
 * 
 */
@Entity
@Table(name="parametros_gen")
@NamedQuery(name="ParametrosGen.findAll", query="SELECT p FROM ParametrosGen p")
public class ParametrosGen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="par_codigo")
	private String parCodigo;

	@Column(name="par_descripcion")
	private String parDescripcion;

	@Column(name="par_valor")
	private String parValor;

	public ParametrosGen() {
	}

	public String getParCodigo() {
		return this.parCodigo;
	}

	public void setParCodigo(String parCodigo) {
		this.parCodigo = parCodigo;
	}

	public String getParDescripcion() {
		return this.parDescripcion;
	}

	public void setParDescripcion(String parDescripcion) {
		this.parDescripcion = parDescripcion;
	}

	public String getParValor() {
		return this.parValor;
	}

	public void setParValor(String parValor) {
		this.parValor = parValor;
	}

}