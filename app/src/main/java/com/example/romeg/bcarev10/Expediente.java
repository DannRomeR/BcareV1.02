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
    Button verExp;

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
        userE = (TextView) findViewById(R.id.UserExp);
        userE.setText(username);

        if (fumE.equals("")||medE.equals("")||coltE.equals("")||colhE.equals("")||presE.equals("0")|| puntE.equals("0")||riskE.equals("0")){
            verExp.setVisibility(View.GONE);
        }
        else {
            verExp.setVisibility(View.VISIBLE);
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


            if(fum.isEmpty() || med.isEmpty() || colt.isEmpty() || colh.isEmpty() || presu.isEmpty() || punt.isEmpty() || risk.isEmpty())
            {
                Toast.makeText(this, "Sus datos estan incompletos, llene el formulario de la calculadora",
                        Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent i = new Intent(Expediente.this, Verexp.class);
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

            Intent i = new Intent(Expediente.this, Riesgo.class);
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
        else if(v.getId() == R.id.btncompartir)
        {

        }
    }

}
