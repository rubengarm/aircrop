package com.aircrop.backend.service;

import org.springframework.http.ResponseEntity;

import com.aircrop.backend.model.Finca;
import com.aircrop.backend.response.FincaResponseRest;


public interface IFincaService {

	public ResponseEntity<FincaResponseRest> buscarFincas();
	
	public ResponseEntity<FincaResponseRest> buscarPorId(long id);
    
    public ResponseEntity<FincaResponseRest> crear (Finca cultivo);
    
    public ResponseEntity<FincaResponseRest> actualizar (Finca cultivo, long id);
    
    public ResponseEntity<FincaResponseRest> eliminar(long id); 
}
