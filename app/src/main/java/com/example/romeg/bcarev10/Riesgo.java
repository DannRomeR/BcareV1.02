package com.example.romeg.bcarev10;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class Riesgo extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);

    NotificationCompat.Builder notificación;
    private static final int idUnica= 51623;

    public Spinner jspfumador,jspdiabetes, spcolesterolTot, spcolesterolHDL;
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
        //String[] BPsistolica = {"-seleccione-","<120","120-129","130-139","140-159",">=160"};


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
        //spPresion = (Spinner) findViewById(R.id.sppresion);
        //spPresion.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, BPsistolica));
        edadCal=(TextView)findViewById(R.id.textEdad);
        jpresion = (EditText) findViewById(R.id.etpresion);
        jspfumador=(Spinner) findViewById(R.id.spFumador);
        String[] valoresfumador = {"-seleccione-","sí","no"};
        jspfumador.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoresfumador));

        jspdiabetes=(Spinner) findViewById(R.id.spDiabetes);
        String[] valoresDiabetes = {"-seleccione-","sí","no"};
        jspdiabetes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoresDiabetes));


    }


    public void calcular(View v){
        int riesgo,porcentaje;
        String usern2 = getIntent().getStringExtra("Username");
        String geneC = generCal.getText().toString();
        String fum = (jspfumador.getSelectedItem().toString());
        String med = (jspdiabetes.getSelectedItem().toString());
        String colt  = (spcolesterolTot.getSelectedItem().toString());
        String colh = (spcolesterolHDL.getSelectedItem().toString());
        String presionBP2 = (jpresion.getText().toString());

        String str = usercalcul.getText().toString();
        String gend = helper.searchgen(str);

        if (presionBP2.isEmpty() || fum.equals("-seleccione-") || med.equals("-seleccione-") || colt.equals("-seleccione-") || colh.equals("-seleccione-") )
        {
            Toast.makeText(this, "Los campos marcados con '*' son obligatorios ",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            int Ipresion2 = Integer.parseInt(presionBP2);
            if (gend.equals(geneC))//(genC=="Masculino")
            {

                if (Ipresion2 < 50 || Ipresion2 > 300) {
                    Toast.makeText(this, "Numero de presión invalido", Toast.LENGTH_LONG).show();
                    jpresion.setError("Numero de presión invalido");
                } else {
                    riesgo = calcularHombre();
                    porcentaje = calcularPorcentajeHombre(riesgo);

                    DBHelper db = new DBHelper(getApplicationContext());
                    String Mensaje = db.insertCalcu(str, fum, med, colt, colh, Ipresion2, riesgo, porcentaje);
                    Toast.makeText(getApplicationContext(), Mensaje, Toast.LENGTH_SHORT).show();


                    notificación = new NotificationCompat.Builder(this);
                    notificación.setAutoCancel(true);
                    if (porcentaje > 10 && porcentaje < 60) {

                        notificación.setSmallIcon(R.drawable.doctor);
                        notificación.setTicker("Su nivel de riesgo es alto no olvide revisar su presión");
                        notificación.setWhen(System.currentTimeMillis());
                        notificación.setContentTitle("Bcare");
                        notificación.setContentText("Su nivel de riesgo es alto no olvide revisar su presión");
                        notificación.setVibrate(new long[] {100, 250, 100, 500});


                        Intent i = new Intent(Riesgo.this, Vincular.class);
                        PendingIntent pendingIntent = PendingIntent.getActivity(Riesgo.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                        notificación.setContentIntent(pendingIntent);

                        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                        nm.notify(idUnica, notificación.build());

                    }

                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                    dialogo1.setTitle("Resultado");
                    dialogo1.setMessage("Sexo: Masculino " + "Puntos: " + riesgo + " " + "Porcentaje: " + porcentaje + " %");
                    dialogo1.setCancelable(false);
                    dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            String str = usercalcul.getText().toString();
                            String named = helper.searchname(str);
                            String edadd = helper.searchedad(str);
                            String emaild = helper.searchemail(str);
                            String teld = helper.searchtel(str);
                            String cont1d = helper.searchcont1(str);
                            String cont2d = helper.searchcont2(str);
                            String presu = helper.searchpresure(str);
                            String punt = helper.searchpunt(str);
                            String risk = helper.searchrisk(str);
                            String gend = helper.searchgen(str);
                            String fum = helper.searchfum(str);
                            String med = helper.searchmed(str);
                            String colt  = helper.searchcolt(str);
                            String colh = helper.searchcolh(str);

                            Intent i = new Intent(Riesgo.this, Expediente.class);
                            i.putExtra("Username", str);
                            i.putExtra("Name", named);
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
                            startActivity(i);
                            finish();
                        }
                    });

                    dialogo1.show();
                }


            } else if (gend.equals(geneC)) {
                if (fum.equals("-seleccione-") || med.equals("-seleccione-") || colt.equals("-seleccione-") || colh.equals("-seleccione-") || presionBP2.isEmpty()) {
                    Toast.makeText(this, "Los campos marcados con '*' son obligatorios ",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (Ipresion2 < 50 || Ipresion2 > 300) {
                        Toast.makeText(this, "Numero de presión invalido", Toast.LENGTH_LONG).show();
                        jpresion.setError("Numero de presión invalido");
                    } else {
                        riesgo = calcularMujer();
                        porcentaje = calcularPorcentajeMujer(riesgo);


                        DBHelper db = new DBHelper(getApplicationContext());
                        String Mensaje = db.insertCalcu(usern2, fum, med, colt, colh, Ipresion2, riesgo, porcentaje);
                        Toast.makeText(getApplicationContext(), Mensaje, Toast.LENGTH_SHORT).show();


                        notificación = new NotificationCompat.Builder(this);
                        notificación.setAutoCancel(true);

                        if (porcentaje > 10 && porcentaje < 60) {
                            notificación.setSmallIcon(R.drawable.doctor);
                            notificación.setTicker("Su nivel de riesgo es alto no olvide revisar su presión");
                            notificación.setWhen(System.currentTimeMillis());
                            notificación.setContentTitle("Bcare");
                            notificación.setContentText("Su nivel de riesgo es alto no olvide revisar su presión");
                            notificación.setVibrate(new long[] {100, 250, 100, 500});
                            Intent i = new Intent(Riesgo.this, Vincular.class);
                            PendingIntent pendingIntent = PendingIntent.getActivity(Riesgo.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                            notificación.setContentIntent(pendingIntent);

                            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                            nm.notify(idUnica, notificación.build());

                        }
                        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                        dialogo1.setTitle("Resultado");
                        dialogo1.setMessage("Sexo: Femenino " + "Puntos: " + riesgo + " " + " Porcentaje: " + porcentaje + "%");
                        dialogo1.setCancelable(false);
                        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo1, int id) {
                                String str = usercalcul.getText().toString();
                                String named = helper.searchname(str);
                                String edadd = helper.searchedad(str);
                                String emaild = helper.searchemail(str);
                                String teld = helper.searchtel(str);
                                String cont1d = helper.searchcont1(str);
                                String cont2d = helper.searchcont2(str);
                                String presu = helper.searchpresure(str);
                                String punt = helper.searchpunt(str);
                                String risk = helper.searchrisk(str);
                                String gend = helper.searchgen(str);
                                String fum = helper.searchfum(str);
                                String med = helper.searchmed(str);
                                String colt  = helper.searchcolt(str);
                                String colh = helper.searchcolh(str);

                                Intent i = new Intent(Riesgo.this, Expediente.class);
                                i.putExtra("Username", str);
                                i.putExtra("Name", named);
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
                                startActivity(i);
                                finish();
                            }
                        });

                        dialogo1.show();

                    }
                }

            }
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
        String presionBP = (jpresion.getText().toString());
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

        String presionBP = (jpresion.getText().toString());
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

        Intent i = new Intent(Riesgo.this, Expediente.class);
        i.putExtra("Username", str);
        i.putExtra("Name", named);
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
        startActivity(i);
        finish();
    }

}
