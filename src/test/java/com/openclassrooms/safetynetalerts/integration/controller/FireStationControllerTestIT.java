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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetalerts.model.FireStation;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationControllerTestIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetFireStationByAddress() throws Exception {
		mockMvc.perform(get("/firestation/address/644 Gershwin Cir")).andExpect(status().isOk())
				.andExpect(jsonPath("station", is("1")));
	}

	@Test
	public void testGetFireStationByFireStation() throws Exception {
		mockMvc.perform(get("/firestation/station/4")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].address", is("489 Manchester St")));
	}

	@Test
	public void testGetFireStationsContent() throws Exception {
		mockMvc.perform(get("/firestations")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].address", is("644 Gershwin Cir")));
	}

	@Test
	public void testCreateFireStationCreated() throws JsonProcessingException, Exception {

		FireStation mappingToCreate = new FireStation();
		mappingToCreate.setAddress("Rue du 14 juillet");
		mappingToCreate.setStation("3");

		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mappingToCreate))).andExpect(status().isCreated());
	}

	@Test
	public void testCreateFireStationNoContent() throws JsonProcessingException, Exception {

		FireStation mappingToCreate = new FireStation();

		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mappingToCreate))).andExpect(status().isNoContent());
	}

	@Test
	public void testUpdateFireStation() throws JsonProcessingException, Exception {

		FireStation mappingToUpdate = new FireStation();
		mappingToUpdate.setStation("3");

		mockMvc.perform(put("/firestation/892 Downing Ct").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mappingToUpdate))).andExpect(status().isOk());
	}

	@Test
	public void testDeleteFireStationByAddress() throws Exception {
		mockMvc.perform(delete("/firestation/address/29 15th St")).andExpect(status().isOk());

	}

	@Test
	public void testDeleteFireStationByFireStation() throws Exception {
		mockMvc.perform(delete("/firestation/station/3")).andExpect(status().isOk());

	}
}
