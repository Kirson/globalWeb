package com.cashback.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the actor database table.
 * 
 */
@Entity
@Table(name = "actor")
@NamedQueries({
		@NamedQuery(name = "Actor.findAll", query = "SELECT a FROM Actor a"),
		@NamedQuery(name = "Actor.findByTipoNombresApellidosCedula", query = "SELECT act FROM Actor act WHERE act.tipoAct LIKE :tipoAct AND act.nombresAct LIKE :nombresAct AND act.apellidosAct LIKE :apellidosAct AND act.cedrucpasAct LIKE :cedrucpasAct AND act.estadoAct LIKE :estadoAct ORDER BY act.nombresAct"),
		@NamedQuery(name = "Actor.findByCedrucpasAct", query = "SELECT a FROM Actor a WHERE a.cedrucpasAct =:cedrucpasAct"),
		@NamedQuery(name = "Actor.findAllByRolNegocio", query = "SELECT DISTINCT ar.actor FROM ActorRol ar WHERE ar.catalogoGen =:rolNegocio AND ar.estadoArol LIKE :estadoArol"),
		@NamedQuery(name = "Actor.findAllByPadreInRolNegocioAndCategoria", query = "SELECT ar.actor FROM ActorRol ar WHERE ar.actor.catId LIKE :catId AND ar.actorRol.actor =:actor AND ar.estadoArol LIKE :estadoArol"),
		@NamedQuery(name = "Actor.findAllByCategoriaInHijosFromRolNegocio", query = "SELECT DISTINCT a FROM Actor a WHERE a.estadoAct LIKE :estadoAct AND a.idAct IN (SELECT ar.actorRol.actor.idAct FROM ActorRol ar WHERE ar.actorRol.catalogoGen =:rolNegocio AND ar.actor.catId LIKE :catId AND ar.estadoArol LIKE :estadoArol)"),
		@NamedQuery(name = "Actor.findAllByCedRucPasAndRazonSocialNombre", query = "SELECT a FROM Actor a WHERE a.cedrucpasAct LIKE :cedRucPas AND a.razonSocialAct LIKE :razonSocialAct AND a.nombresAct LIKE :nombresAct AND a.apellidosAct LIKE :apellidosAct AND a.estadoAct LIKE :estadoAct ORDER BY a.razonSocialAct, a.apellidosAct, a.nombresAct") })
