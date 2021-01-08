package com.example.ejerradiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView lblMensaje =(TextView) findViewById(R.id.LblSeleccion);
        final RadioGroup rg = (RadioGroup)findViewById(R.id.gruporb);

        rg.clearCheck();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            //Segun el RadioButton seleccionado se mostrara el ID del mismo por pantalla
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String textoOpcion="";
                if(group.getCheckedRadioButtonId()==R.id.radio1){
                    textoOpcion+="OPCION 1 con ID: "+checkedId;
                }
                if(group.getCheckedRadioButtonId()==R.id.radio2){
                    textoOpcion+="OPCION 2 con ID: "+checkedId;
                }
                //TextView donde se muestra texto
                lblMensaje.setText(textoOpcion);
            }
        });


    }
}