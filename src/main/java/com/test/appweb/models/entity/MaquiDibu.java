package com.test.appweb.models.entity;

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
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "maquinas_dibujos")
public class MaquiDibu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)	
	private Maquina maquina;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)	
	private Dibujo dibujo;


	@NotNull
	private Long activo;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	private Date fecha;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Dibujo getDibujo() {
		return dibujo;
	}

	public void setDibujo(Dibujo dibujo) {
		this.dibujo = dibujo;
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
	
	
	
	
}
