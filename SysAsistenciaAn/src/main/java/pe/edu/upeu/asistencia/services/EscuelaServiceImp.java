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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upeu.asistencia.dtos.EscuelaDto;
import pe.edu.upeu.asistencia.dtos.MaterialesxDto;
import pe.edu.upeu.asistencia.exceptions.AppException;

import pe.edu.upeu.asistencia.exceptions.ResourceNotFoundException;
import pe.edu.upeu.asistencia.mappers.EscuelaMapper;
import pe.edu.upeu.asistencia.mappers.MaterialesxMapper;
import pe.edu.upeu.asistencia.models.Escuela;

import pe.edu.upeu.asistencia.repositories.EscuelaRepository;
import pe.edu.upeu.asistencia.repositories.MaterialesxRepository;

/**
 *
 * @author DELL
 */

@RequiredArgsConstructor
@Service
@Transactional
public class EscuelaServiceImp implements EscuelaService {

    @Autowired
    private EscuelaRepository escuelaRepo;

    @Autowired
    private FacultadService facultadService;

    private final EscuelaMapper escuelaMapper;

    @Override
    public Escuela save(EscuelaDto.EscuelaCrearDto escuela) {

        Escuela matEnt=escuelaMapper.escuelaCrearDtoToEscuela(escuela);
        matEnt.setFacultadId(facultadService.geFacultadById(escuela.facultadId()));
        System.out.println(escuela.nombreeap());
        System.out.println(escuela.estado());
        try {
            return escuelaRepo.save(matEnt);
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Escuela> findAll() {
        try {
            return escuelaRepo.findAll();
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Escuela escuelax = escuelaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("escu not exist with id :" + id));

        escuelaRepo.delete(escuelax);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;
    }

    @Override
    public Escuela getEscuelaById(Long id) {
        Escuela findEscuela = escuelaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("escuela not exist with id :" + id));
        return findEscuela;
    }

    @Override
    public Escuela update(EscuelaDto.EscuelaCrearDto escuela, Long id) {
        Escuela escuelax = escuelaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Periodo not exist with id :" + id));
          
        escuelax.setNombreeap(escuela.nombreeap());
        escuelax.setEstado(escuela.estado());
        escuelax.setInicialeseap(escuela.inicialeseap());
        return escuelaRepo.save(escuelax);
    }

}