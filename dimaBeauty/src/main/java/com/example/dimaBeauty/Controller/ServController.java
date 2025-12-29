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

import com.example.dimaBeauty.Entity.Serv;
import com.example.dimaBeauty.Repository.ServRepository;


@RestController
@RequestMapping("/services")
public class ServController {

    @Autowired
    ServRepository servRepo;

    @GetMapping
    public List<Serv> getAll() {
        return servRepo.findAll();
    }

    @PostMapping
    public Serv save(@RequestBody Serv s) {
        servRepo.save(s);
        return servRepo.save(s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        servRepo.deleteById(id);
        
    }
    @PutMapping("/{id}")
    public Serv update(@PathVariable Long id, @RequestBody Serv s) {
        Serv existing = servRepo.findById(id).orElseThrow();
        existing.setName(s.getName());
        existing.setPrice(s.getPrice());
        return servRepo.save(existing);
    } 
}


