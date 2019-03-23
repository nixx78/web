package lv.nixx.poc.rest;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import lv.nixx.poc.rest.domain.Person;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerMockTest {

	private MockMvc mockMvc;
	
	@Mock
	PersonDAO personDAO;

	@InjectMocks
	PersonController controller;
	
	@Before
	public void setup() {
		this.mockMvc = standaloneSetup(controller).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
		Person p = new Person("person.name", "person.surname", new Date());
		p.setId(2);
		when(personDAO.getById(2)).thenReturn(p);
	}
	
	@Test
	public void addPersonUsingJSONString() throws Exception {
		 this.mockMvc.perform(
		            post("/rest/person")
		                    .content(PersonFixtures.createPersonJSON(1))
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .accept(MediaType.APPLICATION_JSON))
		            .andDo(print())
		            .andExpect(status().isCreated());
	}
	
	
	@Test
	public void getPersonAndPrintBody() throws Exception {
		 this.mockMvc.perform(get("/rest/person/{id}",1)
				.accept(MediaType.APPLICATION_JSON))
		 		.andDo(print());
		
	}
	
	@Test
	public void getMethodTest() throws Exception{
		
		 this.mockMvc.perform(get("/rest/person/{id}",2)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(jsonPath("$.id").value(2))
		       .andExpect(jsonPath("$.name").value("person.name"))
		       .andExpect(jsonPath("$.surname").value("person.surname"));
	}

}
