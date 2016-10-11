package com.cashback.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.Globales;
import com.cashback.interfaces.IContacto;
import com.cashback.interfaces.ILocalPromocion;
import com.cashback.interfaces.ILocalVenta;
import com.cashback.interfaces.IParametrosGen;
import com.cashback.interfaces.IPersona;
import com.cashback.interfaces.ITextoClave;
import com.cashback.interfaces.IUbicacion;
import com.cashback.model.Barrio;
import com.cashback.model.Categoria;
import com.cashback.model.Ciudad;
import com.cashback.model.Contacto;
import com.cashback.model.HorarioAtencion;
import com.cashback.model.LocalPromocion;
import com.cashback.model.LocalVenta;
import com.cashback.model.Persona;
import com.cashback.model.Provincia;
import com.cashback.model.TextoClave;
import com.cashback.model.Ubicacion;

@SessionScoped
@ManagedBean
public class MntLocalVentaCtr extends Controladores {
	private LocalVenta localVenta;
	private List<LocalVenta> localVentaList;
	private List<Persona> personaList;
	private List<HorarioAtencion> horarioAtencionList;
	private List<Ubicacion> ubicacionList;
	private List<Contacto> contactoList;
	private HorarioAtencion horarioAtencion;
	private LocalPromocion localPromocion;
	private List<Categoria> categoriaList;

	private List<SelectItem> provinciaListItem, ciudadListItem, barrioListItem,
			categoriaListItem;
	private boolean nuevoLocalVentaRender, actualizarLocalVentaRender,
			nuevoHorarioAtencionRender, actualizarHorarioAtencionRender,
			crearUbicacionRender, actualizarUbicacionRender, ubicacionRender,
			pGridLvLogoRender, imagenesRender;
	private String rucLocal = "", nombreComercial = "", lvRazonSocial = "",
			perCedRuc = "", nombres = "", apellidos = "", lvPalabraClave = "";
	private Ubicacion ubicacion;
	private UploadedFile uploadedFile;
	private String catIdSeleccionada;
	private List<LocalPromocion> localPromocionList;
	private int prvIdSeleccionado, ciudadIdSeleccionado, barIdSeleccionado;
	private MapModel model;
	private boolean crearLocalPromocionRender, actualizarLocalPromocionRender;
	private List<String> palabraClaveList = new ArrayList<String>();

	@EJB
	private ILocalVenta sLocalVenta;
	@EJB
	private IPersona sPersona;
	@EJB
	private IUbicacion sUbicacion;
	@EJB
	private IContacto sContacto;
	@EJB
	private IParametrosGen sParametrosGen;
	@EJB
	private ILocalPromocion sLocalPromocion;
	@EJB
	private ITextoClave sTextoClave;

	public MntLocalVentaCtr() {
		model = new DefaultMapModel();
		Marker marker = new Marker(new LatLng(-1.2413046758271742,
				-78.61885736883778), "Test A");
		// Marker m2 = new Marker(new LatLng(36.779900, 30.667900), "Test B");
		// Marker m3 = new Marker(new LatLng(35.402452, 30.667900), "Test C");
		// marker.setDraggable(true);
		model.addOverlay(marker);
		// model.addOverlay(m2);
		// model.addOverlay(m3);
		/*
		 * Polyline polyline = new Polyline(); polyline.getPaths().add(new
		 * LatLng(36.879466, 30.667648)); polyline.getPaths().add(new
		 * LatLng(36.883707, 30.689216)); polyline.getPaths().add(new
		 * LatLng(36.879703, 30.706707)); polyline.getPaths().add(new
		 * LatLng(36.885233, 37.702323)); model.addOverlay(polyline);
		 */
		// Circle circle = new Circle(new LatLng(36.879466, 30.667648), 50);
		// model.addOverlay(circle);
		nuevoLocalVenta();
		nuevoLocalPromocion();
	}

	@PostConstruct
	public void inicio() {
		categoriaListItem = recuperarCategoriaItem();
		provinciaListItem = recuperarProvinciaListItem();
	}

	public void onGeocode(GeocodeEvent event) {
		System.out.println("DBG Entra en metodo geocodeevent");
		List<GeocodeResult> results = event.getResults();
		for (GeocodeResult gr : results) {
			System.out.println("DBG gr.getAddress()=" + gr.getAddress());
		}
	}

