package com.cashback.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the actor_rol database table.
 * 
 */
@Entity
@Table(name = "actor_rol")
@NamedQueries({
		@NamedQuery(name = "ActorRol.findAll", query = "SELECT a FROM ActorRol a"),
		@NamedQuery(name = "ActorRol.findByActorAndRolNegocio", query = "SELECT a FROM ActorRol a WHERE a.actor =:actor AND a.catalogoGen =:rolNegocio AND a.estadoArol LIKE :estadoArol"),
		@NamedQuery(name = "ActorRol.findByActortTipoCuenta", query = "SELECT COUNT(a) FROM ActorRol a WHERE a.actor =:actor AND a.catalogoGen.refCg =:refCg AND a.estadoArol LIKE :estadoArol"),
		@NamedQuery(name = "ActorRol.findByPadre", query = "SELECT ar FROM ActorRol ar WHERE ar.actorRol.actor =:padreActor ORDER BY ar.actor.razonSocialAct, ar.actor.nombresAct"),
		@NamedQuery(name = "ActorRol.findByActorAndRolNegocioAndPorcentaje", query = "SELECT COUNT(ar) FROM ActorRol ar WHERE ar.actor =:actor AND ar.catalogoGen =:rolNegocio AND ar.prcArol =:prcArol"),
		@NamedQuery(name = "ActorRol.findByCedrucpasActAndPadreActor", query = "SELECT ar FROM ActorRol ar WHERE ar.actor.cedrucpasAct LIKE :cedrucpasAct AND ar.actorRol.actor =:padreActor ORDER BY ar.actor.razonSocialAct, ar.actor.apellidosAct, ar.actor.nombresAct"),
		@NamedQuery(name = "ActorRol.findAllByRolNegocioAndCategoria", query = "SELECT ar.actor FROM ActorRol ar WHERE ar.catalogoGen =:rolNegocio AND ar.actor.catId LIKE :catId"),
		@NamedQuery(name = "ActorRol.findAllByPalabraClaveAndRolNegocio", query = "SELECT DISTINCT ar.actor FROM ActorRol ar WHERE ar.catalogoGen =:rolNegocio AND ar.actor.palabrasClaveAct LIKE :palabraClaveAct")
		})
public class ActorRol implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_arol")
	private Integer idArol;

	@Column(name = "estado_arol")
	private String estadoArol;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fec_crea_arol")
	private Date fecCreaArol;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fec_mod_arol")
	private Date fecModArol;

	@Column(name = "prc_arol")
	private BigDecimal prcArol;

	// bi-directional many-to-one association to ActorRol
	@ManyToOne
	@JoinColumn(name = "id_arol_padre")
	private ActorRol actorRol;

	// bi-directional many-to-one association to ActorRol
	@OneToMany(mappedBy = "actorRol")
	private List<ActorRol> actorRols;

	@ManyToOne
	@JoinColumn(name = "id_act")
	private Actor actor;

	@ManyToOne
	@JoinColumn(name = "id_cg")
	private CatalogoGen catalogoGen;

	public ActorRol() {
	}

	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			System.out.println(" no se puede duplicar");
		}
		return obj;
	}

	public String getEstadoArol() {
		return this.estadoArol;
	}

	public void setEstadoArol(String estadoArol) {
		this.estadoArol = estadoArol;
	}

	public Date getFecCreaArol() {
		return this.fecCreaArol;
	}

	public void setFecCreaArol(Date fecCreaArol) {
		this.fecCreaArol = fecCreaArol;
	}

	public BigDecimal getPrcArol() {
		return this.prcArol;
	}

	public void setPrcArol(BigDecimal prcArol) {
		this.prcArol = prcArol;
	}

	public ActorRol getActorRol() {
		return this.actorRol;
	}

	public void setActorRol(ActorRol actorRol) {
		this.actorRol = actorRol;
	}

	public List<ActorRol> getActorRols() {
		return this.actorRols;
	}

	public void setActorRols(List<ActorRol> actorRols) {
		this.actorRols = actorRols;
	}

	public ActorRol addActorRol(ActorRol actorRol) {
		getActorRols().add(actorRol);
		actorRol.setActorRol(this);

		return actorRol;
	}

	public ActorRol removeActorRol(ActorRol actorRol) {
		getActorRols().remove(actorRol);
		actorRol.setActorRol(null);

		return actorRol;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public CatalogoGen getCatalogoGen() {
		return catalogoGen;
	}

	public void setCatalogoGen(CatalogoGen catalogoGen) {
		this.catalogoGen = catalogoGen;
	}

	public Date getFecModArol() {
		return fecModArol;
	}

	public void setFecModArol(Date fecModArol) {
		this.fecModArol = fecModArol;
	}

	public Integer getIdArol() {
		return idArol;
	}

	public void setIdArol(Integer idArol) {
		this.idArol = idArol;
	}

}