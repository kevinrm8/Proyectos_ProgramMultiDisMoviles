package com.example.ejercicioentregaperro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends Activity implements OnClickListener {

    private Button show_pet_button;
    private TextView texto_opinion;
    private TextView page_link;

    public final static String EXTRA_NOMBRE = null;
    public final static int OPINION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obteniendo una instancia del boton show_pet_button
        show_pet_button = (Button)findViewById(R.id.show_pet_button);

        texto_opinion = (TextView)findViewById(R.id.respuesta_texto);
        page_link =(TextView)findViewById(R.id.page_link);

        //Registrando la escucha sobre la actividad Main
        show_pet_button.setOnClickListener(this);

        page_link.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){

        switch (v.getId()){

            case R.id.show_pet_button:{
                //Iniciando la actividad Visor
                Intent intent = new Intent(this,Visor.class);
                intent.putExtra(EXTRA_NOMBRE,"Perro Salchicha");
                startActivityForResult(intent,OPINION_REQUEST_CODE);
                break;
            }
            case R.id.page_link:{
                Uri webpage = Uri.parse("http://hermosaprogramacion.blogspot.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

                // Verificar si hay aplicaciones disponibles
                PackageManager packageManager = getPackageManager();
                List activities = packageManager.queryIntentActivities(webIntent, 0);
                boolean isIntentSafe = activities.size() > 0;

                // Si hay, entonces ejecutamos la actividad
                if (isIntentSafe) {
                    startActivity(webIntent);
                }

                break;


            }
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == OPINION_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {

                String result=data.getStringExtra("opinion");

                texto_opinion.setText("Tu opinion fué "+result);


            }
        }

    }
}