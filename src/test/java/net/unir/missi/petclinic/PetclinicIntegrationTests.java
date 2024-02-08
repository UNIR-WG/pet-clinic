package net.unir.missi.petclinic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.unir.missi.petclinic.repository.VetRepository;

@SpringBootTest
class PetclinicIntegrationTests {

	@Autowired
	private VetRepository vets;

	@Test
	void testFindAll() throws Exception {
		vets.findAll();
		vets.findAll(); // served from cache
	}

}
