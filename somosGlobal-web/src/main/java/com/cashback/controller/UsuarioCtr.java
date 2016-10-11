package com.cashback.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.Globales;
import com.cashback.interfaces.IActor;
import com.cashback.interfaces.IPerfil;
import com.cashback.interfaces.IUsuario;
import com.cashback.model.Actor;
import com.cashback.model.Perfil;
import com.cashback.model.Persona;
import com.cashback.model.Usuario;

@SessionScoped
@ManagedBean
public class UsuarioCtr extends Controladores {
	private Usuario usuario;
	private Actor actor;
	private String usrNombre = "", tipoAct = "", nombresAct = "",
			apellidosAct = "", cedrucpasAct = "";

	private int idActSeleccionado;
	private List<Actor> actorList;
	private List<Persona> personaList;
	private List<Usuario> usuarioList;
	private List<SelectItem> perfilListItem;
	private List<SelectItem> personaListItem;
	private int prfIdSeleccionado;
	private int perIdSeleccionado;
	private boolean crearUsuarioRender = false,
			actualizarUsuarioRender = false;

	@EJB
	private IUsuario sUsuario;
	@EJB
	private IPerfil sPerfil;
	@EJB
	private IActor sActor;

	public UsuarioCtr() {

	}

	@PostConstruct
	public void inicio() {
		nuevoUsuario();
	}

	public String crearUsuario() {
		usuario.setActor(sActor.recuperarActor(idActSeleccionado));
		usuario.setPerfil(sPerfil.recuperarPerfil(prfIdSeleccionado));
		try {
			sUsuario.crearUsuario(usuario);
			nuevoUsuario();
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode(), e.getMessageText());
		}
		return null;
	}

	public String actualizarUsuario() {
		Actor actor = sActor.recuperarActor(idActSeleccionado);
		Perfil perfil = sPerfil.recuperarPerfil(prfIdSeleccionado);
		usuario.setActor(actor);
		usuario.setPerfil(perfil);
		usuario.setUsrPassword(Globales.PAR_CLAVE_DEFAULT);
		try {
			sUsuario.actualizarUsuario(usuario);
			nuevoUsuario();
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode(), e.getMessageText());
		}
		return null;
	}

	public String nuevoUsuario() {
		crearUsuarioRender = true;
		actualizarUsuarioRender = false;
		usuario = new Usuario();
		recuperaPerfilListItem();
		actorList = new ArrayList<Actor>();
		return null;
	}

	public String recuperarActorList() {
		actorList = sActor.recuperarActorList(tipoAct, nombresAct,
				apellidosAct, cedrucpasAct, Globales.EST_OK);
		return null;
	}

	public String recuperaPerfilListItem() {
		prfIdSeleccionado = 0;
		List<Perfil> perfilList = sPerfil.recuperarPerfilList2(Globales.EST_OK);
		perfilListItem = new ArrayList<SelectItem>();
		for (Perfil p : perfilList) {
			SelectItem si = new SelectItem(p.getPrfId(), p.getPrfNombre());
			perfilListItem.add(si);
		}
		return null;
	}

	public String seleccionarUsuario() {
		actor = usuario.getActor();
		prfIdSeleccionado = usuario.getPerfil().getPrfId();
		actualizarUsuarioRender = true;
		crearUsuarioRender = false;
		return null;
	}

	public String recuperarUsuarioList() {
		usuarioList = sUsuario.recuperarUsuarioList(usrNombre);
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public List<SelectItem> getPerfilListItem() {
		return perfilListItem;
	}

	public void setPerfilListItem(List<SelectItem> perfilListItem) {
		this.perfilListItem = perfilListItem;
	}

	public int getPrfIdSeleccionado() {
		return prfIdSeleccionado;
	}

	public void setPrfIdSeleccionado(int prfIdSeleccionado) {
		this.prfIdSeleccionado = prfIdSeleccionado;
	}

	public int getPerIdSeleccionado() {
		return perIdSeleccionado;
	}

	public void setPerIdSeleccionado(int perIdSeleccionado) {
		this.perIdSeleccionado = perIdSeleccionado;
	}

	public List<SelectItem> getPersonaListItem() {
		return personaListItem;
	}

	public void setPersonaListItem(List<SelectItem> personaListItem) {
		this.personaListItem = personaListItem;
	}

	public String getUsrNombre() {
		return usrNombre;
	}

	public void setUsrNombre(String usrNombre) {
		this.usrNombre = usrNombre;
	}

	public List<Persona> getPersonaList() {
		return personaList;
	}

	public void setPersonaList(List<Persona> personaList) {
		this.personaList = personaList;
	}

	public boolean isCrearUsuarioRender() {
		return crearUsuarioRender;
	}

	public void setCrearUsuarioRender(boolean crearUsuarioRender) {
		this.crearUsuarioRender = crearUsuarioRender;
	}

	public boolean isActualizarUsuarioRender() {
		return actualizarUsuarioRender;
	}

	public void setActualizarUsuarioRender(boolean actualizarUsuarioRender) {
		this.actualizarUsuarioRender = actualizarUsuarioRender;
	}

	public List<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	public String getTipoAct() {
		return tipoAct;
	}

	public void setTipoAct(String tipoAct) {
		this.tipoAct = tipoAct;
	}

	public String getNombresAct() {
		return nombresAct;
	}

	public void setNombresAct(String nombresAct) {
		this.nombresAct = nombresAct;
	}

	public String getCedrucpasAct() {
		return cedrucpasAct;
	}

	public void setCedrucpasAct(String cedrucpasAct) {
		this.cedrucpasAct = cedrucpasAct;
	}

	public int getIdActSeleccionado() {
		return idActSeleccionado;
	}

	public void setIdActSeleccionado(int idActSeleccionado) {
		this.idActSeleccionado = idActSeleccionado;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
}
