package com.aircrop.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="SECTOR")
public class Sector  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7390754150712472L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_CLASE")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Clase clase;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FINCA")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Finca finca;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	public Finca getFinca() {
		return finca;
	}

	public void setFinca(Finca finca) {
		this.finca = finca;
	}
	
	
}
