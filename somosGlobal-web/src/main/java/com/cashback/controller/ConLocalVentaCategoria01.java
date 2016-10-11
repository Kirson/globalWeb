package com.cashback.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.cashback.enums.AppMensajes;
import com.cashback.interfaces.Globales;
import com.cashback.interfaces.ICategoria;
import com.cashback.interfaces.ILocalVenta;
import com.cashback.interfaces.IUbicacion;
import com.cashback.model.Categoria;
import com.cashback.model.LocalVenta;

@SessionScoped
@ManagedBean
public class ConLocalVentaCategoria01 extends Controladores {
	private List<LocalVenta> localVentaList;
	private String catId, catIdSeleccionada;
	private List<SelectItem> categoriaListItem;
	private Categoria categoria;

	@EJB
	private IUbicacion sUbicacion;
	@EJB
	private ILocalVenta sLocalVenta;
	@EJB
	private ICategoria sCategoria;

	public ConLocalVentaCategoria01() {

	}

	@PostConstruct
	public void inicio() {
		catId = "01";
		categoria = sCategoria.recuperarCategoria(catId);
		recuperarLocalVentaCategoria(catId);
		recuperarCategoriaListItem();
	}

	public String recuperarLocalVentaCategoria(String catId) {
		localVentaList = sLocalVenta.recuperaLocalVentaList(catId, true, true);
		Collections.shuffle(localVentaList);
		return null;
	}

	public void recuperarCategoriaListItem() {
		List<Categoria> categoriaList = sCategoria.recuperarCategoriaList(
				catId, Globales.EST_OK);
		categoriaListItem = new ArrayList<SelectItem>();
		for (Categoria c : categoriaList) {
			SelectItem si = new SelectItem(c.getCatId(), c.getCatNombre());
			categoriaListItem.add(si);
		}
	}

	public String recuperarLocalVentaList() {
		recuperarLocalVentaCategoria(catIdSeleccionada);
		return null;
	}

	public String recuperarLocalVentaList3() {
		if (lvPalabrasClave.trim().length() == 0) {
			mostrarInfo(AppMensajes.INF_NO_RESULTADOS);
			return null;
		}
		localVentaList = sLocalVenta.recuperarLocalVentaList(lvPalabrasClave,
				"");
		return null;
	}

	public String recuperarLocalVentaList2() {
		recuperarLocalVentaCategoria(catId);
		return null;
	}

	public IUbicacion getsUbicacion() {
		return sUbicacion;
	}

	public void setsUbicacion(IUbicacion sUbicacion) {
		this.sUbicacion = sUbicacion;
	}

	public List<LocalVenta> getLocalVentaList() {
		return localVentaList;
	}

	public void setLocalVentaList(List<LocalVenta> localVentaList) {
		this.localVentaList = localVentaList;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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

	
}

/*
 * public void test() {
 * 
 * }
 * 
 * public StreamedContent streamImage(String parValorLvLogo) {
 * System.out.println("DBG parValorPathLogo=" + parValorLvLogo); try {
 * FacesContext context = FacesContext.getCurrentInstance(); BufferedImage img =
 * null; img = ImageIO.read(new File(parValorLvLogo)); ByteArrayOutputStream
 * baos = new ByteArrayOutputStream(); ImageIO.write(img, "jpg", baos);
 * baos.flush(); byte[] imageInByte = baos.toByteArray(); baos.close(); if
 * (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) { System.out
 * .println(
 * "DBG entra el if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)" );
 * // So, we're rendering the view. Return a stub StreamedContent // so that it
 * will generate right URL. //return new DefaultStreamedContent(); return new
 * DefaultStreamedContent(new ByteArrayInputStream( imageInByte)); } else {
 * System.out.println("DBG entra en else"); // So, browser is requesting the
 * image. Get ID value from actual // request param. // String id =
 * context.getExternalContext() // .getRequestParameterMap().get("id");
 * 
 * return new DefaultStreamedContent(new ByteArrayInputStream( imageInByte)); }
 * 
 * } catch (IOException e) { return new DefaultStreamedContent(); } }
 * 
 * public StreamedContent getStreamedImageById(String logo) {
 * System.out.println("DBG getStreamedImageById"); try { FacesContext context =
 * FacesContext.getCurrentInstance(); String pathImg =
 * sParametrosGen.recuperarParamentrosGen(
 * Globales.PAR_PATH_LOGO_LOCAL).getParValor() +
 * localVentaCategoria.getLocalVenta().getLvLogo();
 * System.out.println("DBG pathImg="+pathImg); BufferedImage img = null; img =
 * ImageIO.read(new File(pathImg)); ByteArrayOutputStream baos = new
 * ByteArrayOutputStream(); ImageIO.write(img, "jpg", baos); baos.flush();
 * byte[] imageInByte = baos.toByteArray(); baos.close(); if
 * (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) { // So, we're
 * rendering the view. Return a stub StreamedContent // so that it will generate
 * right URL. return new DefaultStreamedContent(); }
 * 
 * else { System.out.println("DBG entra en else"); // So, browser is requesting
 * the image. Get ID value from actual // request param. // String id =
 * context.getExternalContext() // .getRequestParameterMap().get("id");
 * 
 * return new DefaultStreamedContent(new ByteArrayInputStream( imageInByte)); }
 * } catch (IOException e) { return new DefaultStreamedContent(); } }
 */
