package br.com.guilhermebehs.controllers;

import java.util.ArrayList;
import java.util.List;

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

@RestController
@RequestMapping(path = "client")
public class ClientController {

	@GetMapping
    public List<ClientModel> getAll(){
		List<ClientModel> clients = new ArrayList<ClientModel>();
		ClientModel client = new ClientModel(1L, "Guilherme","Behs", "1993-03-21", "Av President"); 
		clients.add(client);
		return clients;
	}
	
	@GetMapping("/{id}")
    public ClientModel getById(@PathVariable("id") String id){
		List<ClientModel> clients = new ArrayList<ClientModel>();
		ClientModel client = new ClientModel(1L, "Guilherme","Behs", "1993-03-21", "Av President"); 
		clients.add(client);
		return clients.get(0);
	}
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
    public ClientModel create(@RequestBody() ClientModel newClient){
		return newClient;
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id,@RequestBody() ClientModel client){
		return;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id){
		return;
	}
	
	
}
