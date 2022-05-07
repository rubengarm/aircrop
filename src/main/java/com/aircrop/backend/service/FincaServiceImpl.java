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

import com.aircrop.backend.model.Finca;

import com.aircrop.backend.model.dao.IFincaDao;
import com.aircrop.backend.response.FincaResponseRest;

@Service
public class FincaServiceImpl implements IFincaService{
		
		private static Logger log = LoggerFactory.getLogger(FincaServiceImpl.class);
	
		@Autowired
		private IFincaDao fincaDao;
		
	@Override
	public ResponseEntity<FincaResponseRest> buscarFincas() {
		log.info("Inicio del metodo de buscar fincas");
		FincaResponseRest response = new FincaResponseRest();
		
		try {
			List<Finca> fincas = (List<Finca>) fincaDao.findAll();
			response.getFincaResponse().setFinca(fincas);
			
			response.setMetadata("Respuesta ok","00","Respuesta exitosa");
		}
		catch(Exception e) {
			response.setMetadata("Respuesta nok","-1","Error al consultar las fincas");
			log.error("Error al consultar fincas",e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<FincaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<FincaResponseRest>(response,HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<FincaResponseRest> buscarPorId(long id) {
		
			log.info("Inicio del método buscarPorId");

			FincaResponseRest response = new FincaResponseRest();
			List<Finca> list = new ArrayList<>();
			
			try {
				
				Optional<Finca> finca = fincaDao.findById(id);
				
				if(finca.isPresent()) {//si existe la categoría
					list.add(finca.get());
					response.setMetadata("Respuesta ok", "00", "Finca encontrado");
					response.getFincaResponse().setFinca(list);
				}else {//si no existe la categoría
					log.error("Error al consultar los fincas");
					response.setMetadata("Respuesta nok", "-1", "Finca no encontrado");
					return new ResponseEntity<FincaResponseRest>(response,HttpStatus.NOT_FOUND);
				}
				
			}catch(Exception e) {
				
				log.error("Error al consultar los fincas");
				response.setMetadata("Respuesta nok","-1","Error al consultar el finca");
				return new ResponseEntity<FincaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
			
			return new ResponseEntity<FincaResponseRest>(response,HttpStatus.OK);//respuesta exitosa
	}

	@Override
	@Transactional
	public ResponseEntity<FincaResponseRest> crear(Finca finca) {
		log.info("Inico del método crear categoría");
		
		FincaResponseRest response = new FincaResponseRest();
		List<Finca> list = new ArrayList<>();
		
		try {
			
			Finca fincaGuardada = fincaDao.save(finca);
			
			if (fincaGuardada != null) {
				list.add(fincaGuardada);
				response.getFincaResponse().setFinca(list);
				response.setMetadata("Respuesta OK", "00", "Respuesta guardada correctamente");
			}else {
				log.error("Error al grabar el finca");
				response.setMetadata("Respuesta noK", "-1", "Error al grabar el finca");
				return new ResponseEntity<FincaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			log.error("Error al consultar el finca");
			response.setMetadata("Respuesta noK", "-1", "Error al consultar el finca");
			return new ResponseEntity<FincaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<FincaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<FincaResponseRest> actualizar(Finca finca, long id) {
		log.info("Inicio del método actualizar finca");
		
		FincaResponseRest response = new FincaResponseRest();
		List<Finca> list = new ArrayList<>();
		
		try {
		
			Optional<Finca> fincaBuscado = fincaDao.findById(id);
			
			if(fincaBuscado.isPresent()) {
				
				fincaBuscado.get().setNombre(finca.getNombre());
				fincaBuscado.get().setDireccion(finca.getDireccion());
				fincaBuscado.get().setMunicipio(finca.getMunicipio());
				fincaBuscado.get().setProvincia(finca.getProvincia());
				fincaBuscado.get().setHectareas(finca.getHectareas());
				
				
				Finca fincaActualizar = fincaDao.save(fincaBuscado.get());//actualizando
				
				if (fincaActualizar != null) {
					list.add(fincaActualizar);
					response.getFincaResponse().setFinca(list);
					response.setMetadata("Respuesta Ok", "00", "Finca actualizado correctamente");
				}else {
					log.error("Error al actualizar el finca");
					response.setMetadata("Respuesta noK", "-1", "Finca no actualizado");
					return new ResponseEntity<FincaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			
			}else {
				log.error("Error al acutalizar el finca");
				response.setMetadata("Respuesta nok", "-1", "Finca no encontrado");
				return new ResponseEntity<FincaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch (Exception e) {
			log.error("Error al actualizar finca", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Finca no actualizado");
			return new ResponseEntity<FincaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<FincaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<FincaResponseRest> eliminar(long id) {
		log.info("Inicio del método eliminar");
		
		FincaResponseRest response = new FincaResponseRest();
		
		try {
			
			//Eliminamos el registro
			fincaDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Finca eliminado correctamente");
		}catch(Exception e) {
			log.error("Error al eliminar el clinte", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Finca no eliminado");
			return new ResponseEntity<FincaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<FincaResponseRest>(response, HttpStatus.OK);	
	}

}
