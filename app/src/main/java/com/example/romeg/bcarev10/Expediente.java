package com.example.romeg.bcarev10;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class Expediente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expediente);
    }

    public void onExpedienteClick (View v)
    {
        if (v.getId() == R.id.btnsummary)
        {

        }
        else if(v.getId() == R.id.btncalcular)
        {
            Intent i = new Intent(Expediente.this, Riesgo.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.btncompartir)
        {

        }
    }

}
