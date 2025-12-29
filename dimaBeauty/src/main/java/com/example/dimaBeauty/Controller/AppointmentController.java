package com.example.dimaBeauty.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.web.bind.annotation.*;

import com.example.dimaBeauty.Entity.Appointment;
import com.example.dimaBeauty.Entity.Employee;
import com.example.dimaBeauty.Entity.Serv;
import com.example.dimaBeauty.Entity.Client;
import com.example.dimaBeauty.Repository.AppointmentRepository;
import com.example.dimaBeauty.Repository.ClientRepository;
import com.example.dimaBeauty.Repository.EmployeeRepository;
import com.example.dimaBeauty.Repository.ServRepository;


@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    AppointmentRepository appRepo;

    @Autowired
    ClientRepository clientRepo;

    @Autowired
    ServRepository servRepo;

    @Autowired
    EmployeeRepository employeeRepo;

    @PostMapping("/{clientId}/{servId}/{employeeId}")
    public ResponseEntity<String> save(
            @PathVariable Long clientId,
            @PathVariable Long servId,
            @PathVariable Long employeeId,
            @RequestBody Appointment a) {

        // 🔴 Conflict check
        boolean conflict = !appRepo
                .findByEmployeeIdAndDateAndTime(
                        employeeId, a.getDate(), a.getTime()
                ).isEmpty();

        if (conflict) {
            return ResponseEntity
                    .badRequest()
                    .body("Employee already has an appointment at this time");
        }

        Client c = clientRepo.findById(clientId).orElseThrow();
        Serv s = servRepo.findById(servId).orElseThrow();
        Employee e = employeeRepo.findById(employeeId).orElseThrow();

        a.setClient(c);
        a.setServ(s);
        a.setEmployee(e);

        appRepo.save(a);
        return ResponseEntity.ok("Appointment saved");
    }

    @GetMapping
    public List<Appointment> getAll() {
        return appRepo.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        appRepo.deleteById(id);
    }
}
