package com.example.romeg.bcarev10;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Expediente extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    TextView userE;
    Button verExp, envExp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expediente);
        String username = getIntent().getStringExtra("Username");
        String name = getIntent().getStringExtra("Name");
        String edad = getIntent().getStringExtra("Edad");
        String email = getIntent().getStringExtra("Email");
        String tel = getIntent().getStringExtra("Tel");
        String cont1 = getIntent().getStringExtra("Cont1");
        String cont2 = getIntent().getStringExtra("Cont2");
        String gene = getIntent().getStringExtra("Gen");

        String fumE = getIntent().getStringExtra("Fum");
        String medE = getIntent().getStringExtra("Med");
        String coltE = getIntent().getStringExtra("Colt");
        String colhE = getIntent().getStringExtra("Colh");
        String presE = getIntent().getStringExtra("Presu");
        String puntE = getIntent().getStringExtra("Punt");
        String riskE = getIntent().getStringExtra("Risk");

        verExp = (Button) findViewById(R.id.btnsummary);
        envExp = (Button) findViewById(R.id.btncompartir);
        userE = (TextView) findViewById(R.id.UserExp);
        userE.setText(username);

        if (fumE.equals("Sin dato")||medE.equals("Sin dato")||coltE.equals("Sin dato")||colhE.equals("Sin dato")){
            verExp.setVisibility(View.GONE);
            envExp.setVisibility(View.GONE);
        }
        else {
            verExp.setVisibility(View.VISIBLE);
            envExp.setVisibility(View.VISIBLE);
        }

    }

    public void onExpedienteClick (View v)
    {
        if (v.getId() == R.id.btnsummary)
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


            if(fum.equals("Sin dato") || med.equals("Sin dato") || colt.equals("Sin dato") || colh.equals("Sin dato"))
            {
                Toast.makeText(this, "Sus datos estan incompletos, llene el formulario de la calculadora",
                        Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent i = new Intent(Expediente.this, Verexp.class);
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
                finish();
            }



        }
        else if(v.getId() == R.id.btncalcular)
        {
            String str = userE.getText().toString();
            String userna = helper.searchPass(str);
            String named = helper.searchname(str);
            String edadd = helper.searchedad(str);
            String emaild = helper.searchemail(str);
            String teld = helper.searchtel(str);
            String cont1d = helper.searchcont1(str);
            String cont2d = helper.searchcont2(str);
            String gend = helper.searchgen(str);
            String appd = helper.searchapp(str);
            String apmd = helper.searchapm(str);
            String numpacd = helper.searchnumpac(str);

            Intent i = new Intent(Expediente.this, Riesgo.class);
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
        else if(v.getId() == R.id.btncompartir)
        {


        }
    }

    public void onBackPressed()
    {

        String str = userE.getText().toString();
        String userna = helper.searchPass(str);
        String named = helper.searchname(str);
        String edadd = helper.searchedad(str);
        String emaild = helper.searchemail(str);
        String teld = helper.searchtel(str);
        String cont1d = helper.searchcont1(str);
        String cont2d = helper.searchcont2(str);
        String gend = helper.searchgen(str);
        String appd = helper.searchapp(str);
        String apmd = helper.searchapm(str);
        String numpacd = helper.searchnumpac(str);

        Intent i = new Intent(Expediente.this, Usuario.class);
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
