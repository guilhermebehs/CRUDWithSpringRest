package br.com.guilhermebehs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import br.com.guilhermebehs.data.vos.ClientVO;
import br.com.guilhermebehs.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags= {"Client"})
@RestController
@RequestMapping(path = "client")
public class ClientController {
	
	@Autowired
	ClientService clientService; 

	@ApiOperation(value="Retrieve all clients")
	@GetMapping(produces = {"application/json", "application/xml"})
    public List<ClientVO> getAll(){
		List<ClientVO> clients = clientService.getAll();
		clients.forEach(client -> 
		                      client.add(linkTo(methodOn(ClientController.class)
		                    		       .getById(client.getKey()))
		                    		       .withSelfRel()));
		return clients;
	}
	
	@ApiOperation(value="Retrieve a client by id")
	@GetMapping(value="/{id}", produces = {"application/json", "application/xml"})
    public ClientVO getById(@PathVariable("id") Long id){
		ClientVO client = clientService.getById(id); 
		client.add(linkTo(methodOn(ClientController.class).getById(id)).withSelfRel());
		return client;
	}
	
	@ApiOperation(value="Create a client")
	@PostMapping(produces = {"application/json", "application/xml"})
	@ResponseStatus(code=HttpStatus.CREATED)
    public ClientVO create(@RequestBody() ClientVO newClient){
		ClientVO client = clientService.create(newClient);
		client.add(linkTo(methodOn(ClientController.class).getById(client.getKey())).withSelfRel());
		return client;
	}
	
	@ApiOperation(value="Update a client by id")
	@PatchMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id,@RequestBody() ClientVO client){
		clientService.update(id, client);
	}
	
	@ApiOperation(value="Delete a client by id")
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
		clientService.delete(id);
	}
	
	
}
