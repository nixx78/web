package lv.nixx.poc.crud.service;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lv.nixx.poc.crud.service.dao.PersonDao;

@Configuration
public class TestContextConfiguration {
	
	@Bean
	public PersonDao personDao() {
		return mock(PersonDao.class);
	}
	

}
