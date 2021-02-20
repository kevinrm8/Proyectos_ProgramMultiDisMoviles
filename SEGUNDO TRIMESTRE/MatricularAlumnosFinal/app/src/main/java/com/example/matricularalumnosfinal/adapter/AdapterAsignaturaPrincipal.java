package com.example.matriculacionalumno.adapter;

import android.content.Context;
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
import com.example.matriculacionalumno.database.entidades.Asignatura;
import com.example.matriculacionalumno.database.imp.AsignaturaImp;
import com.example.matriculacionalumno.dialog.DialogListener;
import com.example.matriculacionalumno.dialog.FragmentDialogAsignatura;

import java.util.List;

public class AdapterAsignaturaPrincipal extends RecyclerView.Adapter implements DialogListener.ListenerEditar {

    private Context context;
    private List<Asignatura> asignaturas;
    private AsignaturaImp lab;

    public AdapterAsignaturaPrincipal(Context context, List<Asignatura> asignaturas) {
        this.context = context;
        this.asignaturas = asignaturas;
        lab = new AsignaturaImp(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.item_layout_asignatura,null);
        return new Holder(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Asignatura asignatura = asignaturas.get(position);
        Holder holderAsignatura = (Holder) holder;
        holderAsignatura.tvCodNombre.setText(asignatura.getCodigo() + " " + asignatura.getNombre());
        holderAsignatura.tvNumAlumnos.setText(asignatura.getNumAlumnos() + " alumnos matriculados.");

        holderAsignatura.imbBorrar.setOnClickListener( i -> {
            List<Asignatura> asignaturasAsignadas = lab.getAsignaturasQueEstenAsignadas();
            if(!asignaturasAsignadas.contains(asignatura)){
                lab.deleteAsignatura(asignatura);
                asignaturas.remove(position);
                notifyItemRemoved(position);
            }else{
                Toast.makeText(context,"La asignatura está asignada a algún alumno", Toast.LENGTH_LONG).show();
            }

        });
        holderAsignatura.imbEditar.setOnClickListener( i -> {
            FragmentDialogAsignatura fragmentDialogAsignatura = new FragmentDialogAsignatura(asignatura,this);
            fragmentDialogAsignatura.setCancelable(false);
            fragmentDialogAsignatura.show(((AppCompatActivity) context).getSupportFragmentManager(),"Modificar asignatura");
        });
    }

    @Override
    public int getItemCount() {
        return asignaturas.size();
    }

    @Override
    public void onClickEditar(Object o) {
        lab.updateAsignatura((Asignatura) o);
        notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder{

        TextView tvCodNombre;
        TextView tvNumAlumnos;
        ImageButton imbBorrar;
        ImageButton imbEditar;

        public Holder(@NonNull View vi) {
            super(vi);

            tvCodNombre = (TextView) vi.findViewById(R.id.tvCodNombre);
            tvNumAlumnos = (TextView) vi.findViewById(R.id.tvNumAlumnos);
            imbBorrar = (ImageButton) vi.findViewById(R.id.imbBorrar);
            imbEditar = (ImageButton) vi.findViewById(R.id.imbEditar);

        }
    }
}
