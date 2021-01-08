package com.example.ejerc2checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox chkBoxCycling;
    CheckBox chkBoxTeaching;
    CheckBox chkBoxBlogging;
    TextView txtHobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialUISetup();

    }

    public void initialUISetup()
    {
        chkBoxCycling = (CheckBox) findViewById(R.id.chkBoxCycling);
        chkBoxTeaching = (CheckBox) findViewById(R.id.chkBoxTeaching);
        chkBoxBlogging = (CheckBox) findViewById(R.id.chkBoxBlogging);

        TextView txtHobby = (TextView)findViewById(R.id.txtHobby);
        chkBoxCycling.setOnCheckedChangeListener(new myCheckBoxChangeClicker());
        chkBoxTeaching.setOnCheckedChangeListener(new myCheckBoxChangeClicker());
        chkBoxBlogging.setOnCheckedChangeListener(new myCheckBoxChangeClicker());

    }

    class myCheckBoxChangeClicker implements CheckBox.OnCheckedChangeListener
    {         @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        String aux="";

        if(chkBoxCycling.isChecked()){
            aux +="Cycling ";
        }
        if(chkBoxTeaching.isChecked()){
            aux +="Teaching ";
        }
        if(chkBoxBlogging.isChecked()){
            aux +="Blogging ";
        }
        /*
            if(isChecked)
         {
                if(buttonView==chkBoxCycling)
                {
                    aux += "Cycling";
                   //showTextNotification("Cycling");
                }
                if(buttonView==chkBoxTeaching)
                {
                    aux +="Teaching";
                 //showTextNotification("Teaching");
                }
                if(buttonView==chkBoxBlogging)
                {   aux +="Blogging";
                   //showTextNotification("Blogging");
                }
            }*/
            showTextNotification(aux);

    }
    }// clase interna

    public void showTextNotification(String msgToDisplay)
    { Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();
    }
}