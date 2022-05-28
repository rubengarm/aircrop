package com.aircrop.backend.service;

import org.springframework.http.ResponseEntity;

import com.aircrop.backend.model.Sector;
import com.aircrop.backend.response.SectorResponseRest;

public interface ISectorService {
	
	public ResponseEntity<SectorResponseRest> buscarSectores();
	
	public ResponseEntity<SectorResponseRest> buscarPorId(long id);
	
	public ResponseEntity<SectorResponseRest> crear (Sector sector);
	
	public ResponseEntity<SectorResponseRest> actualizar (Sector sector, long id);
	
	public ResponseEntity<SectorResponseRest> eliminar (long id);

}
