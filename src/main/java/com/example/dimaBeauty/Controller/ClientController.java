package com.example.dimaBeauty.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dimaBeauty.Entity.Client;
import com.example.dimaBeauty.Entity.Employee;
import com.example.dimaBeauty.Repository.ClientRepository;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    ClientRepository clientRepo;

    @GetMapping
    public List<Client> getAll() {
        return clientRepo.findAll();
    }

    @PostMapping
    public String save(@RequestBody Client c) {
        clientRepo.save(c);
        return "Client saved!";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id) {
        clientRepo.deleteById(id);
        return "Client deleted!";
    }
    @PutMapping("{id}")
    public Client updateEmployee(@PathVariable Long id, @RequestBody Client c) {
        Client existing = clientRepo.findById(id).get();

        existing.setName(c.getName());
        existing.setPhone(c.getPhone());

        return clientRepo.save(existing);
    }
}
