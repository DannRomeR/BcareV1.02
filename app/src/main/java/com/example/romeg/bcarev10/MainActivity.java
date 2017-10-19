package com.example.romeg.bcarev10;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    EditText etusario, etpassword;
    Button registrar, iniciar, recuperar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrar = (Button) findViewById(R.id.btnregistrar);
        recuperar = (Button) findViewById(R.id.btnrecuperar);
        iniciar = (Button) findViewById(R.id.btniniciar);
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


     public void onButtonClick(View v)
    {

        if (v.getId() == R.id.btniniciar)
        {

            EditText a = (EditText) findViewById(R.id.user);
            String str = a.getText().toString();

            EditText b = (EditText) findViewById(R.id.contraseña);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);

            if(str.isEmpty() || pass.isEmpty())
            {
                Toast temp = Toast.makeText(MainActivity.this, "Rellene los campos que se le piden", Toast.LENGTH_SHORT);
                temp.show();
            }
            else
            {
                if(pass.equals(password))
                {
                    Intent i = new Intent(MainActivity.this, Usuario.class);
                    i.putExtra("Username",str);
                    startActivity(i);
                }
                else
                {
                    Toast temp = Toast.makeText(MainActivity.this, "Usuario y/o contraseña incorrecto", Toast.LENGTH_SHORT);
                    temp.show();
                }
            }
        }
        else if (v.getId() == R.id.btnregistrar)
        {
            Intent j = new Intent(MainActivity.this, Registro.class);
            startActivity(j);
        }
        else if (v.getId() == R.id.btnrecuperar)
        {
            Intent k = new Intent(MainActivity.this, Recuperar.class);
            startActivity(k);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return onOptionsItemSelected(item);
    }

}
