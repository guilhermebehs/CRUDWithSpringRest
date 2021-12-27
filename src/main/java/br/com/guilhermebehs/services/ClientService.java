package br.com.guilhermebehs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.guilhermebehs.converters.DozerConverter;
import br.com.guilhermebehs.data.models.ClientModel;
import br.com.guilhermebehs.data.vos.ClientVO;
import br.com.guilhermebehs.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
    ClientRepository clientRepository;
	
	public List<ClientVO> getAll(){
		return DozerConverter.parseListObjects(clientRepository.findAll(), ClientVO.class);
	}
	
    public ClientVO getById(Long id){
    	var model = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client with id "+id+" not found"));
		return DozerConverter.parseObject(model, ClientVO.class);
	}
	
	public ClientVO create(ClientVO newClient){
		var model = DozerConverter.parseObject(newClient, ClientModel.class);
		return DozerConverter.parseObject(clientRepository.save(model), ClientVO.class);
	}
	
	public void update(Long id,ClientVO client){
		var clientToUpdate =  clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client with id "+id+" not found"));
		clientToUpdate.setName(client.getName());
		clientToUpdate.setLastName(client.getLastName());
		clientToUpdate.setBirth(client.getBirth());
		clientToUpdate.setAddress(client.getAddress());
		clientRepository.save(clientToUpdate);
	}
	public void delete(Long id){
		var clientToDelete = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client with id "+id+" not found"));
		clientRepository.delete(clientToDelete);
	}

}
