package com.example.romeg.bcarev10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Riesgo extends AppCompatActivity {

    public RadioButton jrbfemenino,jrbmasculino;
    public Spinner jspfumador,jspdiabetes;

    public EditText jgenero,jedad,jcolesterolt,jcolesterolh,jpresion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riesgo);

        jrbfemenino=(RadioButton)findViewById(R.id.rbfemenino) ;
        jrbmasculino=(RadioButton)findViewById(R.id.rbmasculino) ;
        //jgenero=(EditText)findViewById(R.id.edtgenero);
        jedad=(EditText)findViewById(R.id.edtedad);
        jcolesterolt=(EditText)findViewById(R.id.edtcolesterol1);
        jcolesterolh=(EditText)findViewById(R.id.edtcolesterolHDL);
        jspfumador=(Spinner) findViewById(R.id.spFumador);
        String[] valoresfumador = {"si","no"};
        jspfumador.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoresfumador));
        jpresion=(EditText)findViewById(R.id.edtpresion);
        jspdiabetes=(Spinner) findViewById(R.id.spDiabetes);
        String[] valoresDiabetes = {"si","no"};
        jspdiabetes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoresDiabetes));
    }


    public void calcular(View v){
        int riesgo,porcentaje=0;
        //String gen=jgenero.getText().toString();




        if(jrbmasculino.isChecked()) {

            riesgo=calcularHombre();
            porcentaje=calcularPorcentajeHombre(riesgo);
            Toast.makeText(this, "Sexo: Masculino "+"Puntos: "+riesgo+ "Porcentaje: "+ porcentaje+" %", Toast.LENGTH_LONG).show();

        }else if(jrbfemenino.isChecked()){
            //jcbmasculino.setEnabled(false);
            riesgo=calcularMujer();
            porcentaje=calcularPorcentajeMujer(riesgo);
            Toast.makeText(this, "Sexo: Femenino "+"Puntos: "+riesgo+ "Porcentaje: "+ porcentaje+" %", Toast.LENGTH_LONG).show();

        }
    }
    public int calcularHombre(){
        int riesgo=0;
        int edad= Integer.parseInt(jedad.getText().toString());
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
            Toast.makeText(this, "Edad no valida", Toast.LENGTH_LONG).show();
        }


        //********************************COLESTEROL TOTAL*****************************************
        int colesteroltotal=Integer.parseInt(jcolesterolt.getText().toString());
        if(colesteroltotal<160){
            riesgo=riesgo+(-3);
        }else if(colesteroltotal>=160&&colesteroltotal<200){
            riesgo=riesgo+(0);
        }else if(colesteroltotal>=200&&colesteroltotal<240){
            riesgo=riesgo+(1);
        }else if(colesteroltotal>=240&&colesteroltotal<280){
            riesgo=riesgo+(2);
        }else if(colesteroltotal>=280) {
            riesgo = riesgo + (3);
        }

        //********************************COLESTEROL HDL*****************************************
        int colesterolHDL=Integer.parseInt(jcolesterolh.getText().toString());
        if(colesterolHDL<35) {
            riesgo=riesgo+(2);
        }else if(colesterolHDL>=35&&colesterolHDL<45){
            riesgo=riesgo+(1);
        }else if(colesterolHDL>=45&&colesterolHDL<50){
            riesgo=riesgo+(0);
        }else if(colesterolHDL>=50&&colesterolHDL<60){
            riesgo=riesgo+(0);
        }else if(colesterolHDL>=60){
            riesgo=riesgo+(-2);
        }

        //********************************PRESION SISTOLICA*****************************************
        int presion=Integer.parseInt(jpresion.getText().toString());
        if (presion<120){
            riesgo=riesgo+(0);
        }else if(presion>=120&& presion<130){
            riesgo=riesgo+(0);
        }else if(presion>=130&& presion<140){
            riesgo=riesgo+(1);
        }else if(presion>=140&& presion<160){
            riesgo=riesgo+(2);
        }else if(presion>=160){
            riesgo=riesgo+3;
        }

        //********************************FUMADOR*****************************************
        String fumador=(jspfumador.getSelectedItem().toString());
        if(fumador.equals("si")){
            riesgo=riesgo+(2);
        }else if(fumador.equals("no")){
            riesgo=riesgo+(0);
        }

        //********************************DIABETES*****************************************
        String diabetes=(jspdiabetes.getSelectedItem().toString());
        if(diabetes.equals("si")){
            riesgo=riesgo+(2);
        }else if(diabetes.equals("no")){
            riesgo=riesgo+(0);
        }

        return riesgo;
    }





    public int calcularMujer(){
        int riesgo=0;
        int edad= Integer.parseInt(jedad.getText().toString());
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
            Toast.makeText(this, "Edad no valida", Toast.LENGTH_LONG).show();
        }


        //********************************COLESTEROL TOTAL*****************************************
        int colesteroltotal=Integer.parseInt(jcolesterolt.getText().toString());
        if(colesteroltotal<160){
            riesgo=riesgo+(-2);
        }else if(colesteroltotal>160&&colesteroltotal<200){
            riesgo=riesgo+(0);
        }else if(colesteroltotal>200&&colesteroltotal<240){
            riesgo=riesgo+(1);
        }else if(colesteroltotal>240&&colesteroltotal<280){
            riesgo=riesgo+(1);
        }else if(colesteroltotal>280) {
            riesgo=riesgo+(3);
        }

        //********************************COLESTEROL HDL*****************************************
        int colesterolHDL=Integer.parseInt(jcolesterolh.getText().toString());
        if(colesterolHDL<35) {
            riesgo=riesgo+(5);
        }else if(colesterolHDL>35&&colesterolHDL<45){
            riesgo=riesgo+(2);
        }else if(colesterolHDL>45&&colesterolHDL<50){
            riesgo=riesgo+(1);
        }else if(colesterolHDL>50&&colesterolHDL<60){
            riesgo=riesgo+(0);
        }else if(colesterolHDL>60){
            riesgo=riesgo+(-3);
        }

        //********************************PRESION SISTOLICA*****************************************
        int presion=Integer.parseInt(jpresion.getText().toString());
        if (presion<120){
            riesgo=riesgo+(-3);
        }else if(presion>120&& presion<130){
            riesgo=riesgo+(0);
        }else if(presion>130&& presion<140){
            riesgo=riesgo+(0);
        }else if(presion>140&& presion<160){
            riesgo=riesgo+(2);
        }else if(presion>160){
            riesgo=riesgo+3;
        }

        //********************************FUMADOR*****************************************
        String fumador=(jspfumador.getSelectedItem().toString());
        if(fumador.equals("si")){
            riesgo=riesgo+(2);
        }else if(fumador.equals("no")){
            riesgo=riesgo+(0);
        }

        //********************************DIABETES*****************************************
        String diabetes=(jspdiabetes.getSelectedItem().toString());
        if(diabetes.equals("si")){
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

    private boolean flagmale = false;
    private boolean flagfemale = false;




}
