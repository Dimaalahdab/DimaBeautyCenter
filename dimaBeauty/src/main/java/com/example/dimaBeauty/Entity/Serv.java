package com.example.dimaBeauty.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Serv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    @OneToMany(mappedBy = "serv", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Appointment> appointments;

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public double getPrice() {
  return price;
 }

 public void setPrice(double price) {
  this.price = price;
 }

 public List<Appointment> getAppointments() {
  return appointments;
 }

 public void setAppointments(List<Appointment> appointments) {
  this.appointments = appointments;
 }

    
}