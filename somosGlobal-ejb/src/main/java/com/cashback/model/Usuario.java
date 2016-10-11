package com.cashback.model;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name = "usuario")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id")
	private int usrId;

	@Column(name = "usr_estado")
	private String usrEstado;

	@Column(name = "usr_nombre")
	private String usrNombre;

	@Column(name = "usr_nombre2")
	private String usrNombre2;

	@Column(name = "usr_cambia_password")
	private String usrCambiaPassword;
	
	@Column(name = "usr_password")
	private String usrPassword;

	@Column(name = "usr_creado_por")
	private String usrCreadoPor;

	@Column(name = "usr_mod_por")
	private String usrModPor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "usr_fec_crea")
	private Date usrFecCrea;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "usr_fec_mod")
	private Date usrFecMod;

	@ManyToOne
	@JoinColumn(name = "prf_id")
	private Perfil perfil;

	@ManyToOne
	@JoinColumn(name = "id_act")
	private Actor actor;

	public Usuario() {
	}

	public int getUsrId() {
		return this.usrId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	public String getUsrEstado() {
		return this.usrEstado;
	}

	public void setUsrEstado(String usrEstado) {
		this.usrEstado = usrEstado;
	}

	public String getUsrNombre() {
		return this.usrNombre;
	}

	public void setUsrNombre(String usrNombre) {
		this.usrNombre = usrNombre;
	}

	public String getUsrPassword() {
		return this.usrPassword;
	}

	public void setUsrPassword(String usrPassword) {
		this.usrPassword = usrPassword;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public String getUsrCreadoPor() {
		return usrCreadoPor;
	}

	public void setUsrCreadoPor(String usrCreadoPor) {
		this.usrCreadoPor = usrCreadoPor;
	}

	public String getUsrModPor() {
		return usrModPor;
	}

	public void setUsrModPor(String usrModPor) {
		this.usrModPor = usrModPor;
	}

	public Date getUsrFecCrea() {
		return usrFecCrea;
	}

	public void setUsrFecCrea(Date usrFecCrea) {
		this.usrFecCrea = usrFecCrea;
	}

	public Date getUsrFecMod() {
		return usrFecMod;
	}

	public void setUsrFecMod(Date usrFecMod) {
		this.usrFecMod = usrFecMod;
	}

	public String getUsrNombre2() {
		return usrNombre2;
	}

	public void setUsrNombre2(String usrNombre2) {
		this.usrNombre2 = usrNombre2;
	}

	public String passwordToMd5(String password) {
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++)
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getUsrCambiaPassword() {
		return usrCambiaPassword;
	}

	public void setUsrCambiaPassword(String usrCambiaPassword) {
		this.usrCambiaPassword = usrCambiaPassword;
	}

}