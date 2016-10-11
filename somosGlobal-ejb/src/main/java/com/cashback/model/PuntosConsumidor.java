/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashback.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author somosGlobal
 */
@Entity
@Table(name = "puntos_consumidor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PuntosConsumidor.findAll", query = "SELECT p FROM PuntosConsumidor p"),
    @NamedQuery(name = "PuntosConsumidor.findById", query = "SELECT p FROM PuntosConsumidor p WHERE p.id = :id"),
    @NamedQuery(name = "PuntosConsumidor.findByTotalPuntos", query = "SELECT p FROM PuntosConsumidor p WHERE p.totalPuntos = :totalPuntos"),
    @NamedQuery(name = "PuntosConsumidor.findByNumDocumentoConsumidor", query = "SELECT p FROM PuntosConsumidor p WHERE p.numDocumentoConsumidor = :numDocumentoConsumidor")})
public class PuntosConsumidor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "total_puntos")
    private Integer totalPuntos;
    @Size(max = 15)
    @Column(name = "num_documento_consumidor")
    private String numDocumentoConsumidor;
    
    @Temporal(TemporalType.DATE)
    @Column(name="vig_desde")
    private Date vigDesde;

    @Temporal(TemporalType.DATE)
    @Column(name="vig_hasta")
    private Date vigHasta;

    //bi-directional many-to-one association to Persona
    @ManyToOne
    @JoinColumn(name="id_consumidor")
    private Persona persona;

    public PuntosConsumidor() {
    }

    public PuntosConsumidor(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalPuntos() {
        return totalPuntos;
    }

    public void setTotalPuntos(Integer totalPuntos) {
        this.totalPuntos = totalPuntos;
    }

    public String getNumDocumentoConsumidor() {
        return numDocumentoConsumidor;
    }

    public void setNumDocumentoConsumidor(String numDocumentoConsumidor) {
        this.numDocumentoConsumidor = numDocumentoConsumidor;
    }

    public Date getVigDesde() {
        return vigDesde;
    }

    public void setVigDesde(Date vigDesde) {
        this.vigDesde = vigDesde;
    }

    public Date getVigHasta() {
        return vigHasta;
    }

    public void setVigHasta(Date vigHasta) {
        this.vigHasta = vigHasta;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PuntosConsumidor)) {
            return false;
        }
        PuntosConsumidor other = (PuntosConsumidor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cashback.model.PuntosConsumidor[ id=" + id + " ]";
    }
    
}
