package com.shashwat.patient.controller;

import com.shashwat.patient.entity.Patient;
import com.shashwat.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService service;

    @PostMapping("/savePatient")
    @CrossOrigin("*")
    public Patient savePatient(@RequestBody Patient patient) {
        return service.savePatient(patient);
    }

    @GetMapping("/findAllPatients")
    @CrossOrigin("*")
    public List<Patient> getAllPatients() {
        return service.getAllPatients();
    }

    @GetMapping("/findPatientById/{id}")
    @CrossOrigin("*")
    public Patient findPatientById(@PathVariable Long id) {
        return service.getPatientById(id);
    }

    @GetMapping("/findPatientByName/{name}")
    @CrossOrigin("*")
    public Patient findPatientByName(@PathVariable String name) {
        return service.getPatientByName(name);
    }

    @PutMapping("/updatePatient")
    @CrossOrigin("*")
    public Patient updatePatient(@RequestBody Patient patient) {
        return service.updatePatient(patient);
    }

    @DeleteMapping("/deletePatient/{id}")
    @CrossOrigin("*")
    public Boolean deletePatient(@PathVariable Long id) {
        return service.deletePatient(id);
    }
}
