package com.example.matriculacionalumno.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matriculacionalumno.R;
import com.example.matriculacionalumno.database.entidades.AlumnoRelAsignatura;
import com.example.matriculacionalumno.database.entidades.Asignatura;
import com.example.matriculacionalumno.database.imp.AlumnoRelAsignaturaImp;
import com.example.matriculacionalumno.database.imp.AsignaturaImp;

import java.util.List;

public class AdapterAsignaturaDetalle extends RecyclerView.Adapter {

    private Context context;
    private List<Asignatura> asignaturas;
    private String dni;

    public AdapterAsignaturaDetalle(Context context, List<Asignatura> asignaturas, String dni) {
        this.context = context;
        this.asignaturas = asignaturas;
        this.dni = dni;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.item_layout_asignatura_detalle,null);
        return new Holder(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Asignatura asignatura = asignaturas.get(position);
        Holder holderAsignatura = (Holder) holder;
        holderAsignatura.tvCodNombre.setText(asignatura.getCodigo() + " " + asignatura.getNombre());

        holderAsignatura.imbBorrar.setOnClickListener( i -> {
            AlumnoRelAsignaturaImp lab = new AlumnoRelAsignaturaImp(context);
            AlumnoRelAsignatura relacion = new AlumnoRelAsignatura(dni,asignatura.getCodigo());
            lab.deleteAlumnoConAsignaturas(relacion);
            asignaturas.remove(position);
            notifyItemRemoved(position);

            AsignaturaImp asignaturaImp = new AsignaturaImp(context);
            asignatura.setNumAlumnos(asignatura.getNumAlumnos()-1);
            asignaturaImp.updateAsignatura(asignatura);
        });
    }

    @Override
    public int getItemCount() {
        return asignaturas.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{

        TextView tvCodNombre;
        ImageButton imbBorrar;

        public Holder(@NonNull View vi) {
            super(vi);

            tvCodNombre = (TextView) vi.findViewById(R.id.tvCodNombre);
            imbBorrar = (ImageButton) vi.findViewById(R.id.imbBorrar);
        }
    }
}
