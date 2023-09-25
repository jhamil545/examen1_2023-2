/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.asistencia.exceptions.ResourceNotFoundException;
import pe.edu.upeu.asistencia.models.Escuela;
import pe.edu.upeu.asistencia.repositories.EscuelaRepository;

/**
 *
 * @author DELL
 */
@RequiredArgsConstructor
@Service
@Transactional
public class EscuelaServiceImp implements EscuelaService {

    @Autowired
    private EscuelaRepository entidadRepo;

    @Override
    public Escuela save(Escuela entidad) {
        return entidadRepo.save(entidad);
    }

    @Override
    public List<Escuela> findAll() {
        return entidadRepo.findAll();
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Escuela entidadx = entidadRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Escuela not exist with id :" + id));
        entidadRepo.delete(entidadx);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;
    }

    @Override
    public Escuela geEntidadById(Long id) {
        Escuela findEntidad = entidadRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Escuela not exist with id :" + id));
        return findEntidad;
    }

    @Override
    public Escuela update(Escuela entidad, Long id) {
        Escuela entidadx = entidadRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Periodo not exist with id :" + id));
        entidadx.setNombreeap(entidad.getNombreeap());
        entidadx.setEstado(entidad.getEstado());
        entidadx.setInicialeseap(entidad.getInicialeseap());
        return entidadRepo.save(entidadx);
    }

}