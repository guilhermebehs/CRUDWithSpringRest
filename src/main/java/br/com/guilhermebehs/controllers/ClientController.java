package br.com.guilhermebehs.controllers;

import java.util.HashMap;
import java.util.Map;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.guilhermebehs.data.vos.ClientVO;
import br.com.guilhermebehs.services.ClientService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name= "Client Endpoint")
@RestController
@RequestMapping(path = "client")
public class ClientController {
	
	@Autowired
	ClientService clientService; 

	@Operation(summary="Retrieve all clients")
	@GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<Map<String, Object>> getAll(
    		@RequestParam(value="page", defaultValue = "0") int page, 
    		@RequestParam(value="limit", defaultValue = "10") int limit,
    		@RequestParam(value="direction", defaultValue = "asc") String direction
    		){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC; 
		Pageable pageable = PageRequest.of(page, limit,  Sort.by(sortDirection, "name") );
		Page<ClientVO> clients = clientService.getAll(pageable);
		clients.forEach(client -> 
		                      client.add(linkTo(methodOn(ClientController.class)
		                    		       .getById(client.getKey()))
		                    		       .withSelfRel()));
		
		Map<String, Object> response = new HashMap<>();
		response.put("clients", clients.getContent());
		response.put("currentPage", clients.getNumber());
		response.put("totalItems", clients.getTotalElements());
		response.put("totalPages", clients.getTotalPages());
   
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary="Retrieve a client by")
	@GetMapping(path = "/findByName/{name}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Map<String, Object>> getByName(
    		@PathVariable(value="name") String name, 
    		@RequestParam(value="page", defaultValue = "0") int page, 
    		@RequestParam(value="limit", defaultValue = "10") int limit,
    		@RequestParam(value="direction", defaultValue = "asc") String direction
    		){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC; 
		Pageable pageable = PageRequest.of(page, limit,  Sort.by(sortDirection, "name") );
		Page<ClientVO> clients = clientService.getByName(name, pageable);
		clients.forEach(client -> 
		                      client.add(linkTo(methodOn(ClientController.class)
		                    		       .getById(client.getKey()))
		                    		       .withSelfRel()));
		
		Map<String, Object> response = new HashMap<>();
		response.put("clients", clients.getContent());
		response.put("currentPage", clients.getNumber());
		response.put("totalItems", clients.getTotalElements());
		response.put("totalPages", clients.getTotalPages());
   
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary="Retrieve a client by id")
	@GetMapping(value="/{id}", produces = {"application/json", "application/xml"})
    public ClientVO getById(@PathVariable("id") Long id){
		ClientVO client = clientService.getById(id); 
		client.add(linkTo(methodOn(ClientController.class).getById(id)).withSelfRel());
		return client;
	}

	@Operation(summary="Create a client")
	@PostMapping(produces = {"application/json", "application/xml"})
	@ResponseStatus(code=HttpStatus.CREATED)
    public ClientVO create(@RequestBody() ClientVO newClient){
		ClientVO client = clientService.create(newClient);
		client.add(linkTo(methodOn(ClientController.class).getById(client.getKey())).withSelfRel());
		return client;
	}

	@Operation(summary="Update a client by id")
	@PutMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id,@RequestBody() ClientVO client){
		clientService.update(id, client);
	}

	@Operation(summary="Update a client by id")
	@PatchMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id){
		clientService.disable(id);
	}

	@Operation(summary="Delete a client by id")
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
		clientService.delete(id);
	}
	
	
}
