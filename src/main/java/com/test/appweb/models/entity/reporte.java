package com.test.appweb.models.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reporte")
public class reporte implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idr;
	
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
	@ManyToOne(fetch=FetchType.LAZY)	
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
	private String rollor;
	
	@NotEmpty
	private String kilor;
	
	@NotNull
	private String fechar;


	public Long getIdr() {
		return idr;
	}

	public void setIdr(Long idr) {
		this.idr = idr;
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

	public String getRollor() {
		return rollor;
	}

	public void setRollor(String rollor) {
		this.rollor = rollor;
	}


	public String getKilor() {
		return kilor;
	}


	public void setKilor(String kilor) {
		this.kilor = kilor;
	}

	public String getFechar() {
		return fechar;
	}


	public void setFechar(String fechar) {
		this.fechar = fechar;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	private static final long serialVersionUID = 1L;

}
