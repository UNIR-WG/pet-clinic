package net.unir.missi.petclinic.vet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import net.unir.missi.petclinic.controller.VetController;
import net.unir.missi.petclinic.model.Specialty;
import net.unir.missi.petclinic.model.Vet;
import net.unir.missi.petclinic.repository.VetRepository;

/**
 * Test class for the {@link VetController}
 */

@WebMvcTest(VetController.class)
class VetControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VetRepository vets;

	private Vet james;

	private Vet helen;

	@BeforeEach
	void setup() {
		james = new Vet();
		james.setFirstName("James");
		james.setLastName("Carter");
		james.setId(1);
		helen = new Vet();
		helen.setFirstName("Helen");
		helen.setLastName("Leary");
		helen.setId(2);
		Specialty radiology = new Specialty();
		radiology.setId(1);
		radiology.setName("radiology");
		helen.addSpecialty(radiology);
		given(this.vets.findAll()).willReturn(Lists.newArrayList(james, helen));
		given(this.vets.findAll(any(Pageable.class))).willReturn(new PageImpl<Vet>(Lists.newArrayList(james, helen)));

	}

	@Test
	void testShowVetListHtml() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/vets.html?page=1")).andExpect(status().isOk())
				.andExpect(model().attributeExists("listVets")).andExpect(view().name("vets/vetList"));

	}

	@Test
	void testShowResourcesVetList() throws Exception {
		ResultActions actions = mockMvc.perform(get("/vets").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		actions.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.vetList[0].id").value(1));
	}

}
