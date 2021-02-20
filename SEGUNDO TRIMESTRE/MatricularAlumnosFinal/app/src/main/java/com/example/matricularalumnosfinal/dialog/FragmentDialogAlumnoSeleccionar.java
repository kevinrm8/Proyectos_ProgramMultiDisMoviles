package com.example.matriculacionalumno.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.matriculacionalumno.database.entidades.Asignatura;

import java.util.List;

public class FragmentDialogAlumnoSeleccionar extends DialogFragment implements DialogInterface.OnClickListener{
    private DialogListener.ListenerNueva listenerNueva;
    private String codigoSeleccionado;
    private List<Asignatura> asignaturas;
    private String[] lista;

    public FragmentDialogAlumnoSeleccionar(DialogListener.ListenerNueva listenerNueva, List<Asignatura> asignaturas) {
        this.listenerNueva = listenerNueva;
        this.asignaturas = asignaturas;
    }

    public AlertDialog createSimpleDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        lista = new String[asignaturas.size()];

        for(int i=0;i<asignaturas.size();i++){
            lista[i] = asignaturas.get(i).toString();
        }
        codigoSeleccionado = lista[0].split(",")[0];

        builder.setTitle("Añade una asignatura");
        builder.setSingleChoiceItems(lista,0,this);
        builder.setPositiveButton("Añadir",this);
        builder.setNegativeButton("Cancelar",this);
        return builder.create();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createSimpleDialog();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i){
            case -2:
                dismiss();
                break;
            case -1:
                listenerNueva.onClickNueva(codigoSeleccionado);
                break;
            default:
                codigoSeleccionado = lista[i].split(",")[0];
                break;
        }
    }
}
