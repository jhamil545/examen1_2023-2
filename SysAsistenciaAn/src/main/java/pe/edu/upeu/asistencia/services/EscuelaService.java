/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import java.util.List;
import java.util.Map;
import pe.edu.upeu.asistencia.models.Escuela;

/**
 *
 * @author DELL
 */
public interface EscuelaService {
    Escuela save(Escuela entidad);

    List<Escuela> findAll();

    Map<String, Boolean> delete(Long id);

    Escuela geEntidadById(Long id);

    Escuela update(Escuela entidad, Long id); 
}