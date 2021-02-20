package com.example.matriculacionalumno.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.matriculacionalumno.R;
import com.example.matriculacionalumno.adapter.AdapterAlumnoPrincipal;
import com.example.matriculacionalumno.database.entidades.Alumno;
import com.example.matriculacionalumno.database.imp.AlumnoImp;
import com.example.matriculacionalumno.dialog.DialogListener;
import com.example.matriculacionalumno.dialog.FragmentDialogAlumno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ActivityAlumnos extends AppCompatActivity implements DialogListener.ListenerNueva {

    private RecyclerView recyclerView;
    private AdapterAlumnoPrincipal adapter;
    private AlumnoImp alumnoImp;

    private FloatingActionButton fabAltaAlumno;
    private List<Alumno> alumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);

        recyclerView = (RecyclerView) findViewById(R.id.rvAlumnos);
        alumnoImp = new AlumnoImp(this);
        alumnos = alumnoImp.getAlumnos();
        adapter = new AdapterAlumnoPrincipal(this,alumnos);
        recyclerView.setAdapter(adapter);

        fabAltaAlumno = (FloatingActionButton) findViewById(R.id.fabAltaAlumno);
        fabAltaAlumno.setOnClickListener(b -> {
            FragmentDialogAlumno fragmentDialogAlumno = new FragmentDialogAlumno(this);
            fragmentDialogAlumno.setCancelable(false);
            fragmentDialogAlumno.show(getSupportFragmentManager(),"Alta alumno");
        });
    }

    @Override
    public void onClickNueva(Object o) {
        alumnoImp.addAlumno((Alumno) o);
    }
}