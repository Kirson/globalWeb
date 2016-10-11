package com.cashback.controller;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.cashback.interfaces.Globales;
import com.cashback.interfaces.ICategoria;
import com.cashback.interfaces.ILocalVenta;
import com.cashback.interfaces.IParametrosGen;
import com.cashback.interfaces.IUbicacion;
import com.cashback.model.Categoria;
import com.cashback.model.LocalPromocion;
import com.cashback.model.LocalVenta;
import com.cashback.model.ParametrosGen;
import com.cashback.model.Ubicacion;

@SessionScoped
@ManagedBean
public class ConLocalVentaCategoria {
	private List<Ubicacion> ubicacionList;
	private List<LocalVenta> localVentaList;
	private ParametrosGen parametrosGen;
	private List<LocalPromocion> localPromocionList, localPromocionBarraList;
	private LocalVenta localVenta;
	private Ubicacion ubicacion;
	private String catId, centerMap, catIdSeleccionada;
	private String ubiFrameMapa = "";
	private String ubiFrameSv = "";
	private List<SelectItem> categoriaListItem;
	private MapModel model;
	private Marker marker;
	private Categoria categoria;


	@EJB
	private IParametrosGen sParametrosGen;
	@EJB
	private IUbicacion sUbicacion;
	@EJB
	private ILocalVenta sLocalVenta;
	@EJB
	private ICategoria sCategoria;

	public ConLocalVentaCategoria() {
		model = new DefaultMapModel();
	}

	public void asignarCatId01() {
		catId = "01";
		recuperarLocalVentaCategoria();
		
	}

	public void asignarCatId02() {
		catId = "02";
		recuperarLocalVentaCategoria();
	}

	public void asignarCatId03() {
		catId = "03";
		recuperarLocalVentaCategoria();
	}

	public void asignarCatId04() {
		catId = "04";
		recuperarLocalVentaCategoria();
	}

	public void asignarCatId05() {
		catId = "05";
		recuperarLocalVentaCategoria();
	}

	public void asignarCatId06() {
		catId = "06";
		recuperarLocalVentaCategoria();
	}

	public void asignarCatId07() {
		catId = "07";
		recuperarLocalVentaCategoria();
	}

	public String recuperarLocalVentaCategoria() {
		categoria = sCategoria.recuperarCategoria(catId);
		localVentaList = sLocalVenta.recuperaLocalVentaList(this.catId, true,
				true);
		recuperarCategoriaListItem(catId);
		return null;
	}

	private void recuperarCategoriaListItem(String catId) {
		List<Categoria> categoriaList = sCategoria.recuperarCategoriaList(
				catId.substring(0, 2), Globales.EST_OK);
		categoriaListItem = new ArrayList<SelectItem>();
		for (Categoria c : categoriaList) {
			SelectItem si = new SelectItem(c.getCatId(), c.getCatNombre());
			categoriaListItem.add(si);
		}

	}

	public String recuperarLocalVentaList(){
		localVentaList= sLocalVenta.recuperaLocalVentaList(catIdSeleccionada, true, true);
		return null;
	}

	public String seleccionarLocalVenta() {
		localPromocionList = sLocalVenta.recuperarLocalPromocionList(
				localVenta, Globales.IMG_TIPO_PROMO, Globales.EST_OK);

		localPromocionBarraList = sLocalVenta.recuperarLocalPromocionList(
				localVenta, Globales.IMG_TIPO_BARRA, Globales.EST_OK);
		if (localPromocionBarraList.size() < 4) {
			for (int i = localPromocionBarraList.size(); i < 4; i++) {
				LocalPromocion lp = new LocalPromocion();
				lp.setLpImagen("feliz.png");
				localPromocionBarraList.add(lp);
			}
		}

		ubicacionList = sUbicacion.recuperarUbicacionList(localVenta);
		model = new DefaultMapModel();

		for (Ubicacion ubicacion : ubicacionList) {
			double latitud = Double.parseDouble(ubicacion.getUbiLatitud());
			double longitud = Double.parseDouble(ubicacion.getUbiLongitud());
			centerMap = String.valueOf(latitud) + ", "
					+ String.valueOf(longitud);
			Marker marker = new Marker(new LatLng(latitud, longitud),
					localVenta.getLvNombreCom());
			marker.setCursor(Integer.toString(ubicacion.getUbiId()));
			model.addOverlay(marker);
		}
		if (centerMap == null) {
			centerMap = "-1.241541009574931, -78.61058816313744";
		}
		return "conLocal";
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		this.marker = (Marker) event.getOverlay();
		ubicacion = sUbicacion.recuperarUbicacion(Integer.parseInt(marker
				.getCursor()));
		ubiFrameMapa = ubicacion.getUbiFrameMapa();
		ubiFrameSv = ubicacion.getUbiFrameSv();
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public ParametrosGen getParametrosGen() {
		return parametrosGen;
	}

	public void setParametrosGen(ParametrosGen parametrosGen) {
		this.parametrosGen = parametrosGen;
	}

	public List<Ubicacion> getUbicacionList() {
		return ubicacionList;
	}

	public void setUbicacionList(List<Ubicacion> ubicacionList) {
		this.ubicacionList = ubicacionList;
	}

	public IUbicacion getsUbicacion() {
		return sUbicacion;
	}

	public void setsUbicacion(IUbicacion sUbicacion) {
		this.sUbicacion = sUbicacion;
	}

	public MapModel getModel() {
		return model;
	}

	public void setModel(MapModel model) {
		this.model = model;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public List<LocalVenta> getLocalVentaList() {
		return localVentaList;
	}

	public void setLocalVentaList(List<LocalVenta> localVentaList) {
		this.localVentaList = localVentaList;
	}

	public LocalVenta getLocalVenta() {
		return localVenta;
	}

	public void setLocalVenta(LocalVenta localVenta) {
		this.localVenta = localVenta;
	}

	public List<LocalPromocion> getLocalPromocionList() {
		return localPromocionList;
	}

	public void setLocalPromocionList(List<LocalPromocion> localPromocionList) {
		this.localPromocionList = localPromocionList;
	}

	public List<LocalPromocion> getLocalPromocionBarraList() {
		return localPromocionBarraList;
	}

	public void setLocalPromocionBarraList(
			List<LocalPromocion> localPromocionBarraList) {
		this.localPromocionBarraList = localPromocionBarraList;
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

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getCenterMap() {
		return centerMap;
	}

	public void setCenterMap(String centerMap) {
		this.centerMap = centerMap;
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
