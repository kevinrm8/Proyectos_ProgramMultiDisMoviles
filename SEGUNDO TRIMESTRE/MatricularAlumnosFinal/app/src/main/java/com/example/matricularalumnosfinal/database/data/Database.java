package com.example.matriculacionalumno.database.data;

import androidx.room.RoomDatabase;

import com.example.matriculacionalumno.database.dao.AlumnoDao;
import com.example.matriculacionalumno.database.dao.AlumnoRelAsignaturaDao;
import com.example.matriculacionalumno.database.dao.AsignaturaDao;
import com.example.matriculacionalumno.database.entidades.Alumno;
import com.example.matriculacionalumno.database.entidades.AlumnoRelAsignatura;
import com.example.matriculacionalumno.database.entidades.Asignatura;

@androidx.room.Database(entities = {Asignatura.class, Alumno.class, AlumnoRelAsignatura.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase  {
    public abstract AsignaturaDao getAsignaturaDao();
    public abstract AlumnoDao getAlumnoDao();
    public abstract AlumnoRelAsignaturaDao getAlumnoRelAsignaturasDao();
}
