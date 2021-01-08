package com.example.ejercicioentregaperro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Visor extends AppCompatActivity implements View.OnClickListener {

    TextView nombre;
    Button botonEnviar ;
    RadioGroup opiniones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor);

        //Obteniendo la instancia del TextView
         nombre = (TextView) findViewById(R.id.nombre_imagen);
         opiniones = (RadioGroup) findViewById(R.id.radioGroup);
         botonEnviar = (Button) findViewById(R.id.enviar_opi);

//Obteniendo la instancia del Intent
        Intent intent = getIntent();
//Extrayendo el extra de tipo cadena
        String name = intent.getStringExtra(MainActivity.EXTRA_NOMBRE);
//Seteando el valor del extra en el TextView
        nombre.setText(name);

        botonEnviar.setOnClickListener(this);
    }

    public void onClick (View v){
        //Obtener del radiobutton seleccionado actualmente
        RadioButton RBSelec = (RadioButton) findViewById(opiniones.getCheckedRadioButtonId());

        String opinion = RBSelec.getText().toString();

        Intent respuesta = new Intent();

        respuesta.putExtra("opinion",opinion);

        setResult(RESULT_OK,respuesta);

        finish();

    }
}