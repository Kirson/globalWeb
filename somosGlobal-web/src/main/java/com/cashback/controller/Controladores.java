package com.cashback.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.cashback.enums.AppMensajes;
import com.cashback.interfaces.Globales;
import com.cashback.interfaces.IAppmensajes;
import com.cashback.interfaces.IBarrio;
import com.cashback.interfaces.ICategoria;
import com.cashback.interfaces.ICiudad;
import com.cashback.interfaces.IHobbie;
import com.cashback.interfaces.ILicencia;
import com.cashback.interfaces.ILocalVenta;
import com.cashback.interfaces.IParametrosGen;
import com.cashback.interfaces.IPerfil;
import com.cashback.interfaces.IProvincia;
import com.cashback.model.Barrio;
import com.cashback.model.CatalogoGen;
import com.cashback.model.Categoria;
import com.cashback.model.Ciudad;
import com.cashback.model.Hobbie;
import com.cashback.model.ICatalogoGen;
import com.cashback.model.ILocalidad;
import com.cashback.model.Licencia;
import com.cashback.model.LocalVenta;
import com.cashback.model.Localidad;
import com.cashback.model.Perfil;
import com.cashback.model.Provincia;
import com.cashback.model.Usuario;

public class Controladores {
	protected String pathImagenes, lvPalabrasClave, palabraClaveAct;
	protected String servidorNombre = "";

	@EJB
	private IAppmensajes sAppmensajes;
	@EJB
	private IHobbie sHobbie;
	@EJB
	private ICiudad sCiudad;
	@EJB
	protected IPerfil sPerfil;
	@EJB
	protected ILocalVenta sLocalVenta;
	@EJB
	protected ILicencia sLicencia;
	@EJB
	protected ICategoria sCategoria;
	@EJB
	protected IParametrosGen sParametrosGen;
	@EJB
	private IProvincia sProvincia;
	@EJB
	protected IBarrio sBarrio;
	@EJB
	protected ICatalogoGen sCatalogoGen;
	@EJB
	private ILocalidad sLocalidad;

	protected Usuario usuario;

	public Controladores() {
		usuario = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuario");
	}

	@PostConstruct
	public void inicioPrincipal() {
		pathImagenes = sParametrosGen.recuperarParamentrosGen(
				Globales.PAR_PATH_IMAGENES).getParValor();
		servidorNombre = sParametrosGen.recuperarParamentrosGen(
				Globales.SERVIDOR_NOMBRE).getParValor();

	}

