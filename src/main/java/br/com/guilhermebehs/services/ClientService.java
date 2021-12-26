package br.com.guilhermebehs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.guilhermebehs.models.ClientModel;
import br.com.guilhermebehs.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
    ClientRepository clientRepository;
	
	public List<ClientModel> getAll(){
		return clientRepository.findAll();
	}
	
    public ClientModel getById(Long id){
    	
      return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client with id "+id+" not found"));
	}
	
	public ClientModel create(ClientModel newClient){
		clientRepository.save(newClient);
		return newClient;
	}
	
	public void update(Long id,ClientModel client){
		ClientModel clientToUpdate =  clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client with id "+id+" not found"));
		clientToUpdate.setName(client.getName());
		clientToUpdate.setLastName(client.getLastName());
		clientToUpdate.setBirth(client.getBirth());
		clientToUpdate.setAddress(client.getAddress());
		clientRepository.save(clientToUpdate);
	}
	public void delete(Long id){
		ClientModel clientToDelete = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client with id "+id+" not found"));
		clientRepository.delete(clientToDelete);
	}

}
