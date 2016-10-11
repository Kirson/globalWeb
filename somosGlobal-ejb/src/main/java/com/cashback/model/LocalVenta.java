package com.cashback.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the local_venta database table.
 * 
 */
@Entity
@Table(name = "local_venta")
@NamedQuery(name = "LocalVenta.findAll", query = "SELECT l FROM LocalVenta l")
public class LocalVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lv_id")
	private int lvId;

	@Column(name = "lv_actividad_pri")
	private String lvActividadPri;

	@Column(name = "lv_email")
	private String lvEmail;

	@Column(name = "lv_estado")
	private String lvEstado;

	@Column(name = "lv_nombre_com")
	private String lvNombreCom;

	@Column(name = "lv_razon_social")
	private String lvRazonSocial;

	@Column(name = "lv_ruc")
	private String lvRuc;

	@Column(name = "lv_telefono")
	private String lvTelefono;

	@Column(name = "lv_tel_cel")
	private String lvTelCel;

	@Column(name = "lv_web")
	private String lvWeb;

	@Column(name = "lv_logo")
	private String lvLogo;

	@Column(name = "lv_slogan")
	private String lvSlogan;

	@Column(name = "lv_servicio")
	private String lvServicio;

	@Column(name = "lv_ranking")
	private Integer lvRanking;

	@Column(name = "lv_palabras_clave")
	private String lvPalabrasClave;

	@Column(name = "lv_foto")
	private String lvFoto;
	
	@Column(name = "lv_mas")
	private String lvmas;
	
	@Column(name = "lv_promo")
	private String lvpromo;
	
	@Column(name = "lv_geo")
	private String lvgeo;
	
	@Column(name = "lv_facebook")
	private String lvfacebook;
	
	@Column(name = "lv_twitter")
	private String lvtwitter;
	
	@Column(name = "lv_video")
	private String lvvideo;
	
	
	
	

	// bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name = "per_id")
	private Persona persona;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lv_fecha_crea")
	private Date lvFechaCrea;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lv_fecha_modifica")
	private Date lvFechaModifica;

	@Column(name = "lv_usr_crea")
	private String lvUsrCrea;

	@Column(name = "lv_usr_modifica")
	private String lvUsrModifica;

	// bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name = "cat_id")
	private Categoria categoria;
	
	// bi-directional many-to-one association to Punto
	@OneToMany(mappedBy = "localVenta")
	private List<Puntos> puntos;

	// bi-directional many-to-one association to Transaccion
	@OneToMany(mappedBy = "localVenta")
	private List<Transaccion> transaccions;

	// bi-directional many-to-one association to Ubicacion
	@OneToMany(mappedBy = "localVenta")
	private List<Ubicacion> ubicacions;

	// bi-directional many-to-one association to LocalPromocion
	@OneToMany(mappedBy = "localVenta")
	private List<LocalPromocion> localPromocionList;

	// bi-directional many-to-one association to HorarioAtencion
	@OneToMany(mappedBy = "localVenta")
	private List<HorarioAtencion> horarioAtencions;

	@Transient
	private List<LocalPromocion> localPromocionBRList;

	public LocalVenta() {
	}

	public int getLvId() {
		return this.lvId;
	}

	public void setLvId(int lvId) {
		this.lvId = lvId;
	}

	public String getLvActividadPri() {
		return this.lvActividadPri;
	}

	public void setLvActividadPri(String lvActividadPri) {
		this.lvActividadPri = lvActividadPri;
	}

	public String getLvEmail() {
		return this.lvEmail;
	}

	public void setLvEmail(String lvEmail) {
		this.lvEmail = lvEmail;
	}

	public String getLvEstado() {
		return this.lvEstado;
	}

	public void setLvEstado(String lvEstado) {
		this.lvEstado = lvEstado;
	}

	public String getLvNombreCom() {
		return this.lvNombreCom;
	}

	public void setLvNombreCom(String lvNombreCom) {
		this.lvNombreCom = lvNombreCom;
	}

	public String getLvRazonSocial() {
		return this.lvRazonSocial;
	}

	public void setLvRazonSocial(String lvRazonSocial) {
		this.lvRazonSocial = lvRazonSocial;
	}

	public String getLvRuc() {
		return this.lvRuc;
	}

	public void setLvRuc(String lvRuc) {
		this.lvRuc = lvRuc;
	}

	public String getLvTelefono() {
		return this.lvTelefono;
	}

	public void setLvTelefono(String lvTelefono) {
		this.lvTelefono = lvTelefono;
	}

	public String getLvWeb() {
		return this.lvWeb;
	}

	public void setLvWeb(String lvWeb) {
		this.lvWeb = lvWeb;
	}

	public List<HorarioAtencion> getHorarioAtencions() {
		return this.horarioAtencions;
	}

	public void setHorarioAtencions(List<HorarioAtencion> horarioAtencions) {
		this.horarioAtencions = horarioAtencions;
	}

	public HorarioAtencion addHorarioAtencion(HorarioAtencion horarioAtencion) {
		getHorarioAtencions().add(horarioAtencion);
		horarioAtencion.setLocalVenta(this);

		return horarioAtencion;
	}

	public HorarioAtencion removeHorarioAtencion(HorarioAtencion horarioAtencion) {
		getHorarioAtencions().remove(horarioAtencion);
		horarioAtencion.setLocalVenta(null);

		return horarioAtencion;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Puntos> getPuntos() {
		return this.puntos;
	}

	public void setPuntos(List<Puntos> puntos) {
		this.puntos = puntos;
	}

	public Puntos addPunto(Puntos puntos) {
		getPuntos().add(puntos);
		puntos.setLocalVenta(this);
		return puntos;
	}

	public Puntos removePunto(Puntos puntos) {
		getPuntos().remove(puntos);
		puntos.setLocalVenta(null);

		return puntos;
	}

	public List<Transaccion> getTransaccions() {
		return this.transaccions;
	}

	public void setTransaccions(List<Transaccion> transaccions) {
		this.transaccions = transaccions;
	}

	public Transaccion addTransaccion(Transaccion transaccion) {
		getTransaccions().add(transaccion);
		transaccion.setLocalVenta(this);

		return transaccion;
	}

	public Transaccion removeTransaccion(Transaccion transaccion) {
		getTransaccions().remove(transaccion);
		transaccion.setLocalVenta(null);

		return transaccion;
	}

	public List<Ubicacion> getUbicacions() {
		return this.ubicacions;
	}

	public void setUbicacions(List<Ubicacion> ubicacions) {
		this.ubicacions = ubicacions;
	}

	public Ubicacion addUbicacion(Ubicacion ubicacion) {
		getUbicacions().add(ubicacion);
		ubicacion.setLocalVenta(this);

		return ubicacion;
	}

	public Ubicacion removeUbicacion(Ubicacion ubicacion) {
		getUbicacions().remove(ubicacion);
		ubicacion.setLocalVenta(null);

		return ubicacion;
	}

	public String getLvLogo() {
		return lvLogo;
	}

	public void setLvLogo(String lvLogo) {
		this.lvLogo = lvLogo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getLvSlogan() {
		return lvSlogan;
	}

	public void setLvSlogan(String lvSlogan) {
		this.lvSlogan = lvSlogan;
	}

	public String getLvServicio() {
		return lvServicio;
	}

	public void setLvServicio(String lvServicio) {
		this.lvServicio = lvServicio;
	}

	public List<LocalPromocion> getLocalPromocionList() {
		return localPromocionList;
	}

	public void setLocalPromocionList(List<LocalPromocion> localPromocionList) {
		this.localPromocionList = localPromocionList;
	}

	public String getLvTelCel() {
		return lvTelCel;
	}

	public void setLvTelCel(String lvTelCel) {
		this.lvTelCel = lvTelCel;
	}

	public Integer getLvRanking() {
		return lvRanking;
	}

	public void setLvRanking(Integer lvRanking) {
		this.lvRanking = lvRanking;
	}

	public List<LocalPromocion> getLocalPromocionBRList() {
		return localPromocionBRList;
	}

	public void setLocalPromocionBRList(
			List<LocalPromocion> localPromocionBRList) {
		this.localPromocionBRList = localPromocionBRList;
	}

	public String getLvPalabrasClave() {
		return lvPalabrasClave;
	}

	public void setLvPalabrasClave(String lvPalabrasClave) {
		this.lvPalabrasClave = lvPalabrasClave;
	}

	public Date getLvFechaCrea() {
		return lvFechaCrea;
	}

	public void setLvFechaCrea(Date lvFechaCrea) {
		this.lvFechaCrea = lvFechaCrea;
	}

	public Date getLvFechaModifica() {
		return lvFechaModifica;
	}

	public void setLvFechaModifica(Date lvFechaModifica) {
		this.lvFechaModifica = lvFechaModifica;
	}

	public String getLvUsrCrea() {
		return lvUsrCrea;
	}

	public void setLvUsrCrea(String lvUsrCrea) {
		this.lvUsrCrea = lvUsrCrea;
	}

	public String getLvUsrModifica() {
		return lvUsrModifica;
	}

	public void setLvUsrModifica(String lvUsrModifica) {
		this.lvUsrModifica = lvUsrModifica;
	}

	public String getLvFoto() {
		return lvFoto;
	}

	public void setLvFoto(String lvFoto) {
		this.lvFoto = lvFoto;
	}

	public String getLvmas() {
		return lvmas;
	}

	public void setLvmas(String lvmas) {
		this.lvmas = lvmas;
	}

	public String getLvpromo() {
		return lvpromo;
	}

	public void setLvpromo(String lvpromo) {
		this.lvpromo = lvpromo;
	}

	public String getLvgeo() {
		return lvgeo;
	}

	public void setLvgeo(String lvgeo) {
		this.lvgeo = lvgeo;
	}

	public String getLvfacebook() {
		return lvfacebook;
	}

	public void setLvfacebook(String lvfacebook) {
		this.lvfacebook = lvfacebook;
	}

	public String getLvtwitter() {
		return lvtwitter;
	}

	public void setLvtwitter(String lvtwitter) {
		this.lvtwitter = lvtwitter;
	}

	public String getLvvideo() {
		return lvvideo;
	}

	public void setLvvideo(String lvvideo) {
		this.lvvideo = lvvideo;
	}
	
}