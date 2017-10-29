package com.example.romeg.bcarev10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DatosP extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    Button btn_editar;
    Button btn_cambiar;
    TextView tvusuario, tvnombre, tvapp, tvapm, tvnumpac, tvedad, tvemail, tvtel, tvcont1, tvcont2, tvgen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_p);

        String username = getIntent().getStringExtra("Username");
        String name = getIntent().getStringExtra("Name");
        String app = getIntent().getStringExtra("App");
        String apm = getIntent().getStringExtra("Apm");
        String edad = getIntent().getStringExtra("Edad");
        String email = getIntent().getStringExtra("Email");
        String tel = getIntent().getStringExtra("Tel");
        String cont1 = getIntent().getStringExtra("Cont1");
        String cont2 = getIntent().getStringExtra("Cont2");
        String gene = getIntent().getStringExtra("Gen");
        String numpac = getIntent().getStringExtra("Numpac");


        tvnumpac =(TextView) findViewById(R.id.numpacDatos);
        tvnumpac.setText(numpac);
        tvusuario = (TextView) findViewById(R.id.userD);
        tvusuario.setText(username);

        tvnombre = (TextView) findViewById(R.id.nomDatos);
        tvnombre.setText(name);
        tvapp = (TextView) findViewById(R.id.appDatos);
        tvapp.setText(app);
        tvapm = (TextView) findViewById(R.id.apmDatos);
        tvapm.setText(apm);

        tvedad = (TextView) findViewById(R.id.age);
        tvedad.setText(edad);
        tvemail = (TextView) findViewById(R.id.emailD);
        tvemail.setText(email);
        tvtel = (TextView) findViewById(R.id.telD);
        tvtel.setText(tel);
        tvcont1 = (TextView) findViewById(R.id.contD1);
        tvcont1.setText(cont1);
        tvcont2 = (TextView) findViewById(R.id.contD2);
        tvcont2.setText(cont2);
        tvgen = (TextView) findViewById(R.id.generoD);
        tvgen.setText(gene);

    }
    public void onDatosPClick(View v){

        if(v.getId() == R.id.btneditardatos)
        {
            String str = tvusuario.getText().toString();
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

            Intent i = new Intent(DatosP.this, Editar.class);
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
        else if(v.getId() == R.id.btncambiarcontdatos)
        {
            String str = tvusuario.getText().toString();

            Intent i = new Intent(DatosP.this, Cambiar.class);
            i.putExtra("Username", str);
            startActivity(i);
            finish();
        }
    }

    public void onBackPressed()
    {

        String str = tvusuario.getText().toString();
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

        Intent i = new Intent(DatosP.this, Usuario.class);
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
