package lv.nixx.poc.rest;

import lv.nixx.poc.rest.controller.PersonController;
import lv.nixx.poc.rest.domain.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
public class PersonControllerWithMockMvcTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PersonController personController;

    @Autowired
    @Spy
    PersonDAO personDAO;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        Person p = new Person("person.name", "person.surname", new Date());
        p.setId(2);

        doReturn(p).when(personDAO).getById(eq(2));

        personController.setPersonDAO(personDAO);
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
        this.mockMvc.perform(get("/rest/person/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

    }

    @Test
    public void getMethodTest() throws Exception {

        this.mockMvc.perform(get("/rest/person/{id}", 2)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("person.name"))
                .andExpect(jsonPath("$.surname").value("person.surname"));
    }

}
