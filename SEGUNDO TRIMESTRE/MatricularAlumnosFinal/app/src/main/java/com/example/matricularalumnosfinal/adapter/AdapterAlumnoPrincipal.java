package com.example.matriculacionalumno.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matriculacionalumno.R;
import com.example.matriculacionalumno.activity.ActivityAlumnosDetalle;
import com.example.matriculacionalumno.database.entidades.Alumno;
import com.example.matriculacionalumno.database.imp.AlumnoImp;
import com.example.matriculacionalumno.dialog.DialogListener;
import com.example.matriculacionalumno.dialog.FragmentDialogAlumno;

import java.util.List;

public class AdapterAlumnoPrincipal extends RecyclerView.Adapter  implements DialogListener.ListenerEditar {

    private Context context;
    private List<Alumno> alumnos;

    public AdapterAlumnoPrincipal(Context context, List<Alumno> alumnos) {
        this.context = context;
        this.alumnos = alumnos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.item_layout_alumno,null);
        return new Holder(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Alumno alumno = alumnos.get(position);
        Holder holderAlumno = (Holder) holder;
        holderAlumno.tvNombre.setText(alumno.getNombre());
        holderAlumno.tvApellidos.setText(alumno.getApellidos());
        holderAlumno.tvDni.setText(alumno.getDni());

        holderAlumno.imbBorrar.setOnClickListener( i -> {
            AlumnoImp lab = new AlumnoImp(context);
            lab.deleteAlumno(alumno);
            alumnos.remove(position);
            notifyItemRemoved(position);
        });
        holderAlumno.imbEditar.setOnClickListener( i -> {
            FragmentDialogAlumno fragmentDialogAlumno = new FragmentDialogAlumno(alumno,this);
            fragmentDialogAlumno.setCancelable(false);
            fragmentDialogAlumno.show(((AppCompatActivity) context).getSupportFragmentManager(),"Modificar alumno");
        });
        holderAlumno.imbDetalle.setOnClickListener( i -> {
            Intent intent = new Intent(context, ActivityAlumnosDetalle.class);
            intent.putExtra("dni", alumno.getDni());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    @Override
    public void onClickEditar(Object o) {
        Alumno a = (Alumno) o;
        AlumnoImp lab = new AlumnoImp(context);
        lab.updateAlumno(a);
        notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder{

        TextView tvNombre;
        TextView tvApellidos;
        TextView tvDni;
        ImageButton imbBorrar;
        ImageButton imbEditar;
        ImageButton imbDetalle;

        public Holder(@NonNull View vi) {
            super(vi);

            tvNombre = (TextView) vi.findViewById(R.id.tvNombre);
            tvApellidos = (TextView) vi.findViewById(R.id.tvApellidos);
            tvDni = (TextView) vi.findViewById(R.id.tvDni);

            imbBorrar = (ImageButton) vi.findViewById(R.id.imbBorrar);
            imbEditar = (ImageButton) vi.findViewById(R.id.imbEditar);
            imbDetalle = (ImageButton) vi.findViewById(R.id.imbDetalle);
        }
    }
}