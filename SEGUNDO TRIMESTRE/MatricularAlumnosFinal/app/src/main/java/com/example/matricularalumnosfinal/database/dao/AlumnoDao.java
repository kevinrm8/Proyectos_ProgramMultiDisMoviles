package com.example.matriculacionalumno.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.matriculacionalumno.database.entidades.Alumno;
import com.example.matriculacionalumno.database.relaciones.AlumnoConAsignaturasRelacion;

import java.util.List;

@Dao
public interface AlumnoDao {
    @Transaction
    @Query("SELECT * FROM alumno ORDER BY 3")
    List<Alumno> getAlumnos();

    @Transaction
    @Query("SELECT * FROM alumno WHERE dni LIKE :uuid")
    List<AlumnoConAsignaturasRelacion> getAlumnoConAsignaturas(String uuid);

    @Insert
    void addAlumno(Alumno alumno);

    @Delete
    void deleteAlumno(Alumno alumno);

    @Update
    void updateAlumno(Alumno alumno);
}
