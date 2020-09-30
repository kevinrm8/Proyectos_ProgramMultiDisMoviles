package com.example.sumaaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        final TextView Resultado = (TextView) findViewById(R.id.VerResultado);
        final Button Volver = (Button) findViewById(R.id.Volver);

        Bundle  miBundleRecoger = getIntent().getExtras();
        Resultado.setText(miBundleRecoger.getString("ResultadoSuma"));


        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vueltaIntent= new Intent();
                Bundle vueltaBundle=new Bundle();
                vueltaIntent.putExtras(vueltaBundle);
                setResult(RESULT_OK, vueltaIntent);
                finish();
            }
        });
    }

    /* Proceso para entender el ciclo de vida */
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart-Pantalla2", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"onResume-Pantalla2", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        Toast.makeText(this, "onPause-Pantalla2", Toast.LENGTH_SHORT).show();
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"onStop-Pantalla2", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"onRestart-Pantalla2", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        Toast.makeText(this,"onDestroy-Pantalla2", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}