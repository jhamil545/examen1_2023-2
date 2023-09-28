/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.asistencia.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pe.edu.upeu.asistencia.dtos.EscuelaDto;
import pe.edu.upeu.asistencia.models.Escuela;
import pe.edu.upeu.asistencia.models.Escuela;
/**
 *
 * @author DELL
 */
@Mapper(componentModel = "spring")
public interface EscuelaMapper {
    EscuelaDto toEscuela(Escuela entidad);

    //@Mapping(target = "id", ignore = true)
    @Mapping(target = "facultadId", ignore = true)
    Escuela escuelaCrearDtoToEscuela(EscuelaDto.EscuelaCrearDto entidadCrearDto);    
}
