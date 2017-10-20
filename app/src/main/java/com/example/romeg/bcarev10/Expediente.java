package com.example.romeg.bcarev10;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Expediente extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    TextView userE;

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

        userE = (TextView) findViewById(R.id.UserExp);
        userE.setText(username);
    }

    public void onExpedienteClick (View v)
    {
        if (v.getId() == R.id.btnsummary)
        {

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
