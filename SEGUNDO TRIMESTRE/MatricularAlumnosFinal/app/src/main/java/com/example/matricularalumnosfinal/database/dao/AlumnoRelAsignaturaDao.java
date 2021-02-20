package com.example.matriculacionalumno.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.matriculacionalumno.database.entidades.AlumnoRelAsignatura;
import com.example.matriculacionalumno.database.entidades.Asignatura;

import java.util.List;

@Dao
public interface AlumnoRelAsignaturaDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<AlumnoRelAsignatura> alumnoConAsignaturas);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addAlumnoConAsignaturas(AlumnoRelAsignatura asignatura);

    @Transaction
    @Delete
    void deleteAlumnoConAsignaturas(AlumnoRelAsignatura asignatura);

    @Transaction
    @Query("SELECT * FROM alumno_rel_asignatura WHERE dni LIKE :id")
    List<AlumnoRelAsignatura> getAlumnoRelAsignaturas(String id);

    //con esta query obtengo las asignaturas que tiene el alumno matriculadas
    @Transaction
    @Query("SELECT a.* FROM asignatura a INNER JOIN alumno_rel_asignatura rel ON a.codigo = rel.codigo WHERE rel.dni LIKE :dni")
    List<Asignatura> getAsignaturasDeAlumno(String dni);

    //con esta query obtengo las asignaturas que tiene el alumno disponibles
    @Transaction
    @Query("SELECT a.* FROM asignatura a WHERE a.codigo NOT IN " +
            "(SELECT a2.codigo FROM asignatura a2 INNER JOIN alumno_rel_asignatura rel ON a2.codigo = rel.codigo WHERE rel.dni like :dni)")
    List<Asignatura> getAsignaturasDisponiblesDeAlumno(String dni);



}
