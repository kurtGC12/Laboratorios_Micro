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
@RequestMapping("/api/laboratorios")
public class LabsController {

     @Autowired
    private LabsService labsService;

     public LabsController(LabsService labsService) {
        this.labsService = labsService;
    }
    

 // GET /api/laboratorios
    @GetMapping
    public List<Laboratorio> getAll() {
        log.info("Listando laboratorios...");
        log.debug("laboratorios encontrados");
        return labsService.getAll();
        
    }

    //METODO GET /api/laboratorios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Laboratorio> getById(@PathVariable Long id) {
        log.info("Busccando labaoratorio con id:", id);
        return labsService.getById(id)
                .map(ResponseEntity::ok) // Si lo encuentra → 200 OK           
                .orElse(ResponseEntity.notFound().build()); // Si no → 404
                
    }

    //METODO POST /api/laboratorios
    @PostMapping
    public ResponseEntity<Laboratorio> create(@Valid @RequestBody Laboratorio laboratorio) {
        log.info("Creando laboratorio:", laboratorio);
        Laboratorio nuevo = labsService.create(laboratorio);
        log.debug("Laboratorio creado con id:", nuevo.getId());
         return ResponseEntity.status(201).body(nuevo); // Devuelve 201 nuevo
    }

    // METODO PUT /api/laboratorios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Laboratorio> update(@PathVariable Long id, @Valid @RequestBody Laboratorio laboratorio) {
        log.info("Actualizando laboratorio con id:", id);
    Laboratorio updated = labsService.update(id, laboratorio);
        if (updated == null) {
            log.debug("Laboratorio con id {} no encontrado para actualizar", id);
            return ResponseEntity.notFound().build();// Si no → 404
        }
        log.debug("Laboratorio con id {} actualizado", id);
        return ResponseEntity.ok(updated);// Si lo encuentra → 200 OK
    }

    // METODO DELETE /api/laboratorios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Eliminando laboratorio con id:", id);
        // si no existe, tu service no lanza excepción; devolvemos 204 siempre
        labsService.delete(id);
        log.debug("Laboratorio con id {} eliminado", id);
        return ResponseEntity.noContent().build();// Eliminado → 204
    }
}    

