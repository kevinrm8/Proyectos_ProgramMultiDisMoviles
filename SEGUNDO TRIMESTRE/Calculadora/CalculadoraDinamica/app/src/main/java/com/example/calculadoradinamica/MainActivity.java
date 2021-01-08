package com.example.calculadoradinamica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction transaction;
    Fragment fragmentvacio,fragmentcalculadora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentvacio = new vacio();
        fragmentcalculadora = new CalculadoraDinamica();

        getSupportFragmentManager().beginTransaction().add(R.id.ContenedorFragmentos,fragmentvacio).commit();
    }

    public void onClick(View view) {
    transaction = getSupportFragmentManager().beginTransaction();

    switch(view.getId()){
            case R.id.Mostrar:
                transaction.replace(R.id.ContenedorFragmentos,fragmentcalculadora).commit();
                break;
            case R.id.Esconder:
                transaction.replace(R.id.ContenedorFragmentos,fragmentvacio).commit();
                break;
        }
    }
}