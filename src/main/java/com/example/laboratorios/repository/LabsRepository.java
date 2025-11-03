package com.example.laboratorios.repository;



import com.example.laboratorios.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabsRepository extends JpaRepository<Laboratorio, Long> { }