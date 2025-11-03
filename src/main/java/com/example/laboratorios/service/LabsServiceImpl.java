package com.example.laboratorios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.laboratorios.exception.ResourceNotFoundException;
import com.example.laboratorios.model.Laboratorio;
import com.example.laboratorios.repository.LabsRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LabsServiceImpl implements LabsService {
    
    @Autowired
    private LabsRepository labsRepository;
//Metodos de cada parametro
    @Override
    public List<Laboratorio> getAllLabs() {
        log.info("Listando laboratorios..."); 
        List<Laboratorio> labs = labsRepository.findAll();
        log.debug("laboratorios encontrados");
        return labsRepository.findAll();
    }

    @Override // Listar laboratorio por id
    public Optional<Laboratorio> getById(Long id) {
         log.info("Buscando laboratorio con id", id);
        Optional<Laboratorio> lab = labsRepository.findById(id);
        if (lab.isEmpty()) {
            log.warn("No se encontró laboratorio ", id);
        } else {
            log.debug("Laboratorio encontrado ", id);
        }
        return labsRepository.findById(id);
    }

    @Override//Crear laboratorio
    public Laboratorio create(Laboratorio laboratorio) {
        log.info("Creando nuevo laboratorio...");
        Laboratorio saved = labsRepository.save(laboratorio);
        log.debug("Laboratorio creado id={}", saved.getId());
        return labsRepository.save(laboratorio);
    }

    @Override//Actualizar laboratorio
    public Laboratorio update(Long id, Laboratorio laboratorio) {
        log.info("Actualizando laboratorio ", id);
        return labsRepository.findById(id)
            .map(existing -> {
                existing.setNombre(laboratorio.getNombre());
                existing.setDireccion(laboratorio.getDireccion());
                existing.setTelefono(laboratorio.getTelefono());
                Laboratorio updated = labsRepository.save(existing);
                log.debug("Laboratorio actualizado ", id);
                return updated;
            })
            .orElseThrow(() -> {
                log.warn("Intento de actualizar laboratorio inexistente ", id);
                return new ResourceNotFoundException("No existe laboratorio " + id);
            });
    }
    

    @Override//Eliminar laboratorio
    public void deleteLabs(Long id) {
        log.info("Eliminando laboratorio", id);
        if (labsRepository.existsById(id)) {
            labsRepository.deleteById(id);
            log.debug("Laboratorio eliminado id={}", id);
        } else {
            log.warn("Intento de eliminar laboratorio inexistente", id);
            throw new ResourceNotFoundException("No existe laboratorio " + id);
        }
    }
//Login laboratorio
    public String login(String email, String clave) {
        log.info("Iniciando sesión para el email: {}", email);
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
}
