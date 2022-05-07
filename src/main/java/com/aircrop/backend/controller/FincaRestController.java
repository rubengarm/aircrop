package com.aircrop.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aircrop.backend.model.Finca;
import com.aircrop.backend.response.FincaResponseRest;
import com.aircrop.backend.service.IFincaService;

@RestController
@RequestMapping("/aircrop")
public class FincaRestController {


	@Autowired
	IFincaService service;
	
	@GetMapping("/fincas")
	public ResponseEntity<FincaResponseRest> consultaFincas(){
		ResponseEntity<FincaResponseRest> response = service.buscarFincas();
		return response;
	}
	
	@GetMapping("/fincas/{id}")
	public ResponseEntity<FincaResponseRest> buscarPorId(@PathVariable long id){
		ResponseEntity<FincaResponseRest> response = service.buscarPorId(id);
		return response;
		}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/fincas")
	public ResponseEntity<FincaResponseRest> crear(@RequestBody Finca request){
		ResponseEntity<FincaResponseRest> response = service.crear(request);
		return response;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/fincas/{id}")
	public ResponseEntity<FincaResponseRest> actualizar(@RequestBody Finca request, @PathVariable long id){
		ResponseEntity<FincaResponseRest> response = service.actualizar(request,id);
		return response;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/fincas/{id}")
	public ResponseEntity<FincaResponseRest> eliminar(@PathVariable long id){
		ResponseEntity<FincaResponseRest> response = service.eliminar(id);
		return response;
	}
}
