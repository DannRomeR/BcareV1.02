package com.example.romeg.bcarev10;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Registro extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);

    EditText etnombre, etemail, etusuario, etcontraseña, etconfirmar, etTel, etedad, etcont1, etcont2;

    CheckBox chkgeneroM, chkgeneroF;
    Button btnreg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnreg = (Button) findViewById(R.id.btnregistrarreg);

    }


    public void onSignUpClick(View v)
    {

        etnombre = (EditText) findViewById(R.id.txtnombrereg);
        etemail = (EditText) findViewById(R.id.txtemailreg);
        etusuario = (EditText) findViewById(R.id.txtusuarioreg);
        chkgeneroF = (CheckBox) findViewById(R.id.rbfemenino);
        chkgeneroM = (CheckBox) findViewById(R.id.rbmasculino);
        etedad = (EditText) findViewById(R.id.txtedadreg);
        etTel = (EditText) findViewById(R.id.txtelefonoreg);
        etcont1 = (EditText) findViewById(R.id.txcont1reg);
        etcont2 = (EditText) findViewById(R.id.txcont2reg);
        etcontraseña = (EditText) findViewById(R.id.txtprimerpassreg);
        etconfirmar = (EditText) findViewById(R.id.txtsegundopassreg);


        String namestr = etnombre.getText().toString();
        String emailstr = etemail.getText().toString();
        String unamestr = etusuario.getText().toString();
        String pass1str = etcontraseña.getText().toString();
        String pass2str = etconfirmar.getText().toString();
        String edadstr = etedad.getText().toString();
        String telstr = etTel.getText().toString();
        String cont1str = etcont1.getText().toString();
        String cont2str = etcont2.getText().toString();



        if (v.getId() == R.id.btnregistrarreg)
        {

            if (namestr.isEmpty() || emailstr.isEmpty() || unamestr.isEmpty() || pass1str.isEmpty() || pass2str.isEmpty() || edadstr.isEmpty() || telstr.isEmpty())
            {
                Toast.makeText(this, "Los campos marcados con '*' son obligatorios ",
                        Toast.LENGTH_LONG).show();
                if (namestr.isEmpty())
                {
                    etnombre.setError( "Campo Nombre obligatorio");
                }
                if (!chkgeneroM.isChecked() || !chkgeneroF.isChecked())
                {
                    chkgeneroF.setError("Campo Genero obligatorio");
                    chkgeneroM.setError("Campo Genero obligatorio");
                }
                if (edadstr.isEmpty())
                {
                    etedad.setError( "Campo Edad obligatorio");
                }
                if (emailstr.isEmpty())
                {
                    etemail.setError( "Campo Email obligatorio");
                }
                if (unamestr.isEmpty())
                {
                    etusuario.setError( "Campo Usuario obligatorio");
                }
                if (telstr.isEmpty())
                {
                    etTel.setError( "Campo Telefono obligatorio");
                }
                if (pass1str.isEmpty())
                {
                    etcontraseña.setError( "Campo Contraseña obligatorio");
                }
                if (pass2str.isEmpty())
                {
                    etconfirmar.setError( "Campo Contraseña obligatorio");
                }
            }

            else {
                int edadnumero = Integer.parseInt(edadstr);

                if(!pass1str.equals(pass2str) || (chkgeneroM.isChecked() && chkgeneroF.isChecked()) || !validarNom(namestr) || edadnumero > 90 || edadnumero <= 18 || !ValidacionEmail(emailstr) || telstr.length()<8 || cont1str.length()<8 || cont2str.length()<8 || pass1str.length()<6 || !validarPass(pass1str))
                {
                    if (!pass1str.equals(pass2str)) {
                        //popup msg
                        Toast.makeText(this, "Contraseñas no coinciden ",
                                Toast.LENGTH_SHORT).show();
                    }
                    if (chkgeneroM.isChecked() && chkgeneroF.isChecked()) {
                        Toast.makeText(this, "Seleccione unicamente un campo ",
                                Toast.LENGTH_LONG).show();
                        chkgeneroF.setError("Seleccione unicamente un campo");
                        chkgeneroM.setError("Seleccione unicamente un campo");
                    }
                    if(!validarNom(namestr))
                    {
                        etnombre.setError( "Formato del nombre invalido");
                        etnombre.requestFocus();
                    }
                    if(edadnumero > 90 || edadnumero <= 18)
                    {
                        etedad.setError( "Edad invalida");
                        etedad.requestFocus();
                    }
                    if (!ValidacionEmail(emailstr))
                    {
                        etemail.setError("Formato de email invalido");
                        etemail.requestFocus();
                    }
                    if (telstr.length()<8)
                    {
                        etTel.setError("El numero de telefeno no existe");
                        etTel.requestFocus();
                    }
                    if (cont1str.length()<8)
                    {
                        etcont1.setError("El numero de telefeno no existe");
                        etcont1.requestFocus();
                    }
                    if (cont2str.length()<8)
                    {
                        etcont2.setError("El numero de telefeno no existe");
                        etcont2.requestFocus();
                    }
                    if (pass1str.length()<6 || !validarPass(pass1str))
                    {
                        etcontraseña.setError("Formato de contraseña incorrecto");
                        etcontraseña.requestFocus();
                    }
                }

                else//***********************************REGISTRO****************************/
                {
                    String em = helper.searchemail(unamestr);
                    String us = helper.searchUse(unamestr);
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

                        //insert the details in database
                        Contact c = new Contact();
                        c.setName(namestr);
                        c.setEmail(emailstr);
                        c.setUname(unamestr);
                        c.setPass(pass1str);
                        c.setEdad(edadint);
                        c.setTel(telint);
                        c.setCont1(cont1int);
                        c.setCont2(cont2int);


                        if (chkgeneroM.isChecked()) {
                            c.setGenero("Masculino");
                            chkgeneroF.setChecked(false);

                        } else if (chkgeneroF.isChecked()) {
                            c.setGenero("Femenino");
                            chkgeneroM.setChecked(false);
                        }

                        c.setFum("Sin dato");
                        c.setMed("Sin dato");
                        c.setColt("Sin dato");
                        c.setColh("Sin dato");
                        c.setPresu(120);
                        c.setPunt(120);
                        c.setRisk(120);
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
            final String NOMBRE_PATTERN = "^[a-zA-Z]+$";
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
            final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
            pattern = Pattern.compile(PASSWORD_PATTERN);
            matcher = pattern.matcher(pass);

            return matcher.matches();
        }

    }







