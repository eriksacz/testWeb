package com.test.appweb.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "reportedos")
public class reportedos implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private int empleado;
	
	@NotNull
	private int dibujo;
	
	@NotNull
	private int proveedor;
	
	@NotNull
	private int composicion;
	
	@NotNull
	private int maquina;
	
	@NotNull	
	private int lote;
	
	@NotNull
	private int turno;
	
	@NotNull
	private int factura;
	
	@NotNull
	private int cliente;
	
	@NotNull
	private String rollo;
	
	@NotEmpty
	private String kilo;
	
	@NotNull
	private String fecha;



	


	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}






	public int getEmpleado() {
		return empleado;
	}






	public void setEmpleado(int empleado) {
		this.empleado = empleado;
	}






	public int getDibujo() {
		return dibujo;
	}






	public void setDibujo(int dibujo) {
		this.dibujo = dibujo;
	}






	public int getProveedor() {
		return proveedor;
	}






	public void setProveedor(int proveedor) {
		this.proveedor = proveedor;
	}






	public int getComposicion() {
		return composicion;
	}






	public void setComposicion(int composicion) {
		this.composicion = composicion;
	}






	public int getMaquina() {
		return maquina;
	}






	public void setMaquina(int maquina) {
		this.maquina = maquina;
	}






	public int getLote() {
		return lote;
	}






	public void setLote(int lote) {
		this.lote = lote;
	}






	public int getTurno() {
		return turno;
	}






	public void setTurno(int turno) {
		this.turno = turno;
	}






	public int getFactura() {
		return factura;
	}






	public void setFactura(int factura) {
		this.factura = factura;
	}






	public int getCliente() {
		return cliente;
	}






	public void setCliente(int cliente) {
		this.cliente = cliente;
	}






	public String getRollo() {
		return rollo;
	}






	public void setRollo(String rollo) {
		this.rollo = rollo;
	}






	public String getKilo() {
		return kilo;
	}






	public void setKilo(String kilo) {
		this.kilo = kilo;
	}












	public String getFecha() {
		return fecha;
	}






	public void setFecha(String fecha) {
		this.fecha = fecha;
	}






	public static long getSerialversionuid() {
		return serialVersionUID;
	}






	private static final long serialVersionUID = 1L;

}
