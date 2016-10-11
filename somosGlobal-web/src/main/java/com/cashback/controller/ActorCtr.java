package com.cashback.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.Globales;
import com.cashback.interfaces.IActor;
import com.cashback.interfaces.IActorReferencia;
import com.cashback.interfaces.IActorRol;
import com.cashback.interfaces.ITextoClave;
import com.cashback.model.Actor;
import com.cashback.model.ActorReferencia;
import com.cashback.model.ActorRol;
import com.cashback.model.CatalogoGen;
import com.cashback.model.ICatalogoGen;
import com.cashback.model.ILocalidad;
import com.cashback.model.Localidad;
import com.cashback.model.TextoClave;

@SessionScoped
@ManagedBean
public class ActorCtr extends Controladores {
	private Actor actor;
	private CatalogoGen catalogoGen, catalogoGen2;
	private ActorReferencia actorReferencia, horario, imagen, telefono, correo;
	private List<Actor> actorList;
	private String refCg, tipoAct, catIdSeleccionada, palabraClave,
			headerActor, cedRucPasAct, razonSocialAct, nombresAct,
			apellidosAct;
	private List<SelectItem> localidadProvinciaListItem,
			localidadCiudadListItem, localidadBarrioListItem, actorRolListItem;
	private List<SelectItem> categoriaListItem, diasCatalogoGenListItem,
			direccionesCatalogoGenListItem, correosCatalogoGenListItem,
			telefonosCatalogoGenListItem, imagenCatalogoGenListItem;
	private Integer locIdBarrioSeleccionado, locIdProvinciaSeleccionado,
			locIdCiudadSeleccionado, idArolSeleccionado, diaIdCgSeleccionado,
			imagenIdCgSeleccionado, telefonoIdCgSeleccionado,
			direccionIdCgSeleccionado, correoIdCgSeleccionado;
	private UploadedFile uploadedFile;
	private List<String> palabraClaveList = new ArrayList<String>();
	private MapModel model;
	private boolean fileUploadRender, actualizarActorRender, crearActorRender,
			crearTelefonoRender;

	private List<ActorReferencia> telefonosActor = new ArrayList<ActorReferencia>();
	private List<ActorReferencia> direccionesActor = new ArrayList<ActorReferencia>();
	private List<ActorReferencia> horariosActor = new ArrayList<ActorReferencia>();
	private List<ActorReferencia> correosActor = new ArrayList<ActorReferencia>();
	private List<ActorReferencia> galeriaImgActor = new ArrayList<ActorReferencia>();
	private List<ActorReferencia> promocionImgActor = new ArrayList<ActorReferencia>();

	@EJB
	private IActor sActor;
	@EJB
	private ILocalidad sLocalidad;
	@EJB
	private IActorReferencia sActorReferencia;
	@EJB
	private IActorRol sActorRol;
	@EJB
	private ICatalogoGen sCatalogoGen;
	@EJB
	private ITextoClave sTextoClave;

	public ActorCtr() {
		model = new DefaultMapModel();
	}

	@PostConstruct
	public void inicio() {
		actor = new Actor();
		nuevoTelefono();
		nuevoCorreo();
		nuevaDireccion();
		nuevoActorReferencia();
		nuevoHorario();
		nuevaImagen();
		categoriaListItem = recuperarCategoriaItem();

		imagenCatalogoGenListItem = recuperarCatalogoByFather(sCatalogoGen
				.findByTipoCg(Globales.IMAGEN_TIPO_CATALOGO));

		CatalogoGen telefonosCatalogo = sCatalogoGen
				.findByTipoCg(Globales.TELEFONO);
		telefonosCatalogoGenListItem = recuperarCatalogoByFather(telefonosCatalogo);

		CatalogoGen diasCatalogo = sCatalogoGen
				.findByTipoCg(Globales.DIAS_TIPO_CATALOGO);
		diasCatalogoGenListItem = recuperarCatalogoByFather(diasCatalogo);

		CatalogoGen direccionesCatalogo = sCatalogoGen
				.findByTipoCg(Globales.DIRECCION);
		direccionesCatalogoGenListItem = recuperarCatalogoByFather(direccionesCatalogo);

		CatalogoGen correoCatalogo = sCatalogoGen
				.findByTipoCg(Globales.CORREO_ELECTRONICO);
		correosCatalogoGenListItem = recuperarCatalogoByFather(correoCatalogo);

	}

