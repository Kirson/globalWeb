/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashback.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "transacciones_consumidor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransaccionesConsumidor.findAll", query = "SELECT t FROM TransaccionesConsumidor t"),
    @NamedQuery(name = "TransaccionesConsumidor.findById", query = "SELECT t FROM TransaccionesConsumidor t WHERE t.id = :id"),
    @NamedQuery(name = "TransaccionesConsumidor.findByNumDocumentoConsumidor", query = "SELECT t FROM TransaccionesConsumidor t WHERE t.numDocumentoConsumidor = :numDocumentoConsumidor"),
    @NamedQuery(name = "TransaccionesConsumidor.findByNumDocumentoBeneficiario", query = "SELECT t FROM TransaccionesConsumidor t WHERE t.numDocumentoBeneficiario = :numDocumentoBeneficiario"),
    @NamedQuery(name = "TransaccionesConsumidor.findByPuntosTransaccion", query = "SELECT t FROM TransaccionesConsumidor t WHERE t.puntosTransaccion = :puntosTransaccion"),
    @NamedQuery(name = "TransaccionesConsumidor.findByPuntosGanados", query = "SELECT t FROM TransaccionesConsumidor t WHERE t.puntosGanados = :puntosGanados"),
    @NamedQuery(name = "TransaccionesConsumidor.findByFechaTransaccion", query = "SELECT t FROM TransaccionesConsumidor t WHERE t.fechaTransaccion = :fechaTransaccion"),
    @NamedQuery(name = "TransaccionesConsumidor.findByValorCompra", query = "SELECT t FROM TransaccionesConsumidor t WHERE t.valorCompra = :valorCompra")})
public class TransaccionesConsumidor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 15)
    @Column(name = "num_documento_consumidor")
    private String numDocumentoConsumidor;
    @Size(max = 15)
    @Column(name = "num_documento_beneficiario")
    private String numDocumentoBeneficiario;
    @Column(name = "puntos_transaccion")
    private Integer puntosTransaccion;
    @Column(name = "puntos_ganados")
    private Integer puntosGanados;
    @Column(name = "fecha_transaccion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTransaccion;
    @Column(name = "valor_compra")
    private BigDecimal valorCompra;
    

    //bi-directional many-to-one association to Persona
    @ManyToOne
    @JoinColumn(name="id_consumidor")
    private Persona consumidor;
    //bi-directional many-to-one association to Persona
    @ManyToOne
    @JoinColumn(name="id_beneficiario")
    private Persona beneficiario;
    
     //bi-directional many-to-one association to Persona
    @ManyToOne
    @JoinColumn(name="lv_local")
    private LocalVenta localVenta;

    public TransaccionesConsumidor() {
    }

    public TransaccionesConsumidor(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumDocumentoConsumidor() {
        return numDocumentoConsumidor;
    }

    public void setNumDocumentoConsumidor(String numDocumentoConsumidor) {
        this.numDocumentoConsumidor = numDocumentoConsumidor;
    }

    public String getNumDocumentoBeneficiario() {
        return numDocumentoBeneficiario;
    }

    public void setNumDocumentoBeneficiario(String numDocumentoBeneficiario) {
        this.numDocumentoBeneficiario = numDocumentoBeneficiario;
    }

    public Integer getPuntosTransaccion() {
        return puntosTransaccion;
    }

    public void setPuntosTransaccion(Integer puntosTransaccion) {
        this.puntosTransaccion = puntosTransaccion;
    }

    public Integer getPuntosGanados() {
        return puntosGanados;
    }

    public void setPuntosGanados(Integer puntosGanados) {
        this.puntosGanados = puntosGanados;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Persona getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Persona consumidor) {
        this.consumidor = consumidor;
    }

    public Persona getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Persona beneficiario) {
        this.beneficiario = beneficiario;
    }

    public LocalVenta getLocalVenta() {
        return localVenta;
    }

    public void setLocalVenta(LocalVenta localVenta) {
        this.localVenta = localVenta;
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
        if (!(object instanceof TransaccionesConsumidor)) {
            return false;
        }
        TransaccionesConsumidor other = (TransaccionesConsumidor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cashback.model.TransaccionesConsumidor[ id=" + id + " ]";
    }
    
}
