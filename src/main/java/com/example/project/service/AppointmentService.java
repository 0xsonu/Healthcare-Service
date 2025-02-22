package com.example.project.service;

import com.example.project.Model.Appointment;
import com.example.project.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.simple.JSONObject;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepo;

    public JSONObject bookAppointment(Appointment appointment) {
        JSONObject response = new JSONObject();
        if(appointment.getBooking_id() == null || appointment.getBooking_id().isEmpty()){
            appointment.setBooking_id(UUID.randomUUID().toString());
        }
        appointment.setBookingTime(new Date());
        appointmentRepo.save(appointment);
        response.put("message", "Booking successful");
        return response;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    public Appointment getAppointmentById(String appointmentId) {
        return appointmentRepo.findById(appointmentId).orElse(null);
    }

    public List<Appointment> getAppointmentsByPatientId(String patientId) {
        List<Appointment> allAppointments = appointmentRepo.findAll();
        return allAppointments.stream()
                .filter(a -> a.getPatientId().equals(patientId))
                .collect(Collectors.toList());
    }

    public void deleteAppointment(String appintId) {
        appointmentRepo.deleteById(appintId);
    }
}
