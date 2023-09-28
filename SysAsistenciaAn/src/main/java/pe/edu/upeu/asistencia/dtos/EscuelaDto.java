package pe.edu.upeu.asistencia.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.asistencia.models.Actividad;
import pe.edu.upeu.asistencia.models.Facultad;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EscuelaDto {

    Long id;
    String nombreeap;
    String estado;
    String inicialeseap;
   
    @JsonIgnoreProperties({"escuelas"})
    Facultad facultadId;

    public record EscuelaCrearDto(Long id, String nombreeap, String estado, String inicialeseap, Long facultadId) {

    }

}
