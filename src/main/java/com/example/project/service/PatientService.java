package com.example.project.service;

import com.example.project.Model.Patient;
import com.example.project.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.simple.JSONObject;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepo;

    public JSONObject registerPatient(Patient patient) {
        JSONObject response = new JSONObject();
        if(patient.getPatient_Id() == null || patient.getPatient_Id().isEmpty()){
            patient.setPatient_Id(UUID.randomUUID().toString());
        }
        if(patient.getRegisteredDate() == null) {
            patient.setRegisteredDate(new Date());
        }
        patientRepo.save(patient);
        response.put("message", "Registration successful");
        return response;
    }

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    public Patient getPatientById(String id) {
        return patientRepo.findById(id).orElse(null);
    }

    public void deletePatient(String id) {
        patientRepo.deleteById(id);
    }
}
