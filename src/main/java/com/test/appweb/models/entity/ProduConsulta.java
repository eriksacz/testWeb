package com.test.appweb.models.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "produccionmineria")
public class ProduConsulta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idr;

	@NotNull
	@JoinColumn(name = "empleador_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Empleado empleado;

	@NotNull
	@JoinColumn(name = "dibujor_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Dibujo dibujo;

	@NotNull
	@JoinColumn(name = "proveedorr_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Proveedor proveedor;

	@NotNull
	@JoinColumn(name = "composicionr_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Composicion composicion;

	@NotNull
	@JoinColumn(name = "maquinar_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Maquina maquina;

	@NotNull
	@JoinColumn(name = "loter_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Lote lote;

	@NotNull
	@JoinColumn(name = "turnor_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Turno turno;

	@NotNull
	@JoinColumn(name = "entrada_facturar_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private EntradaFactura entradaFactura;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clienter_id")
	private Cliente cliente;

	@NotNull
	private Long rollor;

	@NotEmpty
	private String kilor;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yy-MM-yyyy")
	private Date fechar;

	@Transient
	private String fechainicio;
	
	@Transient
	private String fechafin;

	/*
	@Transient
	private String cempleado;
	@Transient
	private String cfactura;
	@Transient
	private String cdibujo;
	@Transient
	private String cmaquina;
	
	


	public String getCempleado() {
		return cempleado;
	}

	public void setCempleado(String cempleado) {
		this.cempleado = cempleado;
	}

	public String getCfactura() {
		return cfactura;
	}

	public void setCfactura(String cfactura) {
		this.cfactura = cfactura;
	}

	public String getCdibujo() {
		return cdibujo;
	}

	public void setCdibujo(String cdibujo) {
		this.cdibujo = cdibujo;
	}

	public String getCmaquina() {
		return cmaquina;
	}

	public void setCmaquina(String cmaquina) {
		this.cmaquina = cmaquina;
	}
*/
	public String getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}

	public String getFechafin() {
		return fechafin;
	}

	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}

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

	public Long getRollor() {
		return rollor;
	}

	public void setRollor(Long rollor) {
		this.rollor = rollor;
	}

	public String getKilor() {
		return kilor;
	}

	public void setKilor(String kilor) {
		this.kilor = kilor;
	}

	public Date getFechar() {
		return fechar;
	}

	public void setFechar(Date fechar) {
		this.fechar = fechar;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
