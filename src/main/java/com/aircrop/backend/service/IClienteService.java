package com.aircrop.backend.service;



import org.springframework.http.ResponseEntity;

import com.aircrop.backend.model.Cliente;
import com.aircrop.backend.response.ClienteResponseRest;

public interface IClienteService {
	
	public ResponseEntity<ClienteResponseRest> buscarClientes();
	
	public ResponseEntity<ClienteResponseRest> buscarPorId(long id);
    
    public ResponseEntity<ClienteResponseRest> crear (Cliente cliente);
    
    public ResponseEntity<ClienteResponseRest> actualizar (Cliente cliente, long id);
    
    public ResponseEntity<ClienteResponseRest> eliminar(long id); 
}