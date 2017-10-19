package com.example.romeg.bcarev10;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Editar extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    EditText etnombree, etemaile, etusuarioe, etedad, ettel, etcont1, etcont2;
    CheckBox etgeneM, etgeneF;
    Button btnrec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        btnrec = (Button) findViewById(R.id.btnguardarDatos);

        String usernamed = getIntent().getStringExtra("Username");
        String named = getIntent().getStringExtra("Name");
        String edadd = getIntent().getStringExtra("Edad");
        String emaild = getIntent().getStringExtra("Email");
        String teld = getIntent().getStringExtra("Tel");
        String cont1d = getIntent().getStringExtra("Cont1");
        String cont2d = getIntent().getStringExtra("Cont2");
        String gened = getIntent().getStringExtra("Gen");


        etnombree = (EditText) findViewById(R.id.txtnombrerec);
        etnombree.setText(named);
        etemaile = (EditText) findViewById(R.id.txtemailrec);
        etemaile.setText(emaild);
        etusuarioe = (EditText) findViewById(R.id.txtusuariorec);
        etusuarioe.setText(usernamed);
        etedad = (EditText) findViewById(R.id.txtedadrec);
        etedad.setText(edadd);
        ettel = (EditText) findViewById(R.id.txttelefonorec);
        ettel.setText(teld);
        etcont1 = (EditText) findViewById(R.id.txtcontonerec);
        etcont1.setText(cont1d);
        etcont2 = (EditText) findViewById(R.id.txtcontworec);
        etcont2.setText(cont2d);

        if (gened.equals("Masculino") )
        {
            etgeneM = (CheckBox) findViewById(R.id.rbmasculino);
            etgeneM.setChecked(true);

        }else if (gened.equals("Femenino")){
            etgeneF = (CheckBox) findViewById(R.id.rbfemenino);
            etgeneF.setChecked(true);

        }

    }

    public void onEditarClick(View v) {
        if (v.getId() == R.id.btnguardarDatos) {
            String usernamed2 = getIntent().getStringExtra("Username");
            String namee = etnombree.getText().toString();
            String usere = etusuarioe.getText().toString();
            String edadee = etedad.getText().toString();
            String emaile = etemaile.getText().toString();
            String tele = ettel.getText().toString();
            String cont1e = etcont1.getText().toString();
            String cont2e = etcont2.getText().toString();
            int edadnumero = Integer.parseInt(edadee);

            if (namee.isEmpty() || emaile.isEmpty() || usere.isEmpty() || edadee.isEmpty() || tele.isEmpty() || !validarNom(namee) || !ValidacionEmail(emaile) || edadnumero > 90 || edadnumero <= 18 || tele.length()<8 || cont1e.length()<8 || cont2e.length()<8 ) {
                Toast.makeText(this, "Los campos marcados con '*' son obligatorios ",
                        Toast.LENGTH_LONG).show();
                if (namee.isEmpty()) {
                    etnombree.setError("Campo Nombre obligatorio");
                } if (edadee.isEmpty()) {
                    etedad.setError("Campo Edad obligatorio");
                } if (emaile.isEmpty()) {
                    etemaile.setError("Campo Email obligatorio");
                } if (usere.isEmpty()) {
                    etusuarioe.setError("Campo Usuario obligatorio");
                } if (tele.isEmpty()) {
                    ettel.setError("Campo Telefono obligatorio");
                } if (!validarNom(namee)){
                    etnombree.setError("Formato del nombre invalido");
                    etnombree.requestFocus();
                } if (!ValidacionEmail(emaile)){
                    etemaile.setError("Formato del email invalido");
                    etemaile.requestFocus();
                } if(edadnumero > 90 || edadnumero <= 18) {
                    etedad.setError( "Edad invalida");
                    etedad.requestFocus();
                } if (tele.length()<8){
                    ettel.setError("El numero de telefeno no existe");
                    ettel.requestFocus();
                } if (cont1e.length()<8){
                    etcont1.setError("El numero de telefeno no existe");
                    etcont1.requestFocus();
                } if (cont2e.length()<8){
                    etcont2.setError("El numero de telefeno no existe");
                    etcont2.requestFocus();
                }

            } else {

                    String generoE = "";
                    if (etgeneM.isChecked()) {
                        generoE = "Masculino";

                    } else if (etgeneF.isChecked()) {
                        generoE = "Femenino";
                    }

                    int edadinte = Integer.parseInt(edadee);
                    int telinte = Integer.parseInt(tele);
                    int cont1inte = Integer.parseInt(cont1e);
                    int cont2inte = Integer.parseInt(cont2e);
                    DBHelper db = new DBHelper(getApplicationContext());
                    String Mensaje = db.actualizarData(usernamed2, usere, namee, edadinte, emaile, telinte, cont1inte, cont2inte, generoE);
                    Toast.makeText(getApplicationContext(), Mensaje, Toast.LENGTH_LONG).show();

                    if (!Mensaje.isEmpty()) {
                        String agee = String.valueOf(edadinte);
                        String telE = String.valueOf(telinte);
                        String cont1E = String.valueOf(cont1inte);
                        String Cont2E = String.valueOf(cont2inte);

                        Intent i = new Intent(Editar.this, DatosP.class);
                        i.putExtra("Username", usere);
                        i.putExtra("Name", namee);
                        i.putExtra("Edad", agee);
                        i.putExtra("Email", emaile);
                        i.putExtra("Tel", telE);
                        i.putExtra("Cont1", cont1E);
                        i.putExtra("Cont2", Cont2E);
                        i.putExtra("Gen", generoE);
                        startActivity(i);

                        finish();
                    } else {
                        Toast.makeText(this, "La operación no se realizó correctamente, intente de nuevo o reinicie la aplicación",
                                Toast.LENGTH_LONG).show();
                    }
                    finish();
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
