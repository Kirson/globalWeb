package com.cashback.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the catalogo_gen database table.
 * 
 */
@Entity
@Table(name = "catalogo_gen")
@NamedQueries({
		@NamedQuery(name = "CatalogoGen.findAll", query = "SELECT c FROM CatalogoGen c"),
		@NamedQuery(name = "CatalogoGen.findByTipoRef", query = "SELECT cg FROM CatalogoGen cg WHERE cg.tipoCg =:tipoCg AND cg.refCg =:refCg"),
		@NamedQuery(name = "CatalogoGen.findByTipoRef02", query = "SELECT cg FROM CatalogoGen cg WHERE cg.tipoCg =:tipoCg AND cg.ref02Cg =:ref02Cg ORDER BY cg.nombreCg"),
		@NamedQuery(name = "CatalogoGen.findOnlyFather", query = "SELECT cg FROM CatalogoGen cg WHERE (cg.catalogoGen IS NULL OR cg.catalogoGen = cg) AND cg.tipoCg =:tipoCg ORDER BY cg.nombreCg"),
		@NamedQuery(name = "CatalogoGen.findByFather", query = "SELECT cg FROM CatalogoGen cg WHERE cg.catalogoGen =:catalogoGen AND cg.tipoCg LIKE :tipoCg ORDER BY cg.nombreCg"),
		@NamedQuery(name = "CatalogoGen.findByTipoCgOrderRefCg", query = "SELECT c FROM CatalogoGen c WHERE c.tipoCg LIKE :tipoCg ORDER BY c.refCg"),
		@NamedQuery(name = "CatalogoGen.findAllByFather", query = "SELECT c FROM CatalogoGen c WHERE c.catalogoGen =:padreCatalogoGen ORDER BY c.ref02Cg, c.nombreCg"),
		@NamedQuery(name = "CatalogoGen.findByTipoCg", query = "SELECT c FROM CatalogoGen c WHERE c.tipoCg =:tipoCg ORDER BY c.idCg")
})
public class CatalogoGen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cg")
	private int idCg;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fec_crea_cg")
	private Date fecCreaCg;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fec_mod_cg")
	private Date fecModCg;

	@Column(name = "nombre_cg")
	private String nombreCg;

	@Column(name = "ref_cg")
	private String refCg;

	@Column(name = "ref02_cg")
	private String ref02Cg;

	@Column(name = "tipo_cg")
	private String tipoCg;

	@Column(name = "usr_crea_cg")
	private String usrCreaCg;

	@Column(name = "usr_mod_cg")
	private String usrModCg;

	// bi-directional many-to-one association to ActorReferencia
	@OneToMany(mappedBy = "catalogoGen")
	private List<ActorReferencia> actorReferencias;

	// bi-directional many-to-one association to ActorRol
	@OneToMany(mappedBy = "catalogoGen")
	private List<ActorRol> actorRols;

	// bi-directional many-to-one association to Actor
	@ManyToOne
	@JoinColumn(name = "id_cg_padre")
	private CatalogoGen catalogoGen;

	// bi-directional many-to-one association to Actor
	@OneToMany(mappedBy = "catalogoGen")
	private List<CatalogoGen> catalogoGens;

	public CatalogoGen() {
	}

	public int getIdCg() {
		return this.idCg;
	}

	public void setIdCg(int idCg) {
		this.idCg = idCg;
	}

	public Date getFecCreaCg() {
		return this.fecCreaCg;
	}

	public void setFecCreaCg(Date fecCreaCg) {
		this.fecCreaCg = fecCreaCg;
	}

	public Date getFecModCg() {
		return this.fecModCg;
	}

	public void setFecModCg(Date fecModCg) {
		this.fecModCg = fecModCg;
	}

	public String getNombreCg() {
		return this.nombreCg;
	}

	public void setNombreCg(String nombreCg) {
		this.nombreCg = nombreCg;
	}

	public String getRefCg() {
		return this.refCg;
	}

	public void setRefCg(String refCg) {
		this.refCg = refCg;
	}

	public String getTipoCg() {
		return this.tipoCg;
	}

	public void setTipoCg(String tipoCg) {
		this.tipoCg = tipoCg;
	}

	public String getUsrCreaCg() {
		return this.usrCreaCg;
	}

	public void setUsrCreaCg(String usrCreaCg) {
		this.usrCreaCg = usrCreaCg;
	}

	public String getUsrModCg() {
		return this.usrModCg;
	}

	public void setUsrModCg(String usrModCg) {
		this.usrModCg = usrModCg;
	}

	public List<ActorReferencia> getActorReferencias() {
		return this.actorReferencias;
	}

	public void setActorReferencias(List<ActorReferencia> actorReferencias) {
		this.actorReferencias = actorReferencias;
	}

	public ActorReferencia addActorReferencia(ActorReferencia actorReferencia) {
		getActorReferencias().add(actorReferencia);
		actorReferencia.setCatalogoGen(this);

		return actorReferencia;
	}

	public ActorReferencia removeActorReferencia(ActorReferencia actorReferencia) {
		getActorReferencias().remove(actorReferencia);
		actorReferencia.setCatalogoGen(null);

		return actorReferencia;
	}

	public String getRef02Cg() {
		return ref02Cg;
	}

	public void setRef02Cg(String ref02Cg) {
		this.ref02Cg = ref02Cg;
	}

	public List<ActorRol> getActorRols() {
		return actorRols;
	}

	public void setActorRols(List<ActorRol> actorRols) {
		this.actorRols = actorRols;
	}

	public CatalogoGen getCatalogoGen() {
		return catalogoGen;
	}

	public void setCatalogoGen(CatalogoGen catalogoGen) {
		this.catalogoGen = catalogoGen;
	}

	public List<CatalogoGen> getCatalogoGens() {
		return catalogoGens;
	}

	public void setCatalogoGens(List<CatalogoGen> catalogoGens) {
		this.catalogoGens = catalogoGens;
	}

}