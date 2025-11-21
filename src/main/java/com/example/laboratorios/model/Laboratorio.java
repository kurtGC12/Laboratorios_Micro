package com.example.laboratorios.model;


import jakarta.persistence.*; // Librería JPA (maneja las entidades y mapeo a tablas)
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // Genera getters, setters

@Entity
@Table(name = "Laboratorios") // nombre de la tabla
public class Laboratorio  {

    @Id // clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // se genera automaticamente el id
     private Long id;

    @NotBlank// valida que no este vacio
    @NotNull(message = "El nombre no puede ser nulo")// valida que el nombre no sea nulo
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")// valida que el nombre tenga entre 3 y 50 caracteres
    private String nombre;

    
    @NotBlank 
    @NotNull(message = "La dirección no puede ser nula")
    @Size(min = 5, max = 100, message = "La dirección debe tener entre 5 y 100 caracteres")
    private String direccion;

    @NotNull(message = "El teléfono no puede ser nulo")
    @Min(value = 100000000, message = "El teléfono debe tener al menos 9 dígitos")
    private Integer telefono; 


}