	public String asignarTipoCuenta() {
		recuperarActorRolListItem();
		return refCg;
	}

	public void recuperarlocalidadCiudadList() {
		Localidad localidad = new Localidad();
		localidad.setLocId(locIdProvinciaSeleccionado);
		localidadCiudadListItem = recuperarLocalidadItem(localidad);
	}

	public void recuperarlocalidadBarrioList() {
		Localidad localidad = new Localidad();
		localidad.setLocId(locIdCiudadSeleccionado);
		localidadBarrioListItem = recuperarLocalidadItem(localidad);
	}

	public void recuperarActorRolListItem() {
		CatalogoGen rolNegocio = null;
		if (refCg.compareTo(Globales.NIVEL_CONSUMIDOR) == 0) {
			headerActor = "Crear Consumidor";
		}
		if (refCg.compareTo(Globales.NIVEL_GLOBAL_AMIGO) == 0) {
			rolNegocio = sCatalogoGen.recuperarCatalogoGen(
					Globales.ROL_NEGOCIO, Globales.NIVEL_GRUPO_EMPRESARIAL);
			headerActor = "Crear Local / Global Amigo";
		}
		if (refCg.compareTo(Globales.NIVEL_GRUPO_EMPRESARIAL) == 0) {
			rolNegocio = sCatalogoGen.recuperarCatalogoGen(
					Globales.ROL_NEGOCIO, Globales.NIVEL_HOLDING);
			headerActor = "Crear Grupo Empresarial";
		}
		if (refCg.compareTo(Globales.NIVEL_HOLDING) == 0) {
			rolNegocio = sCatalogoGen.recuperarCatalogoGen(
					Globales.ROL_NEGOCIO, Globales.NIVEL_SUPERVISOR_HOLDING);
			headerActor = "Crear Holding";
		}
		if (refCg.compareTo(Globales.NIVEL_SUPERVISOR_HOLDING) == 0) {
			rolNegocio = sCatalogoGen.recuperarCatalogoGen(
					Globales.ROL_NEGOCIO, Globales.NIVEL_GLOBAL);
			headerActor = "Crear Supervisor Holding";
		}

		List<ActorRol> actorRolList = sActorRol.recuperarActorRolList(
				usuario.getActor(), rolNegocio, Globales.EST_OK);
		actorRolListItem = new ArrayList<SelectItem>();
		for (ActorRol actorRol : actorRolList) {
			SelectItem si = new SelectItem(actorRol.getIdArol(), actorRol
					.getPrcArol().toString());
			actorRolListItem.add(si);
		}
	}

