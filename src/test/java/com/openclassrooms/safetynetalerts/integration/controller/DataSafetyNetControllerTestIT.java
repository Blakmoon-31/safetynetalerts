package com.openclassrooms.safetynetalerts.integration.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class DataSafetyNetControllerTestIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetPersonsForAStationContent() throws Exception {
		mockMvc.perform(get("/firestation").param("stationNumber", "1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.persons[0].lastName", is("Duncan")));

	}

	@Test
	public void testGetPersonInfoByFirstNameAndLastNameWithFirstName() throws Exception {
		mockMvc.perform(get("/personInfo").param("firstName", "Jonanathan")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].lastName", is("Marrack")));
	}

	@Test
	public void testGetPersonInfoByFirstNameAndLastNameWithLastName() throws Exception {
		mockMvc.perform(get("/personInfo").param("lastName", "Marrack")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName", is("Jonanathan")));
	}

	@Test
	public void testGetPhoneAlertListForAFireStationContent() throws Exception {
		mockMvc.perform(get("/phoneAlert").param("firestation", "2")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0]", is("841-874-6513")));
	}

	@Test
	public void testGetCommunityEmailListForACityContent() throws Exception {
		mockMvc.perform(get("/communityEmail").param("city", "Culver")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0]", is("jaboyd@email.com")));
	}

	@Test
	public void testGetFireListForAnAddressContent() throws Exception {
		mockMvc.perform(get("/fire").param("address", "908 73rd St")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].station", is("1")));
	}

	@Test
	public void testGetFloodListForAListOfStationsContent() throws Exception {
		mockMvc.perform(get("/flood/stations").param("stations", "1,2")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName", is("Jonanathan")));
//				.andExpect(jsonPath("$[0].address", is("947 E. Rose Dr")));
	}

	@Test
	public void testGetChildAlertListForAnAddressContent() throws Exception {
		mockMvc.perform(get("/childAlert").param("address", "947 E. Rose Dr")).andExpect(status().isOk())
				.andExpect(jsonPath("$.children[0].firstName", is("Kendrik")));
	}

	@Test
	public void testGetPersonInfoByFirstNameAndLastNameWithFullName() throws Exception {
		mockMvc.perform(get("/personInfo").param("firstName", "Allison").param("lastName", "Boyd"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.[0].address", is("112 Steppes Pl")));
	}

}
