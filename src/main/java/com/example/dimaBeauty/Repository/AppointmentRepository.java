package com.example.dimaBeauty.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dimaBeauty.Entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByClientId(Long clientId);
    List<Appointment> findByServId(Long servId);
    List<Appointment> findByEmployeeId(Long employeeId);
}
