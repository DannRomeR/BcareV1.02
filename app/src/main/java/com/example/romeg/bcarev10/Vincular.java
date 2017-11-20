package com.example.romeg.bcarev10;

import android.annotation.TargetApi;
import android.app.NotificationManager;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;

import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.os.AsyncTask;

import android.support.v7.app.NotificationCompat;
import android.text.method.ScrollingMovementMethod;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

public class Vincular extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    BluetoothManager btManager;
    BluetoothAdapter btAdapter;
    BluetoothLeScanner btScanner;
    Button startScanningButton;
    Button stopScanningButton;
    TextView peripheralTextView, etuse;
    EditText bpsistolica;

    NotificationCompat.Builder notificación;
    private static final int idUnica= 51623;


    private final static int REQUEST_ENABLE_BT = 1;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vincular);


        String username = getIntent().getStringExtra("Username");


        etuse = (TextView) findViewById(R.id.userVin);
        etuse.setText(username);

        peripheralTextView = (TextView) findViewById(R.id.PeripheralTextView);
        peripheralTextView.setMovementMethod(new ScrollingMovementMethod());

        startScanningButton = (Button) findViewById(R.id.StartScanButton);
        startScanningButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startScanning();
            }
        });

        stopScanningButton = (Button) findViewById(R.id.StopScanButton);
        stopScanningButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopScanning();
            }
        });
        stopScanningButton.setVisibility(View.INVISIBLE);

        btManager = (BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE);
        btAdapter = btManager.getAdapter();
        btScanner = btAdapter.getBluetoothLeScanner();


        if (btAdapter != null && !btAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent,REQUEST_ENABLE_BT);
        }

        // Make sure we have access coarse location enabled, if not, prompt the user to enable it
        if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Esta aplicación necesita acceso a su locación");
            builder.setMessage("Please grant location access so this app can detect peripherals.");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onDismiss(DialogInterface dialog) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
                }
            });
            builder.show();
        }

    }

    public void onEnviarClick(View v) {
        int riesgo, porcentaje;
        String username = etuse.getText().toString();
        String named = helper.searchname(username);
        String edadd = helper.searchedad(username);
        String emaild = helper.searchemail(username);
        String teld = helper.searchtel(username);
        String cont1d = helper.searchcont1(username);
        String cont2d = helper.searchcont2(username);

        String gend = helper.searchgen(username);

        String fum = helper.searchfum(username);
        String med = helper.searchmed(username);
        String colt = helper.searchcolt(username);
        String colh = helper.searchcolh(username);

        String appd = helper.searchapp(username);
        String apmd = helper.searchapm(username);
        String numpacd = helper.searchnumpac(username);


        bpsistolica = (EditText) findViewById(R.id.etpresionVin);
        String presion = bpsistolica.getText().toString();
        int Ipresion2 = Integer.parseInt(presion);

        if (v.getId() == R.id.Calcular) {
            if (presion.isEmpty()) {
                Toast.makeText(this, "El campo de presion está vacío", Toast.LENGTH_SHORT).show();
            } else {
                if (fum.equals("Sin dato") && med.equals("Sin dato") && colt.equals("Sin dato") && colh.equals("Sin dato")) {
                    Toast.makeText(this, "No ha realizado el calculo de riesgo, llene el formulario que se le pide", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(Vincular.this, Riesgo.class);
                    i.putExtra("Username", username);
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
                } else {
                    if (gend.equals("Masculino")) {
                        if (Ipresion2 < 50 || Ipresion2 > 300) {
                            Toast.makeText(this, "Numero de presión invalido", Toast.LENGTH_LONG).show();
                            bpsistolica.setError("Numero de presión invalido");
                        } else {
                            riesgo = calcularHombre();
                            porcentaje = calcularPorcentajeHombre(riesgo);

                            DBHelper db = new DBHelper(getApplicationContext());
                            String Mensaje = db.insertCalcu(username, fum, med, colt, colh, Ipresion2, riesgo, porcentaje);
                            Toast.makeText(getApplicationContext(), Mensaje, Toast.LENGTH_SHORT).show();


                            notificación = new NotificationCompat.Builder(this);
                            notificación.setAutoCancel(true);
                            if (porcentaje > 10 && porcentaje < 50) {
                                notificación.setSmallIcon(R.drawable.doctor);
                                notificación.setTicker("Su nivel de riesgo es alto no olvide revisar su presión");
                                notificación.setWhen(System.currentTimeMillis());
                                notificación.setContentTitle("Bcare");
                                notificación.setContentText("Su nivel de riesgo es alto no olvide revisar su presión");
                                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                nm.notify(idUnica, notificación.build());
                                new PreferenceManager(this).clearPreference();


                                AlertDialog.Builder dialogo2 = new AlertDialog.Builder(this);
                                dialogo2.setTitle("Importante");
                                dialogo2.setMessage("Sexo: Masculino " + "Puntos: " + riesgo + " " + "Porcentaje: " + porcentaje + " % Su porcentaje a ha salido alto realice el siguiente Test");
                                dialogo2.setCancelable(false);
                                dialogo2.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogo1, int id) {
                                        String username = etuse.getText().toString();
                                        Intent i = new Intent(Vincular.this, Fast2.class);
                                        i.putExtra("Username", username);

                                        startActivity(i);
                                        finish();
                                    }
                                });

                                dialogo2.show();

                            } else {

                                AlertDialog.Builder dialogo3 = new AlertDialog.Builder(this);
                                dialogo3.setTitle("Importante");
                                dialogo3.setMessage("Sexo: Masculino " + "Puntos: " + riesgo + " " + "Porcentaje: " + porcentaje + " % Tus niveles de riesgo son normales, no olvides realizar el calculo de riesgo todos los días");
                                dialogo3.setCancelable(false);
                                dialogo3.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogo1, int id) {
                                        String str = etuse.getText().toString();
                                        String userna = helper.searchPass(str);
                                        String named = helper.searchname(str);
                                        String edadd = helper.searchedad(str);
                                        String emaild = helper.searchemail(str);
                                        String teld = helper.searchtel(str);
                                        String cont1d = helper.searchcont1(str);
                                        String cont2d = helper.searchcont2(str);
                                        String gend = helper.searchgen(str);

                                        Intent i = new Intent(Vincular.this, Usuario.class);
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
                                });

                                dialogo3.show();
                            }
                        }
                    } else if (gend.equals("Femenino")) {
                        if (Ipresion2 < 50 || Ipresion2 > 300) {
                            Toast.makeText(this, "Numero de presión invalido", Toast.LENGTH_LONG).show();
                            bpsistolica.setError("Numero de presión invalido");
                        } else {
                            riesgo = calcularMujer();
                            porcentaje = calcularPorcentajeMujer(riesgo);

                            DBHelper db = new DBHelper(getApplicationContext());
                            String Mensaje = db.insertCalcu(username, fum, med, colt, colh, Ipresion2, riesgo, porcentaje);
                            Toast.makeText(getApplicationContext(), Mensaje, Toast.LENGTH_SHORT).show();

                            notificación = new NotificationCompat.Builder(this);
                            notificación.setAutoCancel(true);
                            if (porcentaje > 10 && porcentaje < 50) {
                                notificación.setSmallIcon(R.drawable.doctor);
                                notificación.setTicker("Su nivel de riesgo es alto no olvide revisar su presión");
                                notificación.setWhen(System.currentTimeMillis());
                                notificación.setContentTitle("Bcare");
                                notificación.setContentText("Su nivel de riesgo es alto no olvide revisar su presión");
                                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                nm.notify(idUnica, notificación.build());
                                String str = etuse.getText().toString();
                                new PreferenceManager(this).clearPreference();


                                AlertDialog.Builder dialogo2 = new AlertDialog.Builder(this);
                                dialogo2.setTitle("Importante");
                                dialogo2.setMessage("Sexo: Femenino " + "Puntos: " + riesgo + " " + "Porcentaje: " + porcentaje + " % Su porcentaje a ha salido alto realice el siguiente Test");
                                dialogo2.setCancelable(false);
                                dialogo2.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogo1, int id) {
                                        String username = etuse.getText().toString();
                                        Intent i = new Intent(Vincular.this, Fast2.class);
                                        i.putExtra("Username", username);

                                        startActivity(i);
                                        finish();
                                    }
                                });

                                dialogo2.show();

                            } else {

                                AlertDialog.Builder dialogo3 = new AlertDialog.Builder(this);
                                dialogo3.setTitle("Importante");
                                dialogo3.setMessage("Sexo: Femenino " + "Puntos: " + riesgo + " " + "Porcentaje: " + porcentaje + " % Tus niveles de riesgo son normales, no olvides realizar el calculo de riesgo todos los días");
                                dialogo3.setCancelable(false);
                                dialogo3.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogo1, int id) {
                                        String str = etuse.getText().toString();
                                        String userna = helper.searchPass(str);
                                        String named = helper.searchname(str);
                                        String edadd = helper.searchedad(str);
                                        String emaild = helper.searchemail(str);
                                        String teld = helper.searchtel(str);
                                        String cont1d = helper.searchcont1(str);
                                        String cont2d = helper.searchcont2(str);
                                        String gend = helper.searchgen(str);

                                        Intent i = new Intent(Vincular.this, Usuario.class);
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
                                });

                                dialogo3.show();
                            }
                        }
                    }
                }

            }
        }
    }

    public int calcularHombre(){
        String username = etuse.getText().toString();
        String edadd = helper.searchedad(username);
        int riesgo=0;
        int edad= Integer.parseInt(edadd);
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
        String colesterol = helper.searchcolt(username);
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
        String colesteHDL = helper.searchcolh(username);
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
        bpsistolica = (EditText) findViewById(R.id.etpresionVin);
        String presionBP = bpsistolica.getText().toString();
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
        String fumador = helper.searchfum(username);
        if (fumador.equals("-seleccione-")){
            Toast.makeText(this, "Seleccione un campo de Fumador", Toast.LENGTH_SHORT).show();
        }
        else if(fumador.equals("si")){
            riesgo=riesgo+(2);
        }else if(fumador.equals("no")){
            riesgo=riesgo+(0);
        }

        //********************************DIABETES*****************************************
        String diabetes = helper.searchmed(username);
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
        String username = etuse.getText().toString();
        String edadC = helper.searchedad(username);
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
        String colesterol = helper.searchcolt(username);
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
        String colesteHDL = helper.searchcolh(username);
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

        String presionBP = helper.searchpresure(username);
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
        String fumador = helper.searchfum(username);
        if (fumador.equals("-seleccione-")){
            Toast.makeText(this, "Seleccione un campo de Fumador", Toast.LENGTH_SHORT).show();
        }
        else if(fumador.equals("si")){
            riesgo=riesgo+(2);
        }else if(fumador.equals("no")){
            riesgo=riesgo+(0);
        }

        //********************************DIABETES*****************************************
        String diabetes = helper.searchmed(username);
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


    // Device scan callback.
    private ScanCallback leScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            peripheralTextView.append("Device Name: " + result.getDevice().getName() + " rssi: " + result.getRssi() + "\n");

            // auto scroll for text view
            final int scrollAmount = peripheralTextView.getLayout().getLineTop(peripheralTextView.getLineCount()) - peripheralTextView.getHeight();
            // if there is no need to scroll, scrollAmount will be <=0
            if (scrollAmount > 0)
                peripheralTextView.scrollTo(0, scrollAmount);
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    System.out.println("coarse location permission granted");
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Functionality limited");
                    builder.setMessage("Since location access has not been granted, this app will not be able to discover beacons when in the background.");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                        @Override
                        public void onDismiss(DialogInterface dialog) {
                        }

                    });
                    builder.show();
                }
                return;
            }
        }
    }

    public void startScanning() {
        System.out.println("start scanning");
        peripheralTextView.setText("");
        startScanningButton.setVisibility(View.INVISIBLE);
        stopScanningButton.setVisibility(View.VISIBLE);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                btScanner.startScan(leScanCallback);
            }
        });
    }

    public void stopScanning() {
        System.out.println("stopping scanning");
        peripheralTextView.append("Stopped Scanning");
        startScanningButton.setVisibility(View.VISIBLE);
        stopScanningButton.setVisibility(View.INVISIBLE);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                btScanner.stopScan(leScanCallback);
            }
        });
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

        Intent i = new Intent(Vincular.this, Usuario.class);
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
