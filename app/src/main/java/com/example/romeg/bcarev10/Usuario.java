package com.example.romeg.bcarev10;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Usuario extends AppCompatActivity {

    NotificationCompat.Builder notificación;
    private static final int idUnica= 51623;
    // Session Manager Class
    SessionManagement session;
    DBHelper helper = new DBHelper(this);
    TextView tvuserD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        session = new SessionManagement(getApplicationContext());

        String usern = getIntent().getStringExtra("Username");



        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        session.checkLogin();
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
        // name
        String name = user.get(SessionManagement.KEY_NAME);
        // email
        String email = user.get(SessionManagement.KEY_EMAIL);

        tvuserD = (TextView) findViewById(R.id.bieuser);
        tvuserD.setText(name);

        String fum = helper.searchfum(name);
        String med = helper.searchmed(name);
        String colt = helper.searchcolt(name);
        String colh = helper.searchcolh(name);
        String punt = helper.searchpunt(name);
        int puntt = Integer.parseInt(punt);

        notificación = new NotificationCompat.Builder(this);
        notificación.setAutoCancel(true);

        if (puntt > 10 && puntt < 50)
        {
            notificación.setSmallIcon(R.drawable.doctor);
            notificación.setTicker("Su nivel de riesgo es alto no olvide revisar su presión");
            notificación.setWhen(System.currentTimeMillis());
            notificación.setContentTitle("Bcare");
            notificación.setContentText("Su nivel de riesgo es alto no olvide revisar su presión");

            Intent i = new Intent(Usuario.this, Riesgo.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(Usuario.this,0, i,PendingIntent.FLAG_UPDATE_CURRENT);
            notificación.setContentIntent(pendingIntent);

            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            nm.notify(idUnica, notificación.build());

        }
        else if (fum.equals("Sin dato") && med.equals("Sin dato") && colt.equals("Sin dato") && colh.equals("Sin dato"))
        {

            notificación.setSmallIcon(R.drawable.doctor);
            notificación.setTicker("No olvide llenar la calculadora de riesgo");
            notificación.setWhen(System.currentTimeMillis());
            notificación.setContentTitle("Bcare");
            notificación.setContentText(" No olvide llenar el formulario de riesgo");

            Intent i = new Intent(Usuario.this, Riesgo.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(Usuario.this,0, i,PendingIntent.FLAG_UPDATE_CURRENT);
            notificación.setContentIntent(pendingIntent);

            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            nm.notify(idUnica, notificación.build());
        }

    }

    public void onMenuClick(View v) {

        if (v.getId() == R.id.btnexpedientemenu) {
            String str = tvuserD.getText().toString();
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
            String fum = helper.searchfum(str);
            String med = helper.searchmed(str);
            String colt = helper.searchcolt(str);
            String colh = helper.searchcolh(str);
            String presu = helper.searchpresure(str);
            String punt = helper.searchpunt(str);
            String risk = helper.searchrisk(str);
            String numpacd = helper.searchnumpac(str);



            Intent i = new Intent(Usuario.this, Expediente.class);
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

        } else if (v.getId() == R.id.btnemergenciamenu) {
            String str = tvuserD.getText().toString();
            String userna = helper.searchPass(str);
            String teln = helper.searchtel(str);
            String cont1 = helper.searchcont1(str);
            String cont2 = helper.searchcont2(str);

            Intent i = new Intent(Usuario.this, Emergencia.class);
            i.putExtra("Username", str);
            i.putExtra("Tel", teln);
            i.putExtra("Cont1", cont1);
            i.putExtra("Cont2", cont2);
            startActivity(i);


        } else if (v.getId() == R.id.btnvincularmenu) {
            String str = tvuserD.getText().toString();
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
            String fum = helper.searchfum(str);
            String med = helper.searchmed(str);
            String colt = helper.searchcolt(str);
            String colh = helper.searchcolh(str);
            String presu = helper.searchpresure(str);
            String punt = helper.searchpunt(str);
            String risk = helper.searchrisk(str);
            String numpacd = helper.searchnumpac(str);



            Intent i = new Intent(Usuario.this, Vincular.class);
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


        } else if (v.getId() == R.id.btndatospersonalesmenu) {
            String str = tvuserD.getText().toString();
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



            Intent i = new Intent(Usuario.this, DatosP.class);
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


        }else if(v.getId() == R.id.btnsalirmenu){

            session.logoutUser();
        }
}
    public void loadSlides(View view)
    {
        String str = tvuserD.getText().toString();
        String userna = helper.searchPass(str);
        new PreferenceManager(this).clearPreference();
        Intent i = new Intent(Usuario.this, Fast2.class);
        i.putExtra("Username", str);

        startActivity(i);
        finish();
    }



    public void onBackPressed()
    {
        String str = tvuserD.getText().toString();
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

        Intent i = new Intent(Usuario.this, Usuario.class);
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
