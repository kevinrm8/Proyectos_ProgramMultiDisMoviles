package com.example.matriculacionalumno.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.matriculacionalumno.R;
import com.example.matriculacionalumno.database.entidades.Alumno;
import com.example.matriculacionalumno.database.imp.AlumnoImp;

public class FragmentDialogAlumno extends DialogFragment implements DialogInterface.OnClickListener {

    private TextView tvTitulo;
    private EditText txtNombre;
    private EditText txtApellidos;
    private EditText txtDni;
    private Alumno a;
    private DialogListener.ListenerNueva listenerNueva;
    private DialogListener.ListenerEditar listenerEditar;

    public FragmentDialogAlumno(Alumno a, DialogListener.ListenerEditar listenerEditar){
        this.a = a;
        this.listenerEditar = listenerEditar;
    }

    public FragmentDialogAlumno(DialogListener.ListenerNueva listenerNueva){
        this.a = null;
        this.listenerNueva = listenerNueva;
    }

    public AlertDialog createSimpleDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.fragment_dialog_alumno, null);

        builder.setView(v);
        builder.setPositiveButton("Añadir",this);
        builder.setNegativeButton("Cancelar",this);

        txtNombre = (EditText) v.findViewById(R.id.txtNombre);
        txtDni = (EditText) v.findViewById(R.id.txtDni);
        txtApellidos = (EditText) v.findViewById(R.id.txtApellidos);
        tvTitulo = (TextView) v.findViewById(R.id.tvTitulo);
        if(a!=null){
            txtNombre.setText(a.getNombre());
            txtApellidos.setText(a.getApellidos());
            txtDni.setText(a.getDni());
            txtDni.setEnabled(false);
        }
        tvTitulo.setText(getTag());
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
                if(a==null){
                    a = new Alumno(txtDni.getText().toString(),txtNombre.getText().toString(),txtApellidos.getText().toString());
                    listenerNueva.onClickNueva(a);
                }else{
                    a.setNombre(txtNombre.getText().toString());
                    a.setApellidos(txtApellidos.getText().toString());
                    listenerEditar.onClickEditar(a);
                }
                break;
            default:
                break;
        }
    }
}
