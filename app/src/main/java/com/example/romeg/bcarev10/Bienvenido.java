package com.example.romeg.bcarev10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Bienvenido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
    }

    public void onWelcomeClick(View v)
    {
        if (v.getId()== R.id.btniniciarsesionB)
        {
            Intent i = new Intent(Bienvenido.this, MainActivity.class);
            startActivity(i);
        }
        else if (v.getId()==R.id.btnregistrarseB)
        {
            Intent j = new Intent(Bienvenido.this, Registro.class);
            startActivity(j);
        }
    }

}
