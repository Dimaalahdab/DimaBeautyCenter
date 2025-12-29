package com.example.dimaBeauty.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("appointments")
public class AppointmentController {

    @Autowired
    AppointmentRepository appRepo;

    @Autowired
    ClientRepository clientRepo;

    @Autowired
    ServRepository servRepo;

    @Autowired
    EmployeeRepository EmployeeRepo;

    @PostMapping("{clientId}/{servId}/{EmployeeId}")
    public String save(
            @PathVariable Long clientId,
            @PathVariable Long servId,
            @PathVariable Long employeeId,
            @RequestBody Appointment a) {

        Client c = clientRepo.findById(clientId).get();
        Serv s = servRepo.findById(servId).get();
        Employee e = EmployeeRepo.findById(employeeId).get();

        a.setClient(c);
        a.setServ(s);
        a.setEmployee(e);

        appRepo.save(a);
        return "Appointment saved!";
    }

    @GetMapping
    public List<Appointment> getAll() {
        return appRepo.findAll();
    }

    @GetMapping("client/{id}")
    public List<Appointment> getByClient(@PathVariable Long id) {
        return appRepo.findByClientId(id);
    }

    @GetMapping("worker/{id}")
    public List<Appointment> getByEmployee(@PathVariable Long id) {
        return appRepo.findByEmployeeId(id);
    }
    @DeleteMapping("{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appRepo.deleteById(id);
        return "Appointment deleted!";
    }
}


