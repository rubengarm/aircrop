package com.aircrop.backend.service;

import org.springframework.http.ResponseEntity;

import com.aircrop.backend.model.Clase;
import com.aircrop.backend.response.ClaseResponseRest;

public interface IClaseService {
	
	public ResponseEntity<ClaseResponseRest> buscarClase();
	
	public ResponseEntity<ClaseResponseRest> buscarPorId(Long id);
	
	public ResponseEntity<ClaseResponseRest> crear (Clase clase);
	
	public ResponseEntity<ClaseResponseRest> actualizar (Clase clase, Long id);
	
	public ResponseEntity<ClaseResponseRest> eliminar (Long id);

}
