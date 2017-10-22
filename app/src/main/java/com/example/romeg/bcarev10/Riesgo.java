package com.example.romeg.bcarev10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Riesgo extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);

    public Spinner jspfumador,jspdiabetes, spcolesterolTot, spcolesterolHDL, spPresion;
    TextView edadCal, generCal, usercalcul;

    public EditText jgenero,jedad,jcolesterolt,jcolesterolh,jpresion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riesgo);

        String usern = getIntent().getStringExtra("Username");
        String name = getIntent().getStringExtra("Name");
        String edad = getIntent().getStringExtra("Edad");
        String gene = getIntent().getStringExtra("Gen");
        String[] colesteroTot = {"-seleccione-","<160","160-199","200-239","240-279",">=280"};
        String[] colesterolHDL = {"-seleccione-",">=60","50-59","40-49","30-39","<30"};
        String[] BPsistolica = {"-seleccione-","<120","120-129","130-139","140-159",">=160"};


        usercalcul = (TextView) findViewById(R.id.UserCalcu);
        usercalcul.setText(usern);
        edadCal = (TextView) findViewById(R.id.textEdad);
        edadCal.setText(edad);
        generCal = (TextView) findViewById(R.id.textGenero);
        generCal.setText(gene);
        spcolesterolTot = (Spinner) findViewById(R.id.spcolesterol1);
        spcolesterolTot.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colesteroTot));
        spcolesterolHDL = (Spinner) findViewById(R.id.spcolesterolHDL);
        spcolesterolHDL.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colesterolHDL));
        spPresion = (Spinner) findViewById(R.id.sppresion);
        spPresion.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, BPsistolica));
        edadCal=(TextView)findViewById(R.id.textEdad);

        jspfumador=(Spinner) findViewById(R.id.spFumador);
        String[] valoresfumador = {"-seleccione-","sí","no"};
        jspfumador.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoresfumador));

        jspdiabetes=(Spinner) findViewById(R.id.spDiabetes);
        String[] valoresDiabetes = {"-seleccione-","sí","no"};
        jspdiabetes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoresDiabetes));
    }


    public void calcular(View v){
        int riesgo,porcentaje=0;
        String geneC = generCal.getText().toString();

        String str = usercalcul.getText().toString();
        String gend = helper.searchgen(str);

        if(gend.equals(geneC))//(genC=="Masculino")
        {
            riesgo=calcularHombre();
            porcentaje=calcularPorcentajeHombre(riesgo);
            Toast.makeText(this, "Sexo: Masculino "+"Puntos: "+riesgo+ "Porcentaje: "+ porcentaje+" %", Toast.LENGTH_LONG).show();

        }else if (gend.equals(geneC))
        {
            riesgo=calcularMujer();
            porcentaje=calcularPorcentajeMujer(riesgo);
            Toast.makeText(this, "Sexo: Femenino "+"Puntos: "+riesgo+ "Porcentaje: "+ porcentaje+" %", Toast.LENGTH_LONG).show();
        }

    }
    public int calcularHombre(){
        String edadC = edadCal.getText().toString();
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
        String colesterol = (spcolesterolTot.getSelectedItem().toString());
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
        String colesteHDL = (spcolesterolHDL.getSelectedItem().toString());
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
        //int presion=Integer.parseInt(jpresion.getText().toString());
        String presionBP = (spPresion.getSelectedItem().toString());
        if (presionBP.equals("-seleccione-")){
            Toast.makeText(this, "Seleccione un valor de Presión", Toast.LENGTH_SHORT).show();
        } else if (presionBP.equals("<120")){//presion<120){
            riesgo=riesgo+(0);
        }else if(presionBP.equals("120-129")){//presion>=120&& presion<130){
            riesgo=riesgo+(0);
        }else if(presionBP.equals("130-139")){//presion>=130&& presion<140){
            riesgo=riesgo+(1);
        }else if(presionBP.equals("140-159")){//presion>=140&& presion<160){
            riesgo=riesgo+(2);
        }else if(presionBP.equals(">=160")){//presion>=160){
            riesgo=riesgo+3;
        }

        //********************************FUMADOR*****************************************
        String fumador=(jspfumador.getSelectedItem().toString());
        if (fumador.equals("-seleccione-")){
            Toast.makeText(this, "Seleccione un campo de Fumador", Toast.LENGTH_SHORT).show();
        }
        else if(fumador.equals("si")){
            riesgo=riesgo+(2);
        }else if(fumador.equals("no")){
            riesgo=riesgo+(0);
        }

        //********************************DIABETES*****************************************
        String diabetes=(jspdiabetes.getSelectedItem().toString());
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
        String edadC = edadCal.getText().toString();
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
        String colesterol = (spcolesterolTot.getSelectedItem().toString());
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
        String colesteHDL = (spcolesterolHDL.getSelectedItem().toString());
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
        String BPpresion = (spPresion.getSelectedItem().toString());
        if (BPpresion.equals("-seleccione-")){
            Toast.makeText(this, "Seleccione un valor de Presión", Toast.LENGTH_SHORT).show();
        }
        else if (BPpresion.equals("<120")){//presion<120){
            riesgo=riesgo+(-3);
        }else if(BPpresion.equals("120-129")){//presion>120&& presion<130){
            riesgo=riesgo+(0);
        }else if(BPpresion.equals("130-139")){//presion>130&& presion<140){
            riesgo=riesgo+(0);
        }else if(BPpresion.equals("140-159")){//presion>140&& presion<160){
            riesgo=riesgo+(2);
        }else if(BPpresion.equals(">=160")){//presion>160){
            riesgo=riesgo+3;
        }

        //********************************FUMADOR*****************************************
        String fumador=(jspfumador.getSelectedItem().toString());
        if (fumador.equals("-seleccione-")){
            Toast.makeText(this, "Seleccione un campo de Fumador", Toast.LENGTH_SHORT).show();
        }
        else if(fumador.equals("si")){
            riesgo=riesgo+(2);
        }else if(fumador.equals("no")){
            riesgo=riesgo+(0);
        }

        //********************************DIABETES*****************************************
        String diabetes=(jspdiabetes.getSelectedItem().toString());
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
    public void onBackPressed()
    {
        String str = usercalcul.getText().toString();
        String userna = helper.searchPass(str);
        String named = helper.searchname(str);
        String edadd = helper.searchedad(str);
        String emaild = helper.searchemail(str);
        String teld = helper.searchtel(str);
        String cont1d = helper.searchcont1(str);
        String cont2d = helper.searchcont2(str);
        String gend = helper.searchgen(str);

        Intent i = new Intent(Riesgo.this, Expediente.class);
        i.putExtra("Username", str);
        i.putExtra("Name", named);
        i.putExtra("Edad", edadd);
        i.putExtra("Email", emaild);
        i.putExtra("Tel", teld);
        i.putExtra("Cont1", cont1d);
        i.putExtra("Cont2", cont2d);
        i.putExtra("Gen", gend);
        startActivity(i);
        finish();
    }



}
