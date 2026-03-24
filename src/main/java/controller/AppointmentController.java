package com.bashar.appointmentbookingsystem.controller;

import com.bashar.appointmentbookingsystem.model.Appointment;
import com.bashar.appointmentbookingsystem.repository.AppointmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        if (appointment.getCustomerName() == null || appointment.getCustomerName().isBlank() ||
                appointment.getCustomerPhone() == null || appointment.getCustomerPhone().isBlank() ||
                appointment.getDate() == null ||
                appointment.getTime() == null ||
                appointment.getService() == null || appointment.getService().isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "All fields must be filled."
            );
        }

        if (appointmentRepository.existsByDateAndTime(appointment.getDate(), appointment.getTime())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "This time slot is already booked."
            );
        }

        return appointmentRepository.save(appointment);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Appointment not found."
            );
        }

        appointmentRepository.deleteById(id);
    }
}