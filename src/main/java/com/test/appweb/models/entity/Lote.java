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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "lotes")
public class Lote implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "lote")
	private String nomlote;

	@NotNull
	private Long activo;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fecha;

	@OneToMany(mappedBy = "lote", fetch = FetchType.LAZY)
	private List<ContentClient> ConcentClients;

	@OneToMany(mappedBy = "lote", fetch = FetchType.LAZY)
	private List<EntradaFactura> EntradaFactura;

	@OneToMany(mappedBy = "lote", fetch = FetchType.LAZY)
	private List<ClientLot> ClientLot;

	@OneToMany(mappedBy = "lote", fetch = FetchType.LAZY)
	private List<Produccion> Produccion;
	
	@OneToMany(mappedBy = "lote", fetch = FetchType.LAZY)
	private List<Salida> Salida;
	
	@OneToMany(mappedBy = "lote", fetch = FetchType.LAZY)
	private List<Segunda> Segunda;
	
	@OneToMany(mappedBy = "lote", fetch = FetchType.LAZY)
	private List<ProduConsulta> ProduConsulta;
	
	@OneToMany(mappedBy = "lote", fetch = FetchType.LAZY)
	private List<reporte> reporte;

	public Lote() {
		ConcentClients = new ArrayList<ContentClient>();
		EntradaFactura = new ArrayList<EntradaFactura>();
		ClientLot = new ArrayList<ClientLot>();
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

	public List<ClientLot> getClientLot() {
		return ClientLot;
	}

	public void setClientLot(List<ClientLot> clientLot) {
		ClientLot = clientLot;
	}

	public List<ContentClient> getConcentClients() {
		return ConcentClients;
	}

	public void setConcentClients(List<ContentClient> concentClients) {
		ConcentClients = concentClients;
	}

	public List<EntradaFactura> getEntradaFactura() {
		return EntradaFactura;
	}

	public void setEntradaFactura(List<EntradaFactura> entradaFactura) {
		EntradaFactura = entradaFactura;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomlote() {
		return nomlote;
	}

	public void setNomlote(String nomlote) {
		this.nomlote = nomlote;
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
