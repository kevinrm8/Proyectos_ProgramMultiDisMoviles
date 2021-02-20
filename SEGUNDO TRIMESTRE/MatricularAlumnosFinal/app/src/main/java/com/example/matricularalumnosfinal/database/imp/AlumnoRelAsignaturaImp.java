package com.example.matriculacionalumno.database.imp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import androidx.room.Room;

import com.example.matriculacionalumno.database.dao.AlumnoRelAsignaturaDao;
import com.example.matriculacionalumno.database.data.Database;
import com.example.matriculacionalumno.database.entidades.AlumnoRelAsignatura;
import com.example.matriculacionalumno.database.entidades.Asignatura;

import java.util.List;

public class AlumnoRelAsignaturaImp {
    @SuppressLint("StaticFieldLeak")
    private static AlumnoRelAsignaturaImp asignaturaLab;

    private AlumnoRelAsignaturaDao asignaturaDao;
    private Context appContext;

    public AlumnoRelAsignaturaImp(Context context) {
        appContext = context.getApplicationContext();
        Database database = Room.databaseBuilder(appContext, Database.class, "asignaturaDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
        asignaturaDao = database.getAlumnoRelAsignaturasDao();
    }

    public static AlumnoRelAsignaturaImp get(Context context) {
        if (asignaturaLab == null) {
            asignaturaLab = new AlumnoRelAsignaturaImp(context);
        }
        return asignaturaLab;
    }

    public void addAlumnoConAsignaturas(AlumnoRelAsignatura asignatura) {
        try{
            asignaturaDao.addAlumnoConAsignaturas(asignatura);
        }catch (Exception e){
            Toast.makeText(appContext,"Error al insertar",Toast.LENGTH_LONG).show();
        }
    }

    public void deleteAlumnoConAsignaturas(AlumnoRelAsignatura asignatura) {
        try{
            asignaturaDao.deleteAlumnoConAsignaturas(asignatura);
        }catch (Exception e){
            Toast.makeText(appContext,"Error al borrar",Toast.LENGTH_LONG).show();
        }
    }

    public List<AlumnoRelAsignatura> getAlumnoConAsignaturas(String id){
        return asignaturaDao.getAlumnoRelAsignaturas(id);
    }

    public List<Asignatura> getAsignaturasDeAlumno(String id){
        return asignaturaDao.getAsignaturasDeAlumno(id);
    }
    public List<Asignatura> getAsignaturasDisponiblesDeAlumno(String id){
        return asignaturaDao.getAsignaturasDisponiblesDeAlumno(id);
    }

}
