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

import com.aircrop.backend.model.Clase;
import com.aircrop.backend.model.dao.IClaseDao;
import com.aircrop.backend.response.ClaseResponseRest;
import com.aircrop.backend.response.ClienteResponseRest;

@Service
public class ClaseServiceImpl implements IClaseService{
	
	private static final Logger log = LoggerFactory.getLogger(ClaseServiceImpl.class);
	
	@Autowired
	private IClaseDao claseDao;

	@Override
	public ResponseEntity<ClaseResponseRest> buscarClase() {
		log.info("Inicio método buscar clase");
		ClaseResponseRest response = new ClaseResponseRest();
		
		try {
			List<Clase> clase = (List<Clase>) claseDao.findAll();
			response.getClaseResponse().setClase(clase);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		}catch(Exception e) {
			response.setMetadata("Respuesta noK", "-1", "Error al consultar las clases");
			log.error("Error al consultar las clases",e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<ClaseResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ClaseResponseRest>(response,HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<ClaseResponseRest> buscarPorId(Long id) {
		
		log.info("Inicio del método buscarPorId");
		
		ClaseResponseRest response = new ClaseResponseRest();
		List<Clase> list = new ArrayList<>();
		
		try {
			Optional<Clase> clase = claseDao.findById(id);
			
			if(clase.isPresent()) {
				list.add(clase.get());
				response.setMetadata("Respuesta ok", "00", "Clase encontrada");
				response.getClaseResponse().setClase(list);
			}else {
				log.error("Error al consultar las clases");
				response.setMetadata("Respuesta nok", "-1", "Clase no encontrada");
				return new ResponseEntity<ClaseResponseRest>(response,HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			
			log.error("Error al consultar las clases");
			response.setMetadata("Respuesta nok", "-1", "Error al consultar las clases");
			return new ResponseEntity<ClaseResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ClaseResponseRest>(response,HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ClaseResponseRest> crear(Clase clase) {

		log.info("Inicio del método crear clase");
		
		ClaseResponseRest response = new ClaseResponseRest();
		List<Clase> list = new ArrayList<>();
		
		try {
			
			Clase claseGuardada = claseDao.save(clase);
			
			if (claseGuardada != null) {
				list.add(claseGuardada);
				response.getClaseResponse().setClase(list);
				response.setMetadata("Respuesta ok", "00", "Respuesta guardada correctamente");
			}else {
				log.error("Error al grabar la clase");
				response.setMetadata("Respuesta nok", "-1", "Error al grabar la clase");
				return new ResponseEntity<ClaseResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			log.error("Error al consultar la clase");
			response.setMetadata("Respuesta nok", "-1", "Error al grabar la clase");
			return new ResponseEntity<ClaseResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ClaseResponseRest>(response,HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ClaseResponseRest> actualizar(Clase clase, Long id) {
		log.info("Inicio del método actualizar clase");
		
		ClaseResponseRest response = new ClaseResponseRest();
		List<Clase> list = new ArrayList<>();
		
		try {
			
			Optional<Clase> claseBuscada = claseDao.findById(id);
			
			if(claseBuscada.isPresent()) {
				claseBuscada.get().setNombre(clase.getNombre());
				claseBuscada.get().setCultivo(clase.getCultivo());
				
				Clase claseActualizar = claseDao.save(claseBuscada.get());
				
				if (claseActualizar != null) {
					list.add(claseActualizar);
					response.getClaseResponse().setClase(list);
					response.setMetadata("Respuesta ok", "00", "Clase actualizada correctamente");
				}else {
					log.error("Error al actualizar la clase");
					response.setMetadata("Respuesta nok", "-1", "Clase no actualizada");
					return new ResponseEntity<ClaseResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			}else {
				log.error("Error al actualizar la clase");
				response.setMetadata("Respuesta nok", "-1", "Clase no encontrada");
				return new ResponseEntity<ClaseResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			log.error("Error al actualizar la clase", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Clase no actualizado");
			return new ResponseEntity<ClaseResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ClaseResponseRest>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ClaseResponseRest> eliminar(Long id) {
			log.info("Inicio del método eliminar");
		
		ClaseResponseRest response = new ClaseResponseRest();
		
		try {
			
			//Eliminamos el registro
			claseDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Clase eliminada correctamente");
		}catch(Exception e) {
			log.error("Error al eliminar la clase", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Clase no eliminada");
			return new ResponseEntity<ClaseResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ClaseResponseRest>(response, HttpStatus.OK);
	}

}
