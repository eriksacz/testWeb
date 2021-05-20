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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "facdibu_maqui")
public class FacDibuMaqui implements Serializable{



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechai;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechaf;

	@NotNull
	private Long activo;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)	
	private Maquina maquina;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)	
	private DibuFac dibuFac;
	
	@PrePersist
	public void prePersist() {
		fechai = new Date();
		fechaf = new Date();
	}

	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Date getFechai() {
		return fechai;
	}



	public void setFechai(Date fechai) {
		this.fechai = fechai;
	}



	public Date getFechaf() {
		return fechaf;
	}



	public void setFechaf(Date fechaf) {
		this.fechaf = fechaf;
	}



	public Long getActivo() {
		return activo;
	}



	public void setActivo(Long activo) {
		this.activo = activo;
	}



	public Maquina getMaquina() {
		return maquina;
	}



	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}



	public DibuFac getDibuFac() {
		return dibuFac;
	}



	public void setDibuFac(DibuFac dibuFac) {
		this.dibuFac = dibuFac;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	private static final long serialVersionUID = 1L;
	
}
