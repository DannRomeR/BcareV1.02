package com.example.romeg.bcarev10;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Editar extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    EditText etnombree, etappe, etapme, etemaile, etusuarioe, etedad, ettel, etcont1, etcont2;
    Spinner spGeneroE;
    Button btnrec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        String[] gener = {"-seleccione-","Masculino","Femenino"};
        btnrec = (Button) findViewById(R.id.btnguardarDatos);
        spGeneroE =(Spinner) findViewById(R.id.spgeneroEd);
        spGeneroE.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gener));

        String usernamed = getIntent().getStringExtra("Username");
        String named = getIntent().getStringExtra("Name");
        String appd = getIntent().getStringExtra("App");
        String apmd = getIntent().getStringExtra("Apm");
        String edadd = getIntent().getStringExtra("Edad");
        String emaild = getIntent().getStringExtra("Email");
        String teld = getIntent().getStringExtra("Tel");
        String cont1d = getIntent().getStringExtra("Cont1");
        String cont2d = getIntent().getStringExtra("Cont2");
        String numpacd = getIntent().getStringExtra("Numpac");



        etnombree = (EditText) findViewById(R.id.txtnombrerec);
        etnombree.setText(named);
        etappe = (EditText) findViewById(R.id.txtapprec);
        etappe.setText(appd);
        etapme = (EditText) findViewById(R.id.txtapmrec);
        etapme.setText(apmd);

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

    }

    public void onEditarClick(View v) {
        if (v.getId() == R.id.btnguardarDatos) {
            String usernamed2 = getIntent().getStringExtra("Username");
            String namee = etnombree.getText().toString();
            String appe = etappe.getText().toString();
            String apme = etapme.getText().toString();
            String usere = etusuarioe.getText().toString();
            String edadee = etedad.getText().toString();
            String emaile = etemaile.getText().toString();
            String tele = ettel.getText().toString();
            String cont1e = etcont1.getText().toString();
            String cont2e = etcont2.getText().toString();
            int edadnumero = Integer.parseInt(edadee);
            String genero = (spGeneroE.getSelectedItem().toString());

           /* int numa = (int)(Math.random()*999)+1;
            String numastr = String.valueOf(numa);
            String nomS = namee.substring(0,1);
            String appS = appe.substring(0,1);
            String apmS = apme.substring(0,1);

            String  numPac = nomS + appS + apmS + edadee +numastr;*/
            String  numPac = helper.searchnumpac(usernamed2);


            if (namee.isEmpty() || appe.isEmpty() || apme.isEmpty() || emaile.isEmpty() || usere.isEmpty() || edadee.isEmpty() || tele.isEmpty() || !validarNom(namee) || !ValidacionEmail(emaile) || edadnumero > 90 || edadnumero <= 18 || tele.length()<3 || cont1e.length()<8 || cont2e.length()<8 || genero.equals("-seleccione-")) {
                Toast.makeText(this, "Los campos marcados con '*' son obligatorios ",
                        Toast.LENGTH_SHORT).show();
                if (namee.isEmpty()) {
                    etnombree.setError("Campo obligatorio");
                }if (appe.isEmpty()){
                    etappe.setError("Campo obligatorio");
                }if (apme.isEmpty()){
                    etapme.setError("Campo obligatorio");
                }if (edadee.isEmpty()) {
                    etedad.setError("Campo obligatorio");
                } if (emaile.isEmpty()) {
                    etemaile.setError("Campo obligatorio");
                } if (usere.isEmpty()) {
                    etusuarioe.setError("Campo obligatorio");
                } if (tele.isEmpty()) {
                    ettel.setError("Campo obligatorio");
                } if (!validarNom(namee)){
                    etnombree.setError("Formato incorrecto");
                    etnombree.requestFocus();
                } if (!validarNom(appe)){
                    etappe.setError("Formato incorrecto");
                    etappe.requestFocus();
                } if (!validarNom(apme)){
                    etapme.setError("Formato incorrecto");
                    etapme.requestFocus();
                } if (!ValidacionEmail(emaile)){
                    etemaile.setError("Formato incorrecto");
                    etemaile.requestFocus();
                } if(edadnumero > 90 || edadnumero < 20) {
                    etedad.setError( "Formato incorrecto");
                    etedad.requestFocus();
                } if (tele.length()<3){
                    ettel.setError("Formato incorrecto");
                    ettel.requestFocus();
                } if (cont1e.length()<8){
                    etcont1.setError("Formato incorrecto");
                    etcont1.requestFocus();
                } if (cont2e.length()<8){
                    etcont2.setError("Formato incorrecto");
                    etcont2.requestFocus();
                }

            } else {

                    int edadinte = Integer.parseInt(edadee);
                    int telinte = Integer.parseInt(tele);
                    int cont1inte = Integer.parseInt(cont1e);
                    int cont2inte = Integer.parseInt(cont2e);
                    DBHelper db = new DBHelper(getApplicationContext());
                    String Mensaje = db.actualizarData(usernamed2, usere, namee, appe, apme, edadinte, emaile, telinte, cont1inte, cont2inte, genero);
                    Toast.makeText(getApplicationContext(), Mensaje, Toast.LENGTH_LONG).show();

                    if (!Mensaje.isEmpty()) {
                        String agee = String.valueOf(edadinte);
                        String telE = String.valueOf(telinte);
                        String cont1E = String.valueOf(cont1inte);
                        String Cont2E = String.valueOf(cont2inte);

                        Toast.makeText(this, "La operación exitosa",
                                Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Editar.this, DatosP.class);
                        i.putExtra("Username", usere);
                        i.putExtra("Name", namee);
                        i.putExtra("App", appe);
                        i.putExtra("Apm", appe);
                        i.putExtra("Edad", agee);
                        i.putExtra("Email", emaile);
                        i.putExtra("Tel", telE);
                        i.putExtra("Cont1", cont1E);
                        i.putExtra("Cont2", Cont2E);
                        i.putExtra("Gen", genero);
                        i.putExtra("Gen", genero);
                        i.putExtra("Numpac", numPac);
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
        final String NOMBRE_PATTERN = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ]+$";
        pattern = Pattern.compile(NOMBRE_PATTERN);
        matcher = pattern.matcher(name);

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

        Intent i = new Intent(Editar.this, DatosP.class);
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
