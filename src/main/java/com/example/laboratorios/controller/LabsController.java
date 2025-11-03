package com.example.laboratorios.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.laboratorios.model.Laboratorio;
import com.example.laboratorios.service.LabsService;

import java.util.List;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
@RequestMapping("/api/usuarios")
public class LabsController {

     @Autowired
    private LabsService labsService;

     public LabsController(LabsService labsService) {
        this.labsService = labsService;
    }
    

 // GET /api/users
    @GetMapping
    public List<Laboratorio> getAll() {
        log.info("Listando usuarios...");
        return labsService.getAllLabs();
    }

    //METODO GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Laboratorio> getById(@PathVariable Long id) {
        return labsService.getById(id)
                .map(ResponseEntity::ok) // Si lo encuentra → 200 OK
                .orElse(ResponseEntity.notFound().build()); // Si no → 404
    }

    //METODO POST /api/users
    @PostMapping
    public ResponseEntity<Laboratorio> create(@Valid @RequestBody Laboratorio laboratorio) {
        Laboratorio nuevo = labsService.create(laboratorio);
         return ResponseEntity.status(201).body(nuevo); // Devuelve 201 nuevo
    }

    // METODO PUT /api/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Laboratorio> update(@PathVariable Long id, @Valid @RequestBody Laboratorio laboratorio) {
    Laboratorio updated = labsService.update(id, laboratorio);
        if (updated == null) {
            return ResponseEntity.notFound().build();// Si no → 404
        }
        return ResponseEntity.ok(updated);// Si lo encuentra → 200 OK
    }

    // METODO DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // si no existe, tu service no lanza excepción; devolvemos 204 siempre
        labsService.deleteLabs(id);
        return ResponseEntity.noContent().build();// Eliminado → 204
    }
}    

