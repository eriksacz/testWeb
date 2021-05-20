package com.test.appweb.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "dibu_fac")
public class DibuFac implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private EntradaFactura entradaFactura;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Dibujo dibujo;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fecha;

	@NotNull
	private Long activo;

	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}

	@OneToMany(mappedBy = "dibuFac", fetch = FetchType.LAZY)
	private List<FacDibuMaqui> FacDibuMaquis;

	public List<FacDibuMaqui> getFacDibuMaquis() {
		return FacDibuMaquis;
	}

	public void setFacDibuMaquis(List<FacDibuMaqui> facDibuMaquis) {
		FacDibuMaquis = facDibuMaquis;
	}

	public Long getActivo() {
		return activo;
	}

	public void setActivo(Long activo) {
		this.activo = activo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntradaFactura getEntradaFactura() {
		return entradaFactura;
	}

	public void setEntradaFactura(EntradaFactura entradaFactura) {
		this.entradaFactura = entradaFactura;
	}

	public Dibujo getDibujo() {
		return dibujo;
	}

	public void setDibujo(Dibujo dibujo) {
		this.dibujo = dibujo;
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
