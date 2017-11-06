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
                if (etnombre.getText().length() != 0)
                {
                    if(!validarNom(namestr))
                    {
                        etnombre.setError( "Formato del nombre invalido");

                    }
                }
            }
        });

        etapp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String namestr = etnombre.getText().toString();
                if (namestr.isEmpty()) {
                    etnombre.setError("Campo Nombre obligatorio");
                    etnombre.requestFocus();
                }
                else if (!validarNom(namestr))
                {
                    etnombre.setError("Formato del nombre invalido");
                    etnombre.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String appstr = etapp.getText().toString();
                if (etapp.getText().length()!=0)
                {
                    if (!validarNom(appstr))
                    {
                        etapp.setError( "Formato del nombre invalido");

                    }
                }
            }
        });

        etapm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String namestr = etnombre.getText().toString();
                String appstr = etapp.getText().toString();
                if (namestr.isEmpty()) {
                    etnombre.setError("Campo Nombre obligatorio");
                    etnombre.requestFocus();
                }
                else if (!validarNom(namestr))
                {
                    etnombre.setError("Formato de Nombre Invalido");
                    etnombre.requestFocus();
                }
                else if (appstr.isEmpty()) {
                    etapp.setError("Campo Nombre obligatorio");
                    etapp.requestFocus();
                }
                else if (!validarNom(appstr))
                {
                    etapp.setError("Formato de Apellido Invalido");
                    etapp.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                String apmstr = etapm.getText().toString();
                if (etapm.getText().length()!=0)
                {
                    if (!validarNom(apmstr))
                    {
                        etapm.setError( "Formato del nombre invalido");

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
                String namestr = etnombre.getText().toString();
                String appstr = etapp.getText().toString();
                String apmstr = etapm.getText().toString();
                if (namestr.isEmpty()) {
                    etnombre.setError("Campo Nombre obligatorio");
                    etnombre.requestFocus();
                }
                else if (!validarNom(namestr))
                {
                    etnombre.setError("Formato de Nombre Invalido");
                    etnombre.requestFocus();
                }
                else if (appstr.isEmpty()) {
                    etapp.setError("Campo Nombre obligatorio");
                    etapp.requestFocus();
                }
                else if (!validarNom(appstr))
                {
                    etapp.setError("Formato de Apellido Invalido");
                    etapp.requestFocus();
                }
                else if (apmstr.isEmpty()) {
                    etapm.setError("Campo Nombre obligatorio");
                    etapm.requestFocus();
                }

                else if (!validarNom(apmstr))
                {
                    etapm.setError("Formato deApellido Invalido");
                    etapm.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                String edadstr = etedad.getText().toString();
                if (edadstr.equals("0"))
                {
                    etedad.setError( "Edad invalida");
                }
                else if (edadstr.equals("00"))
                {
                    etedad.setError( "Edad invalida");
                }
            }
        });

        etemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String namestr = etnombre.getText().toString();
                String appstr = etapp.getText().toString();
                String apmstr = etapm.getText().toString();
                String edadstr = etedad.getText().toString();
                if (namestr.isEmpty()) {
                    etnombre.setError("Campo Nombre obligatorio");
                    etnombre.requestFocus();
                }
                else if (!validarNom(namestr))
                {
                    etnombre.setError("Formato de Nombre Invalido");
                    etnombre.requestFocus();
                }
                else if (appstr.isEmpty()) {
                    etapp.setError("Campo Nombre obligatorio");
                    etapp.requestFocus();
                }
                else if (!validarNom(appstr))
                {
                    etapp.setError("Formato de Apellido Invalido");
                    etapp.requestFocus();
                }
                else if (apmstr.isEmpty()) {
                    etapm.setError("Campo Nombre obligatorio");
                    etapm.requestFocus();
                }

                else if (!validarNom(apmstr))
                {
                    etapm.setError("Formato de Apellido Invalido");
                    etapm.requestFocus();
                }
                else if (edadstr.isEmpty())
                {
                    etedad.setError( "Campo vacío");
                    etedad.requestFocus();
                }

                else if (edadstr.isEmpty())
                {
                    etedad.setError( "Campo vacío");
                    etedad.requestFocus();
                }

                else if (etedad.getText().length() ==1)
                {
                    etedad.setError( "Formato de Edad invalida");
                    etedad.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String emailstr = etemail.getText().toString();
                String unamestr = etusuario.getText().toString();
                String em = helper.searchownemail(emailstr);
                if (em.equals(emailstr)) {
                    etemail.setError("El email ya esta siendo usado");
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
                String namestr = etnombre.getText().toString();
                String appstr = etapp.getText().toString();
                String apmstr = etapm.getText().toString();
                String edadstr = etedad.getText().toString();
                String emailstr = etemail.getText().toString();


                if (namestr.isEmpty()) {
                    etnombre.setError("Campo Nombre obligatorio");
                    etnombre.requestFocus();
                }
                else if (!validarNom(namestr))
                {
                    etnombre.setError("Formato de Nombre Invalido");
                    etnombre.requestFocus();
                }
                else if (appstr.isEmpty()) {
                    etapp.setError("Campo Nombre obligatorio");
                    etapp.requestFocus();
                }
                else if (!validarNom(appstr))
                {
                    etapp.setError("Formato de Apellido Invalido");
                    etapp.requestFocus();
                }
                else if (apmstr.isEmpty()) {
                    etapm.setError("Campo Nombre obligatorio");
                    etapm.requestFocus();
                }

                else if (!validarNom(apmstr))
                {
                    etapm.setError("Formato de Apellido Invalido");
                    etapm.requestFocus();
                }
               else if (edadstr.isEmpty())
                {
                    etedad.setError( "Campo vacío");
                    etedad.requestFocus();
                }

                else if (edadstr.isEmpty())
                {
                    etedad.setError( "Campo vacío");
                    etedad.requestFocus();
                }
               else if (etedad.getText().length() ==1)
                {
                    etedad.setError( "Formato de Edad invalida");
                    etemail.requestFocus();
                }

                else if (emailstr.isEmpty())
                {
                    etemail.setError( "Campo Email obligatorio");
                    etemail.requestFocus();
                }

                else if (!ValidacionEmail(emailstr))
                    {
                        etemail.setError("Formato de email invalido");
                        etemail.requestFocus();
                    }


            }

            @Override
            public void afterTextChanged(Editable s) {
                String unamestr = etusuario.getText().toString();
                String us = helper.searchUse(unamestr);
                if (us.equals(unamestr)) {
                    etusuario.setError("El nombre de usuario ya existe");

                }
            }
        });

        etTel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String namestr = etnombre.getText().toString();
                String appstr = etapp.getText().toString();
                String apmstr = etapm.getText().toString();
                String edadstr = etedad.getText().toString();
                String emailstr = etemail.getText().toString();
                String unamestr = etusuario.getText().toString();


                if (namestr.isEmpty()) {
                    etnombre.setError("Campo Nombre obligatorio");
                    etnombre.requestFocus();
                }
                else if (!validarNom(namestr))
                {
                    etnombre.setError("Formato de Nombre Invalido");
                    etnombre.requestFocus();
                }
                else if (appstr.isEmpty()) {
                    etapp.setError("Campo Nombre obligatorio");
                    etapp.requestFocus();
                }
                else if (!validarNom(appstr))
                {
                    etapp.setError("Formato de Apellido Invalido");
                    etapp.requestFocus();
                }
                else if (apmstr.isEmpty()) {
                    etapm.setError("Campo Nombre obligatorio");
                    etapm.requestFocus();
                }

                else if (!validarNom(apmstr))
                {
                    etapm.setError("Formato deApellido Invalido");
                    etapm.requestFocus();
                }
                else if (edadstr.isEmpty())
                {
                    etedad.setError( "Campo vacío");
                    etedad.requestFocus();
                }

                else if (etedad.getText().length() ==1)
                {
                    etedad.setError( "Formato de Edad invalida");
                    etedad.requestFocus();
                }

                else if (emailstr.isEmpty())
                {
                    etemail.setError( "Campo Email obligatorio");
                    etemail.requestFocus();
                }

                else if (!ValidacionEmail(emailstr))
                {
                    etemail.setError("Formato de email invalido");
                    etemail.requestFocus();
                }

                else if (unamestr.isEmpty())
                {
                    etusuario.setError( "Campo Usuario obligatorio");
                    etusuario.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                String telstr = etTel.getText().toString();
                switch (telstr)
                {
                    case "0":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "00000000":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case"0000000000":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "000":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "11111111":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "1111111111":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "22222222":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "2222222222":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "33333333":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "3333333333":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "44444444":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "4444444444":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "55555555":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "5555555555":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "66666666":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "6666666666":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "77777777":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "7777777777":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "88888888":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "8888888888":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "99999999":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                    case "9999999999":
                        etTel.setError("El numero de teléfeno no existe");
                        break;
                }

            }
        });

        etcont1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String namestr = etnombre.getText().toString();
                String appstr = etapp.getText().toString();
                String apmstr = etapm.getText().toString();
                String edadstr = etedad.getText().toString();
                String emailstr = etemail.getText().toString();
                String unamestr = etusuario.getText().toString();
                String telstr = etTel.getText().toString();
                if (namestr.isEmpty()) {
                    etnombre.setError("Campo Nombre obligatorio");
                    etnombre.requestFocus();
                }
                else if (!validarNom(namestr))
                {
                    etnombre.setError("Formato de Nombre Invalido");
                    etnombre.requestFocus();
                }
                else if (appstr.isEmpty()) {
                    etapp.setError("Campo Nombre obligatorio");
                    etapp.requestFocus();
                }
                else if (!validarNom(appstr))
                {
                    etapp.setError("Formato de Apellido Invalido");
                    etapp.requestFocus();
                }
                else if (apmstr.isEmpty()) {
                    etapm.setError("Campo Nombre obligatorio");
                    etapm.requestFocus();
                }

                else if (!validarNom(apmstr))
                {
                    etapm.setError("Formato de Apellido Invalido");
                    etapm.requestFocus();
                }
                else if (edadstr.isEmpty())
                {
                    etedad.setError( "Campo vacío");
                    etedad.requestFocus();
                }

                else if (etedad.getText().length() ==1)
                {
                    etedad.setError( "Formato de Edad invalida");
                    etedad.requestFocus();
                }

                else if (emailstr.isEmpty())
                {
                    etemail.setError( "Campo Email obligatorio");
                    etemail.requestFocus();
                }

                else if (!ValidacionEmail(emailstr))
                {
                    etemail.setError("Formato de email invalido");
                    etemail.requestFocus();
                }

                else if (unamestr.isEmpty())
                {
                    etusuario.setError( "Campo Usuario obligatorio");
                    etusuario.requestFocus();
                }

                else if (telstr.isEmpty())
                {
                    etTel.setError( "Campo Teléfono obligatorio");
                    etTel.requestFocus();
                }

                else if (telstr.length()<3 || telstr.length()>10)
                    {
                        etTel.setError("El numero de teléfeno no existe");
                        etTel.requestFocus();
                    }
                }
            @Override
            public void afterTextChanged(Editable s) {
                String cont1str = etcont1.getText().toString();
                switch (cont1str)
                {
                    case "0":
                        etcont1.setError("El numero de teléfeno no existe");
                        break;
                    case "00000000":
                        etcont1.setError("El numero de teléfeno no existe");
                        break;
                    case"0000000000":
                        etcont1.setError("El numero de teléfeno no existe");
                        break;
                    case "000":
                        etcont1.setError("El numero de teléfeno no existe");
                        break;
                }
            }
        });

        etcont2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String namestr = etnombre.getText().toString();
                String appstr = etapp.getText().toString();
                String apmstr = etapm.getText().toString();
                String edadstr = etedad.getText().toString();
                String emailstr = etemail.getText().toString();
                String unamestr = etusuario.getText().toString();

                String telstr = etTel.getText().toString();
                String cont1str = etcont1.getText().toString();
                if (namestr.isEmpty()) {
                    etnombre.setError("Campo Nombre obligatorio");
                    etnombre.requestFocus();
                }
                else if (!validarNom(namestr))
                {
                    etnombre.setError("Formato de Nombre Invalido");
                    etnombre.requestFocus();
                }
                else if (appstr.isEmpty()) {
                    etapp.setError("Campo Nombre obligatorio");
                    etapp.requestFocus();
                }
                else if (!validarNom(appstr))
                {
                    etapp.setError("Formato de Apellido Invalido");
                    etapp.requestFocus();
                }
                else if (apmstr.isEmpty()) {
                    etapm.setError("Campo Nombre obligatorio");
                    etapm.requestFocus();
                }

                else if (!validarNom(apmstr))
                {
                    etapm.setError("Formato de Apellido Invalido");
                    etapm.requestFocus();
                }
                else if (edadstr.isEmpty())
                {
                    etedad.setError( "Campo vacío");
                    etedad.requestFocus();
                }

                else if (etedad.getText().length() ==1)
                {
                    etedad.setError( "Formato de Edad invalida");
                    etedad.requestFocus();
                }

                else if (emailstr.isEmpty())
                {
                    etemail.setError( "Campo Email obligatorio");
                    etemail.requestFocus();
                }

                else if (!ValidacionEmail(emailstr))
                {
                    etemail.setError("Formato de email invalido");
                    etemail.requestFocus();
                }

                else if (unamestr.isEmpty())
                {
                    etusuario.setError( "Campo Usuario obligatorio");
                    etusuario.requestFocus();
                }

                else if (telstr.isEmpty())
                {
                    etTel.setError( "Campo Teléfono obligatorio");
                    etTel.requestFocus();
                }

                else if (telstr.length()<3 || telstr.length()>10)
                {
                    etTel.setError("El numero de teléfeno no existe");
                    etTel.requestFocus();

                }
                else if (cont1str.isEmpty())
                {
                    etcont1.setError( "Campo Contacto 1 obligatorio");
                    etcont1.requestFocus();
                }
                else if (cont1str.length()<8)
                {
                    etcont1.setError("El numero de teléfeno no existe");
                    etcont1.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                String cont2str = etcont2.getText().toString();
                switch (cont2str)
                {
                    case "0":
                        etcont2.setError("El numero de teléfeno no existe");
                        break;
                    case "00000000":
                        etcont2.setError("El numero de teléfeno no existe");
                        break;
                    case"0000000000":
                        etcont2.setError("El numero de teléfeno no existe");
                        break;
                    case "000":
                        etcont2.setError("El numero de teléfeno no existe");
                        break;
                }

            }
        });

        etcontraseña.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String namestr = etnombre.getText().toString();
                String appstr = etapp.getText().toString();
                String apmstr = etapm.getText().toString();
                String edadstr = etedad.getText().toString();
                String emailstr = etemail.getText().toString();
                String unamestr = etusuario.getText().toString();
                String telstr = etTel.getText().toString();
                String cont1str = etcont1.getText().toString();
                String cont2str = etcont2.getText().toString();

                if (namestr.isEmpty()) {
                    etnombre.setError("Campo Nombre obligatorio");
                    etnombre.requestFocus();
                }
                else if (!validarNom(namestr))
                {
                    etnombre.setError("Formato de Nombre Invalido");
                    etnombre.requestFocus();
                }
                else if (appstr.isEmpty()) {
                    etapp.setError("Campo Nombre obligatorio");
                    etapp.requestFocus();
                }
                else if (!validarNom(appstr))
                {
                    etapp.setError("Formato de Apellido Invalido");
                    etapp.requestFocus();
                }
                else if (apmstr.isEmpty()) {
                    etapm.setError("Campo Nombre obligatorio");
                    etapm.requestFocus();
                }

                else if (!validarNom(apmstr))
                {
                    etapm.setError("Formato de Apellido Invalido");
                    etapm.requestFocus();
                }
                else if (edadstr.isEmpty())
                {
                    etedad.setError( "Campo vacío");
                    etedad.requestFocus();
                }

                else if (etedad.getText().length() ==1)
                {
                    etedad.setError( "Formato de Edad invalida");
                    etedad.requestFocus();
                }

                else if (emailstr.isEmpty())
                {
                    etemail.setError( "Campo Email obligatorio");
                    etemail.requestFocus();
                }

                else if (!ValidacionEmail(emailstr))
                {
                    etemail.setError("Formato de email invalido");
                    etemail.requestFocus();
                }

                else if (unamestr.isEmpty())
                {
                    etusuario.setError( "Campo Usuario obligatorio");
                    etusuario.requestFocus();
                }

                else if (telstr.isEmpty())
                {
                    etTel.setError( "Campo Teléfono obligatorio");
                    etTel.requestFocus();
                }

                else if (telstr.length()<3 || telstr.length()>10)
                {
                    etTel.setError("El numero de teléfeno no existe");
                    etTel.requestFocus();

                }
                else if (cont1str.isEmpty())
                {
                    etcont1.setError( "Campo Contacto 1 obligatorio");
                    etcont1.requestFocus();
                }
                else if (cont1str.length()<8)
                {
                    etcont1.setError("El numero de teléfeno no existe");
                    etcont1.requestFocus();

                }
                else if (cont2str.isEmpty())
                {
                    etcont2.setError( "Campo Contacto 1 obligatorio");
                    etcont2.requestFocus();
                }
                else if (cont2str.length()<8)
                {
                    etcont2.setError("El numero de teléfeno no existe");
                    etcont2.requestFocus();

                }
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
                String namestr = etnombre.getText().toString();
                String appstr = etapp.getText().toString();
                String apmstr = etapm.getText().toString();

                String edadstr = etedad.getText().toString();
                String emailstr = etemail.getText().toString();
                String unamestr = etusuario.getText().toString();
                String em = helper.searchemail(unamestr);
                String us = helper.searchUse(unamestr);
                String telstr = etTel.getText().toString();
                String cont1str = etcont1.getText().toString();
                String cont2str = etcont2.getText().toString();
                String pass1str = etcontraseña.getText().toString();
                if (namestr.isEmpty()) {
                    etnombre.setError("Campo Nombre obligatorio");
                    etnombre.requestFocus();
                }
                else if (!validarNom(namestr))
                {
                    etnombre.setError("Formato de Nombre Invalido");
                    etnombre.requestFocus();
                }
                else if (appstr.isEmpty()) {
                    etapp.setError("Campo Nombre obligatorio");
                    etapp.requestFocus();
                }
                else if (!validarNom(appstr))
                {
                    etapp.setError("Formato de Apellido Invalido");
                    etapp.requestFocus();
                }
                else if (apmstr.isEmpty()) {
                    etapm.setError("Campo Nombre obligatorio");
                    etapm.requestFocus();
                }

                else if (!validarNom(apmstr))
                {
                    etapm.setError("Formato de Apellido Invalido");
                    etapm.requestFocus();
                }

                else if (etedad.getText().length() ==1)
                {
                    etedad.setError( "Formato de Edad invalida");
                    etedad.requestFocus();
                }

                else if (emailstr.isEmpty())
                {
                    etemail.setError( "Campo Email obligatorio");
                    etemail.requestFocus();
                }

                else if (!ValidacionEmail(emailstr))
                {
                    etemail.setError("Formato de email invalido");
                    etemail.requestFocus();
                }
                else if (em.equals(emailstr)) {
                    etemail.setError("El email ya esta siendo usado");
                    etemail.requestFocus();
                }
                else if (unamestr.isEmpty())
                {
                    etusuario.setError( "Campo Usuario obligatorio");
                    etusuario.requestFocus();
                }
                else if (us.equals(unamestr)) {
                    etusuario.setError("El nombre de usuario ya existe");
                    etusuario.requestFocus();

                }
                else if (telstr.isEmpty())
                {
                    etTel.setError( "Campo Teléfono obligatorio");
                    etTel.requestFocus();
                }

                else if (telstr.length()<3 || telstr.length()>10)
                {
                    etTel.setError("El numero de teléfeno no existe");
                    etTel.requestFocus();

                }
                else if (cont1str.isEmpty())
                {
                    etcont1.setError( "Campo Contacto 1 obligatorio");
                    etcont1.requestFocus();
                }
                else if (cont1str.length()<8)
                {
                    etcont1.setError("El numero de teléfeno no existe");
                    etcont1.requestFocus();

                }
                else if (cont2str.isEmpty())
                {
                    etcont2.setError( "Campo Contacto 1 obligatorio");
                    etcont2.requestFocus();
                }
                else if (cont2str.length()<8)
                {
                    etcont2.setError("El numero de teléfeno no existe");
                    etcont2.requestFocus();

                }
                else if (pass1str.isEmpty())
                {
                    etcontraseña.setError( "Campo Contraseña obligatorio");
                    etcontraseña.requestFocus();
                }

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

            if (!pass2str.equals(pass1str) || pass2str.isEmpty() || pass1str.isEmpty() || pass1str.length()<6
                    || !validarPass(pass1str) || cont2str.length() < 8 || cont1str.length() < 8
                    || telstr.length() < 3 || telstr.length() > 10 || us.equals(unamestr) || unamestr.isEmpty()
                    || em.equals(emailstr) || !ValidacionEmail(emailstr) || emailstr.isEmpty() || etedad.getText().length() == 1
                    || edadstr.equals("00") || edadstr.equals("0") || edadstr.equals("000") || edadstr.isEmpty() || apmstr.isEmpty() || !validarNom(apmstr)
                    || appstr.isEmpty() || namestr.isEmpty() || !validarNom(namestr) || !validarNom(appstr) || genero.equals("-seleccione-") || pass1str.length()<6)
            {
                Toast.makeText(this, "Los campos marcados con '*' son obligatorios ",
                        Toast.LENGTH_LONG).show();

            }

            else {
                int edadnumero = Integer.parseInt(edadstr);

                if(edadnumero > 90 || edadnumero <= 18 )
                {
                        etedad.setError( "Edad invalida");
                        etedad.requestFocus();
                }
                else//***********************************REGISTRO****************************/
                {

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







