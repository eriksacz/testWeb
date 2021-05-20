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
@Table(name = "dibujos")
public class Dibujo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "dibujo")
	private String nomdibujo;

	@NotNull
	private Long activo;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fecha;

	@OneToMany(mappedBy = "dibujo", fetch = FetchType.LAZY)
	private List<ContentClient> ConcentClients;

	@OneToMany(mappedBy = "dibujo", fetch = FetchType.LAZY)
	private List<MaquiDibu> MaquiDibus;

	@OneToMany(mappedBy = "dibujo", fetch = FetchType.LAZY)
	private List<DibuFac> dibuFac;

	@OneToMany(mappedBy = "dibujo", fetch = FetchType.LAZY)
	private List<ClientDibu> ClientDibu;

	@OneToMany(mappedBy = "dibujo", fetch = FetchType.LAZY)
	private List<Produccion> Produccion;
	
	@OneToMany(mappedBy = "dibujo", fetch = FetchType.LAZY)
	private List<Salida> Salida;
	
	@OneToMany(mappedBy = "dibujo", fetch = FetchType.LAZY)
	private List<Segunda> Segunda;

	@OneToMany(mappedBy = "dibujo", fetch = FetchType.LAZY)
	private List<ProduConsulta> ProduConsulta;
	
	@OneToMany(mappedBy = "dibujo", fetch = FetchType.LAZY)
	private List<reporte> reporte;
	
	public Dibujo() {
		ConcentClients = new ArrayList<ContentClient>();
		MaquiDibus = new ArrayList<MaquiDibu>();
		dibuFac = new ArrayList<DibuFac>();
		ClientDibu = new ArrayList<ClientDibu>();
		Produccion = new ArrayList<Produccion>();
		Salida = new  ArrayList<Salida>();
		Segunda = new  ArrayList<Segunda>();
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

	public List<ClientDibu> getClientDibu() {
		return ClientDibu;
	}

	public void setClientDibu(List<ClientDibu> clientDibu) {
		ClientDibu = clientDibu;
	}

	public List<DibuFac> getDibuFac() {
		return dibuFac;
	}

	public void setDibuFac(List<DibuFac> dibuFac) {
		this.dibuFac = dibuFac;
	}

	public List<ContentClient> getConcentClients() {
		return ConcentClients;
	}

	public void setConcentClients(List<ContentClient> concentClients) {
		ConcentClients = concentClients;
	}

	public List<MaquiDibu> getMaquiDibus() {
		return MaquiDibus;
	}

	public void setMaquiDibus(List<MaquiDibu> maquiDibus) {
		MaquiDibus = maquiDibus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomdibujo() {
		return nomdibujo;
	}

	public void setNomdibujo(String nomdibujo) {
		this.nomdibujo = nomdibujo;
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
