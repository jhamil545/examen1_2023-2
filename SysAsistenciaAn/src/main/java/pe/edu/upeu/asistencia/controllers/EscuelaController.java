/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import pe.edu.upeu.asistencia.dtos.EscuelaDto;
import pe.edu.upeu.asistencia.dtos.MaterialesxDto;
import pe.edu.upeu.asistencia.models.Escuela;
import pe.edu.upeu.asistencia.models.Materialesx;
import pe.edu.upeu.asistencia.services.EscuelaService;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/upeu/escuela")
public class EscuelaController {
    @Autowired
    private EscuelaService escuelaService;    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Escuela>> listActividad() {
        List<Escuela> actDto = escuelaService.findAll();
        
        //Gson gson = new Gson();
        //String jsonCartList = gson.toJson(actDto);
        //System.out.println("Ver Aqui: "+jsonCartList);
        return ResponseEntity.ok(actDto);
        //return new ResponseEntity<>(actDto, HttpStatus.OK);
    }
    
    @PostMapping("/crear")
    public ResponseEntity<Escuela> createMaterialesx(@RequestBody EscuelaDto.EscuelaCrearDto escuela) {

        
        Escuela data = escuelaService.save(escuela);
        return ResponseEntity.ok(data);
    }
    
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Escuela> getActividadById(@PathVariable Long id) {
        Escuela actividad = escuelaService.getEscuelaById(id);
        return ResponseEntity.ok(actividad);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteActividad(@PathVariable Long id) {
        Escuela actividad = escuelaService.getEscuelaById(id);
        return ResponseEntity.ok(escuelaService.delete(actividad.getId()));
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<Escuela> updateEscuela(@PathVariable Long id,
            @RequestBody EscuelaDto.EscuelaCrearDto escuelaDetails) {
        Escuela updatedEscuela = escuelaService.update(escuelaDetails, id);
        return ResponseEntity.ok(updatedEscuela); 
    } }