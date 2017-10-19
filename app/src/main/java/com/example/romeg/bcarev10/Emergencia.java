package com.example.romeg.bcarev10;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Emergencia extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    Button btnllam;
    TextView userllam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencia);
        String username = getIntent().getStringExtra("Username");

        userllam = (TextView) findViewById(R.id.usercall);
        userllam.setText(username);
    }

    public void onCallClick(View v) {

        String username = getIntent().getStringExtra("Username");
        String telefono = getIntent().getStringExtra("Tel");
        String cont1 = getIntent().getStringExtra("Cont1");
        String cont2 = getIntent().getStringExtra("Cont2");

        String tel = helper.searchtel(username);
        String contac1 = helper.searchcont1(username);
        String contac2 = helper.searchcont2(username);

        if (v.getId() == R.id.btncall) {

            llamar(telefono);
        }
    }

    public void onBackPressed() {
        startActivity(new Intent(getBaseContext(), Usuario.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();
    }

    public void llamar(String tel) {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel)));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
