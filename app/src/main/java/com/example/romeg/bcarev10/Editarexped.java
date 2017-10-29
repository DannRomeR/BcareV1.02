package com.example.romeg.bcarev10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import agency.tango.materialintroscreen.listeners.IFinishListener;

public class Editarexped extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);

    TextView tvnomEx, tvappEx, tvapmEx, tvedadEx, tvemailEx, tvpresure,  tvnumpacEx;
    Spinner spGenEx, spFumEx,spmedEx,spColtEx, spColhEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarexped);

        String usernamed = getIntent().getStringExtra("Username");
        String named = getIntent().getStringExtra("Name");
        String edadd = getIntent().getStringExtra("Edad");
        String appd = getIntent().getStringExtra("App");
        String apmd = getIntent().getStringExtra("Apm");

        String emaild = getIntent().getStringExtra("Email");
        String gened = getIntent().getStringExtra("Gen");
        String fumE = getIntent().getStringExtra("Fum");
        String medE = getIntent().getStringExtra("Med");
        String coltE = getIntent().getStringExtra("Colt");
        String colhE = getIntent().getStringExtra("Colh");
        String presE = getIntent().getStringExtra("Presu");
        String numpacE = getIntent().getStringExtra("Numpac");
        String[] gener = {"-seleccione-","Masculino","Femenino"};
        String[] colesteroTot = {"-seleccione-","<160","160-199","200-239","240-279",">=280"};
        String[] colesterolHDL = {"-seleccione-",">=60","50-59","40-49","30-39","<30"};
        String[] valoresfumador = {"-seleccione-","sí","no"};
        String[] valoresDiabetes = {"-seleccione-","sí","no"};

        tvnomEx = (EditText) findViewById(R.id.txtnombreEx);
        tvnomEx.setText(named);
        tvappEx = (EditText) findViewById(R.id.txtappEx);
        tvappEx.setText(appd);
        tvapmEx = (EditText) findViewById(R.id.txtapmEx);
        tvapmEx.setText(apmd);

        tvedadEx = (EditText) findViewById(R.id.txtedadEx);
        tvedadEx.setText(edadd);
        tvemailEx = (EditText) findViewById(R.id.txtemailEx);
        tvemailEx.setText(emaild);
        tvpresure = (EditText) findViewById(R.id.etpresionEx);
        tvpresure.setText(presE);
        spGenEx =(Spinner) findViewById(R.id.spgeneroExp);
        spGenEx.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gener));
        spFumEx = (Spinner) findViewById(R.id.spFumadorEx);
        spFumEx.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoresfumador));
        spmedEx = (Spinner) findViewById(R.id.spDiabetesEx);
        spmedEx.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoresDiabetes));
        spColhEx = (Spinner) findViewById(R.id.spcolesterolHDLEx);
        spColhEx.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colesterolHDL));
        spColtEx = (Spinner) findViewById(R.id.spcolesterol1Ex);
        spColtEx.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colesteroTot));
    }

    public void onEditarEClick(View v)
    {
        if(v.getId() == R.id.btnguardarDatos)
        {
            String usernamed2 = getIntent().getStringExtra("Username");
            String namee = tvnomEx.getText().toString();
            String appe = tvappEx.getText().toString();
            String apme = tvapmEx.getText().toString();

            String edadee = tvedadEx.getText().toString();
            String emaile = tvemailEx.getText().toString();
            String presure = tvpresure.getText().toString();
            String gene = (spGenEx.getSelectedItem().toString());
            String fum = (spFumEx.getSelectedItem().toString());
            String med = (spmedEx.getSelectedItem().toString());
            String colesterol = (spColtEx.getSelectedItem().toString());
            String colesteHDL = (spColhEx.getSelectedItem().toString());

            int edadnumero = Integer.parseInt(edadee);
            int presurenumero = Integer.parseInt(presure);

            int numa = (int)(Math.random()*999)+1;
            String numastr = String.valueOf(numa);
            String nomS = namee.substring(0,1);
            String appS = appe.substring(0,1);
            String apmS = apme.substring(0,1);

            String  numPac = nomS + appS + apmS + edadee +numastr;


            if (namee.isEmpty() || appe.isEmpty() || apme.isEmpty() || edadee.isEmpty() || emaile.isEmpty() || presure.isEmpty() ||gene.equals("-seleccione-") || fum.equals("-seleccione-") || med.equals("-seleccione-") || colesterol.equals("-seleccione-") || colesteHDL.equals("-seleccione-"))
            {
                Toast.makeText(this, "Los campos marcados con '*' son obligatorios ",
                        Toast.LENGTH_SHORT).show();
                if (namee.isEmpty()) {
                    tvnomEx.setError("Campo Nombre obligatorio");
                } if (appe.isEmpty()) {
                    tvappEx.setError("Campo Nombre obligatorio");
                } if (apme.isEmpty()) {
                    tvapmEx.setError("Campo Nombre obligatorio");
                }
                if (edadee.isEmpty()){
                    tvedadEx.setError("Campo Edad obligatorio");
                } if (emaile.isEmpty()){
                    tvemailEx.setError("Campo Email obligatorio");
                } if (presure.isEmpty()) {
                    tvpresure.setError("Campo Presión obligatorio");
                }
            }else {
                if (!validarNom(namee) || !validarNom(appe) || !validarNom(apme) || !ValidacionEmail(emaile) || edadnumero > 90 || edadnumero <= 18){
                    if(!validarNom(namee))
                    {
                        tvnomEx.setError( "Formato del nombre invalido");
                        tvnomEx.requestFocus();
                    }
                    if(!validarNom(appe))
                    {
                        tvappEx.setError( "Formato del nombre invalido");
                        tvappEx.requestFocus();
                    }
                    if(!validarNom(apme))
                    {
                        tvapmEx.setError( "Formato del nombre invalido");
                        tvapmEx.requestFocus();
                    }
                    if (!ValidacionEmail(emaile))
                    {
                        tvemailEx.setError("Formato de email invalido");
                        tvemailEx.requestFocus();
                    }
                    if(edadnumero > 90 || edadnumero <= 18)
                    {
                        tvedadEx.setError( "Edad invalida");
                        tvedadEx.requestFocus();
                    }
                } else{
                    int riesgo,porcentaje=0;
                    String em = helper.searchemail(usernamed2);


                        if (gene.equals("Masculino"))
                        {
                            riesgo=calcularHombre();
                            porcentaje=calcularPorcentajeHombre(riesgo);
                            Toast.makeText(this, "Sexo: Masculino "+"Puntos: "+riesgo+ "Porcentaje: "+ porcentaje+" %", Toast.LENGTH_LONG).show();

                            DBHelper db = new DBHelper(getApplicationContext());
                            String Mensaje = db.actualizarExpediente(usernamed2, namee, appe, apme, edadnumero, emaile, presurenumero, gene, fum, med, colesterol,colesteHDL,riesgo, porcentaje, numPac);
                            Toast.makeText(getApplicationContext(), Mensaje, Toast.LENGTH_SHORT).show();


                        } else if (gene.equals("Femenino")){
                            riesgo=calcularMujer();
                            porcentaje=calcularPorcentajeMujer(riesgo);
                            Toast.makeText(this, "Sexo: Femenino "+"Puntos: "+riesgo+ "Porcentaje: "+ porcentaje+" %", Toast.LENGTH_LONG).show();

                            DBHelper db = new DBHelper(getApplicationContext());
                            String Mensaje = db.actualizarExpediente(usernamed2, namee, appe, apme, edadnumero, emaile, presurenumero, gene, fum, med, colesterol,colesteHDL,riesgo, porcentaje, numPac);
                            Toast.makeText(getApplicationContext(), Mensaje, Toast.LENGTH_SHORT).show();


                    }
                }

            }

        }
    }

    public int calcularHombre(){
        String edadC = tvedadEx.getText().toString();
        int riesgo=0;
        int edad= Integer.parseInt(edadC);
        if (edad<=35){
            riesgo= -1;
        }else if(edad>=35&&edad<40){
            riesgo=0;
        }else if(edad>=40&&edad<45){
            riesgo=1;
        }else if(edad>=45&&edad<50){
            riesgo=2;
        }else if(edad>=50&&edad<55){
            riesgo=3;
        } else if(edad>=55&&edad<60){
            riesgo=4;
        }else if(edad>=60&&edad<65){
            riesgo=5;
        }else if(edad>=65&&edad<70){
            riesgo=6;
        }else if(edad>70){
            riesgo=7;
        }else{
            Toast.makeText(this, "Edad no valida", Toast.LENGTH_SHORT).show();
        }


        //********************************COLESTEROL TOTAL*****************************************
        //int colesteroltotal=Integer.parseInt(jcolesterolt.getText().toString());
        String colesterol = (spColtEx.getSelectedItem().toString());
        if (colesterol.equals("-seleccione-")) {
            Toast.makeText(this, "Seleccione un valor de Colesterol Total", Toast.LENGTH_SHORT).show();
        } else if(colesterol.equals("<160")){
            riesgo=riesgo+(-3);
        }else if(colesterol.equals("160-199")){
            riesgo=riesgo+(0);
        }else if(colesterol.equals("200-239")){
            riesgo=riesgo+(1);
        }else if(colesterol.equals("240-279")){
            riesgo=riesgo+(2);
        }else if(colesterol.equals(">=280")) {
            riesgo = riesgo + (3);
        }

        //********************************COLESTEROL HDL*****************************************
        //int colesterolHDL=Integer.parseInt(jcolesterolh.getText().toString());
        String colesteHDL = (spColhEx.getSelectedItem().toString());
        if (colesteHDL.equals("-seleccione-")) {
            Toast.makeText(this, "Seleccione un valor de Colesterol HDL", Toast.LENGTH_SHORT).show();
        }
        else if (colesteHDL.equals("<30")){
            riesgo=riesgo+(5);
        }else if(colesteHDL.equals("30-39")) { //30
            riesgo=riesgo+(2);
        }else if(colesteHDL.equals("40-49")){//colesterolHDL>=35&&colesterolHDL<45){
            riesgo=riesgo+(1);
        }else if(colesteHDL.equals("50-59")){//colesterolHDL>=45&&colesterolHDL<50){
            riesgo=riesgo+(0);
        }else if(colesteHDL.equals(">=60")){//colesterolHDL>=50&&colesterolHDL<60){
            riesgo=riesgo+(-2);
        }//colesterolHDL>=60){

        //********************************PRESION SISTOLICA*****************************************
        String presionBP = (tvpresure.getText().toString());
        int Ipresion=Integer.parseInt(presionBP);

        if (presionBP.isEmpty()) {
            Toast.makeText(this, "El campo de presión está vacío", Toast.LENGTH_SHORT).show();
        }else if (Ipresion<120){//presionBP.equals("<120")){//
            riesgo=riesgo+(0);
        }else if(Ipresion>=120&& Ipresion<130){//presionBP.equals("120-129")){
            riesgo=riesgo+(0);
        }else if(Ipresion>=130&& Ipresion<140){//presionBP.equals("130-139")){//
            riesgo=riesgo+(1);
        }else if(Ipresion>=140&& Ipresion<160){//presionBP.equals("140-159")){//
            riesgo=riesgo+(2);
        }else if(Ipresion>=160){//presionBP.equals(">=160")){//
            riesgo=riesgo+3;
        }


        //********************************FUMADOR*****************************************
        String fumador=(spFumEx.getSelectedItem().toString());
        if (fumador.equals("-seleccione-")){
            Toast.makeText(this, "Seleccione un campo de Fumador", Toast.LENGTH_SHORT).show();
        }
        else if(fumador.equals("si")){
            riesgo=riesgo+(2);
        }else if(fumador.equals("no")){
            riesgo=riesgo+(0);
        }

        //********************************DIABETES*****************************************
        String diabetes=(spmedEx.getSelectedItem().toString());
        if (diabetes.equals("-seleccione-")){
            Toast.makeText(this, "Seleccione un campo de Medicamento", Toast.LENGTH_SHORT).show();
        }
        else if(diabetes.equals("si")){
            riesgo=riesgo+(2);
        }else if(diabetes.equals("no")){
            riesgo=riesgo+(0);
        }

        return riesgo;
    }

    public int calcularMujer(){
        String edadC = tvedadEx.getText().toString();
        int riesgo=0;
        int edad= Integer.parseInt(edadC);
        if (edad<=35){
            riesgo=-9;
        }else if(edad>=35&&edad<40){
            riesgo=-4;
        }else if(edad>=40&&edad<45){
            riesgo=0;
        }else if(edad>=45&&edad<50){
            riesgo=3;
        }else if(edad>=50&&edad<55){
            riesgo=6;
        } else if(edad>=55&&edad<60){
            riesgo=7;
        }else if(edad>=60&&edad<65){
            riesgo=8;
        }else if(edad>=65&&edad<70){
            riesgo=8;
        }else if(edad>=70){
            riesgo=8;
        }else{
            Toast.makeText(this, "Edad no valida", Toast.LENGTH_SHORT).show();
        }


        //********************************COLESTEROL TOTAL*****************************************
        //int colesteroltotal=Integer.parseInt(jcolesterolt.getText().toString());
        String colesterol = (spColtEx.getSelectedItem().toString());
        if (colesterol.equals("-seleccione")){
            Toast.makeText(this, "Seleccione un valor de Colesterol Total", Toast.LENGTH_SHORT).show();
        }
        else if(colesterol.equals("<160")){//colesteroltotal<160){
            riesgo=riesgo+(-2);
        }else if(colesterol.equals("160-199")){//colesteroltotal>160&&colesteroltotal<200){
            riesgo=riesgo+(0);
        }else if(colesterol.equals("200-239")){//colesteroltotal>200&&colesteroltotal<240){
            riesgo=riesgo+(1);
        }else if(colesterol.equals("240-279")){//colesteroltotal>240&&colesteroltotal<280){
            riesgo=riesgo+(1);
        }else if(colesterol.equals(">=280")){//colesteroltotal>280) {
            riesgo=riesgo+(3);
        }

        //********************************COLESTEROL HDL*****************************************
        //int colesterolHDL=Integer.parseInt(jcolesterolh.getText().toString());
        String colesteHDL = (spColhEx.getSelectedItem().toString());
        if (colesteHDL.equals("-seleccione-")){
            Toast.makeText(this, "Seleccione un valor de Colesterol HDL", Toast.LENGTH_SHORT).show();
        }
        else if(colesteHDL.equals("<30")){//colesterolHDL<35) {
            riesgo=riesgo+(5);
        }else if(colesteHDL.equals("30-39")){//colesterolHDL>35&&colesterolHDL<45){
            riesgo=riesgo+(2);
        }else if(colesteHDL.equals("40-49")){//colesterolHDL>45&&colesterolHDL<50){
            riesgo=riesgo+(1);
        }else if(colesteHDL.equals("50-59")){//colesterolHDL>50&&colesterolHDL<60){
            riesgo=riesgo+(0);
        }else if(colesteHDL.equals(">=60")){//colesterolHDL>60){
            riesgo=riesgo+(-3);
        }

        //********************************PRESION SISTOLICA*****************************************
        //int presion=Integer.parseInt(jpresion.getText().toString());

        String presionBP = (tvpresure.getText().toString());
        int Ipresion=Integer.parseInt(presionBP);

        if (presionBP.isEmpty()) {
            Toast.makeText(this, "El campo de presión está vacío", Toast.LENGTH_SHORT).show();
        }else if (Ipresion<120){//presionBP.equals("<120")){//
            riesgo=riesgo+(-3);
        }else if(Ipresion>=120&& Ipresion<130){//presionBP.equals("120-129")){
            riesgo=riesgo+(0);
        }else if(Ipresion>=130&& Ipresion<140){//presionBP.equals("130-139")){//
            riesgo=riesgo+(0);
        }else if(Ipresion>=140&& Ipresion<160){//presionBP.equals("140-159")){//
            riesgo=riesgo+(2);
        }else if(Ipresion>=160){//presionBP.equals(">=160")){//
            riesgo=riesgo+3;
        }

        //********************************FUMADOR*****************************************
        String fumador=(spFumEx.getSelectedItem().toString());
        if (fumador.equals("-seleccione-")){
            Toast.makeText(this, "Seleccione un campo de Fumador", Toast.LENGTH_SHORT).show();
        }
        else if(fumador.equals("si")){
            riesgo=riesgo+(2);
        }else if(fumador.equals("no")){
            riesgo=riesgo+(0);
        }

        //********************************DIABETES*****************************************
        String diabetes=(spmedEx.getSelectedItem().toString());
        if (fumador.equals("-seleccione-")){
            Toast.makeText(this, "Seleccione un campo de Medicamento", Toast.LENGTH_SHORT).show();
        }
        else if(diabetes.equals("si")){
            riesgo=riesgo+(4);
        }else if(diabetes.equals("no")){
            riesgo=riesgo+(0);
        }

        return riesgo;
    }

    public int calcularPorcentajeHombre(int riesgo) {
        int porcentaje = 0;
        if (riesgo < 0) {
            porcentaje = 2;
        }else if (riesgo == 0){
            porcentaje = 3;
        }else if (riesgo == 1){
            porcentaje = 3;
        }else if (riesgo == 2){
            porcentaje = 4;
        }else if (riesgo == 3){
            porcentaje = 5;
        }else if (riesgo == 4){
            porcentaje = 7;
        }else if (riesgo == 5){
            porcentaje = 8;
        }else if (riesgo == 6){
            porcentaje = 10;
        }else if (riesgo == 7){
            porcentaje = 13;
        }else if (riesgo == 8){
            porcentaje = 16;
        }else if (riesgo == 9){
            porcentaje = 20;
        }else if (riesgo == 10){
            porcentaje = 25;
        }else if (riesgo == 11){
            porcentaje = 31;
        }else if (riesgo == 12){
            porcentaje = 37;
        }else if (riesgo == 13){
            porcentaje = 45;
        }else if (riesgo > 14) {
            porcentaje = 53;
        }
        return porcentaje;

    }

    public int calcularPorcentajeMujer(int riesgo) {
        int porcentaje = 0;
        if (riesgo < -1) {
            porcentaje = 1;
        }if (riesgo == -1) {
            porcentaje = 2;
        }if (riesgo == 0) {
            porcentaje = 2;
        }if (riesgo == 1) {
            porcentaje = 3;
        }if (riesgo == 2) {
            porcentaje = 3;
        }if (riesgo == 3) {
            porcentaje = 3;
        }if (riesgo == 4) {
            porcentaje = 4;
        }if (riesgo == 5) {
            porcentaje = 4;
        }if (riesgo == 6) {
            porcentaje = 5;
        }if (riesgo == 7) {
            porcentaje = 6;
        }if (riesgo == 8) {
            porcentaje = 7;
        }if (riesgo == 9) {
            porcentaje = 8;
        }if (riesgo == 10) {
            porcentaje = 10;
        }if (riesgo == 11) {
            porcentaje = 11;
        }if (riesgo == 12) {
            porcentaje = 13;
        }if (riesgo == 13) {
            porcentaje = 15;
        }if (riesgo == 14) {
            porcentaje = 18;
        }if (riesgo == 15) {
            porcentaje = 20;
        }if (riesgo == 16) {
            porcentaje = 24;
        }if (riesgo > 17) {
            porcentaje = 27;
        }
        return porcentaje;

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

    public void onBackPressed()
    {
        String str = getIntent().getStringExtra("Username");
        String userna = helper.searchPass(str);
        String named = helper.searchname(str);
        String edadd = helper.searchedad(str);
        String emaild = helper.searchemail(str);
        String teld = helper.searchtel(str);
        String cont1d = helper.searchcont1(str);
        String cont2d = helper.searchcont2(str);
        String gend = helper.searchgen(str);
        String fum = helper.searchfum(str);
        String med = helper.searchmed(str);
        String colt = helper.searchcolt(str);
        String colh = helper.searchcolh(str);
        String presu = helper.searchpresure(str);
        String punt = helper.searchpunt(str);
        String risk = helper.searchrisk(str);
        String appd = helper.searchapp(str);
        String apmd = helper.searchapm(str);
        String numpacd = helper.searchnumpac(str);

        Intent i = new Intent(Editarexped.this, Verexp.class);
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

        i.putExtra("Fum", fum);
        i.putExtra("Med", med);
        i.putExtra("Colt", colt);
        i.putExtra("Colh", colh);
        i.putExtra("Presu", presu);
        i.putExtra("Punt", punt);
        i.putExtra("Risk", risk);
        i.putExtra("Numpac", numpacd);
        startActivity(i);
    }

}
