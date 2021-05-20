package com.test.appweb.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name = "maquinas")
public class Maquina implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String numero;

	@NotEmpty
	private String marca;

	@NotNull
	private Long activo;

	@NotNull
	private Long estado;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fecha;

	@OneToMany(mappedBy = "maquina", fetch = FetchType.LAZY)
	private List<MaquiDibu> MaquiDibus;

	@OneToMany(mappedBy = "maquina", fetch = FetchType.LAZY)
	private List<FacDibuMaqui> FacDibuMaquis;

	@OneToMany(mappedBy = "maquina", fetch = FetchType.LAZY)
	private List<Produccion> Produccion;
	
	@OneToMany(mappedBy = "maquina", fetch = FetchType.LAZY)
	private List<Salida> Salida;
	
	@OneToMany(mappedBy = "maquina", fetch = FetchType.LAZY)
	private List<Segunda> Segunda;
		
	@OneToMany(mappedBy = "maquina", fetch = FetchType.LAZY)
	private List<ProduConsulta> ProduConsulta;
	
	@OneToMany(mappedBy = "maquina", fetch = FetchType.LAZY)
	private List<reporte> reporte;
	
	

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

	public List<FacDibuMaqui> getFacDibuMaquis() {
		return FacDibuMaquis;
	}

	public void setFacDibuMaquis(List<FacDibuMaqui> facDibuMaquis) {
		FacDibuMaquis = facDibuMaquis;
	}

	public Maquina() {
		MaquiDibus = new ArrayList<MaquiDibu>();
	}

	public List<MaquiDibu> getMaquiDibus() {
		return MaquiDibus;
	}

	public void setMaquiDibus(List<MaquiDibu> maquiDibus) {
		MaquiDibus = maquiDibus;
	}

	public Long getEstado() {
		return estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
