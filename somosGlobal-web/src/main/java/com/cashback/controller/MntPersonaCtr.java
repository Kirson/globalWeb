package com.cashback.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.IContacto;
import com.cashback.interfaces.IHobbie;
import com.cashback.interfaces.IPersona;
import com.cashback.interfaces.IPuntos;
import com.cashback.model.Contacto;
import com.cashback.model.Hobbie;
import com.cashback.model.HobbiePersona;
import com.cashback.model.Persona;
import com.cashback.model.Puntos;

@SessionScoped
@ManagedBean
public class MntPersonaCtr extends Controladores {
	private Persona persona;
	private HobbiePersona hobbiePersona;
	private List<Persona> personaList;
	private List<HobbiePersona> hobbiePersonaList;
	private List<Contacto> contactoList;
	private Integer indexHobbiePersona, codigoHobbieSeleccionado;
	private Contacto contacto;
	private List<SelectItem> hobbieListItem;
	private List<Puntos> puntosList;

	private Boolean nuevaPersonaRender, actualizarPersonaRender,
			crearContactoRender, actualizarContactoRender;
	private String nombres = "", apellidos = "", perCedRuc;;
	private Integer codigo;
	@EJB
	private IPersona sPersona;
	@EJB
	private IHobbie sHobbie;
	@EJB
	private IContacto sContacto;
	@EJB
	private IPuntos sPuntos;

	public MntPersonaCtr() {
		persona = new Persona();
		hobbiePersonaList = new ArrayList<HobbiePersona>();
		nuevaPersona();
	}

	public String nuevaPersona() {
		persona = new Persona();
		nuevaPersonaRender = true;
		actualizarPersonaRender = false;
		hobbiePersona = new HobbiePersona();
		Persona persona = new Persona();
		Hobbie hobbie = new Hobbie();
		hobbiePersona.setHobbie(hobbie);
		hobbiePersona.setPersona(persona);
		return null;
	}

	public String seleccionarPersona() {
		nuevaPersonaRender = false;
		actualizarPersonaRender = true;
		return null;
	}

