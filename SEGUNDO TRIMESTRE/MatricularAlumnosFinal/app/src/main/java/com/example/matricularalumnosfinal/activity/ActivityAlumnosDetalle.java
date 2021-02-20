package com.example.matriculacionalumno.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.matriculacionalumno.R;
import com.example.matriculacionalumno.adapter.AdapterAsignaturaDetalle;
import com.example.matriculacionalumno.database.entidades.AlumnoRelAsignatura;
import com.example.matriculacionalumno.database.entidades.Asignatura;
import com.example.matriculacionalumno.database.imp.AlumnoRelAsignaturaImp;
import com.example.matriculacionalumno.database.imp.AsignaturaImp;
import com.example.matriculacionalumno.dialog.DialogListener;
import com.example.matriculacionalumno.dialog.FragmentDialogAlumnoSeleccionar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ActivityAlumnosDetalle extends AppCompatActivity implements DialogListener.ListenerNueva {

    private RecyclerView recyclerView;
    private AdapterAsignaturaDetalle adapter;
    private AlumnoRelAsignaturaImp asignaturaLab;

    private FloatingActionButton fabNuevaAsignatura;
    private List<Asignatura> asignaturas;
    private List<Asignatura> asignaturasDisponibles;
    private String dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos_detalle);

        dni = getIntent().getStringExtra("dni");

        recyclerView = (RecyclerView) findViewById(R.id.rvAsignaturasAlumno);
        asignaturaLab = new AlumnoRelAsignaturaImp(this);
        asignaturas = asignaturaLab.getAsignaturasDeAlumno(dni);
        asignaturasDisponibles = asignaturaLab.getAsignaturasDisponiblesDeAlumno(dni);

        adapter = new AdapterAsignaturaDetalle(this,asignaturas,dni);
        recyclerView.setAdapter(adapter);

        //añadir asignatura al alumno
        fabNuevaAsignatura = (FloatingActionButton) findViewById(R.id.fabNuevaAsignatura);
        fabNuevaAsignatura.setOnClickListener(b -> {
            if(asignaturasDisponibles.size()==0){
                Toast.makeText(this,"No hay asignaturas disponibles", Toast.LENGTH_SHORT).show();
            }else {
                FragmentDialogAlumnoSeleccionar fragment = new FragmentDialogAlumnoSeleccionar(this, asignaturasDisponibles);
                fragment.setCancelable(false);
                fragment.show(getSupportFragmentManager(), "Añadir asignatura");
            }
        });
    }

    @Override
    public void onClickNueva(Object o) {
        String codigo = (String) o;

        AlumnoRelAsignatura relacion = new AlumnoRelAsignatura(dni,codigo);

        asignaturaLab.addAlumnoConAsignaturas(relacion);
        AsignaturaImp asignaturaImp = new AsignaturaImp(this);

        Asignatura actualizar = asignaturaImp.getAsignatura(codigo);
        actualizar.setNumAlumnos(actualizar.getNumAlumnos()+1);
        asignaturaImp.updateAsignatura(actualizar);
    }
}