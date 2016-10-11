package com.cashback.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the persona database table.
 * 
 */
@Entity
@Table(name = "persona")
@NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "per_id")
	private int perId;

	@Column(name = "per_apellidos")
	private String perApellidos;

	@Column(name = "per_ced_ruc_pas")
	private String perCedRucPas;

	@Column(name = "per_email")
	private String perEmail;

	@Column(name = "per_estado")
	private String perEstado;

	@Temporal(TemporalType.DATE)
	@Column(name = "per_fec_nac")
	private Date perFecNac;

	@Column(name = "per_foto")
	private String perFoto;

	@Column(name = "per_nombres")
	private String perNombres;

	@Column(name = "per_sexo")
	private int perSexo;

	// bi-directional many-to-one association to Contacto
	@OneToMany(mappedBy = "persona")
	private List<Contacto> contactos;

	// bi-directional many-to-one association to HobbiePersona
	@OneToMany(mappedBy = "persona")
	private List<HobbiePersona> hobbiePersonas;

	// bi-directional many-to-one association to LocalVenta
	@OneToMany(mappedBy = "persona")
	private List<LocalVenta> localVentas;

	// bi-directional many-to-one association to Punto
	@OneToMany(mappedBy = "persona")
	private List<Puntos> puntos;

	// bi-directional many-to-one association to Transaccion
	@OneToMany(mappedBy = "persona")
	private List<Transaccion> transaccions;

	// bi-directional many-to-one association to Usuario
	//@OneToMany(mappedBy = "persona")
	//private List<Usuario> usuarioList;

	public Persona() {
	}

	public int getPerId() {
		return this.perId;
	}

	public void setPerId(int perId) {
		this.perId = perId;
	}

	public String getPerApellidos() {
		return this.perApellidos;
	}

	public void setPerApellidos(String perApellidos) {
		this.perApellidos = perApellidos;
	}

	public String getPerCedRucPas() {
		return this.perCedRucPas;
	}

	public void setPerCedRucPas(String perCedRucPas) {
		this.perCedRucPas = perCedRucPas;
	}

	public String getPerEmail() {
		return this.perEmail;
	}

	public void setPerEmail(String perEmail) {
		this.perEmail = perEmail;
	}

	public String getPerEstado() {
		return this.perEstado;
	}

	public void setPerEstado(String perEstado) {
		this.perEstado = perEstado;
	}

	public Date getPerFecNac() {
		return this.perFecNac;
	}

	public void setPerFecNac(Date perFecNac) {
		this.perFecNac = perFecNac;
	}

	public String getPerFoto() {
		return this.perFoto;
	}

	public void setPerFoto(String perFoto) {
		this.perFoto = perFoto;
	}

	public String getPerNombres() {
		return this.perNombres;
	}

	public void setPerNombres(String perNombres) {
		this.perNombres = perNombres;
	}

	public int getPerSexo() {
		return this.perSexo;
	}

	public void setPerSexo(int perSexo) {
		this.perSexo = perSexo;
	}

	public List<Contacto> getContactos() {
		return this.contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}

	public Contacto addContacto(Contacto contacto) {
		getContactos().add(contacto);
		contacto.setPersona(this);

		return contacto;
	}

	public Contacto removeContacto(Contacto contacto) {
		getContactos().remove(contacto);
		contacto.setPersona(null);

		return contacto;
	}

	public List<HobbiePersona> getHobbiePersonas() {
		return this.hobbiePersonas;
	}

	public void setHobbiePersonas(List<HobbiePersona> hobbiePersonas) {
		this.hobbiePersonas = hobbiePersonas;
	}

	public HobbiePersona addHobbiePersona(HobbiePersona hobbiePersona) {
		getHobbiePersonas().add(hobbiePersona);
		hobbiePersona.setPersona(this);

		return hobbiePersona;
	}

	public HobbiePersona removeHobbiePersona(HobbiePersona hobbiePersona) {
		getHobbiePersonas().remove(hobbiePersona);
		hobbiePersona.setPersona(null);

		return hobbiePersona;
	}

	public List<LocalVenta> getLocalVentas() {
		return this.localVentas;
	}

	public void setLocalVentas(List<LocalVenta> localVentas) {
		this.localVentas = localVentas;
	}

	public LocalVenta addLocalVenta(LocalVenta localVenta) {
		getLocalVentas().add(localVenta);
		localVenta.setPersona(this);

		return localVenta;
	}

	public LocalVenta removeLocalVenta(LocalVenta localVenta) {
		getLocalVentas().remove(localVenta);
		localVenta.setPersona(null);

		return localVenta;
	}

	public List<Puntos> getPuntos() {
		return this.puntos;
	}

	public void setPuntos(List<Puntos> puntos) {
		this.puntos = puntos;
	}

	public Puntos addPunto(Puntos punto) {
		getPuntos().add(punto);
		punto.setPersona(this);

		return punto;
	}

	public Puntos removePunto(Puntos punto) {
		getPuntos().remove(punto);
		punto.setPersona(null);

		return punto;
	}

	public List<Transaccion> getTransaccions() {
		return this.transaccions;
	}

	public void setTransaccions(List<Transaccion> transaccions) {
		this.transaccions = transaccions;
	}

	public Transaccion addTransaccion(Transaccion transaccion) {
		getTransaccions().add(transaccion);
		transaccion.setPersona(this);

		return transaccion;
	}

	public Transaccion removeTransaccion(Transaccion transaccion) {
		getTransaccions().remove(transaccion);
		transaccion.setPersona(null);

		return transaccion;
	}

	//public List<Usuario> getUsuarioList() {
	//	return usuarioList;
	//}

	//public void setUsuarioList(List<Usuario> usuarioList) {
	//	this.usuarioList = usuarioList;
	//}

}