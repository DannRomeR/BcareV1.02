package com.example.romeg.bcarev10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Registro extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);

    EditText etnombre, etapp, etapm, etemail, etusuario, etcontraseña, etconfirmar, etTel, etedad, etcont1, etcont2;
    TextView gen;
    Spinner spGeneroR;
    Button btnreg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        String[] gener = {"-seleccione-","Masculino","Femenino"};
        btnreg = (Button) findViewById(R.id.btnregistrarreg);
        spGeneroR =(Spinner) findViewById(R.id.spgeneroR);
        spGeneroR.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gener));

        etnombre = (EditText) findViewById(R.id.txtnombrereg);
        etapp = (EditText) findViewById(R.id.txtappreg);
        etapm = (EditText) findViewById(R.id.txtapmreg);
        etemail = (EditText) findViewById(R.id.txtemailreg);
        etusuario = (EditText) findViewById(R.id.txtusuarioreg);
        etedad = (EditText) findViewById(R.id.txtedadreg);
        etTel = (EditText) findViewById(R.id.txtelefonoreg);
        etcont1 = (EditText) findViewById(R.id.txcont1reg);
        etcont2 = (EditText) findViewById(R.id.txcont2reg);
        etcontraseña = (EditText) findViewById(R.id.txtprimerpassreg);
        etconfirmar = (EditText) findViewById(R.id.txtsegundopassreg);
        gen = (TextView) findViewById(R.id.textView2);


        etnombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String namestr = etnombre.getText().toString();

                if(!validarNom(namestr))
                {
                    etnombre.setError( "Formato incorrecto");

                }

            }
        });

        etapp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String appstr = etapp.getText().toString();

                if (!validarNom(appstr))
                {
                    etapp.setError( "Formato incorrecto");

                }

            }
        });

        etapm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String apmstr = etapm.getText().toString();
                if (etapm.getText().length()!=0)
                {
                    if (!validarNom(apmstr))
                    {
                        etapm.setError( "Formato incorrecto");

                    }
                }
            }
        });

        etedad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String edadstr = etedad.getText().toString();
                switch (edadstr) {
                    case "0":
                        etedad.setError("Formato incorrecto");

                        break;
                    case "00":
                        etedad.setError("Formato incorrecto");

                        break;
                }
                if (etedad.getText().length() ==1)
                {
                    etedad.setError( "Formato incorrecto");

                }
            }
        });

        etemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String emailstr = etemail.getText().toString();
                String em = helper.searchownemail(emailstr);

                if(!ValidacionEmail(emailstr)) {
                    etemail.setError("Formato de correo electrónico incorrecto");

                }
                else if (em.equals(emailstr)) {
                    etemail.setError("Ya existe un usuario con este email");
                    etemail.requestFocus();
                }
            }
        });

        etusuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {
                String unamestr = etusuario.getText().toString();
                String us = helper.searchUse(unamestr);
                if (us.equals(unamestr)) {
                    etusuario.setError("Ya existe un usuario con este nombre");

                }
            }
        });

        etTel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String telstr = etTel.getText().toString();
                switch (telstr)
                {
                    case "0":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "00000000":
                        etTel.setError("Formato incorrecto");
                        break;
                    case"0000000000":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "000":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "11111111":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "1111111111":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "22222222":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "2222222222":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "33333333":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "3333333333":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "44444444":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "4444444444":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "55555555":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "5555555555":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "66666666":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "6666666666":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "77777777":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "7777777777":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "88888888":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "8888888888":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "99999999":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "9999999999":
                        etTel.setError("Formato incorrecto");
                        break;
                }
                if (telstr.length()<3 || telstr.length()>10)
                {
                    etTel.setError("Formato incorrecto");
                    etTel.requestFocus();
                }

            }
        });

        etcont1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
            @Override
            public void afterTextChanged(Editable s) {
                String cont1str = etcont1.getText().toString();
                switch (cont1str)
                {
                    case "0":
                        etcont1.setError("Formato incorrecto");
                        break;
                    case "00000000":
                        etcont1.setError("Formato incorrecto");
                        break;
                    case"0000000000":
                        etcont1.setError("Formato incorrecto");
                        break;
                    case "000":
                        etcont1.setError("Formato incorrecto");
                        break;
                }
                if (cont1str.length()<8)
                {
                    etcont1.setError("Formato incorrecto");
                    etcont1.requestFocus();
                }
            }
        });

        etcont2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String cont2str = etcont2.getText().toString();
                switch (cont2str)
                {
                    case "0":
                        etcont2.setError("Formato incorrecto");
                        break;
                    case "00000000":
                        etcont2.setError("Formato incorrecto");
                        break;
                    case"0000000000":
                        etcont2.setError("Formato incorrecto");
                        break;
                    case "000":
                        etcont2.setError("Formato incorrecto");
                        break;
                }
                if (cont2str.length()<8)
                {
                    etcont2.setError("Formato incorrecto");
                    etcont2.requestFocus();

                }

            }
        });

        etcontraseña.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass1str = etcontraseña.getText().toString();
                if (etcontraseña.getText().length() != 0)
                {
                    if (pass1str.length()<6 || !validarPass(pass1str))
                    {
                        etcontraseña.setError("Formato de contraseña incorrecto");
                    }
                }
            }
        });

        etconfirmar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass1str = etcontraseña.getText().toString();
                String pass2str = etconfirmar.getText().toString();
                if (etconfirmar.getText().length() != 0)
                {
                    if (!pass2str.equals(pass1str))
                    {
                        etconfirmar.setError("Las contraseñas no coinciden");
                    }
                }

            }
        });
    }

    public void onSignUpClick(View v)
    {
        try
        {
            String namestr = etnombre.getText().toString();
            String appstr = etapp.getText().toString();
            String apmstr = etapm.getText().toString();

            String edadstr = etedad.getText().toString();
            String emailstr = etemail.getText().toString();
            String unamestr = etusuario.getText().toString();
            String em = helper.searchownemail(emailstr);
            String us = helper.searchUse(unamestr);
            String telstr = etTel.getText().toString();
            String cont1str = etcont1.getText().toString();
            String cont2str = etcont2.getText().toString();
            String pass1str = etcontraseña.getText().toString();
            String pass2str = etconfirmar.getText().toString();

            String genero = (spGeneroR.getSelectedItem().toString());

            if (v.getId() == R.id.btnregistrarreg)
            {

                if (namestr.isEmpty() || appstr.isEmpty() || apmstr.isEmpty() || emailstr.isEmpty() || unamestr.isEmpty() || pass1str.isEmpty() || pass2str.isEmpty() || edadstr.isEmpty() || telstr.isEmpty() || genero.equals("-seleccione-"))
                {
                    Toast.makeText(this, "Los campos marcados con '*' son obligatorios ",
                            Toast.LENGTH_SHORT).show();
                    if(namestr.isEmpty())
                    {
                        etnombre.setError("Complete este campo");
                    }
                    if(appstr.isEmpty())
                    {
                        etapp.setError("Complete este campo");
                    }
                    if (apmstr.isEmpty())
                    {
                        etapm.setError("Complete este campo");
                    }

                    if (edadstr.isEmpty())
                    {
                        etedad.setError("Complete este campo");
                    }
                    if (emailstr.isEmpty())
                    {
                        etemail.setError("Complete este campo");
                    }
                    if (unamestr.isEmpty())
                    {
                        etusuario.setError("Complete este campo");
                    }
                    if (telstr.isEmpty())
                    {
                        etTel.setError("Complete este campo");
                    }
                    if (cont1str.isEmpty())
                    {
                        etcont1.setError("Complete este campo");
                    }
                    if (cont2str.isEmpty())
                    {
                        etcont2.setError("Complete este campo");
                    }
                    if (pass1str.isEmpty())
                    {
                        etcontraseña.setError("Complete este campo");
                    }
                    if (pass2str.isEmpty())
                    {
                        etconfirmar.setError("Complete este campo");
                    }

                }

                else {

                    int edadnumero = Integer.parseInt(edadstr);
                    if( !validarNom(namestr) || !validarNom(appstr) || !validarNom(apmstr)
                            || !ValidacionEmail(emailstr) || telstr.length()<3
                            || telstr.length()>10 || cont1str.length()<8 || cont2str.length()<8 || pass1str.length()<6
                            || !validarPass(pass1str) || !pass2str.equals(pass1str)|| edadnumero > 90 || edadnumero <= 18)
                    {

                        if (edadnumero > 90 || edadnumero <= 18)
                        {
                            etedad.setError( "Formato incorrecto");
                            etedad.requestFocus();

                        }
                        if (!validarNom(namestr))
                        {
                            etnombre.setError( "Formato incorrecto");
                        }
                        if (!validarNom(appstr))
                        {
                            etapp.setError( "Formato incorrecto");
                        }
                        if (!validarNom(apmstr))
                        {
                            etapm.setError( "Formato incorrecto");
                        }
                        if (!ValidacionEmail(emailstr) || em.equals(emailstr))
                        {
                            etemail.setError( "Formato incorrecto");
                        }
                        if (us.equals(unamestr))
                        {
                            etusuario.setError( "Formato incorrecto");
                        }
                        if (telstr.length()<3 || telstr.length()>10)
                        {
                            etTel.setError( "Formato incorrecto");
                        }
                        if (cont1str.length()<8)
                        {
                            etcont1.setError( "Formato incorrecto");
                        }
                        if (cont2str.length()<8)
                        {
                            etcont2.setError( "Formato incorrecto");
                        }
                        if (pass1str.length()<6 || !validarPass(pass1str))
                        {
                            etcontraseña.setError( "Formato incorrecto");
                        }
                        if (!pass2str.equals(pass1str))
                        {
                            etconfirmar.setError( "Formato incorrecto");
                        }

                    }
                    else//***********************************REGISTRO****************************/
                    {

                        if(em.equals(emailstr) || us.equals(unamestr))
                        {
                            if (em.equals(emailstr)) {
                                etemail.setError("El email ya esta siendo usado");
                                Toast.makeText(this, "Este email ya esta siendo usado",
                                        Toast.LENGTH_LONG).show();
                            }
                            if (us.equals(unamestr)) {
                                etusuario.setError("El nombre de usuario ya existe");
                                Toast.makeText(this, "El usuario ya existe",
                                        Toast.LENGTH_LONG).show();
                            }

                        }
                        else {
                            int edadint = Integer.parseInt(edadstr);
                            int telint = Integer.parseInt(telstr);
                            int cont1int = Integer.parseInt(cont1str);
                            int cont2int = Integer.parseInt(cont2str);
                            int numa = (int)(Math.random()*999)+1;
                            String numastr = String.valueOf(numa);
                            String nomS = namestr.substring(0,1);
                            String appS = appstr.substring(0,1);
                            String apmS = apmstr.substring(0,1);

                            String  numPac = nomS + appS + apmS + edadstr +numastr;

                            //insert the details in database
                            Contact c = new Contact();
                            c.setName(namestr);
                            c.setApp(appstr);
                            c.setApm(apmstr);
                            c.setEmail(emailstr);
                            c.setUname(unamestr);
                            c.setPass(pass1str);
                            c.setEdad(edadint);
                            c.setTel(telint);
                            c.setCont1(cont1int);
                            c.setCont2(cont2int);
                            c.setGenero(genero);

                            c.setFum("Sin dato");
                            c.setMed("Sin dato");
                            c.setColt("Sin dato");
                            c.setColh("Sin dato");
                            c.setPresu(120);
                            c.setPunt(120);
                            c.setRisk(120);
                            c.setNumPac(numPac);
                            helper.insertContact(c);

                            Toast pass = Toast.makeText(Registro.this, "Registro exitoso", Toast.LENGTH_LONG);
                            pass.show();

                            Intent i = new Intent(Registro.this, Bienvenido.class);
                            startActivity(i);

                        }

                    }
                }
            }
        }
        catch(Exception e)
        {
            Toast pass = Toast.makeText(Registro.this, "Ocurrio un error, registre nuevamente los datos ", Toast.LENGTH_LONG);
            pass.show();
            finish();
        }


    }

        public boolean ValidacionEmail(String email)
        {
            String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    +"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern = Pattern.compile(emailPattern);
            Matcher matcher = pattern.matcher(email);


            return matcher.matches();
        }
        public boolean validarNom(String name)
        {
            Pattern pattern;
            Matcher matcher;
            final String NOMBRE_PATTERN = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ]+$";
            pattern = Pattern.compile(NOMBRE_PATTERN);
            matcher = pattern.matcher(name);

            return matcher.matches();
        }
        public boolean validarPass(String pass)
        {
            // ^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$
            // ^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$
            Pattern pattern;
            Matcher matcher;
            final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!.-_])(?=\\S+$).{4,}$";
            pattern = Pattern.compile(PASSWORD_PATTERN);
            matcher = pattern.matcher(pass);

            return matcher.matches();
        }

    }







