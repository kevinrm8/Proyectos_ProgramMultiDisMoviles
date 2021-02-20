package com.example.matriculacionalumno.database.imp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import androidx.room.Room;

import com.example.matriculacionalumno.database.dao.AlumnoDao;
import com.example.matriculacionalumno.database.data.Database;
import com.example.matriculacionalumno.database.entidades.Alumno;
import com.example.matriculacionalumno.database.relaciones.AlumnoConAsignaturasRelacion;

import java.util.List;

public class AlumnoImp {

    @SuppressLint("StaticFieldLeak")
    private static AlumnoImp alumnoImp;

    private AlumnoDao alumnoDao;
    private Context appContext;

    public AlumnoImp(Context context) {
        appContext = context.getApplicationContext();
        Database database = Room.databaseBuilder(appContext, Database.class, "asignaturaDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
        alumnoDao = database.getAlumnoDao();
    }

    public static AlumnoImp get(Context context) {
        if (alumnoImp == null) {
            alumnoImp = new AlumnoImp(context);
        }
        return alumnoImp;
    }

    public List<Alumno> getAlumnos() {
        return alumnoDao.getAlumnos();
    }

    public List<AlumnoConAsignaturasRelacion> getAlumnoConAsignaturas(String id) {
        return alumnoDao.getAlumnoConAsignaturas(id);
    }

    public void addAlumno(Alumno alumno) {
        try {
            alumnoDao.addAlumno(alumno);
        }catch (Exception e){
            Toast.makeText(appContext,"Error al insertar",Toast.LENGTH_LONG).show();
        }
    }

    public void updateAlumno(Alumno alumno) {
        try {
            alumnoDao.updateAlumno(alumno);
        }catch (Exception e){
            Toast.makeText(appContext,"Error al actualizar",Toast.LENGTH_LONG).show();
        }
    }

    public void deleteAlumno(Alumno alumno) {
        try{
            alumnoDao.deleteAlumno(alumno);
        }catch (Exception e){
            Toast.makeText(appContext,"Error al borrar",Toast.LENGTH_LONG).show();
        }
    }
}
