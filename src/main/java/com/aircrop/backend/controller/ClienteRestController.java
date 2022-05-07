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

import com.aircrop.backend.model.Cliente;
import com.aircrop.backend.response.ClienteResponseRest;
import com.aircrop.backend.service.IClienteService;

@RestController
@RequestMapping("/aircrop")
public class ClienteRestController {
	
	@Autowired
	IClienteService service;
	
	@GetMapping("/clientes")
	public ResponseEntity<ClienteResponseRest> consultaClientes(){
		ResponseEntity<ClienteResponseRest> response = service.buscarClientes();
		return response;
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<ClienteResponseRest> buscarPorId(@PathVariable long id){
		ResponseEntity<ClienteResponseRest> response = service.buscarPorId(id);
		return response;
		}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/clientes")
	public ResponseEntity<ClienteResponseRest> crear(@RequestBody Cliente request){
		ResponseEntity<ClienteResponseRest> response = service.crear(request);
		return response;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/clientes/{id}")
	public ResponseEntity<ClienteResponseRest> actualizar(@RequestBody Cliente request, @PathVariable long id){
		ResponseEntity<ClienteResponseRest> response = service.actualizar(request,id);
		return response;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<ClienteResponseRest> eliminar(@PathVariable long id){
		ResponseEntity<ClienteResponseRest> response = service.eliminar(id);
		return response;
	}
	
	}
	


