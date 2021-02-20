package com.example.matriculacionalumno.database.imp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import androidx.room.Room;

import com.example.matriculacionalumno.database.dao.AsignaturaDao;
import com.example.matriculacionalumno.database.data.Database;
import com.example.matriculacionalumno.database.entidades.Asignatura;

import java.util.List;

public class AsignaturaImp {

    @SuppressLint("StaticFieldLeak")
    private static AsignaturaImp asignaturaImp;

    private AsignaturaDao asignaturaDao;
    private Context appContext;

    public AsignaturaImp(Context context) {
        appContext = context.getApplicationContext();
        Database database = Room.databaseBuilder(appContext, Database.class, "asignaturaDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
        asignaturaDao = database.getAsignaturaDao();
    }

    public static AsignaturaImp get(Context context) {
        if (asignaturaImp == null) {
            asignaturaImp = new AsignaturaImp(context);
        }
        return asignaturaImp;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturaDao.getAsignaturas();
    }

    public Asignatura getAsignatura(String id) {
        return asignaturaDao.getAsignatura(id);
    }

    public void addAsignatura(Asignatura asignatura) {
        try{
            asignaturaDao.addAsignatura(asignatura);
        }catch (Exception e){
            Toast.makeText(appContext,"Error al insertar",Toast.LENGTH_LONG).show();
        }
    }

    public void updateAsignatura(Asignatura asignatura) {
        try{
            asignaturaDao.updateAsignatura(asignatura);
        }catch (Exception e){
            Toast.makeText(appContext,"Error al actualizar",Toast.LENGTH_LONG).show();
        }
    }

    public void deleteAsignatura(Asignatura asignatura) {
        try{
            asignaturaDao.deleteAsignatura(asignatura);
        }catch (Exception e){
            Toast.makeText(appContext,"Error al borrar",Toast.LENGTH_LONG).show();
        }
    }

    public List<Asignatura> getAsignaturasQueEstenAsignadas(){
        return asignaturaDao.getAsignaturasQueEstenAsignadas();
    }
}
