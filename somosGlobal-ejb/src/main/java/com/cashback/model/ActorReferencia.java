package com.cashback.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the actor_referencia database table.
 * 
 */
@Entity
@Table(name = "actor_referencia")
@NamedQueries({
		@NamedQuery(name = "ActorReferencia.findAll", query = "SELECT a FROM ActorReferencia a"),
		@NamedQuery(name = "ActorReferencia.findByActorReferencia", query = "SELECT ar FROM ActorReferencia ar WHERE ar.actor =:actor AND ar.catalogoGen.tipoCg =:tipoCg AND ar.catalogoGen.refCg =:refCg"),
		@NamedQuery(name = "ActorReferencia.findAllByActorAndPadreCatalogoGen", query = "SELECT ar FROM ActorReferencia ar WHERE ar.actor =:actor AND ar.catalogoGen.catalogoGen =:padreCatalogoGen"),
})
public class ActorReferencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ar")
	private int idAr;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fec_crea_ar")
	private Date fecCreaAr;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fec_mod_ar")
	private Date fecModAr;

	@Column(name = "usr_crea_ar")
	private String usrCreaAr;

	@Column(name = "usr_mod_ar")
	private String usrModAr;

	@Column(name = "val1_ar")
	private String val1Ar;

	@Column(name = "val2_ar")
	private String val2Ar;
	
	@Column(name = "val3_ar")
	private String val3Ar;
	
	@Column(name = "val4_ar")
	private String val4ar;

	@Column(name = "estado_ar")
	private String estado_ar;
	
	@Column(name = "url_gmap_ar")
	private String urlGmapAr;
	
	@Column(name = "url_sview_ar")
	private String urlSviewAr;
	
	@Column(name = "latitud_ar")
	private String latitudAr;
	
	@Column(name = "longitud_ar")
	private String longitudAr;
	
	@Column(name = "sector_ar")
	private String sector_ar;
	
	
	// bi-directional many-to-one association to Actor
	@ManyToOne
	@JoinColumn(name = "id_act")
	private Actor actor;

	// bi-directional many-to-one association to CatalogoGen
	@ManyToOne
	@JoinColumn(name = "id_cg")
	private CatalogoGen catalogoGen;

	@Transient
	private Localidad localidad;
	
	public ActorReferencia() {
	}

	public int getIdAr() {
		return this.idAr;
	}

	public void setIdAr(int idAr) {
		this.idAr = idAr;
	}

	public String getUsrCreaAr() {
		return this.usrCreaAr;
	}

	public void setUsrCreaAr(String usrCreaAr) {
		this.usrCreaAr = usrCreaAr;
	}

	public String getUsrModAr() {
		return this.usrModAr;
	}

	public void setUsrModAr(String usrModAr) {
		this.usrModAr = usrModAr;
	}

	public String getVal1Ar() {
		return this.val1Ar;
	}

	public void setVal1Ar(String val1Ar) {
		this.val1Ar = val1Ar;
	}

	public String getVal2Ar() {
		return this.val2Ar;
	}

	public void setVal2Ar(String val2Ar) {
		this.val2Ar = val2Ar;
	}

	public Actor getActor() {
		return this.actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public CatalogoGen getCatalogoGen() {
		return this.catalogoGen;
	}

	public void setCatalogoGen(CatalogoGen catalogoGen) {
		this.catalogoGen = catalogoGen;
	}

	public Date getFecCreaAr() {
		return fecCreaAr;
	}

	public void setFecCreaAr(Date fecCreaAr) {
		this.fecCreaAr = fecCreaAr;
	}

	public Date getFecModAr() {
		return fecModAr;
	}

	public void setFecModAr(Date fecModAr) {
		this.fecModAr = fecModAr;
	}

	public String getVal3Ar() {
		return val3Ar;
	}

	public void setVal3Ar(String val3Ar) {
		this.val3Ar = val3Ar;
	}


	public String getEstado_ar() {
		return estado_ar;
	}

	public void setEstado_ar(String estado_ar) {
		this.estado_ar = estado_ar;
	}

	public String getVal4ar() {
		return val4ar;
	}

	public void setVal4ar(String val4ar) {
		this.val4ar = val4ar;
	}

	public String getUrlGmapAr() {
		return urlGmapAr;
	}

	public void setUrlGmapAr(String urlGmapAr) {
		this.urlGmapAr = urlGmapAr;
	}

	public String getUrlSviewAr() {
		return urlSviewAr;
	}

	public void setUrlSviewAr(String urlSviewAr) {
		this.urlSviewAr = urlSviewAr;
	}

	public String getLatitudAr() {
		return latitudAr;
	}

	public void setLatitudAr(String latitudAr) {
		this.latitudAr = latitudAr;
	}

	public String getLongitudAr() {
		return longitudAr;
	}

	public void setLongitudAr(String longitudAr) {
		this.longitudAr = longitudAr;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getSector_ar() {
		return sector_ar;
	}

	public void setSector_ar(String sector_ar) {
		this.sector_ar = sector_ar;
	}

}