package br.com.guilhermebehs.controllers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Java6Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import br.com.guilhermebehs.Startup;
import br.com.guilhermebehs.controllers.ClientController;
import br.com.guilhermebehs.data.vos.ClientVO;
import br.com.guilhermebehs.services.ClientService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Startup.class)
@WebMvcTest(controllers = ClientController.class)
public class ClientControllerTest {
	
	 @Autowired
	  private MockMvc mockMvc;
	 
	 @Autowired
	  private ObjectMapper mapper;
	
	 @MockBean
	  private ClientService clientService;
	 
	  private List<ClientVO> expectedClients;
	 
	 @Before
	 public void setup() {
		 ClientVO client = new ClientVO();
			client.setId(1L);
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
		
		MvcResult requestResponse = mockMvc.perform(get("/client")).andExpect(status().isOk()).andReturn();
		String requestDataAsString = requestResponse.getResponse().getContentAsString();
		String expectedDataAsString = mapper.writeValueAsString(expectedClients);
		
		assertThat(requestDataAsString).isEqualToIgnoringWhitespace(expectedDataAsString);
		
	  }
	
	@Test
	  public void getById200() throws Exception {
		
		MvcResult requestResponse = mockMvc.perform(get("/client/1")).andExpect(status().isOk()).andReturn();
		String requestDataAsString = requestResponse.getResponse().getContentAsString();
		String expectedDataAsString = mapper.writeValueAsString(expectedClients.get(0));
		
		assertThat(requestDataAsString).isEqualToIgnoringWhitespace(expectedDataAsString);
		
	  }

}
