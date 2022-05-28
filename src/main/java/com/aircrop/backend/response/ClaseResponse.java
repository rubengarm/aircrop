package com.aircrop.backend.response;

import java.util.List;

import com.aircrop.backend.model.Clase;

public class ClaseResponse {
	
	private List<Clase> clase;
	
	private List<Clase> getClase(){
		return clase;
	}
	
	public void setClase(List<Clase> clase) {
		this.clase=clase;
	}

}
