package com.cashback.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ubicacion database table.
 * 
 */
@Entity
@Table(name="ubicacion")
@NamedQuery(name="Ubicacion.findAll", query="SELECT u FROM Ubicacion u")
public class Ubicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ubi_id")
	private int ubiId;

	@Column(name="ubi_calle_prin")
	private String ubiCallePrin;

	@Column(name="ubi_calle_sec")
	private String ubiCalleSec;

	@Column(name="ubi_estado")
	private String ubiEstado;

	@Column(name="ubi_latitud")
	private String ubiLatitud;

	@Column(name="ubi_longitud")
	private String ubiLongitud;

	@Column(name="ubi_num_predio")
	private String ubiNumPredio;

	@Column(name="ubi_sector")
	private String ubiSector;
	
	@Column(name="ubi_lun_vie_abre")
	private String ubiLunVieAbre;

	@Column(name="ubi_lun_vie_cierra")
	private String ubiLunVieCierra;
	
	@Column(name="ubi_sdf_abre")
	private String ubiSdfAbre;
	
	@Column(name="ubi_sdf_cierra")
	private String ubiSdfCierra;
	
	@Column(name="ubi_frame_mapa")
	private String ubiFrameMapa;
	
	@Column(name="ubi_frame_sv")
	private String ubiFrameSv;
	
	@Column(name="ubi_palabras_clave")
	private String ubiPalabrasClave;
	
	@Column(name="ubi_mas")
	private String ubimas;
	
	@Column(name="ubi_geo")
	private String ubigeo;
	
	@Column(name="ubi_promo")
	private String ubipromo;

	//bi-directional many-to-one association to Ciudad
	@ManyToOne
	@JoinColumn(name="bar_id")
	private Barrio barrio;

	//bi-directional many-to-one association to LocalVenta
	@ManyToOne
	@JoinColumn(name="lv_id")
	private LocalVenta localVenta;

	public Ubicacion() {
	}

	public int getUbiId() {
		return this.ubiId;
	}

	public void setUbiId(int ubiId) {
		this.ubiId = ubiId;
	}

	public String getUbiCallePrin() {
		return this.ubiCallePrin;
	}

	public void setUbiCallePrin(String ubiCallePrin) {
		this.ubiCallePrin = ubiCallePrin;
	}

	public String getUbiCalleSec() {
		return this.ubiCalleSec;
	}

	public void setUbiCalleSec(String ubiCalleSec) {
		this.ubiCalleSec = ubiCalleSec;
	}

	public String getUbiEstado() {
		return this.ubiEstado;
	}

	public void setUbiEstado(String ubiEstado) {
		this.ubiEstado = ubiEstado;
	}

	public String getUbiLatitud() {
		return this.ubiLatitud;
	}

	public void setUbiLatitud(String ubiLatitud) {
		this.ubiLatitud = ubiLatitud;
	}

	public String getUbiLongitud() {
		return this.ubiLongitud;
	}

	public void setUbiLongitud(String ubiLongitud) {
		this.ubiLongitud = ubiLongitud;
	}

	public String getUbiNumPredio() {
		return this.ubiNumPredio;
	}

	public void setUbiNumPredio(String ubiNumPredio) {
		this.ubiNumPredio = ubiNumPredio;
	}

	public String getUbiSector() {
		return this.ubiSector;
	}

	public void setUbiSector(String ubiSector) {
		this.ubiSector = ubiSector;
	}

	public LocalVenta getLocalVenta() {
		return this.localVenta;
	}

	public void setLocalVenta(LocalVenta localVenta) {
		this.localVenta = localVenta;
	}

	public String getUbiLunVieAbre() {
		return ubiLunVieAbre;
	}

	public void setUbiLunVieAbre(String ubiLunVieAbre) {
		this.ubiLunVieAbre = ubiLunVieAbre;
	}

	public String getUbiLunVieCierra() {
		return ubiLunVieCierra;
	}

	public void setUbiLunVieCierra(String ubiLunVieCierra) {
		this.ubiLunVieCierra = ubiLunVieCierra;
	}

	public String getUbiSdfAbre() {
		return ubiSdfAbre;
	}

	public void setUbiSdfAbre(String ubiSdfAbre) {
		this.ubiSdfAbre = ubiSdfAbre;
	}

	public String getUbiSdfCierra() {
		return ubiSdfCierra;
	}

	public void setUbiSdfCierra(String ubiSdfCierra) {
		this.ubiSdfCierra = ubiSdfCierra;
	}

	public String getUbiFrameMapa() {
		return ubiFrameMapa;
	}

	public void setUbiFrameMapa(String ubiFrameMapa) {
		this.ubiFrameMapa = ubiFrameMapa;
	}

	public String getUbiFrameSv() {
		return ubiFrameSv;
	}

	public void setUbiFrameSv(String ubiFrameSv) {
		this.ubiFrameSv = ubiFrameSv;
	}

	public String getUbiPalabrasClave() {
		return ubiPalabrasClave;
	}

	public void setUbiPalabrasClave(String ubiPalabrasClave) {
		this.ubiPalabrasClave = ubiPalabrasClave;
	}

	public Barrio getBarrio() {
		return barrio;
	}

	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
	}

	public String getUbimas() {
		return ubimas;
	}

	public void setUbimas(String ubimas) {
		this.ubimas = ubimas;
	}

	public String getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}

	public String getUbipromo() {
		return ubipromo;
	}

	public void setUbipromo(String ubipromo) {
		this.ubipromo = ubipromo;
	}
	
	
	

}