	public void onStateChange(StateChangeEvent event) {
		LatLngBounds bounds = event.getBounds();
		int zoomLevel = event.getZoomLevel();
		// addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Zoom Level",
		// String.valueOf(zoomLevel)));
		// addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Center",
		// event.getCenter().toString()));
		// addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "NorthEast",
		// bounds.getNorthEast().toString()));
		// addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "SouthWest",
		// bounds.getSouthWest().toString()));
	}

	public void onPointSelect(PointSelectEvent event) {
		LatLng latlng = event.getLatLng();
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Point Selected", "Lat:" + latlng.getLat() + ", Lng:"
						+ latlng.getLng()));
		ubicacion.setUbiLatitud(Double.toString(latlng.getLat()));
		ubicacion.setUbiLongitud(Double.toString(latlng.getLng()));
	}

	public void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String nuevoLocalVenta() {
		localVenta = new LocalVenta();
		localVentaList = new ArrayList<LocalVenta>();
		personaList = new ArrayList<Persona>();
		palabraClaveList = new ArrayList<String>();
		nuevoLocalVentaRender = true;
		actualizarLocalVentaRender = false;
		horarioAtencionList = new ArrayList<HorarioAtencion>();
		contactoList = new ArrayList<Contacto>();
		ubicacionRender = false;
		pGridLvLogoRender = false;
		ubicacionRender = false;
		imagenesRender = false;
		return null;
	}

	public String nuevoLocalVenta2() {
		nuevoLocalVenta();
		categoriaListItem = recuperarCategoriaItem();
		return null;
	}

	public String crearLocalVenta() {
		try {
			if (palabraClaveList.size() > 0) {
				String lvPalabrasClave = palabraClaveList.toString().replace(
						"[", "");
				localVenta.setLvPalabrasClave(lvPalabrasClave.replace("]", ""));
			} else {
				localVenta.setLvPalabrasClave("");
			}
			Categoria categoria = sCategoria
					.recuperarCategoria(catIdSeleccionada);
			localVenta.setCategoria(categoria);
			localVenta.setLvUsrCrea(usuario.getUsrNombre());
			localVenta.setLvFechaCrea(new Date());
			sLocalVenta.crearLocarVenta(localVenta);
			seleccionarLocalVenta();
			recuperarUbicacionList();
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode());
		}
		return null;
	}

	public String actualizarLocalVenta() {
		try {
			if (palabraClaveList.size() > 0) {
				String lvPalabrasClave = palabraClaveList.toString().replace(
						"[", "");
				localVenta.setLvPalabrasClave(lvPalabrasClave.replace("]", ""));
			} else {
				localVenta.setLvPalabrasClave(null);
			}
			Categoria categoria = sCategoria
					.recuperarCategoria(catIdSeleccionada);
			localVenta.setCategoria(categoria);
			localVenta.setLvUsrModifica(usuario.getUsrNombre());
			localVenta.setLvFechaModifica(new Date());
			localVenta = sLocalVenta.actualizarLocalVenta(localVenta);
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode());
		}
		return null;
	}

	public String recuperarLocalVentaList() {
		localVentaList = sLocalVenta.recuperarLocalVentaList(rucLocal,
				nombreComercial, lvRazonSocial);
		return null;
	}

	public String recuperarPersonaList() {
		personaList = sPersona.recuperarPersonaList(nombres, apellidos,
				perCedRuc);
		return null;
	}

	public String seleccionarLocalVenta() {
		palabraClaveList = new ArrayList<String>();
		ubicacionRender = true;
		contactoList = sContacto.recuperarContactoList(localVenta.getPersona());
		catIdSeleccionada = localVenta.getCategoria().getCatId();
		recuperarUbicacionList();
		model = new DefaultMapModel();
		for (Ubicacion ubicacion : ubicacionList) {
			double latitud = Double.parseDouble(ubicacion.getUbiLatitud());
			double longitud = Double.parseDouble(ubicacion.getUbiLongitud());
			Marker marker = new Marker(new LatLng(latitud, longitud),
					localVenta.getLvNombreCom());
			model.addOverlay(marker);
		}
		localPromocionList = sLocalPromocion.recuperarLocalPromocionList(
				localVenta, "", "");

		if (localVenta.getLvPalabrasClave() != null) {

			String[] palabraClaveArray = localVenta.getLvPalabrasClave().split(
					",");
			palabraClaveList = new ArrayList<String>(
					Arrays.asList(palabraClaveArray));
		}
		nuevoLocalVentaRender = false;
		actualizarLocalVentaRender = true;
		pGridLvLogoRender = true;
		imagenesRender = true;
		localPromocion = new LocalPromocion();
		return null;
	}

	public String seleccionarPersona() {
		contactoList = sContacto.recuperarContactoList(localVenta.getPersona());
		return null;
	}

	public String recuperarHorarioAtencionList() {
		horarioAtencionList = sLocalVenta
				.recuperarHorarioAtencionList(localVenta);
		return null;
	}

	public String nuevoHorarioAtencion() {
		nuevoHorarioAtencionRender = true;
		actualizarHorarioAtencionRender = false;
		horarioAtencion = new HorarioAtencion();
		horarioAtencion.setLocalVenta(localVenta);
		return null;
	}

	public String seleccionarHorarioAtencion() {
		nuevoHorarioAtencionRender = false;
		actualizarHorarioAtencionRender = true;
		return null;
	}

	public String nuevaUbicacion() {
		ubicacion = new Ubicacion();
		ubicacion.setLocalVenta(localVenta);
		crearUbicacionRender = true;
		actualizarUbicacionRender = false;
		provinciaListItem = recuperarProvinciaListItem();
		return null;
	}

	public String seleccionarUbicacion() {
		Barrio barrio = ubicacion.getBarrio();
		barIdSeleccionado = barrio.getBarId();

		Ciudad ciudad = barrio.getCiudad();
		ciudadIdSeleccionado = ciudad.getCiuId();
		barrioListItem = recuperarBarrioListItem(ciudad);

		Provincia provincia = ciudad.getProvincia();
		prvIdSeleccionado = provincia.getPrvId();
		ciudadListItem = recuperarCiudadListItem(provincia);

		crearUbicacionRender = false;
		actualizarUbicacionRender = true;
		model = new DefaultMapModel();
		double latitud = Double.parseDouble(ubicacion.getUbiLatitud());
		double longitud = Double.parseDouble(ubicacion.getUbiLongitud());
		Marker marker = new Marker(new LatLng(latitud, longitud),
				localVenta.getLvNombreCom());
		model.addOverlay(marker);
		return null;
	}

	public String crearUbicacion() {
		try {
			Barrio barrio = sBarrio.recuperarBarrio(barIdSeleccionado);
			ubicacion.setBarrio(barrio);
			String ubiPalabrasClave = barrio.getBarNombre() + ", "
					+ barrio.getCiudad().getCiuNombre() + " "
					+ barrio.getCiudad().getProvincia().getPrvNombre();
			ubicacion.setUbiPalabrasClave(ubiPalabrasClave);
			sUbicacion.crearUbicacion(ubicacion);
			ubicacionList.add(ubicacion);
			TextoClave textoClave = new TextoClave();
			textoClave.setTcTexto(ubiPalabrasClave);
			textoClave.setTcTipo(Globales.TC_TIPO_UBICACION);
			textoClave.setTcEstado(Globales.EST_OK);
			try {
				sTextoClave.crearTextoClave(textoClave);
			} catch (ExcGuardarRegistro e) {

			}
			model = new DefaultMapModel();
			for (Ubicacion ubicacion : ubicacionList) {
				double latitud = Double.parseDouble(ubicacion.getUbiLatitud());
				double longitud = Double
						.parseDouble(ubicacion.getUbiLongitud());
				Marker marker = new Marker(new LatLng(latitud, longitud),
						localVenta.getLvNombreCom());
				model.addOverlay(marker);
			}
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
			nuevaUbicacion();
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode(), e.getMessageText());
		}
		return null;
	}

	public String actualizarUbicacion() {
		try {
			Barrio barrio = sBarrio.recuperarBarrio(barIdSeleccionado);
			ubicacion.setBarrio(barrio);
			String ubiPalabrasClave = barrio.getBarNombre() + ", "
					+ barrio.getCiudad().getCiuNombre() + " "
					+ barrio.getCiudad().getProvincia().getPrvNombre();
			ubicacion.setUbiPalabrasClave(ubiPalabrasClave);
			ubicacion = sUbicacion.actualizarUbicacion(ubicacion);

			TextoClave textoClave = new TextoClave();
			textoClave.setTcTexto(ubiPalabrasClave);
			textoClave.setTcTipo(Globales.TC_TIPO_UBICACION);
			textoClave.setTcEstado(Globales.EST_OK);
			try {
				sTextoClave.crearTextoClave(textoClave);
			} catch (ExcGuardarRegistro e) {

			}

			model = new DefaultMapModel();
			for (Ubicacion ubicacion : ubicacionList) {
				double latitud = Double.parseDouble(ubicacion.getUbiLatitud());
				double longitud = Double
						.parseDouble(ubicacion.getUbiLongitud());
				Marker marker = new Marker(new LatLng(latitud, longitud),
						localVenta.getLvNombreCom());
				model.addOverlay(marker);
			}
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
			nuevaUbicacion();
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode(), e.getMessageText());
		}
		return null;
	}

	public String recuperarUbicacionList() {
		nuevaUbicacion();
		ubicacionList = sUbicacion.recuperarUbicacionList(localVenta);
		return null;
	}

	public String eliminarUbicacion() {
		ubicacionList.remove(ubicacion);
		sUbicacion.eliminarUbicacion(ubicacion);
		model = new DefaultMapModel();
		for (Ubicacion ubicacion : ubicacionList) {
			double latitud = Double.parseDouble(ubicacion.getUbiLatitud());
			double longitud = Double.parseDouble(ubicacion.getUbiLongitud());
			Marker marker = new Marker(new LatLng(latitud, longitud),
					localVenta.getLvNombreCom());
			model.addOverlay(marker);
		}
		ubicacion = new Ubicacion();
		mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		return null;
	}

	public void handleFileUpload(FileUploadEvent event) {
		uploadedFile = event.getFile();
		OutputStream out = null;
		File newFile = null;
		String arcNombreCompleto = uploadedFile.getFileName();
		String arcNombreFinal= localVenta.getLvId()	+ arcNombreCompleto;
		newFile = new File(pathImagenes + arcNombreFinal);
		try {
			newFile.createNewFile();
			out = new FileOutputStream(newFile);
			byte[] b = uploadedFile.getContents();
			out.write(b);
			localVenta.setLvLogo(arcNombreFinal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleFileUploadPRBR(FileUploadEvent event) {
		uploadedFile = event.getFile();
		OutputStream out = null;
		File newFile = null;
		String arcNombreOriginal = uploadedFile.getFileName();
		String arcNombreFinal = localVenta.getLvId() + "-" + arcNombreOriginal;
		try {
			newFile = new File(pathImagenes + arcNombreFinal);
			newFile.createNewFile();
			out = new FileOutputStream(newFile);
			byte[] b = uploadedFile.getContents();
			out.write(b);
			localPromocion.setLpImagen(arcNombreFinal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String nuevoLocalPromocion() {
		localPromocion = new LocalPromocion();
		crearLocalPromocionRender = true;
		actualizarLocalPromocionRender = false;
		return null;
	}

	public String seleccionarLocalPromocion() {
		crearLocalPromocionRender = false;
		actualizarLocalPromocionRender = true;
		return null;
	}

	public String crearLocalPromocion() {
		int i = 0;
		if (localPromocion.getLpTipo().compareTo(Globales.IMG_TIPO_BARRA) == 0) {
			for (LocalPromocion lp : localPromocionList) {
				if (lp.getLpTipo().compareTo(Globales.IMG_TIPO_BARRA) == 0) {
					i++;
				}
			}
			if (i >= 4) {
				mostrarError(AppMensajes.ERR_CREAR_REGISTRO.getMessageCode());
				return null;
			}
		}
		try {
			localPromocion.setLpUsrCrea(usuario.getUsrNombre());
			localPromocion.setLocalVenta(localVenta);
			localPromocion.setLpFecCrea(new Date());
			sLocalPromocion.crearLocalPromocion(localPromocion);
			localPromocionList.add(localPromocion);
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
			return null;
		} catch (ExcGuardarRegistro e) {
			e.printStackTrace();
		}
		return null;
	}

	public String actualizarLocalPromocion() {
		try {

			localPromocion.setLpUsrModif(usuario.getUsrNombre());
			sLocalPromocion.actualizarLocalPromocion(localPromocion);
			mostrarInfo(AppMensajes.INF_OPERACION_EXITO);
		} catch (ExcGuardarRegistro e) {
			mostrarError(e.getMessageCode());
		}
		return null;
	}

	public String eliminarLocalPromocion() {
		localPromocionList.remove(localPromocion);
		sLocalPromocion.eliminarLocalPromocion(localPromocion);
		File fichero = new File(pathImagenes + localPromocion.getLpImagen());
		if (fichero.delete()) {
		}
		mostrarInfo(AppMensajes.INF_ELIMINAR_REGISTRO);
		return null;
	}

	public String agregarPalabraClave() {
		if (lvPalabraClave != null) {
			if (lvPalabraClave.trim().length() > 0) {
				TextoClave textoClave = new TextoClave();
				textoClave.setTcTexto(lvPalabraClave);
				textoClave.setTcTipo(Globales.TC_TIPO_LOCAL);
				textoClave.setTcEstado(Globales.EST_OK);
				try {
					sTextoClave.crearTextoClave(textoClave);
				} catch (ExcGuardarRegistro e) {

				}
				palabraClaveList.add(lvPalabraClave);
				lvPalabraClave = null;
			}
		}
		return null;
	}

	public String quitarPalabraClave() {
		palabraClaveList.remove(lvPalabraClave);
		return null;
	}

	public String recuperarCiudadListItem() {
		Provincia provincia = new Provincia();
		provincia.setPrvId(prvIdSeleccionado);
		ciudadListItem = recuperarCiudadListItem(provincia);
		return null;
	}

	public String recuperarBarrioListItem() {
		Ciudad ciudad = new Ciudad();
		ciudad.setCiuId(ciudadIdSeleccionado);
		barrioListItem = recuperarBarrioListItem(ciudad);
		return null;
	}

	public LocalVenta getLocalVenta() {
		return localVenta;
	}

	public void setLocalVenta(LocalVenta localVenta) {
		this.localVenta = localVenta;
	}

	public List<LocalVenta> getLocalVentaList() {
		return localVentaList;
	}

	public void setLocalVentaList(List<LocalVenta> localVentaList) {
		this.localVentaList = localVentaList;
	}

	public Boolean getNuevoLocalVentaRender() {
		return nuevoLocalVentaRender;
	}

	public void setNuevoLocalVentaRender(Boolean nuevoLocalVentaRender) {
		this.nuevoLocalVentaRender = nuevoLocalVentaRender;
	}

	public Boolean getActualizarLocalVentaRender() {
		return actualizarLocalVentaRender;
	}

	public void setActualizarLocalVentaRender(Boolean actualizarLocalVentaRender) {
		this.actualizarLocalVentaRender = actualizarLocalVentaRender;
	}

	public String getRucLocal() {
		return rucLocal;
	}

	public void setRucLocal(String rucLocal) {
		this.rucLocal = rucLocal;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
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

	public List<Persona> getPersonaList() {
		return personaList;
	}

	public void setPersonaList(List<Persona> personaList) {
		this.personaList = personaList;
	}

	public List<HorarioAtencion> getHorarioAtencionList() {
		return horarioAtencionList;
	}

	public void setHorarioAtencionList(List<HorarioAtencion> horarioAtencionList) {
		this.horarioAtencionList = horarioAtencionList;
	}

	public HorarioAtencion getHorarioAtencion() {
		return horarioAtencion;
	}

	public void setHorarioAtencion(HorarioAtencion horarioAtencion) {
		this.horarioAtencion = horarioAtencion;
	}

	public Boolean getNuevoHorarioAtencionRender() {
		return nuevoHorarioAtencionRender;
	}

	public void setNuevoHorarioAtencionRender(Boolean nuevoHorarioAtencionRender) {
		this.nuevoHorarioAtencionRender = nuevoHorarioAtencionRender;
	}

	public Boolean getActualizarHorarioAtencionRender() {
		return actualizarHorarioAtencionRender;
	}

	public void setActualizarHorarioAtencionRender(
			Boolean actualizarHorarioAtencionRender) {
		this.actualizarHorarioAtencionRender = actualizarHorarioAtencionRender;
	}

	public Boolean getCrearUbicacionRender() {
		return crearUbicacionRender;
	}

	public void setCrearUbicacionRender(Boolean crearUbicacionRender) {
		this.crearUbicacionRender = crearUbicacionRender;
	}

	public Boolean getActualizarUbicacionRender() {
		return actualizarUbicacionRender;
	}

	public void setActualizarUbicacionRender(Boolean actualizarUbicacionRender) {
		this.actualizarUbicacionRender = actualizarUbicacionRender;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<Ubicacion> getUbicacionList() {
		return ubicacionList;
	}

	public void setUbicacionList(List<Ubicacion> ubicacionList) {
		this.ubicacionList = ubicacionList;
	}

	public List<SelectItem> getCiudadListItem() {
		return ciudadListItem;
	}

	public void setCiudadListItem(List<SelectItem> ciudadListItem) {
		this.ciudadListItem = ciudadListItem;
	}

	public int getCiudadIdSeleccionado() {
		return ciudadIdSeleccionado;
	}

	public void setCiudadIdSeleccionado(int ciudadIdSeleccionado) {
		this.ciudadIdSeleccionado = ciudadIdSeleccionado;
	}

	public List<Contacto> getContactoList() {
		return contactoList;
	}

	public void setContactoList(List<Contacto> contactoList) {
		this.contactoList = contactoList;
	}

	public String getPerCedRuc() {
		return perCedRuc;
	}

	public void setPerCedRuc(String perCedRuc) {
		this.perCedRuc = perCedRuc;
	}

	public MapModel getModel() {
		return model;
	}

	public void setModel(MapModel model) {
		this.model = model;
	}

	public Boolean getUbicacionRender() {
		return ubicacionRender;
	}

	public void setUbicacionRender(Boolean ubicacionRender) {
		this.ubicacionRender = ubicacionRender;
	}

	public List<SelectItem> getCategoriaListItem() {
		return categoriaListItem;
	}

	public void setCategoriaListItem(List<SelectItem> categoriaListItem) {
		this.categoriaListItem = categoriaListItem;
	}

	public List<Categoria> getCategoriaList() {
		return categoriaList;
	}

	public void setCategoriaList(List<Categoria> categoriaList) {
		this.categoriaList = categoriaList;
	}

	public Boolean getpGridLvLogoRender() {
		return pGridLvLogoRender;
	}

	public void setpGridLvLogoRender(Boolean pGridLvLogoRender) {
		this.pGridLvLogoRender = pGridLvLogoRender;
	}

	public String getCatIdSeleccionada() {
		return catIdSeleccionada;
	}

	public void setCatIdSeleccionada(String catIdSeleccionada) {
		this.catIdSeleccionada = catIdSeleccionada;
	}

	public String getLvRazonSocial() {
		return lvRazonSocial;
	}

	public void setLvRazonSocial(String lvRazonSocial) {
		this.lvRazonSocial = lvRazonSocial;
	}

	public List<LocalPromocion> getLocalPromocionList() {
		return localPromocionList;
	}

	public void setLocalPromocionList(List<LocalPromocion> localPromocionList) {
		this.localPromocionList = localPromocionList;
	}

	public LocalPromocion getLocalPromocion() {
		return localPromocion;
	}

	public void setLocalPromocion(LocalPromocion localPromocion) {
		this.localPromocion = localPromocion;
	}

	public boolean isCrearLocalPromocionRender() {
		return crearLocalPromocionRender;
	}

	public void setCrearLocalPromocionRender(boolean crearLocalPromocionRender) {
		this.crearLocalPromocionRender = crearLocalPromocionRender;
	}

	public boolean isActualizarLocalPromocionRender() {
		return actualizarLocalPromocionRender;
	}

	public void setActualizarLocalPromocionRender(
			boolean actualizarLocalPromocionRender) {
		this.actualizarLocalPromocionRender = actualizarLocalPromocionRender;
	}

	public boolean isImagenesRender() {
		return imagenesRender;
	}

	public void setImagenesRender(boolean imagenesRender) {
		this.imagenesRender = imagenesRender;
	}

	public List<String> getPalabraClaveList() {
		return palabraClaveList;
	}

	public void setPalabraClaveList(List<String> palabraClaveList) {
		this.palabraClaveList = palabraClaveList;
	}

	public String getLvPalabraClave() {
		return lvPalabraClave;
	}

	public void setLvPalabraClave(String lvPalabraClave) {
		this.lvPalabraClave = lvPalabraClave;
	}

	public int getPrvIdSeleccionado() {
		return prvIdSeleccionado;
	}

	public void setPrvIdSeleccionado(int prvIdSeleccionado) {
		this.prvIdSeleccionado = prvIdSeleccionado;
	}

	public int getBarIdSeleccionado() {
		return barIdSeleccionado;
	}

	public void setBarIdSeleccionado(int barIdSeleccionado) {
		this.barIdSeleccionado = barIdSeleccionado;
	}

	public List<SelectItem> getProvinciaListItem() {
		return provinciaListItem;
	}

	public void setProvinciaListItem(List<SelectItem> provinciaListItem) {
		this.provinciaListItem = provinciaListItem;
	}

	public List<SelectItem> getBarrioListItem() {
		return barrioListItem;
	}

	public void setBarrioListItem(List<SelectItem> barrioListItem) {
		this.barrioListItem = barrioListItem;
	}

}
