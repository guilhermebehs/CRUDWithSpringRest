package br.com.guilhermebehs.controllers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import br.com.guilhermebehs.Startup;
import br.com.guilhermebehs.data.vos.ClientVO;
import br.com.guilhermebehs.services.ClientService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Startup.class)
@WebMvcTest(controllers = ClientController.class)
public class ClientControllerTest {
	
	 @Autowired
	  private MockMvc mockMvc;
	
	 @MockBean
	  private ClientService clientService;
	 
	  private List<ClientVO> expectedClients;
	 
	 @Before
	 public void setup() {
		 ClientVO client = new ClientVO();
			client.setKey(1L);
			client.setName("Name Test");
			client.setLastName("Last Name Test");
			client.setBirth("Birth Test");
			client.setAddress("Address Test");
			expectedClients = new ArrayList<ClientVO>();
			expectedClients.add(client);
			
			when(clientService.getAll()).thenReturn(expectedClients);
			when(clientService.getById(1L)).thenReturn(expectedClients.get(0));
	 }
	
		
	@Test
	  public void getAll200() throws Exception {
		
		mockMvc.perform(get("/client")).andExpect(status().isOk());
	  }
	
	@Test
	  public void getById200() throws Exception {
		
		mockMvc.perform(get("/client/1")).andExpect(status().isOk());
	  }
	
	@Test
	  public void getById404() throws Exception {
		when(clientService.getById(1L)).thenThrow(new ResourceNotFoundException("Client with id 1 not found"));
		mockMvc.perform(get("/client/1")).andExpect(status().isNotFound());
	  }

}
