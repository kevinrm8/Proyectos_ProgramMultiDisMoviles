package com.example.matriculacionalumno.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.matriculacionalumno.database.entidades.Asignatura;

import java.util.List;

@Dao
public interface AsignaturaDao {
    @Transaction
    @Query("SELECT * FROM asignatura ORDER BY 1")
    List<Asignatura> getAsignaturas();

    @Transaction
    @Query("SELECT * FROM asignatura WHERE codigo LIKE :uuid")
    Asignatura getAsignatura(String uuid);

    @Transaction
    @Insert
    void addAsignatura(Asignatura asignatura);

    @Transaction
    @Delete
    void deleteAsignatura(Asignatura asignatura);

    @Transaction
    @Update
    void updateAsignatura(Asignatura asignatura);

    //con esta query obtengo las asignaturas que con algún alumno matriculado
    @Transaction
    @Query("SELECT a.* FROM asignatura a INNER JOIN alumno_rel_asignatura rel ON a.codigo = rel.codigo")
    List<Asignatura> getAsignaturasQueEstenAsignadas();
}
