package com.example.romeg.bcarev10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Verexp extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);
    TextView tvnombreV, tvgeneroV, tvedadV, tvemailV, tvfumV, tvmedV, tvcoltV, tvcolhV, tvpresV, tvpuntV, tvriskV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verexp);
        String username = getIntent().getStringExtra("Username");
        String named = helper.searchname(username);
        String edadd = helper.searchedad(username);
        String emaild = helper.searchemail(username);
        String gend = helper.searchgen(username);
        String fum = helper.searchfum(username);
        String med = helper.searchmed(username);
        String colt = helper.searchcolt(username);
        String colh = helper.searchcolh(username);
        String presu = helper.searchpresure(username);
        String punt = helper.searchpunt(username);
        String risk = helper.searchrisk(username);


        tvnombreV = (TextView) findViewById(R.id.tvnomExp);
        tvnombreV.setText(named);
        tvgeneroV = (TextView) findViewById(R.id.tvgeneroE);
        tvgeneroV.setText(gend);
        tvedadV = (TextView) findViewById(R.id.tvageE);
        tvedadV.setText(edadd);
        tvemailV = (TextView) findViewById(R.id.tvemailE);
        tvemailV.setText(emaild);
        tvfumV = (TextView) findViewById(R.id.tvfumE);
        tvfumV.setText(fum);
        tvmedV = (TextView) findViewById(R.id.tvdiaE);
        tvmedV.setText(med);
        tvcoltV = (TextView) findViewById(R.id.tvcoltE);
        tvcoltV.setText(colt);
        tvcolhV = (TextView) findViewById(R.id.tvcolh);
        tvcolhV.setText(colh);
        tvpresV = (TextView) findViewById(R.id.tvpresu);
        tvpresV.setText(presu);
        tvpuntV = (TextView) findViewById(R.id.tvpunt);
        tvpuntV.setText(punt);
        tvriskV = (TextView) findViewById(R.id.tvrisk);
        tvriskV.setText(risk);


    }

    public void onVerClick(View v)
    {
        if(v.getId()==R.id.btneditarexp)
        {
            String str = getIntent().getStringExtra("Username");
            String named = helper.searchname(str);
            String edadd = helper.searchedad(str);
            String emaild = helper.searchemail(str);
            String gend = helper.searchgen(str);
            String fum = helper.searchfum(str);
            String med = helper.searchmed(str);
            String colt = helper.searchcolt(str);
            String colh = helper.searchcolh(str);
            String presu = helper.searchpresure(str);
            String punt = helper.searchpunt(str);
            String risk = helper.searchrisk(str);

            Intent i = new Intent(Verexp.this, Editarexped.class);
            i.putExtra("Username", str);
            i.putExtra("Name", named);
            i.putExtra("Edad", edadd);
            i.putExtra("Email", emaild);
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
        else if(v.getId() == R.id.btnexportarexp)
        {

        }

    }


}
