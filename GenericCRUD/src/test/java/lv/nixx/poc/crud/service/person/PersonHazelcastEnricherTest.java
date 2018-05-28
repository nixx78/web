package lv.nixx.poc.crud.service.person;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import lv.nixx.poc.crud.model.hazelcast.PersonHazelcastModel;
import lv.nixx.poc.crud.model.internal.PersonInternalModel;
import lv.nixx.poc.crud.service.HazelcastService;

public class PersonHazelcastEnricherTest {
	

	@Test
	public void enrichTest() {
		
		PersonHazelcastEnricher enricher = new PersonHazelcastEnricher();
		HazelcastService hazelcastService = mock(HazelcastService.class);
		
		enricher.setHazelcastService(hazelcastService);
		
		
		PersonHazelcastModel hm1 = new PersonHazelcastModel();
		hm1.setEntityId("id1");
		hm1.setOverride1("Override1");
		hm1.setOverride2("Override2");
		
		PersonHazelcastModel hm2 = new PersonHazelcastModel();
		hm2.setEntityId("hm1");
		hm2.setOverride1("HmOverride1");
		hm2.setOverride2("HmOverride2");

		when(hazelcastService.getAllPersonModels()).thenReturn(Arrays.asList(
				hm1, 
				hm2)
				);
		
		PersonInternalModel pim1 = new PersonInternalModel("id1");
		pim1.setName("Name1");
		pim1.setSurname("Surname1");
		
		PersonInternalModel pim2 = new PersonInternalModel("id2");
		pim2.setName("Name2");
		pim2.setSurname("Surname2");
		
		Collection<PersonInternalModel> dbEntities = Arrays.asList(
				pim1,
				pim2
				);
		
		Collection<PersonInternalModel> enrichedValues = enricher.enrich(dbEntities);
		
		assertEquals(3, enrichedValues.size());
		
		enrichedValues.forEach(System.out::println);
		
		
		
	}

}
