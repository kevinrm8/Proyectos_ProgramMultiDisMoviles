package com.example.matriculacionalumno.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.matriculacionalumno.R;
import com.example.matriculacionalumno.database.entidades.Asignatura;

public class FragmentDialogAsignatura extends DialogFragment implements DialogInterface.OnClickListener {

    private TextView tvTitulo;
    private EditText txtNombre;
    private EditText txtCodigo;
    private Asignatura a;
    private DialogListener.ListenerNueva listenerNueva;
    private DialogListener.ListenerEditar listenerEditar;

    public FragmentDialogAsignatura(Asignatura a, DialogListener.ListenerEditar listenerEditar){
        this.a = a;
        this.listenerEditar = listenerEditar;
    }

    public FragmentDialogAsignatura(DialogListener.ListenerNueva listenerNueva){
        this.a = null;
        this.listenerNueva = listenerNueva;
    }

    public AlertDialog createSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.fragment_dialog_asignatura, null);

        builder.setView(v);
        builder.setPositiveButton("Añadir",this);
        builder.setNegativeButton("Cancelar",this);

        txtNombre = (EditText) v.findViewById(R.id.txtNombre);
        txtCodigo = (EditText) v.findViewById(R.id.txtCodigo);
        tvTitulo = (TextView) v.findViewById(R.id.tvTitulo);

        if(a!=null){
            txtNombre.setText(a.getNombre());
            txtCodigo.setText(a.getCodigo());
            txtCodigo.setEnabled(false);
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
                    a = new Asignatura(txtCodigo.getText().toString(),txtNombre.getText().toString());
                    listenerNueva.onClickNueva(a);
                }else{
                    a.setNombre(txtNombre.getText().toString());
                    listenerEditar.onClickEditar(a);
                }
                break;
            default:
                break;
        }
    }

}
