package com.example.romeg.bcarev10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Cambiar extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    EditText etpass1, etpass2;
    Button btnchange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar);

        String usernamed = getIntent().getStringExtra("Username");
        etpass1 = (EditText) findViewById(R.id.passw1);

        etpass2 = (EditText) findViewById(R.id.passw2);


    }

    public void onCambiarClick(View v)
    {
        if(v.getId()==R.id.btncambiar){



            String usernamed = getIntent().getStringExtra("Username");

            EditText b = (EditText) findViewById(R.id.passw1);
            String passC = b.getText().toString();

            EditText p = (EditText) findViewById(R.id.passw2);
            String passC2 = p.getText().toString();

            String passwordC = helper.searchPass(usernamed);


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
                if (passwordC.length()<6 && !validarPass(passwordC))
                {
                    etpass1.setError("Formato de contraseña incorrecto");
                    etpass1.requestFocus();
                }
                else
                {
                    if(passC.equals(passwordC))
                    {
                        DBHelper db = new DBHelper(getApplicationContext());
                        String Mensaje = db.actualizaPass(usernamed,passC2);
                        Toast.makeText(getApplicationContext(), Mensaje,Toast.LENGTH_LONG).show();

                        if(!Mensaje.isEmpty())
                        {
                            b.setText("");
                            p.setText("");
                        }
                        finish();
                    }
                    else
                    {
                        Toast.makeText(this, "La contraseña no coincide",
                                Toast.LENGTH_LONG).show();
                        b.setError("Contraseña incorrecta");
                    }
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

    public void onBackPressed()
    {

        String str = getIntent().getStringExtra("Username");
        String userna = helper.searchPass(str);
        String named = helper.searchname(str);
        String appd = helper.searchapp(str);
        String apmd = helper.searchapm(str);
        String edadd = helper.searchedad(str);
        String emaild = helper.searchemail(str);
        String teld = helper.searchtel(str);
        String cont1d = helper.searchcont1(str);
        String cont2d = helper.searchcont2(str);
        String gend = helper.searchgen(str);
        String numpacd = helper.searchnumpac(str);

        Intent i = new Intent(Cambiar.this, DatosP.class);
        i.putExtra("Username", str);
        i.putExtra("Name", named);
        i.putExtra("App", appd);
        i.putExtra("Apm", apmd);
        i.putExtra("Edad", edadd);
        i.putExtra("Email", emaild);
        i.putExtra("Tel", teld);
        i.putExtra("Cont1", cont1d);
        i.putExtra("Cont2", cont2d);
        i.putExtra("Gen", gend);
        i.putExtra("Numpac", numpacd);
        startActivity(i);
        finish();
    }

}
