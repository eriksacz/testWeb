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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Pattern(regexp ="[a-zA-Z ]{2,254}")
	@NotEmpty
	private String cliente;

	@NotEmpty
	private String direccion;

	@Pattern(regexp ="[a-zA-Z ]{2,254}")
	@NotEmpty
	private String ciudad;

	@NotEmpty
	private String rfc;

	@Size(min = 10, max = 10)
	@NotEmpty
	private String telefono;

	@NotEmpty
	@Email
	private String correo;

	@Pattern(regexp ="[a-zA-Z ]{2,254}")
	@NotEmpty
	private String nom_contacto;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	private Date fecha;

	@NotNull
	private Long activo;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<ContentClient> ConcentClients;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<EntradaFactura> EntradaFactura;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<ClientLot> ClientLot;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<ClientDibu> ClientDibu;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<ClientComposi> ClientComposi;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<ClientProveedor> ClientProveedor;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<Produccion> Produccion;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<Salida> Salida;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<Segunda> Segunda;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<ProduConsulta> ProduConsulta;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<reporte> reporte;

	public Cliente() {
		ConcentClients = new ArrayList<ContentClient>();
		EntradaFactura = new ArrayList<EntradaFactura>();
		ClientLot = new ArrayList<ClientLot>();
		ClientDibu = new ArrayList<ClientDibu>();
		ClientComposi = new ArrayList<ClientComposi>();
		ClientProveedor = new ArrayList<ClientProveedor>();
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

	public List<ClientProveedor> getClientProveedor() {
		return ClientProveedor;
	}

	public void setClientProveedor(List<ClientProveedor> clientProveedor) {
		ClientProveedor = clientProveedor;
	}

	public List<ClientComposi> getClientComposi() {
		return ClientComposi;
	}

	public void setClientComposi(List<ClientComposi> clientComposi) {
		ClientComposi = clientComposi;
	}

	public List<ClientDibu> getClientDibu() {
		return ClientDibu;
	}

	public void setClientDibu(List<ClientDibu> clientDibu) {
		ClientDibu = clientDibu;
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

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNom_contacto() {
		return nom_contacto;
	}

	public void setNom_contacto(String nom_contacto) {
		this.nom_contacto = nom_contacto;
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