public class Actor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_act")
	private int idAct;

	@Column(name = "actividad_act")
	private String actividadAct;

	@Column(name = "apellidos_act")
	private String apellidosAct;

	@Column(name = "cal_prin_act")
	private String calPrinAct;

	@Column(name = "cal_sec_act")
	private String calSecAct;

	@Column(name = "cat_id")
	private String catId;

	@Column(name = "cedrucpas_act")
	private String cedrucpasAct;

	@Column(name = "estado_act")
	private String estadoAct;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fec_crea_act")
	private Date fecCreaAct;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fec_mod_act")
	private Date fecModAct;

	@Temporal(TemporalType.DATE)
	@Column(name = "fec_nac_act")
	private Date fecNacAct;

	@Column(name = "foto_act")
	private String fotoAct;

	@Column(name = "url_direccion_act")
	private String urlDireccionAct;

	@Column(name = "url_sview_act")
	private String urlSViewAct;

	@Column(name = "latitud_act")
	private String latitudAct;

	@Column(name = "logo_act")
	private String logoAct;

	@Column(name = "longitud_act")
	private String longitudAct;

	@Column(name = "mail_act")
	private String mailAct;

	@Column(name = "nombres_act")
	private String nombresAct;

	@Column(name = "nombre_comercial_act")
	private String nombreComercialAct;

	@Column(name = "representante_act")
	private String representanteAct;

	@Column(name = "num_pre_act")
	private String numPreAct;

	@Column(name = "palabras_clave_act")
	private String palabrasClaveAct;

	@Column(name = "ranking_act")
	private int rankingAct;

	@Column(name = "razon_social_act")
	private String razonSocialAct;

	@Column(name = "sector_act")
	private String sectorAct;

	@Column(name = "servicio_act")
	private String servicioAct;

	@Column(name = "sexo_act")
	private String sexoAct;

	@Column(name = "slogan_act")
	private String sloganAct;

	@Column(name = "tipo_act")
	private String tipoAct;

	@Column(name = "usr_crea_act")
	private String usrCreaAct;

	@Column(name = "usr_mod_act")
	private String usrModAct;

	// bi-directional many-to-one association to ActorReferencia
	@OneToMany(mappedBy = "actor")
	private List<ActorReferencia> actorReferencias;

	@OneToMany(mappedBy = "actor")
	private List<Usuario> usuarios;

	@ManyToOne
	@JoinColumn(name = "loc_id")
	private Localidad localidad;

	@OneToMany(mappedBy = "actor", cascade = CascadeType.PERSIST)
	private List<ActorRol> actorRols;

	@Transient
	private List<ActorReferencia> telefonosActor;

	@Transient
	private List<ActorReferencia> correosActor;

	@Transient
	private List<ActorReferencia> horariosActor;

	@Transient
	private List<ActorReferencia> direccionesActor;

	@Transient
	private List<ActorReferencia> galeriaImgActor;

	@Transient
	private List<ActorReferencia> promocionImgActor;

	@Transient
	private List<Actor> actoresHijos;

	public Actor() {
	}

	public int getIdAct() {
		return this.idAct;
	}

	public void setIdAct(int idAct) {
		this.idAct = idAct;
	}

	public String getActividadAct() {
		return this.actividadAct;
	}

	public void setActividadAct(String actividadAct) {
		this.actividadAct = actividadAct;
	}

	public String getApellidosAct() {
		return this.apellidosAct;
	}

	public void setApellidosAct(String apellidosAct) {
		this.apellidosAct = apellidosAct;
	}

	public String getCalPrinAct() {
		return this.calPrinAct;
	}

	public void setCalPrinAct(String calPrinAct) {
		this.calPrinAct = calPrinAct;
	}

	public String getCalSecAct() {
		return this.calSecAct;
	}

	public void setCalSecAct(String calSecAct) {
		this.calSecAct = calSecAct;
	}

	public String getCatId() {
		return this.catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getCedrucpasAct() {
		return this.cedrucpasAct;
	}

	public void setCedrucpasAct(String cedrucpasAct) {
		this.cedrucpasAct = cedrucpasAct;
	}

	public String getEstadoAct() {
		return this.estadoAct;
	}

	public void setEstadoAct(String estadoAct) {
		this.estadoAct = estadoAct;
	}

	public Date getFecCreaAct() {
		return this.fecCreaAct;
	}

	public void setFecCreaAct(Date fecCreaAct) {
		this.fecCreaAct = fecCreaAct;
	}

	public Date getFecModAct() {
		return this.fecModAct;
	}

	public void setFecModAct(Date fecModAct) {
		this.fecModAct = fecModAct;
	}

	public Date getFecNacAct() {
		return this.fecNacAct;
	}

	public void setFecNacAct(Date fecNacAct) {
		this.fecNacAct = fecNacAct;
	}

	public String getFotoAct() {
		return this.fotoAct;
	}

	public void setFotoAct(String fotoAct) {
		this.fotoAct = fotoAct;
	}

	public String getLatitudAct() {
		return this.latitudAct;
	}

	public void setLatitudAct(String latitudAct) {
		this.latitudAct = latitudAct;
	}

	public String getLogoAct() {
		return this.logoAct;
	}

	public void setLogoAct(String logoAct) {
		this.logoAct = logoAct;
	}

	public String getMailAct() {
		return this.mailAct;
	}

	public void setMailAct(String mailAct) {
		this.mailAct = mailAct;
	}

	public String getNombresAct() {
		return this.nombresAct;
	}

	public void setNombresAct(String nombresAct) {
		this.nombresAct = nombresAct;
	}

	public String getNumPreAct() {
		return this.numPreAct;
	}

	public void setNumPreAct(String numPreAct) {
		this.numPreAct = numPreAct;
	}

	public String getPalabrasClaveAct() {
		return this.palabrasClaveAct;
	}

	public void setPalabrasClaveAct(String palabrasClaveAct) {
		this.palabrasClaveAct = palabrasClaveAct;
	}

	public int getRankingAct() {
		return this.rankingAct;
	}

	public void setRankingAct(int rankingAct) {
		this.rankingAct = rankingAct;
	}

	public String getRazonSocialAct() {
		return this.razonSocialAct;
	}

	public void setRazonSocialAct(String razonSocialAct) {
		this.razonSocialAct = razonSocialAct;
	}

	public String getSectorAct() {
		return this.sectorAct;
	}

	public void setSectorAct(String sectorAct) {
		this.sectorAct = sectorAct;
	}

	public String getServicioAct() {
		return this.servicioAct;
	}

	public void setServicioAct(String servicioAct) {
		this.servicioAct = servicioAct;
	}

	public String getSloganAct() {
		return this.sloganAct;
	}

	public void setSloganAct(String sloganAct) {
		this.sloganAct = sloganAct;
	}

	public String getTipoAct() {
		return this.tipoAct;
	}

	public void setTipoAct(String tipoAct) {
		this.tipoAct = tipoAct;
	}

	public String getUsrCreaAct() {
		return this.usrCreaAct;
	}

	public void setUsrCreaAct(String usrCreaAct) {
		this.usrCreaAct = usrCreaAct;
	}

	public String getUsrModAct() {
		return this.usrModAct;
	}

	public void setUsrModAct(String usrModAct) {
		this.usrModAct = usrModAct;
	}

	public List<ActorReferencia> getActorReferencias() {
		return this.actorReferencias;
	}

	public void setActorReferencias(List<ActorReferencia> actorReferencias) {
		this.actorReferencias = actorReferencias;
	}

	public ActorReferencia addActorReferencia(ActorReferencia actorReferencia) {
		getActorReferencias().add(actorReferencia);
		actorReferencia.setActor(this);

		return actorReferencia;
	}

	public ActorReferencia removeActorReferencia(ActorReferencia actorReferencia) {
		getActorReferencias().remove(actorReferencia);
		actorReferencia.setActor(null);

		return actorReferencia;
	}

	public String getUrlDireccionAct() {
		return urlDireccionAct;
	}

	public void setUrlDireccionAct(String urlDireccionAct) {
		this.urlDireccionAct = urlDireccionAct;
	}

	public String getUrlSViewAct() {
		return urlSViewAct;
	}

	public void setUrlSViewAct(String urlSViewAct) {
		this.urlSViewAct = urlSViewAct;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getRepresentanteAct() {
		return representanteAct;
	}

	public void setRepresentanteAct(String representanteAct) {
		this.representanteAct = representanteAct;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getLongitudAct() {
		return longitudAct;
	}

	public void setLongitudAct(String longitudAct) {
		this.longitudAct = longitudAct;
	}

	public String getSexoAct() {
		return sexoAct;
	}

	public void setSexoAct(String sexoAct) {
		this.sexoAct = sexoAct;
	}

	public List<ActorRol> getActorRols() {
		return actorRols;
	}

	public void setActorRols(List<ActorRol> actorRols) {
		this.actorRols = actorRols;
	}

	public List<ActorReferencia> getHorariosActor() {
		return horariosActor;
	}

	public void setHorariosActor(List<ActorReferencia> horariosActor) {
		this.horariosActor = horariosActor;
	}

	public List<ActorReferencia> getDireccionesActor() {
		return direccionesActor;
	}

	public void setDireccionesActor(List<ActorReferencia> direccionesActor) {
		this.direccionesActor = direccionesActor;
	}

	public List<ActorReferencia> getTelefonosActor() {
		return telefonosActor;
	}

	public void setTelefonosActor(List<ActorReferencia> telefonosActor) {
		this.telefonosActor = telefonosActor;
	}

	public List<ActorReferencia> getCorreosActor() {
		return correosActor;
	}

	public void setCorreosActor(List<ActorReferencia> correosActor) {
		this.correosActor = correosActor;
	}

	public List<ActorReferencia> getGaleriaImgActor() {
		return galeriaImgActor;
	}

	public void setGaleriaImgActor(List<ActorReferencia> galeriaImgActor) {
		this.galeriaImgActor = galeriaImgActor;
	}

	public List<ActorReferencia> getPromocionImgActor() {
		return promocionImgActor;
	}

	public void setPromocionImgActor(List<ActorReferencia> promocionImgActor) {
		this.promocionImgActor = promocionImgActor;
	}

	public String getNombreComercialAct() {
		return nombreComercialAct;
	}

	public void setNombreComercialAct(String nombreComercialAct) {
		this.nombreComercialAct = nombreComercialAct;
	}

	public List<Actor> getActoresHijos() {
		return actoresHijos;
	}

	public void setActoresHijos(List<Actor> actoresHijos) {
		this.actoresHijos = actoresHijos;
	}

}

// public Actor addActor(Actor actor) {
// getActors().add(actor);
// actor.setActor(this);
//
// return actor;
// }
//
// public Actor removeActor(Actor actor) {
// getActors().remove(actor);
// actor.setActor(null);
//
// return actor;
// }
