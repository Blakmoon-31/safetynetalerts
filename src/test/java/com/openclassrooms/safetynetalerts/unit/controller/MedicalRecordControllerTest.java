package com.openclassrooms.safetynetalerts.unit.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.safetynetalerts.controller.MedicalRecordController;

@WebMvcTest(controllers = MedicalRecordController.class)
public class MedicalRecordControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MedicalRecordController medicalRecordController;

	@Test
	public void testGetMedicalRecords() throws Exception {
		mockMvc.perform(get("/medicalrecords")).andExpect(status().isOk());
	}

}
