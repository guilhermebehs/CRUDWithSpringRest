package br.com.guilhermebehs.converters.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.guilhermebehs.data.models.ClientModel;
import br.com.guilhermebehs.data.vos.ClientVO;

public class MockClient {
	
	 public ClientModel mockEntity() {
	    	return mockEntity(0);
	    }
	    
	    public ClientVO mockVO() {
	    	return mockVO(0);
	    }
	    
	    public List<ClientModel> mockEntityList() {
	        List<ClientModel> clients = new ArrayList<ClientModel>();
	        for (int i = 0; i < 14; i++) {
	        	clients.add(mockEntity(i));
	        }
	        return clients;
	    }

	    public List<ClientVO> mockVOList() {
	        List<ClientVO> clients = new ArrayList<>();
	        for (int i = 0; i < 14; i++) {
	        	clients.add(mockVO(i));
	        }
	        return clients;
	    }
	    
	    private ClientModel mockEntity(Integer number) {
	    	ClientModel client = new ClientModel();
	    	client.setAddress("Addres Test" + number);
	    	client.setName("First Name Test" + number);
	    	client.setBirth("1993-03-21");
	    	client.setId(number.longValue());
	    	client.setLastName("Last Name Test" + number);
	        return client;
	    }

	    private ClientVO mockVO(Integer number) {
	    	ClientVO client = new ClientVO();
	    	client.setAddress("Addres Test" + number);
	    	client.setName("First Name Test" + number);
	    	client.setBirth("1993-03-21");
	    	client.setId(number.longValue());
	    	client.setLastName("Last Name Test" + number);
	        return client;
	    }

}
