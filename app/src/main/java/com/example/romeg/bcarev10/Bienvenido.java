package com.example.romeg.bcarev10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Bienvenido extends AppCompatActivity {


    AlertDialogManager alert = new AlertDialogManager();
    DBHelper helper = new DBHelper(this);

    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        session = new SessionManagement(getApplicationContext());
       /* Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();*/

        if (session.isLoggedIn()) {
            //here, pref is the instance of your preference manager
            Intent intent = new Intent(Bienvenido.this, Usuario.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

            finish();

        }
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

    public void onBackPressed()
    {
     finish();
    }

}
