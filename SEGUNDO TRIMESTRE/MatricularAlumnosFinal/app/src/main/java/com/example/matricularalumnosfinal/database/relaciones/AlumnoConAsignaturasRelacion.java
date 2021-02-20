package com.example.matriculacionalumno.database.relaciones;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.matriculacionalumno.database.entidades.Alumno;
import com.example.matriculacionalumno.database.entidades.AlumnoRelAsignatura;
import com.example.matriculacionalumno.database.entidades.Asignatura;

import java.util.List;

public class AlumnoConAsignaturasRelacion {

    @Embedded
    public Alumno alumno;

    @Relation(
            parentColumn = "dni",
            entityColumn = "codigo",
            associateBy = @Junction(AlumnoRelAsignatura.class)
    )
    public List<Asignatura> asignaturas;

}