	public String crearPersona() {
		try {
			sPersona.crearPersona(persona);
			persona = new Persona();
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode());
		}
		return null;
	}

	public String actualizarPersona() {
		try {
			sPersona.actualizarPersona(persona);
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode());
		}
		return null;
	}

	public String recuperarPersona() {
		personaList = new ArrayList<Persona>();
		Persona persona = sPersona.recuperarPersona(codigo);
		if (persona != null) {
			personaList.add(persona);
		} else {
			mostrarInfo(AppMensajes.INF_NO_RESULTADOS);
		}
		return null;
	}

	public String recuperarPersonaList() {
		personaList = sPersona.recuperarPersonaList(nombres, apellidos, perCedRuc);
		if (personaList.size() == 0) {
			mostrarInfo(AppMensajes.INF_NO_RESULTADOS);
		}
		return null;
	}

	public String recuperarHobbiePersonaList() {
		hobbiePersonaList = sPersona.recuperarHobbiePersonaList(persona);
		hobbieListItem = recuperarHobbieListItem();
		for (HobbiePersona hp : hobbiePersonaList) {
			System.out.println("DBG hobbiePersonaList" + hp);
		}
		return null;
	}

	public String recuperarPuntosList(){
		puntosList= sPuntos.recuperarPuntos(persona);
		return null;
	}
	
	public String crearHobbiePersona() {
		Hobbie hobbie = sHobbie.recuperarHobbie(codigoHobbieSeleccionado);
		hobbiePersona.setHobbie(hobbie);
		hobbiePersona.setPersona(persona);
		hobbiePersona = sPersona.actualizarHobbiePersona(hobbiePersona);
		hobbiePersonaList = sPersona.recuperarHobbiePersonaList(persona);
		/*
		 * System.out.println("DBG indexHobbiePersona=" + indexHobbiePersona);
		 * if (indexHobbiePersona == null) {
		 * hobbiePersonaList.add(hobbiePersona); } else {
		 * hobbiePersonaList.set(indexHobbiePersona, hobbiePersona); }
		 * persona.setHobbiesList(hobbiePersonaList); try {
		 * sPersona.actualizarPersona(persona); hobbiePersona = new
		 * HobbiePersona(); mostrarInfo(AppMensajes.INF_OPERACION_EXITO); }
		 * catch (ExcGuardarRegistro e) { mostrarError(e.getMessageCode()); }
		 */
		mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		hobbiePersona = new HobbiePersona();
		return null;
	}

	public String seleccionarHobbiePersona() {
		indexHobbiePersona = hobbiePersonaList.indexOf(hobbiePersona);
		codigoHobbieSeleccionado = hobbiePersona.getHobbie().getHobId();
		return null;
	}

	public String nuevoHobbiePersona() {
		hobbiePersona = new HobbiePersona();
		Persona persona = new Persona();
		Hobbie hobbie = new Hobbie();
		hobbiePersona.setHobbie(hobbie);
		hobbiePersona.setPersona(persona);
		codigoHobbieSeleccionado = 0;
		return null;
	}

	public String recuperarContactoList() {
		contactoList = sContacto.recuperarContactoList(persona);
		return null;
	}

	public String nuevoContacto() {
		crearContactoRender = true;
		actualizarContactoRender = false;
		contacto = new Contacto();
		contacto.setPersona(persona);
		return null;
	}

	public String seleccionarContacto() {
		crearContactoRender = false;
		actualizarContactoRender = true;
		return null;
	}

	public String crearContacto() {
		try {
			sContacto.crearContacto(contacto);
			contactoList.add(contacto);
			nuevoContacto();
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode(), e.getMessageText());
		}
		return null;
	}

	public String actualizarContacto() {
		try {
			contacto = sContacto.actualizarContacto(contacto);
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode(), e.getMessageText());
		}
		return null;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Persona> getPersonaList() {
		return personaList;
	}

	public void setPersonaList(List<Persona> personaList) {
		this.personaList = personaList;
	}

	public Boolean getNuevaPersonaRender() {
		return nuevaPersonaRender;
	}

	public void setNuevaPersonaRender(Boolean nuevaPersonaRender) {
		this.nuevaPersonaRender = nuevaPersonaRender;
	}

	public Boolean getActualizarPersonaRender() {
		return actualizarPersonaRender;
	}

	public void setActualizarPersonaRender(Boolean actualizarPersonaRender) {
		this.actualizarPersonaRender = actualizarPersonaRender;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public List<HobbiePersona> getHobbiePersonaList() {
		return hobbiePersonaList;
	}

	public void setHobbiePersonaList(List<HobbiePersona> hobbiePersonaList) {
		this.hobbiePersonaList = hobbiePersonaList;
	}

	public HobbiePersona getHobbiePersona() {
		return hobbiePersona;
	}

	public void setHobbiePersona(HobbiePersona hobbiePersona) {
		this.hobbiePersona = hobbiePersona;
	}

	public List<SelectItem> getHobbieListItem() {
		return hobbieListItem;
	}

	public void setHobbieListItem(List<SelectItem> hobbieListItem) {
		this.hobbieListItem = hobbieListItem;
	}

	public Integer getIndexHobbiePersona() {
		return indexHobbiePersona;
	}

	public void setIndexHobbiePersona(Integer indexHobbiePersona) {
		this.indexHobbiePersona = indexHobbiePersona;
	}

	public Integer getCodigoHobbieSeleccionado() {
		return codigoHobbieSeleccionado;
	}

	public void setCodigoHobbieSeleccionado(Integer codigoHobbieSeleccionado) {
		this.codigoHobbieSeleccionado = codigoHobbieSeleccionado;
	}

	public List<Contacto> getContactoList() {
		return contactoList;
	}

	public void setContactoList(List<Contacto> contactoList) {
		this.contactoList = contactoList;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public Boolean getCrearContactoRender() {
		return crearContactoRender;
	}

	public void setCrearContactoRender(Boolean crearContactoRender) {
		this.crearContactoRender = crearContactoRender;
	}

	public Boolean getActualizarContactoRender() {
		return actualizarContactoRender;
	}

	public void setActualizarContactoRender(Boolean actualizarContactoRender) {
		this.actualizarContactoRender = actualizarContactoRender;
	}

	public String getPerCedRuc() {
		return perCedRuc;
	}

	public void setPerCedRuc(String perCedRuc) {
		this.perCedRuc = perCedRuc;
	}

	public List<Puntos> getPuntosList() {
		return puntosList;
	}

	public void setPuntosList(List<Puntos> puntosList) {
		this.puntosList = puntosList;
	}
}
