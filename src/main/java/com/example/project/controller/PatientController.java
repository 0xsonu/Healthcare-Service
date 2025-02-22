package com.example.project.controller;

import com.example.project.Model.Patient;
import com.example.project.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;
import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/patients/register")
    public JSONObject registerPatient(@RequestBody Patient patient) {
        return patientService.registerPatient(patient);
    }

    @GetMapping("/patients/list")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/patients/view/{id}")
    public Patient getPatientById(@PathVariable("id") String id) {
        return patientService.getPatientById(id);
    }

    @DeleteMapping("/patients/delete/{id}")
    public JSONObject deletePatient(@PathVariable("id") String id) {
        patientService.deletePatient(id);
        JSONObject response = new JSONObject();
        response.put("message", "Patient deleted successfully");
        return response;
    }
}
