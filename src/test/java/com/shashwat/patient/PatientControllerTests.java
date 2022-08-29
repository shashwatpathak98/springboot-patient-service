package com.shashwat.patient;

import com.shashwat.patient.controller.PatientController;
import com.shashwat.patient.entity.Patient;
import com.shashwat.patient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
public class PatientControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatientService service;

	@Test
	public void shouldReturnStudentById() throws Exception {
		Patient mockPatient = new Patient();
		mockPatient.setId(1l);
		mockPatient.setName("test");
		mockPatient.setPrescription("Daily");
		mockPatient.setVisitDate("12/12/2022");
		mockPatient.setVisitedDoctor("D1");

		when(service.getPatientById(1l)).thenReturn(mockPatient);
		this.mockMvc.perform(get("/patient/findPatientById/1")).andExpect(status().isOk());
	}

	@Test
	public void shouldReturnStudentByIdResponse() throws Exception {
		Patient mockPatient = new Patient();
		mockPatient.setId(1L);
		mockPatient.setName("test");
		mockPatient.setPrescription("Daily");
		mockPatient.setVisitDate("12/12/2022");
		mockPatient.setVisitedDoctor("D1");

		when(service.getPatientById(1L)).thenReturn(mockPatient);
		this.mockMvc.perform(get("/patient/findPatientById/1")).andExpect(status().isOk()).andExpect(content().json("{" +
				"    \"id\": 1," +
				"    \"name\": \"test\"," +
				"    \"prescription\": \"Daily\",\n" +
				"    \"visitDate\": \"12/12/2022\",\n" +
				"    \"visitedDoctor\": \"D1\"\n" +
				"}"));
	}

	@Test
	public void shouldThrowExceptionIfPatientNotFound() throws Exception {
		when(service.getPatientById(0l)).thenThrow(new NoSuchElementException());
		this.mockMvc.perform(get("/patient/findPatientById/")).andDo(print()).andExpect(status().isNotFound());
	}
}
