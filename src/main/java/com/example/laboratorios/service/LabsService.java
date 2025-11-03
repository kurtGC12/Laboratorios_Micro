package com.example.laboratorios.service;



import com.example.laboratorios.model.Laboratorio;
import java.util.List;
import java.util.Optional;

public interface LabsService {
  List<Laboratorio> getAllLabs();
  Optional<Laboratorio> getById(Long id);
  Laboratorio create(Laboratorio lab);
  Laboratorio update(Long id, Laboratorio lab);
  void deleteLabs(Long id);
  String login(String email, String clave);
}