package com.cashback.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.Globales;
import com.cashback.interfaces.IActorRol;
import com.cashback.interfaces.IUsuario;
import com.cashback.model.Usuario;

@SessionScoped
@ManagedBean
public class LoginCtr {
	private Usuario u, usuario;
	private String usrNombre;
	private String usrPassword, usrPasswordNuevo, usrPasswordNuevo2;
	private boolean isLocal, isGrupoEmpresarial, isHolding,
			isSupervisorHolding, isGlobal;

	@EJB
	private IUsuario sUsuario;
	@EJB
	private IActorRol sActorRol;

	public String acceder() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		u = sUsuario.recuperarUsuario(usrNombre, usrPassword);
		if (u == null) {
			facesContext.addMessage(null, new FacesMessage(
					javax.faces.application.FacesMessage.SEVERITY_FATAL,
					"Unknown login, try again",""));
			return null;
		}

		if (u.getUsrCambiaPassword().compareTo("S") == 0) {
			return "cambiarClave?faces-redirec=true";
		} else {
			usuario = u;
			facesContext.getExternalContext().getSessionMap()
					.put("usuario", usuario);
			isLocal = sActorRol.tieneRolNegocio(usuario.getActor(),
					Globales.NIVEL_GLOBAL_AMIGO, Globales.EST_OK);
			isGrupoEmpresarial = sActorRol.tieneRolNegocio(usuario.getActor(),
					Globales.NIVEL_GRUPO_EMPRESARIAL, Globales.EST_OK);
			isHolding = sActorRol.tieneRolNegocio(usuario.getActor(),
					Globales.NIVEL_HOLDING, Globales.EST_OK);
			isSupervisorHolding = sActorRol.tieneRolNegocio(usuario.getActor(),
					Globales.NIVEL_SUPERVISOR_HOLDING, Globales.EST_OK);
			isGlobal = sActorRol.tieneRolNegocio(usuario.getActor(),
					Globales.NIVEL_GLOBAL, Globales.EST_OK);
			return "menu?faces-redirect=true";
		}
		// HttpSession session = (HttpSession) facesContext.getExternalContext()
		// .getSession(true);
		// session.setAttribute("att", att);
	}

	public String cerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("http://www.somosglobal.com");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String cambiarClave() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (usrPasswordNuevo.compareTo(usrPasswordNuevo2) == 0) {
			u.setUsrPassword(u.passwordToMd5(usrPasswordNuevo));
			try {
				u.setUsrCambiaPassword("N");
				sUsuario.actualizarUsuario(u);
				facesContext.addMessage(null, new FacesMessage(
						javax.faces.application.FacesMessage.SEVERITY_INFO,
						"Cambio de Clave", "Tu clave ha sido actualizada!!!"));
				u = new Usuario();
				return "login";
			} catch (ExcGuardarRegistro e) {
				facesContext
						.addMessage(
								null,
								new FacesMessage(
										javax.faces.application.FacesMessage.SEVERITY_FATAL,
										"Cambio de Clave",
										"Tu clave no pudo ser actualizada, por favor intenta mas tarde"));
				return null;
			}

		} else {
			facesContext.addMessage(null, new FacesMessage(
					javax.faces.application.FacesMessage.SEVERITY_FATAL,
					"Cambio de Clave", "Las claves no coinciden"));
			return null;
		}
	}

	public String getUsrNombre() {
		return usrNombre;
	}

	public void setUsrNombre(String usrNombre) {
		this.usrNombre = usrNombre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUsrPassword() {
		return usrPassword;
	}

	public void setUsrPassword(String usrPassword) {
		this.usrPassword = usrPassword;
	}

	public boolean isLocal() {
		return isLocal;
	}

	public void setLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}

	public boolean isGrupoEmpresarial() {
		return isGrupoEmpresarial;
	}

	public void setGrupoEmpresarial(boolean isGrupoEmpresarial) {
		this.isGrupoEmpresarial = isGrupoEmpresarial;
	}

	public boolean isHolding() {
		return isHolding;
	}

	public void setHolding(boolean isHolding) {
		this.isHolding = isHolding;
	}

	public boolean isSupervisorHolding() {
		return isSupervisorHolding;
	}

	public void setSupervisorHolding(boolean isSupervisorHolding) {
		this.isSupervisorHolding = isSupervisorHolding;
	}

	public boolean isGlobal() {
		return isGlobal;
	}

	public void setGlobal(boolean isGlobal) {
		this.isGlobal = isGlobal;
	}

	public Usuario getU() {
		return u;
	}

	public void setU(Usuario u) {
		this.u = u;
	}

	public String getUsrPasswordNuevo() {
		return usrPasswordNuevo;
	}

	public void setUsrPasswordNuevo(String usrPasswordNuevo) {
		this.usrPasswordNuevo = usrPasswordNuevo;
	}

	public String getUsrPasswordNuevo2() {
		return usrPasswordNuevo2;
	}

	public void setUsrPasswordNuevo2(String usrPasswordNuevo2) {
		this.usrPasswordNuevo2 = usrPasswordNuevo2;
	}
}
