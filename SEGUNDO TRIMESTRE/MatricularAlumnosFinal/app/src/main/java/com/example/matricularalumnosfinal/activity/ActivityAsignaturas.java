package com.example.matriculacionalumno.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.matriculacionalumno.R;
import com.example.matriculacionalumno.adapter.AdapterAsignaturaPrincipal;
import com.example.matriculacionalumno.database.entidades.Asignatura;
import com.example.matriculacionalumno.database.imp.AsignaturaImp;
import com.example.matriculacionalumno.dialog.DialogListener;
import com.example.matriculacionalumno.dialog.FragmentDialogAsignatura;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ActivityAsignaturas extends AppCompatActivity implements DialogListener.ListenerNueva{

    private RecyclerView recyclerView;
    private AdapterAsignaturaPrincipal adapter;
    private AsignaturaImp asignaturaImp;

    private FloatingActionButton fabAltaAsignatura;
    private List<Asignatura> asignaturas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);

        recyclerView = (RecyclerView) findViewById(R.id.rvAsignaturas);
        asignaturaImp = new AsignaturaImp(this);
        asignaturas = asignaturaImp.getAsignaturas();
        adapter = new AdapterAsignaturaPrincipal(this,asignaturas);
        recyclerView.setAdapter(adapter);

        fabAltaAsignatura = (FloatingActionButton) findViewById(R.id.fabAltaAsignatura);
        fabAltaAsignatura.setOnClickListener(b -> {
            FragmentDialogAsignatura fragmentDialogAsignatura = new FragmentDialogAsignatura(this);
            fragmentDialogAsignatura.setCancelable(false);
            fragmentDialogAsignatura.show(getSupportFragmentManager(),"Alta asignatura");
        });
    }

    @Override
    public void onClickNueva(Object o) {
        asignaturaImp.addAsignatura((Asignatura) o);
    }
}