	public String dateTimeToFechaHora(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public void mostrarError(String messageCode) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, sAppmensajes
						.recuperarAppmensaje(messageCode).getMenTexto(), null));
	}

	public void mostrarError(String messageCode, String messageText) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, sAppmensajes
						.recuperarAppmensaje(messageCode).getMenTexto()
						+ " "
						+ messageText, null));
	}

	public void mostrarInfo(AppMensajes a) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Somos Global",
						sAppmensajes.recuperarAppmensaje(a.getMessageCode())
								.getMenTexto()));
	}

	public void mostrarInfo(String summary, AppMensajes a) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary,
						sAppmensajes.recuperarAppmensaje(a.getMessageCode())
								.getMenTexto()));
	}

	public void mostrarErrorSummary(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
	}

	protected List<SelectItem> recuperarHobbieListItem() {
		List<Hobbie> hobbieList = sHobbie.recuperarHobbieList(Globales.EST_OK);
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		for (Hobbie h : hobbieList) {
			SelectItem si = new SelectItem(h.getHobId(), h.getHobNombre());
			selectItemList.add(si);
		}
		return selectItemList;
	}

	protected List<SelectItem> recuperarProvinciaListItem() {
		List<Provincia> provinciaList = sProvincia.recuperarProvinciaList();
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		for (Provincia p : provinciaList) {
			SelectItem si = new SelectItem(p.getPrvId(), p.getPrvNombre());
			selectItemList.add(si);
		}
		return selectItemList;
	}

	protected List<SelectItem> recuperarCiudadListItem(Provincia provincia) {
		List<Ciudad> ciudadList = sCiudad.recuperarCiudadList(provincia);
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		for (Ciudad c : ciudadList) {
			SelectItem si = new SelectItem(c.getCiuId(), c.getCiuNombre());
			selectItemList.add(si);
		}
		return selectItemList;
	}

	protected List<SelectItem> recuperarBarrioListItem(Ciudad ciudad) {
		List<Barrio> barrioList = sBarrio.recuperarBarrioList(ciudad);
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		for (Barrio b : barrioList) {
			SelectItem si = new SelectItem(b.getBarId(), b.getBarNombre());
			selectItemList.add(si);
		}
		return selectItemList;
	}

	protected List<SelectItem> recuperarPerfilItem() {
		List<Perfil> perfilList = sPerfil.recuperarPerfilList("");
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		for (Perfil perfil : perfilList) {
			SelectItem si = new SelectItem(perfil.getPrfId(),
					perfil.getPrfNombre());
			selectItemList.add(si);
		}
		return selectItemList;
	}

	protected List<SelectItem> recuperarLocalVentaItem() {
		List<LocalVenta> localVentaList = sLocalVenta.recuperarLocalVenta();
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		for (LocalVenta localVenta : localVentaList) {
			SelectItem si = new SelectItem(localVenta.getLvId(),
					localVenta.getLvNombreCom());
			selectItemList.add(si);
		}
		return selectItemList;
	}

	protected List<SelectItem> recuperarLicenciaItem() {
		List<Licencia> licenciaList = sLicencia.recuperarLicenciaList();
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		for (Licencia licencia : licenciaList) {
			SelectItem si = new SelectItem(licencia.getLicId(),
					licencia.getLicNombre());
			selectItemList.add(si);
		}
		return selectItemList;
	}

	protected List<SelectItem> recuperarCategoriaItem() {
		List<Categoria> categoriaList = sCategoria
				.recuperarCategoriaList(Globales.EST_OK);
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		for (Categoria categoria : categoriaList) {
			SelectItem si = new SelectItem(categoria.getCatId(),
					categoria.getCatId() + " - "
							+ categoria.getCatNombre().trim().toUpperCase(),
					"", categoria.getCatId().length() < 6);
			selectItemList.add(si);
		}
		return selectItemList;
	}

	protected List<SelectItem> recuperarCatalogoByFather(
			CatalogoGen padreCatalogoGen) {
		List<CatalogoGen> catalogoGenList = sCatalogoGen
				.findAllByFather(padreCatalogoGen);
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		for (CatalogoGen cg : catalogoGenList) {
			SelectItem si = new SelectItem(cg.getIdCg(), cg.getNombreCg());
			selectItemList.add(si);
		}
		return selectItemList;
	}

	protected List<SelectItem> recuperarLocalidadItem(Localidad localidad) {
		List<Localidad> localidadList = sLocalidad
				.recuperarLocalidad(localidad);
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		for (Localidad loc : localidadList) {
			SelectItem si = new SelectItem(loc.getLocId(), loc.getLocNombre());
			selectItemList.add(si);
		}
		return selectItemList;
	}

	public List<SelectItem> recuperarCategoriaListItem(String catId) {
		List<Categoria> categoriaList = sCategoria.recuperarCategoriaList(
				catId, Globales.EST_OK);
		List<SelectItem> categoriaListItem = new ArrayList<SelectItem>();
		for (Categoria c : categoriaList) {
			SelectItem si = new SelectItem(c.getCatId(), c.getCatNombre());
			categoriaListItem.add(si);
		}
		return categoriaListItem;
	}

	public String getServidorNombre() {
		return servidorNombre;
	}

	public void setServidorNombre(String servidorNombre) {
		this.servidorNombre = servidorNombre;
	}

	public String getLvPalabrasClave() {
		return lvPalabrasClave;
	}

	public void setLvPalabrasClave(String lvPalabrasClave) {
		this.lvPalabrasClave = lvPalabrasClave;
	}

	public String getPalabraClaveAct() {
		return palabraClaveAct;
	}

	public void setPalabraClaveAct(String palabraClaveAct) {
		this.palabraClaveAct = palabraClaveAct;
	}

}
