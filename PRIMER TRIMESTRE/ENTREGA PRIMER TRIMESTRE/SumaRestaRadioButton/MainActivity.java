package com.example.sumarestaradiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         final EditText numero1 = (EditText) findViewById(R.id.Numero1);
         final EditText numero2 = (EditText) findViewById(R.id.Numero2);
        final TextView resultado = (TextView) findViewById(R.id.resultado);

        RadioGroup grupo = (RadioGroup) findViewById(R.id.rg);

        //Comprobar cual esta seleccionado
        grupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

               int num,num2,suma,resta;
               // Si el boton seleccionado es la suma se sumara
                if(group.getCheckedRadioButtonId()==R.id.rb_suma){
                   num = Integer.parseInt(String.valueOf(numero1.getText()));
                   num2 = Integer.parseInt(String.valueOf(numero2.getText()));

                   suma = num+num2;
                    resultado.setText(String.valueOf(suma));

                }
                // Si el boton seleccionado es la resta se resta
                if(group.getCheckedRadioButtonId()==R.id.rb_resta){
                    num = Integer.parseInt(String.valueOf(numero1.getText()));
                    num2 = Integer.parseInt(String.valueOf(numero2.getText()));
                    resta = num-num2;
                    resultado.setText(String.valueOf(resta));

                }
            }
        });




    }
}