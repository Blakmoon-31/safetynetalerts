package com.openclassrooms.safetynetalerts.unit.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.safetynetalerts.controller.DataSafetyNetController;
import com.openclassrooms.safetynetalerts.service.DataSafetyNetService;
import com.openclassrooms.safetynetalerts.service.MapDtoService;

@WebMvcTest(controllers = DataSafetyNetController.class)
public class DataSafetyNetControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DataSafetyNetService dataSafetyNetService;

	@MockBean
	private MapDtoService mapDtoService;

	@Test
	public void testGetPersonsForAStation() throws Exception {
		mockMvc.perform(get("/firestation").param("stationNumber", "1")).andExpect(status().isOk());

	}

	@Test
	public void testGetPhoneAlertListForAFireStation() throws Exception {
		mockMvc.perform(get("/phoneAlert").param("firestation", "2")).andExpect(status().isOk());
	}

	@Test
	public void testGetCommunityEmailListForACity() throws Exception {
		mockMvc.perform(get("/communityEmail").param("city", "Culver")).andExpect(status().isOk());
	}

	@Test
	public void testGetFireListForAnAddress() throws Exception {
		mockMvc.perform(get("/fire").param("address", "908 73rd St")).andExpect(status().isOk());
	}

	@Test
	public void testGetFloodListForAListOfStations() throws Exception {
		mockMvc.perform(get("/flood/stations").param("stations", "1,2")).andExpect(status().isOk());
	}

	@Test
	public void testGetPersonInfoByFirstNameAndLastNameWithFullName() throws Exception {
		mockMvc.perform(get("/personInfo").param("lastName", "Allison").param("lastName", "Boyd"))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetPersonInfoByFirstNameAndLastNameWithNoName() throws Exception {
		mockMvc.perform(get("/personInfo")).andExpect(status().isOk());
	}

	@Test
	public void testGetChildAlertListForAnAddress() throws Exception {
		mockMvc.perform(get("/childAlert").param("address", "908 73rd St")).andExpect(status().isOk());
	}

}
