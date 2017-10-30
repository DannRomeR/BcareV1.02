package com.example.romeg.bcarev10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Recuperar extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);
    EditText emailR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);


    }

    public void onRecuperarClick(View v)
    {
        emailR = (EditText) findViewById(R.id.txtrecuperar);
        String b = emailR.getText().toString();
        if(v.getId() == R.id.enviar)
        {
            String a = helper.searchownemail(b);
            String a1 = helper.searchbyemail(a);
            if (b.isEmpty())
            {
                Toast.makeText(this, "Los campos marcados con '*' son obligatorios ",
                        Toast.LENGTH_SHORT).show();
            }
            else if (a.equals(b))
            {
                Intent i = new Intent(Recuperar.this, Generar.class);
                i.putExtra("Username", a1);
                startActivity(i);
                finish();
            }
            else
            {
                Toast.makeText(this, "El correo no existe",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
