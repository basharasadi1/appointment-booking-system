# Appointment Booking System

A backend REST API for managing appointment bookings, built with Spring Boot and JPA.

---

## Features

- Create appointments
- Retrieve all appointments
- Delete appointments
- Prevent double booking (same date & time)
- Validate input fields (no empty values)
- Date and time validation

---

## Tech Stack

- Java 21+
- Spring Boot
- Spring Data JPA
- H2 Database (in-memory)
- Maven

---

## API Endpoints

### Get all appointments
GET /appointments

---

### Create appointment
POST /appointments

Example request body:
```json
{
  "customerName": "Bashar",
  "customerPhone": "123456",
  "date": "2026-03-26",
  "time": "15:00:00",
  "service": "Haircut"
}
