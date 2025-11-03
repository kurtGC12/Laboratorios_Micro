package com.example.laboratorios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.laboratorios.model.Laboratorio;
import com.example.laboratorios.repository.LabsRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LabsServiceImpl implements LabsService {
    
    @Autowired
    private LabsRepository labsRepository;

    @Override
    public List<Laboratorio> getAllLabs() {
        return labsRepository.findAll();
    }

    @Override
    public Optional<Laboratorio> getById(Long id) {
        return labsRepository.findById(id);
    }

    @Override
    public Laboratorio create(Laboratorio laboratorio) {
        return labsRepository.save(laboratorio);
    }

    @Override
    public Laboratorio update(Long id, Laboratorio laboratorio) {
        if (labsRepository.existsById(id)) {
            laboratorio.setId(id);
            return labsRepository.save(laboratorio);
        } else {
            return null;
        }
    }

    @Override
    public void deleteLabs(Long id) {
        if (labsRepository.existsById(id)) {
            labsRepository.deleteById(id);
        }
    }

    public String login(String email, String clave) {
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
}
