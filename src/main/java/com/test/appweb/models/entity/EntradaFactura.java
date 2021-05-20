package com.test.appweb.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "entradas_fac")
public class EntradaFactura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Lote lote;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Composicion composicion;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Proveedor proveedor;

	@NotEmpty
	@Column(name = "no_factura")
	private String noFactura;

	@NotEmpty
	@Column(name = "no_articulo")
	private String noArticulo;

	@NotEmpty
	private String kilo;

	@NotNull
	private Long activo;

	@NotNull
	private Long estado;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fecha;

	@OneToMany(mappedBy = "entradaFactura", fetch = FetchType.LAZY)
	private List<DibuFac> dibuFac;

	@OneToMany(mappedBy = "entradaFactura", fetch = FetchType.LAZY)
	private List<Produccion> Produccion;
	
	@OneToMany(mappedBy = "entradaFactura", fetch = FetchType.LAZY)
	private List<Salida> Salida;
	
	@OneToMany(mappedBy = "entradaFactura", fetch = FetchType.LAZY)
	private List<Segunda> Segunda;
	
	@OneToMany(mappedBy = "entradaFactura", fetch = FetchType.LAZY)
	private List<ProduConsulta> ProduConsulta;
	
	@OneToMany(mappedBy = "entradaFactura", fetch = FetchType.LAZY)
	private List<reporte> reporte;

	public EntradaFactura() {
		dibuFac = new ArrayList<DibuFac>();
		Produccion = new ArrayList<Produccion>();
		Salida = new ArrayList<Salida>();
		Segunda = new ArrayList<Segunda>();
		ProduConsulta = new ArrayList<ProduConsulta>();
		reporte = new ArrayList<reporte>();
	}

	
	
	public List<reporte> getReporte() {
		return reporte;
	}



	public void setReporte(List<reporte> reporte) {
		this.reporte = reporte;
	}



	public List<ProduConsulta> getProduConsulta() {
		return ProduConsulta;
	}

	public void setProduConsulta(List<ProduConsulta> produConsulta) {
		ProduConsulta = produConsulta;
	}

	public List<Segunda> getSegunda() {
		return Segunda;
	}

	public void setSegunda(List<Segunda> segunda) {
		Segunda = segunda;
	}

	public List<Salida> getSalida() {
		return Salida;
	}

	public void setSalida(List<Salida> salida) {
		Salida = salida;
	}

	public List<Produccion> getProduccion() {
		return Produccion;
	}

	public void setProduccion(List<Produccion> produccion) {
		Produccion = produccion;
	}

	public List<DibuFac> getDibuFac() {
		return dibuFac;
	}

	public void setDibuFac(List<DibuFac> dibuFac) {
		this.dibuFac = dibuFac;
	}

	public Long getEstado() {
		return estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public String getKilo() {
		return kilo;
	}

	public void setKilo(String kilo) {
		this.kilo = kilo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public Composicion getComposicion() {
		return composicion;
	}

	public void setComposicion(Composicion composicion) {
		this.composicion = composicion;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public String getNoFactura() {
		return noFactura;
	}

	public void setNoFactura(String noFactura) {
		this.noFactura = noFactura;
	}

	public String getNoArticulo() {
		return noArticulo;
	}

	public void setNoArticulo(String noArticulo) {
		this.noArticulo = noArticulo;
	}

	public Long getActivo() {
		return activo;
	}

	public void setActivo(Long activo) {
		this.activo = activo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
