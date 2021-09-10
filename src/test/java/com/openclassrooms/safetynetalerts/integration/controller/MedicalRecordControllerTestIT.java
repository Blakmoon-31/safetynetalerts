package com.openclassrooms.safetynetalerts.integration.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetalerts.model.MedicalRecord;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTestIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetMedicalRecord() throws Exception {
		mockMvc.perform(get("/medicalrecord/Allison/Boyd")).andExpect(status().isOk())
				.andExpect(jsonPath("birthdate", is("03/15/1965")));
	}

	@Test
	public void testGetMedicalRecordsContent() throws Exception {
		mockMvc.perform(get("/medicalrecords")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0]birthdate", is("03/06/1984")));
	}

	@Test
	public void testCreateMedicalRecord() throws JsonProcessingException, Exception {
		MedicalRecord medicalRecordToCreate = new MedicalRecord();
		medicalRecordToCreate.setFirstName("Manu");
		medicalRecordToCreate.setLastName("Dupont");

		LocalDate birth = LocalDate.of(2012, 12, 25);
		medicalRecordToCreate.setBirthdate(birth);
		medicalRecordToCreate.setMedications(null);
		medicalRecordToCreate.setAllergies(null);

		mockMvc.perform(post("/medicalrecord").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(medicalRecordToCreate))).andExpect(status().isCreated());
	}

	@Test
	public void testUpdateMedicalRecord() throws JsonProcessingException, Exception {
		MedicalRecord medicalRecordToUpdate = new MedicalRecord();
		LocalDate birth = LocalDate.of(2012, 12, 25);
		medicalRecordToUpdate.setBirthdate(birth);
		medicalRecordToUpdate.setMedications(null);
		medicalRecordToUpdate.setAllergies(null);

		mockMvc.perform(put("/medicalrecord/Reginold/Walker").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(medicalRecordToUpdate))).andExpect(status().isOk());
	}

	@Test
	public void testDeleteMedicalRecord() throws Exception {
		mockMvc.perform(delete("/medicalrecord/Sophia/Zemicks")).andExpect(status().isOk());
	}
}
