package com.aircrop.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aircrop.backend.model.Sector;
import com.aircrop.backend.model.Sector;
import com.aircrop.backend.model.Sector;
import com.aircrop.backend.model.Sector;
import com.aircrop.backend.model.dao.ISectorDao;
import com.aircrop.backend.response.SectorResponseRest;
import com.aircrop.backend.response.SectorResponseRest;
import com.aircrop.backend.response.SectorResponseRest;
import com.aircrop.backend.response.SectorResponseRest;
import com.aircrop.backend.response.SectorResponseRest;

@Service
public class SectorServiceImpl implements ISectorService{
	
	private static final Logger log = LoggerFactory.getLogger(SectorServiceImpl.class);
	
	@Autowired
	private ISectorDao sectorDao;
	
	
	@Override
	public ResponseEntity<SectorResponseRest> buscarSectores() {
		log.info("Inicio método buscar sector");
		SectorResponseRest response = new SectorResponseRest();
		
		try {
			List<Sector> sector = (List<Sector>) sectorDao.findAll();
			response.getSectorResponse().setSector(sector);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		}catch(Exception e) {
			response.setMetadata("Respuesta noK", "-1", "Error al consultar los sectores");
			log.error("Error al consultar los sectores",e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<SectorResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SectorResponseRest>(response,HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<SectorResponseRest> buscarPorId(long id) {
		log.info("Inicio del método buscarPorId");
		
		SectorResponseRest response = new SectorResponseRest();
		List<Sector> list = new ArrayList<>();
		
		try {
			Optional<Sector> sector = sectorDao.findById(id);
			
			if(sector.isPresent()) {
				list.add(sector.get());
				response.setMetadata("Respuesta ok", "00", "Sector encontrada");
				response.getSectorResponse().setSector(list);
			}else {
				log.error("Error al consultar los sectores");
				response.setMetadata("Respuesta nok", "-1", "Sector no encontrada");
				return new ResponseEntity<SectorResponseRest>(response,HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			
			log.error("Error al consultar los sectores");
			response.setMetadata("Respuesta nok", "-1", "Error al consultar los sectores");
			return new ResponseEntity<SectorResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<SectorResponseRest>(response,HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<SectorResponseRest> crear(Sector sector) {
		log.info("Inicio del método crear sector");
		
		SectorResponseRest response = new SectorResponseRest();
		List<Sector> list = new ArrayList<>();
		
		try {
			
			Sector sectorGuardado = sectorDao.save(sector);
			
			if (sectorGuardado != null) {
				list.add(sectorGuardado);
				response.getSectorResponse().setSector(list);
				response.setMetadata("Respuesta ok", "00", "Respuesta guardada correctamente");
			}else {
				log.error("Error al grabar el sector");
				response.setMetadata("Respuesta nok", "-1", "Error al grabar el sector");
				return new ResponseEntity<SectorResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			log.error("Error al consultar el sector");
			response.setMetadata("Respuesta nok", "-1", "Error al grabar el sector");
			return new ResponseEntity<SectorResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<SectorResponseRest>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SectorResponseRest> actualizar(Sector sector, long id) {
log.info("Inicio del método actualizar sector");
		
		SectorResponseRest response = new SectorResponseRest();
		List<Sector> list = new ArrayList<>();
		
		try {
			
			Optional<Sector> sectorBuscado = sectorDao.findById(id);
			
			if(sectorBuscado.isPresent()) {
				sectorBuscado.get().setNombre(sector.getNombre());
				sectorBuscado.get().setFinca(sector.getFinca());
				sectorBuscado.get().setClase(sector.getClase());
				
				Sector sectorActualizar = sectorDao.save(sectorBuscado.get());
				
				if (sectorActualizar != null) {
					list.add(sectorActualizar);
					response.getSectorResponse().setSector(list);
					response.setMetadata("Respuesta ok", "00", "Sector actualizado correctamente");
				}else {
					log.error("Error al actualizar el sector");
					response.setMetadata("Respuesta nok", "-1", "Sector no actualizado");
					return new ResponseEntity<SectorResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			}else {
				log.error("Error al actualizar el sector");
				response.setMetadata("Respuesta nok", "-1", "Sector no encontrado");
				return new ResponseEntity<SectorResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			log.error("Error al actualizar la sector", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Sector no actualizado");
			return new ResponseEntity<SectorResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SectorResponseRest>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SectorResponseRest> eliminar(long id) {
		log.info("Inicio del método eliminar");
		
		SectorResponseRest response = new SectorResponseRest();
		
		try {
			
			//Eliminamos el registro
			sectorDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Sector eliminada correctamente");
		}catch(Exception e) {
			log.error("Error al eliminar la sector", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Sector no eliminada");
			return new ResponseEntity<SectorResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<SectorResponseRest>(response, HttpStatus.OK);
	}
	

}
