package com.example.romeg.bcarev10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Generar extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar);
        String usern = getIntent().getStringExtra("Username");
    }

    public void onGenerarClick(View v)
    {
        String usern = getIntent().getStringExtra("Username");
        EditText b = (EditText) findViewById(R.id.txtpassw1);
        String passC = b.getText().toString();

        EditText p = (EditText) findViewById(R.id.txtpassw2);
        String passC2 = p.getText().toString();



        if(v.getId() == R.id.btnenviar)
        {
            if(passC.isEmpty()||passC2.isEmpty())
            {
                Toast.makeText(this, "Los campos marcados con '*' son obligatorios ",
                        Toast.LENGTH_LONG).show();
                if (passC.isEmpty())
                {
                    b.setError("Campo obligatorio");
                }
                if (passC2.isEmpty())
                {
                    p.setError("Campo obligatorio");
                }
            }
            else
            {
                if (passC.length()<6 && !validarPass(passC))
                {
                    b.setError("Formato de contraseña incorrecto");
                    b.requestFocus();
                }
                else
                {

                        DBHelper db = new DBHelper(getApplicationContext());
                        String Mensaje = db.actualizaPass(usern,passC2);
                        Toast.makeText(getApplicationContext(), Mensaje,Toast.LENGTH_LONG).show();

                        if(!Mensaje.isEmpty())
                        {
                            b.setText("");
                            p.setText("");
                        }
                        else
                        {
                            Toast.makeText(this, "La operación no se realizó correctamente, intente de nuevo o reinicie la aplicación",
                                    Toast.LENGTH_LONG).show();
                        }
                        finish();

                }
            }
        }
    }

    public boolean validarPass(String pass)
    {
        // ^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$
        // ^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(pass);

        return matcher.matches();
    }

}
