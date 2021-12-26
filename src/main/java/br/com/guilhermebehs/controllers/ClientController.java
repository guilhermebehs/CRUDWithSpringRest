package br.com.guilhermebehs.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.guilhermebehs.models.ClientModel;
import br.com.guilhermebehs.services.ClientService;

@RestController
@RequestMapping(path = "client")
public class ClientController {
	
	@Autowired
	ClientService clientService; 

	@GetMapping
    public List<ClientModel> getAll(){
		return clientService.getAll();
	}
	
	@GetMapping("/{id}")
    public ClientModel getById(@PathVariable("id") Long id){
		
		return clientService.getById(id);
	}
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
    public ClientModel create(@RequestBody() ClientModel newClient){
		return clientService.create(newClient);
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id,@RequestBody() ClientModel client){
		clientService.update(id, client);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
		clientService.delete(id);
	}
	
	
}
