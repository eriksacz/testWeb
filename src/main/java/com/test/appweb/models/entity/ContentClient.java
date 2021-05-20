package com.test.appweb.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "concentrado_cliente")
public class ContentClient implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	private Cliente cliente;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)	
	private Dibujo dibujo;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	private Lote lote;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	private Composicion composicion;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	private Proveedor proveedor;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fecha;

	@NotNull
	private Long activo;
	
	

	
		
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	

	public Dibujo getDibujo() {
		return dibujo;
	}

	public void setDibujo(Dibujo dibujo) {
		this.dibujo = dibujo;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getActivo() {
		return activo;
	}

	public void setActivo(Long activo) {
		this.activo = activo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	private static final long serialVersionUID = 1L;

}
