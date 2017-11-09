package com.example.romeg.bcarev10;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class Expediente extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    TextView userE;
    Button verExp, envExp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expediente);
        String username = getIntent().getStringExtra("Username");

        String fumE = getIntent().getStringExtra("Fum");
        String medE = getIntent().getStringExtra("Med");
        String coltE = getIntent().getStringExtra("Colt");
        String colhE = getIntent().getStringExtra("Colh");


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


            String str = getIntent().getStringExtra("Username");
            String numpacd = helper.searchnumpac(str);

            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean sucess = jsonObject.getBoolean("success");
                        if (sucess)
                        {
                            String str = getIntent().getStringExtra("Username");
                            final String named = helper.searchname(str);
                            final String edadd = helper.searchedad(str);
                            final String emaild = helper.searchemail(str);

                            final String gend = helper.searchgen(str);
                            final String fum = helper.searchfum(str);
                            final String med = helper.searchmed(str);
                            final String colt = helper.searchcolt(str);
                            final String colh = helper.searchcolh(str);
                            final String presu = helper.searchpresure(str);
                            final String punt = helper.searchpunt(str);
                            final String risk = helper.searchrisk(str);
                            final String appd = helper.searchapp(str);
                            final String apmd = helper.searchapm(str);
                            final String numpacd = helper.searchnumpac(str);
                            int edadint = Integer.parseInt(edadd);
                            int press = Integer.parseInt(presu);
                            int puntint = Integer.parseInt(punt);
                            int riskint = Integer.parseInt(risk);


                            Response.Listener<String> resposListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonResponse = new JSONObject(response);
                                        boolean success = jsonResponse.getBoolean("success");
                                        if (success)
                                        {
                                            Toast pass = Toast.makeText(Expediente.this, "El expediente ha sido enviado, pronto lo verá su médico", Toast.LENGTH_LONG);
                                            pass.show();
                                        }
                                        else
                                        {
                                            Toast pass = Toast.makeText(Expediente.this, "Operación no exitosa", Toast.LENGTH_SHORT);
                                            pass.show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            UpdateRequest updateRequest = new UpdateRequest(emaild,named,appd,apmd,edadint,gend,fum,med,colt,colh,press,puntint,riskint,resposListener);
                            RequestQueue queue = Volley.newRequestQueue(Expediente.this);
                            queue.add(updateRequest);

                        }
                        else
                        {
                            String str = getIntent().getStringExtra("Username");
                            final String named = helper.searchname(str);
                            final String edadd = helper.searchedad(str);
                            final String emaild = helper.searchemail(str);

                            final String gend = helper.searchgen(str);
                            final String fum = helper.searchfum(str);
                            final String med = helper.searchmed(str);
                            final String colt = helper.searchcolt(str);
                            final String colh = helper.searchcolh(str);
                            final String presu = helper.searchpresure(str);
                            final String punt = helper.searchpunt(str);
                            final String risk = helper.searchrisk(str);
                            final String appd = helper.searchapp(str);
                            final String apmd = helper.searchapm(str);
                            final String numpacd = helper.searchnumpac(str);
                            int edadint = Integer.parseInt(edadd);
                            int press = Integer.parseInt(presu);
                            int puntint = Integer.parseInt(punt);
                            int riskint = Integer.parseInt(risk);

                            Response.Listener<String> respoListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonResponse = new JSONObject(response);
                                        boolean success = jsonResponse.getBoolean("success");
                                        if (success)
                                        {
                                            Toast pass = Toast.makeText(Expediente.this, "El expediente ha sido enviado, pronto lo verá su médico", Toast.LENGTH_LONG);
                                            pass.show();
                                        }
                                        else
                                        {
                                            Toast pass = Toast.makeText(Expediente.this, "Operación no exitosa", Toast.LENGTH_SHORT);
                                            pass.show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            RegisterRequest registerRequest = new RegisterRequest(emaild,numpacd,named,appd,apmd,edadint,gend,fum,med,colt,colh,press,puntint,riskint,respoListener);
                            RequestQueue queue = Volley.newRequestQueue(Expediente.this);
                            queue.add(registerRequest);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            LoginRequest loginRequest = new LoginRequest(numpacd,responseListener);
            RequestQueue queue = Volley.newRequestQueue(Expediente.this);
            queue.add(loginRequest);



        }
    }

    public void onBackPressed()
    {

        String str = userE.getText().toString();
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
