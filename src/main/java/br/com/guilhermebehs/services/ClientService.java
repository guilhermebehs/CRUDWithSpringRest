package br.com.guilhermebehs.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.guilhermebehs.models.ClientModel;

@Service
public class ClientService {

	public List<ClientModel> getAll(){
		List<ClientModel> clients = new ArrayList<ClientModel>();
		ClientModel client = new ClientModel(1L, "Guilherme","Behs", "1993-03-21", "Av President"); 
		clients.add(client);
		return clients;
	}
	
    public ClientModel getById(String id){
		List<ClientModel> clients = new ArrayList<ClientModel>();
		ClientModel client = new ClientModel(1L, "Guilherme","Behs", "1993-03-21", "Av President"); 
		clients.add(client);
		return clients.get(0);
	}
	
	public ClientModel create(ClientModel newClient){
		return newClient;
	}
	
	public void update(String id,ClientModel client){
		return;
	}
	public void delete(String id){
		return;
	}

}
