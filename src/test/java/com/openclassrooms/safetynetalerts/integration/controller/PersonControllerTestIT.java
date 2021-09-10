package com.openclassrooms.safetynetalerts.integration.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetalerts.model.Person;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTestIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetPerson() throws Exception {
		mockMvc.perform(get("/person/Allison/Boyd")).andExpect(status().isOk())
				.andExpect(jsonPath("address", is("112 Steppes Pl")));
	}

	@Test
	public void testGetPersonsContent() throws Exception {
		mockMvc.perform(get("/persons")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].address", is("1509 Culver St")));
	}

	@Test
	public void testCreatePersonCreated() throws Exception {

		Person personToCreate = new Person();
		personToCreate.setFirstName("Manu");
		personToCreate.setLastName("Dupont");
		personToCreate.setAddress("Rue du 14 juillet");
		personToCreate.setCity("Toulouse");
		personToCreate.setZip("31000");
		personToCreate.setPhone("123456");
		personToCreate.setEmail("manu@mail.fr");

		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(personToCreate))).andExpect(status().isCreated());

	}

	@Test
	public void testCreatePersonNoContent() throws Exception {

		Person personToCreate = new Person();

		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(personToCreate))).andExpect(status().isNoContent());

	}

	@Test
	public void testUpdatePerson() throws Exception {

		Person personToUpdate = new Person();
		personToUpdate.setAddress("Rue de la gare");
		personToUpdate.setCity("paris");
		personToUpdate.setZip("75000");
		personToUpdate.setPhone("111-222-3333");
		personToUpdate.setEmail("toto@mail.fr");

		mockMvc.perform(put("/person/Roger/Boyd", personToUpdate).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(personToUpdate))).andExpect(status().isOk());

	}

	@Test
	public void testDeletePerson() throws Exception {
		mockMvc.perform(delete("/person/Clive/Ferguson")).andExpect(status().isOk());

	}

}
