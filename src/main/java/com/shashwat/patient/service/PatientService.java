package com.shashwat.patient.service;

import com.shashwat.patient.entity.Patient;
import com.shashwat.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepository repo;

    public Patient savePatient(Patient patient) {
       return repo.save(patient);
    }

    public List<Patient> getAllPatients() {
        return repo.findAll();
    }

    public Patient updatePatient(Patient patient) {
        Patient patientToUpdate = repo.findById(patient.getId()).orElse(null);
        if (patientToUpdate != null) {
            if (patient.getName() != null) patientToUpdate.setName(patient.getName());
            if (patient.getVisitedDoctor() != null) patientToUpdate.setVisitedDoctor(patient.getVisitedDoctor());
            if (patient.getVisitDate() != null) patientToUpdate.setVisitDate(patient.getVisitDate());
            if (patient.getPrescription() != null) patientToUpdate.setPrescription(patient.getPrescription());
            repo.save(patientToUpdate);
        }
        return patientToUpdate;
    }

    public Boolean deletePatient(Long id) {
        Boolean status = false;
        Patient patientToUpdate = repo.findById(id).orElse(null);
        if (patientToUpdate != null) {
            repo.deleteById(patientToUpdate.getId());
            status = true;
        }
        return status;
    }

    public Patient getPatientByName(String name) {
        return repo.findPatientByName(name);
    }

    public Patient getPatientById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
