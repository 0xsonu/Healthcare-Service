package com.example.project.controller;

import com.example.project.Model.Appointment;
import com.example.project.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;
import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/appointment/register")
    public JSONObject bookAppointment(@RequestBody Appointment appointment) {
        return appointmentService.bookAppointment(appointment);
    }

    @GetMapping("/appointment/list")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/appointment/view/{appointmentId}")
    public Appointment getAppointmentById(@PathVariable("appointmentId") String appointmentId) {
        return appointmentService.getAppointmentById(appointmentId);
    }

    @GetMapping("/appointment/list/{patientid}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable("patientid") String patientId) {
        return appointmentService.getAppointmentsByPatientId(patientId);
    }

    @DeleteMapping("/appointment/delete/{appointmentId}")
    public JSONObject deleteAppointment(@PathVariable("appointmentId") String appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        JSONObject response = new JSONObject();
        response.put("message", "Booking deleted successfully");
        return response;
    }
}
