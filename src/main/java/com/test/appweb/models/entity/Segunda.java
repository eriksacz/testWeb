package com.test.appweb.models.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "segunda")
public class Segunda implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Empleado empleado;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Dibujo dibujo;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Proveedor proveedor;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Composicion composicion;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Maquina maquina;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Lote lote;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Turno turno;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private EntradaFactura entradaFactura;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	@NotNull
	private Long rollo;

	@NotEmpty
	private String kilo;
	
	@NotEmpty
	private String defecto;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fecha;

	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Empleado getEmpleado() {
		return empleado;
	}



	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}



	public Dibujo getDibujo() {
		return dibujo;
	}



	public void setDibujo(Dibujo dibujo) {
		this.dibujo = dibujo;
	}



	public Proveedor getProveedor() {
		return proveedor;
	}



	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}



	public Composicion getComposicion() {
		return composicion;
	}



	public void setComposicion(Composicion composicion) {
		this.composicion = composicion;
	}



	public Maquina getMaquina() {
		return maquina;
	}



	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}



	public Lote getLote() {
		return lote;
	}



	public void setLote(Lote lote) {
		this.lote = lote;
	}



	public Turno getTurno() {
		return turno;
	}



	public void setTurno(Turno turno) {
		this.turno = turno;
	}



	public EntradaFactura getEntradaFactura() {
		return entradaFactura;
	}



	public void setEntradaFactura(EntradaFactura entradaFactura) {
		this.entradaFactura = entradaFactura;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public Long getRollo() {
		return rollo;
	}



	public void setRollo(Long rollo) {
		this.rollo = rollo;
	}



	public String getKilo() {
		return kilo;
	}



	public void setKilo(String kilo) {
		this.kilo = kilo;
	}



	public String getDefecto() {
		return defecto;
	}



	public void setDefecto(String defecto) {
		this.defecto = defecto;
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
