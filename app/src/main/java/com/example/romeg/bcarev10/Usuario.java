package com.example.romeg.bcarev10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Usuario extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    TextView tvuserD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        String usern = getIntent().getStringExtra("Username");
        tvuserD = (TextView) findViewById(R.id.bieuser);
        tvuserD.setText(usern);
    }

    public void onMenuClick(View v) {

        if (v.getId() == R.id.btnexpedientemenu) {
            String str = tvuserD.getText().toString();
            String userna = helper.searchPass(str);
            String named = helper.searchname(str);
            String edadd = helper.searchedad(str);
            String emaild = helper.searchemail(str);
            String teld = helper.searchtel(str);
            String cont1d = helper.searchcont1(str);
            String cont2d = helper.searchcont2(str);
            String gend = helper.searchgen(str);

            Intent i = new Intent(Usuario.this, Expediente.class);
            i.putExtra("Username", str);
            i.putExtra("Name", named);
            i.putExtra("Edad", edadd);
            i.putExtra("Email", emaild);
            i.putExtra("Tel", teld);
            i.putExtra("Cont1", cont1d);
            i.putExtra("Cont2", cont2d);
            i.putExtra("Gen", gend);
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
            startActivity(new Intent(this, Vincular.class));
            finish();
        } else if (v.getId() == R.id.btndatospersonalesmenu) {
            String str = tvuserD.getText().toString();
            String userna = helper.searchPass(str);
            String namen = helper.searchname(str);
            String edadn = helper.searchedad(str);
            String emailn = helper.searchemail(str);
            String teln = helper.searchtel(str);
            String cont1 = helper.searchcont1(str);
            String cont2 = helper.searchcont2(str);
            String genn = helper.searchgen(str);


            Intent i = new Intent(Usuario.this, DatosP.class);
            i.putExtra("Username", str);
            i.putExtra("Name", namen);
            i.putExtra("Edad", edadn);
            i.putExtra("Email", emailn);
            i.putExtra("Tel", teln);
            i.putExtra("Cont1", cont1);
            i.putExtra("Cont2", cont2);
            i.putExtra("Gen", genn);
            startActivity(i);
    }

}



    public void loadSlides(View view)
    {
        new PreferenceManager(this).clearPreference();
        startActivity(new Intent(this, Fast2.class));
        finish();
    }
}