	public String crearActor() {
		// Crear Actor con cuenta
		// Crear Actor
		actor.setCedrucpasAct(actor.getCedrucpasAct().trim());
		actor.setCatId(catIdSeleccionada);
		actor.setUsrCreaAct(usuario.getUsrNombre());
		actor.setEstadoAct(Globales.EST_OK);
		if (palabraClaveList.size() > 0) {
			String palabrasClaveAct = palabraClaveList.toString().replace("[",
					"");
			actor.setPalabrasClaveAct(palabrasClaveAct.replace("]", ""));
		} else {
			actor.setPalabrasClaveAct(null);
		}

		// Crear cuenta
		ActorRol actorRolPadre = null;
		BigDecimal prcArol = null;
		if (refCg.compareTo(Globales.NIVEL_CONSUMIDOR) == 0) {
			CatalogoGen consumidorRolNegocio = sCatalogoGen
					.recuperarCatalogoGen(Globales.ROL_NEGOCIO,
							Globales.NIVEL_GLOBAL_AMIGO);
			actorRolPadre = sActorRol.recuperarActorRol(usuario.getActor(),
					consumidorRolNegocio, Globales.EST_OK);

			prcArol = new BigDecimal(sCatalogoGen.recuperarCatalogoGen(
					Globales.ROL_NEGOCIO, Globales.NIVEL_CONSUMIDOR)
					.getRef02Cg());
		} else {
			actorRolPadre = sActorRol.recuperarActorRol(idArolSeleccionado);
			prcArol = actorRolPadre.getPrcArol();
		}

		ActorRol actorRol = new ActorRol();
		actorRol.setActor(actor);
		actorRol.setCatalogoGen(sCatalogoGen.recuperarCatalogoGen(
				Globales.ROL_NEGOCIO, refCg));
		actorRol.setActorRol(actorRolPadre);
		actorRol.setPrcArol(prcArol);
		actorRol.setEstadoArol(Globales.EST_OK);

		try {
			if (sActor.crearActorActorRolUsuairo(actor, actorRol)) {
				actorRol = new ActorRol();
				mostrarInfo("Crear Actor", AppMensajes.INF_OPERACION_EXITO);
				return "agregarContacto";
			}
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode(), e.getMessageText());
		}
		return null;
	}

	public void buscarActor() {
		actorList = sActor.findAllByCedRucPasAndRazonSocialNombre(cedRucPasAct,
				razonSocialAct, nombresAct, apellidosAct, "");

	}

	public String seleccionarActor() {
		actor = sActor.findByIdAct(actor.getIdAct());
		for (ActorReferencia ar : actor.getActorReferencias()) {
			String tipoCatalogo = ar.getCatalogoGen().getCatalogoGen()
					.getTipoCg();
			if (tipoCatalogo.compareTo(Globales.TELEFONO) == 0) {
				telefonosActor.add(ar);
			}
			if (tipoCatalogo.compareTo(Globales.DIRECCION) == 0) {
				ar.setLocalidad(sLocalidad.recuperarLocalidad(Integer
						.parseInt(ar.getVal1Ar())));
				direccionesActor.add(ar);
			}
			if (tipoCatalogo.compareTo(Globales.DIAS_TIPO_CATALOGO) == 0) {
				horariosActor.add(ar);
			}
			if (tipoCatalogo.compareTo(Globales.CORREO_ELECTRONICO) == 0) {
				correosActor.add(ar);
			}

			if (tipoCatalogo.compareTo(Globales.IMAGEN_TIPO_CATALOGO) == 0) {
				if (ar.getCatalogoGen().getRefCg()
						.compareTo(Globales.IMAGEN_PROMOCION) == 0) {
					promocionImgActor.add(ar);
				}
				if (ar.getCatalogoGen().getRefCg()
						.compareTo(Globales.IMAGEN_GALERIA) == 0) {
					galeriaImgActor.add(ar);
				}
			}
		}

		return "GA";
	}

	public String recuperarFormatoCatalogo() {
		catalogoGen2 = sCatalogoGen
				.recuperarCatalogoGen(telefonoIdCgSeleccionado);
		return null;
	}

	public void crearTelefono() {
		telefono.setUsrCreaAr(usuario.getUsrNombre());
		telefono.setActor(actor);
		telefono.setCatalogoGen(sCatalogoGen
				.recuperarCatalogoGen(telefonoIdCgSeleccionado));
		sActorReferencia.crearActorReferencia(telefono);
		nuevoHorario();
		mostrarInfo("Crear Teléfono", AppMensajes.INF_OPERACION_EXITO);
	}

	public String nuevoTelefono() {
		telefono = new ActorReferencia();
		return null;
	}

	public void crearCorreo() {
		correo.setUsrCreaAr(usuario.getUsrNombre());
		correo.setActor(actor);
		correo.setCatalogoGen(sCatalogoGen
				.recuperarCatalogoGen(correoIdCgSeleccionado));
		sActorReferencia.crearActorReferencia(correo);
		nuevoHorario();
		mostrarInfo("Crear Correo", AppMensajes.INF_OPERACION_EXITO);
	}

	public String nuevoCorreo() {
		correo = new ActorReferencia();
		return null;
	}

	public String crearActorReferencia() {
		actorReferencia.setUsrCreaAr(usuario.getUsrNombre());
		actorReferencia.setActor(actor);
		actorReferencia.setCatalogoGen(sCatalogoGen
				.recuperarCatalogoGen(direccionIdCgSeleccionado));
		actorReferencia.setVal1Ar(Integer.toString(locIdBarrioSeleccionado));
		sActorReferencia.crearActorReferencia(actorReferencia);
		nuevoActorReferencia();
		mostrarInfo("Crear Dirección", AppMensajes.INF_OPERACION_EXITO);
		return null;
	}

	public String nuevoActorReferencia() {
		actorReferencia = new ActorReferencia();
		catalogoGen = new CatalogoGen();
		localidadProvinciaListItem = recuperarLocalidadItem(null);
		localidadCiudadListItem = new ArrayList<SelectItem>();
		localidadBarrioListItem = new ArrayList<SelectItem>();
		return null;
	}

	public void nuevaDireccion() {
		actorReferencia = new ActorReferencia();
	}

	public void handleFileUpload(FileUploadEvent event) {
		uploadedFile = event.getFile();
		OutputStream out = null;
		File newFile = null;
		String arcNombreOriginal = uploadedFile.getFileName();
		String arcNombreFinal = arcNombreOriginal;
		try {
			newFile = new File(pathImagenes + arcNombreFinal);
			newFile.createNewFile();
			out = new FileOutputStream(newFile);
			byte[] b = uploadedFile.getContents();
			out.write(b);
			actor.setLogoAct(arcNombreFinal);
			mostrarInfo("Carga de Archivos", AppMensajes.INF_OPERACION_EXITO);
		} catch (IOException e) {
			e.printStackTrace();
			mostrarErrorSummary("Carga de Archivos", e.getMessage());
		}
	}

	public String agregarPalabraClave() {
		if (palabraClave != null) {
			if (palabraClave.trim().length() > 0) {
				TextoClave textoClave = new TextoClave();
				textoClave.setTcTexto(palabraClave);
				textoClave.setTcTipo(Globales.TC_TIPO_LOCAL);
				textoClave.setTcEstado(Globales.EST_OK);
				try {
					sTextoClave.crearTextoClave(textoClave);
				} catch (ExcGuardarRegistro e) {

				}
				palabraClaveList.add(palabraClave);
				palabraClave = null;
			}
		}
		return null;
	}

	public String quitarPalabraClave() {
		palabraClaveList.remove(palabraClave);
		return null;
	}

	public String nuevoHorario() {
		horario = new ActorReferencia();
		return null;
	}

	public void crearHorario() {
		horario.setUsrCreaAr(usuario.getUsrNombre());
		horario.setActor(actor);
		horario.setCatalogoGen(sCatalogoGen
				.recuperarCatalogoGen(diaIdCgSeleccionado));
		sActorReferencia.crearActorReferencia(horario);
		nuevoHorario();
		mostrarInfo("Crear Horario", AppMensajes.INF_OPERACION_EXITO);
	}

	public void nuevaImagen() {
		imagen = new ActorReferencia();
		imagenIdCgSeleccionado = 0;
		fileUploadRender = false;
	}

	public void crearImagen() {
		CatalogoGen tipoImagenCatalogoGen = sCatalogoGen
				.recuperarCatalogoGen(imagenIdCgSeleccionado);
		imagen.setUsrCreaAr(usuario.getUsrNombre());
		imagen.setActor(actor);
		imagen.setCatalogoGen(tipoImagenCatalogoGen);

		sActorReferencia.crearActorReferencia(imagen);
		nuevaImagen();
	}

	public void handleFileUpload2(FileUploadEvent event) {
		uploadedFile = event.getFile();
		OutputStream out = null;
		File newFile = null;
		String arcNombreOriginal = uploadedFile.getFileName();
		String arcNombreFinal = actor.getIdAct() + "-" + arcNombreOriginal;
		try {
			newFile = new File(pathImagenes + arcNombreFinal);
			newFile.createNewFile();
			out = new FileOutputStream(newFile);
			byte[] b = uploadedFile.getContents();
			out.write(b);
			imagen.setVal1Ar(arcNombreFinal);
			crearImagen();
			mostrarInfo("Carga de Imágenes", AppMensajes.INF_OPERACION_EXITO);
		} catch (IOException e) {
			e.printStackTrace();
			mostrarErrorSummary("Carga de Imágenes", e.getMessage());
		}
	}

	public void onPointSelect(PointSelectEvent event) {
		LatLng latlng = event.getLatLng();
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Point Selected", "Lat:" + latlng.getLat() + ", Lng:"
						+ latlng.getLng()));
		actorReferencia.setLatitudAr(Double.toString(latlng.getLat()));
		actorReferencia.setLongitudAr(Double.toString(latlng.getLng()));
	}

	public void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void seleccionarTipoImagen() {
		if (imagenIdCgSeleccionado != null) {
			fileUploadRender = true;
		} else {
			fileUploadRender = false;
		}

	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public String getRefCg() {
		return refCg;
	}

	public void setRefCg(String refCg) {
		this.refCg = refCg;
	}

	public String getTipoAct() {
		return tipoAct;
	}

	public void setTipoAct(String tipoAct) {
		this.tipoAct = tipoAct;
	}

	public List<SelectItem> getCategoriaListItem() {
		return categoriaListItem;
	}

	public void setCategoriaListItem(List<SelectItem> categoriaListItem) {
		this.categoriaListItem = categoriaListItem;
	}

	public String getCatIdSeleccionada() {
		return catIdSeleccionada;
	}

	public void setCatIdSeleccionada(String catIdSeleccionada) {
		this.catIdSeleccionada = catIdSeleccionada;
	}

	public CatalogoGen getCatalogoGen() {
		return catalogoGen;
	}

	public void setCatalogoGen(CatalogoGen catalogoGen) {
		this.catalogoGen = catalogoGen;
	}

	public ActorReferencia getActorReferencia() {
		return actorReferencia;
	}

	public void setActorReferencia(ActorReferencia actorReferencia) {
		this.actorReferencia = actorReferencia;
	}

	public List<SelectItem> getLocalidadProvinciaListItem() {
		return localidadProvinciaListItem;
	}

	public void setLocalidadProvinciaListItem(
			List<SelectItem> localidadProvinciaListItem) {
		this.localidadProvinciaListItem = localidadProvinciaListItem;
	}

	public List<SelectItem> getLocalidadCiudadListItem() {
		return localidadCiudadListItem;
	}

	public void setLocalidadCiudadListItem(
			List<SelectItem> localidadCiudadListItem) {
		this.localidadCiudadListItem = localidadCiudadListItem;
	}

	public List<SelectItem> getLocalidadBarrioListItem() {
		return localidadBarrioListItem;
	}

	public void setLocalidadBarrioListItem(
			List<SelectItem> localidadBarrioListItem) {
		this.localidadBarrioListItem = localidadBarrioListItem;
	}

	public Integer getLocIdBarrioSeleccionado() {
		return locIdBarrioSeleccionado;
	}

	public void setLocIdBarrioSeleccionado(Integer locIdBarrioSeleccionado) {
		this.locIdBarrioSeleccionado = locIdBarrioSeleccionado;
	}

	public Integer getLocIdProvinciaSeleccionado() {
		return locIdProvinciaSeleccionado;
	}

	public void setLocIdProvinciaSeleccionado(Integer locIdProvinciaSeleccionado) {
		this.locIdProvinciaSeleccionado = locIdProvinciaSeleccionado;
	}

	public Integer getLocIdCiudadSeleccionado() {
		return locIdCiudadSeleccionado;
	}

	public void setLocIdCiudadSeleccionado(Integer locIdCiudadSeleccionado) {
		this.locIdCiudadSeleccionado = locIdCiudadSeleccionado;
	}

	public CatalogoGen getCatalogoGen2() {
		return catalogoGen2;
	}

	public void setCatalogoGen2(CatalogoGen catalogoGen2) {
		this.catalogoGen2 = catalogoGen2;
	}

	public List<SelectItem> getActorRolListItem() {
		return actorRolListItem;
	}

	public void setActorRolListItem(List<SelectItem> actorRolListItem) {
		this.actorRolListItem = actorRolListItem;
	}

	public Integer getIdArolSeleccionado() {
		return idArolSeleccionado;
	}

	public void setIdArolSeleccionado(Integer idArolSeleccionado) {
		this.idArolSeleccionado = idArolSeleccionado;
	}

	public List<String> getPalabraClaveList() {
		return palabraClaveList;
	}

	public void setPalabraClaveList(List<String> palabraClaveList) {
		this.palabraClaveList = palabraClaveList;
	}

	public String getPalabraClave() {
		return palabraClave;
	}

	public void setPalabraClave(String palabraClave) {
		this.palabraClave = palabraClave;
	}

	public String getHeaderActor() {
		return headerActor;
	}

	public void setHeaderActor(String headerActor) {
		this.headerActor = headerActor;
	}

	public MapModel getModel() {
		return model;
	}

	public void setModel(MapModel model) {
		this.model = model;
	}

	public Integer getDiaIdCgSeleccionado() {
		return diaIdCgSeleccionado;
	}

	public void setDiaIdCgSeleccionado(Integer diaIdCgSeleccionado) {
		this.diaIdCgSeleccionado = diaIdCgSeleccionado;
	}

	public List<SelectItem> getDiasCatalogoGenListItem() {
		return diasCatalogoGenListItem;
	}

	public void setDiasCatalogoGenListItem(
			List<SelectItem> diasCatalogoGenListItem) {
		this.diasCatalogoGenListItem = diasCatalogoGenListItem;
	}

	public ActorReferencia getHorario() {
		return horario;
	}

	public void setHorario(ActorReferencia horario) {
		this.horario = horario;
	}

	public ActorReferencia getImagen() {
		return imagen;
	}

	public void setImagen(ActorReferencia imagen) {
		this.imagen = imagen;
	}

	public List<SelectItem> getImagenCatalogoGenListItem() {
		return imagenCatalogoGenListItem;
	}

	public void setImagenCatalogoGenListItem(
			List<SelectItem> imagenCatalogoGenListItem) {
		this.imagenCatalogoGenListItem = imagenCatalogoGenListItem;
	}

	public Integer getImagenIdCgSeleccionado() {
		return imagenIdCgSeleccionado;
	}

	public void setImagenIdCgSeleccionado(Integer imagenIdCgSeleccionado) {
		this.imagenIdCgSeleccionado = imagenIdCgSeleccionado;
	}

	public boolean isFileUploadRender() {
		return fileUploadRender;
	}

	public void setFileUploadRender(boolean fileUploadRender) {
		this.fileUploadRender = fileUploadRender;
	}

	public List<SelectItem> getTelefonosCatalogoGenListItem() {
		return telefonosCatalogoGenListItem;
	}

	public void setTelefonosCatalogoGenListItem(
			List<SelectItem> telefonosCatalogoGenListItem) {
		this.telefonosCatalogoGenListItem = telefonosCatalogoGenListItem;
	}

	public ActorReferencia getTelefono() {
		return telefono;
	}

	public void setTelefono(ActorReferencia telefono) {
		this.telefono = telefono;
	}

	public List<SelectItem> getDireccionesCatalogoGenListItem() {
		return direccionesCatalogoGenListItem;
	}

	public void setDireccionesCatalogoGenListItem(
			List<SelectItem> direccionesCatalogoGenListItem) {
		this.direccionesCatalogoGenListItem = direccionesCatalogoGenListItem;
	}

	public List<SelectItem> getCorreosCatalogoGenListItem() {
		return correosCatalogoGenListItem;
	}

	public void setCorreosCatalogoGenListItem(
			List<SelectItem> correosCatalogoGenListItem) {
		this.correosCatalogoGenListItem = correosCatalogoGenListItem;
	}

	public Integer getTelefonoIdCgSeleccionado() {
		return telefonoIdCgSeleccionado;
	}

	public void setTelefonoIdCgSeleccionado(Integer telefonoIdCgSeleccionado) {
		this.telefonoIdCgSeleccionado = telefonoIdCgSeleccionado;
	}

	public Integer getDireccionIdCgSeleccionado() {
		return direccionIdCgSeleccionado;
	}

	public void setDireccionIdCgSeleccionado(Integer direccionIdCgSeleccionado) {
		this.direccionIdCgSeleccionado = direccionIdCgSeleccionado;
	}

	public ActorReferencia getCorreo() {
		return correo;
	}

	public void setCorreo(ActorReferencia correo) {
		this.correo = correo;
	}

	public Integer getCorreoIdCgSeleccionado() {
		return correoIdCgSeleccionado;
	}

	public void setCorreoIdCgSeleccionado(Integer correoIdCgSeleccionado) {
		this.correoIdCgSeleccionado = correoIdCgSeleccionado;
	}

	public List<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	public String getRazonSocialAct() {
		return razonSocialAct;
	}

	public void setRazonSocialAct(String razonSocialAct) {
		this.razonSocialAct = razonSocialAct;
	}

	public String getNombresAct() {
		return nombresAct;
	}

	public void setNombresAct(String nombresAct) {
		this.nombresAct = nombresAct;
	}

	public String getApellidosAct() {
		return apellidosAct;
	}

	public void setApellidosAct(String apellidosAct) {
		this.apellidosAct = apellidosAct;
	}

	public String getCedRucPasAct() {
		return cedRucPasAct;
	}

	public void setCedRucPasAct(String cedRucPasAct) {
		this.cedRucPasAct = cedRucPasAct;
	}

	public List<ActorReferencia> getTelefonosActor() {
		return telefonosActor;
	}

	public void setTelefonosActor(List<ActorReferencia> telefonosActor) {
		this.telefonosActor = telefonosActor;
	}

	public List<ActorReferencia> getDireccionesActor() {
		return direccionesActor;
	}

	public void setDireccionesActor(List<ActorReferencia> direccionesActor) {
		this.direccionesActor = direccionesActor;
	}

	public List<ActorReferencia> getHorariosActor() {
		return horariosActor;
	}

	public void setHorariosActor(List<ActorReferencia> horariosActor) {
		this.horariosActor = horariosActor;
	}

	public List<ActorReferencia> getCorreosActor() {
		return correosActor;
	}

	public void setCorreosActor(List<ActorReferencia> correosActor) {
		this.correosActor = correosActor;
	}

	public List<ActorReferencia> getGaleriaImgActor() {
		return galeriaImgActor;
	}

	public void setGaleriaImgActor(List<ActorReferencia> galeriaImgActor) {
		this.galeriaImgActor = galeriaImgActor;
	}

	public List<ActorReferencia> getPromocionImgActor() {
		return promocionImgActor;
	}

	public void setPromocionImgActor(List<ActorReferencia> promocionImgActor) {
		this.promocionImgActor = promocionImgActor;
	}

	public boolean isActualizarActorRender() {
		return actualizarActorRender;
	}

	public void setActualizarActorRender(boolean actualizarActorRender) {
		this.actualizarActorRender = actualizarActorRender;
	}

	public boolean isCrearActorRender() {
		return crearActorRender;
	}

	public void setCrearActorRender(boolean crearActorRender) {
		this.crearActorRender = crearActorRender;
	}

	public boolean isCrearTelefonoRender() {
		return crearTelefonoRender;
	}

	public void setCrearTelefonoRender(boolean crearTelefonoRender) {
		this.crearTelefonoRender = crearTelefonoRender;
	}

}
