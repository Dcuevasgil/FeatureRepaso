package org.fundacion.repaso.dto;

import java.util.ArrayList;
import java.util.List;

import org.fundacion.repaso.persistance.model.Alumno;
import org.fundacion.repaso.persistance.model.Asignatura;
import org.fundacion.repaso.persistance.model.Nota;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AsignaturaDTO {
    private Long id;
    private String asignaturaName;
    private List<NotaDTO> notas; // Se agregaran las notas solo de esa asignatura

    public AsignaturaDTO(Asignatura a) {
        this.id = a.getAsignaturaId();
        this.asignaturaName = a.getAsignaturaName();
        this.notas = toNotaDTO(a.getAlumnosMatriculados()); // Convertir las notas del alumno 
    }

    private List<NotaDTO> toNotaDTO(List<Alumno> alumnos) {
        List<NotaDTO> notasDTO =  new ArrayList<>();
        for (Alumno alumno : alumnos) {
            for(Nota nota : alumno.getNotas()) { // Se agregan las notas de esta asignatura de ese alumno
                if(nota.getAsignatura().getAsignaturaId().equals(this.id)) {
                    notasDTO.add(new NotaDTO(nota));
                }
            }
        }
        return notasDTO;
    }
}
