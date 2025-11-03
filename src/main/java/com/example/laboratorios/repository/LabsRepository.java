package com.example.laboratorios.repository;



import com.example.laboratorios.model.Laboratorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// Extiende Jpa
public interface LabsRepository extends JpaRepository<Laboratorio, Long> { 

       List<Laboratorio> findByNombre(String nombre);
}