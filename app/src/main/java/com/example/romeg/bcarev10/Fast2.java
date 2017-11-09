package com.example.romeg.bcarev10;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fast2 extends AppCompatActivity  implements View.OnClickListener{

    private ViewPager mPager;
    private int[] layouts = {R.layout.face, R.layout.arms, R.layout.speak, R.layout.time};
    private MpagerAdapter mpagerAdapter;
    private LinearLayout Dost_Layout;
    private ImageView[] dots;
    private Button BnNext,BnSkip;
    DBHelper helper = new DBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (new PreferenceManager(this).checkPreference())
        {
            loadHome();
        }

        if(Build.VERSION.SDK_INT>19)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setContentView(R.layout.activity_fast2);

        mPager = (ViewPager) findViewById(R.id.viewPager);
        mpagerAdapter = new MpagerAdapter(layouts, this);
        mPager.setAdapter(mpagerAdapter);

        Dost_Layout = (LinearLayout) findViewById(R.id.dotsLayout);
        BnNext = (Button) findViewById(R.id.bnNext);
        BnSkip = (Button) findViewById(R.id.bnSkip);
        BnNext.setOnClickListener(this);
        BnSkip.setOnClickListener(this);
        createDots(0);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);

                if (position == layouts.length-1)
                {
                    BnNext.setText("Emergencia");
                    BnSkip.setVisibility(View.INVISIBLE);

                }
                else
                {
                    BnNext.setText("Si");
                    BnSkip.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void createDots(int current_position)
    {
        if(Dost_Layout != null)
            Dost_Layout.removeAllViews();

        dots = new ImageView[layouts.length];

        for(int i = 0; i<layouts.length; i++)
        {
            dots[i] = new ImageView(this);
            if(i == current_position)
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dots_selected));
            }
            else
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dots_default));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);

            Dost_Layout.addView(dots[i],params);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bnNext:
                loadNextSlide();
                break;
            case R.id.bnSkip:
                loadHome();
                new PreferenceManager(this).writePreference();
                break;
        }
    }

    private void loadHome()
    {
        String username = getIntent().getStringExtra("Username");
        String str = getIntent().getStringExtra("Username");
        String userna = helper.searchPass(str);
        String named = helper.searchname(str);
        String edadd = helper.searchedad(str);
        String emaild = helper.searchemail(str);
        String teld = helper.searchtel(str);
        String cont1d = helper.searchcont1(str);
        String cont2d = helper.searchcont2(str);
        String gend = helper.searchgen(str);

        Intent i = new Intent(Fast2.this, Usuario.class);
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
    private void loadNextSlide()
    {
        int next_slide = mPager.getCurrentItem()+1;

        if (next_slide<layouts.length)
        {
            mPager.setCurrentItem(next_slide);
        }
        else
        {
            String usestr = getIntent().getStringExtra("Username");
            String risk = helper.searchrisk(usestr);
            int riesgo = Integer.parseInt(risk);
            if(riesgo > 10 && riesgo < 50){
                loadEmergencia();
                new PreferenceManager(this).writePreference();
            }else if (riesgo == 120)
            {
                Toast.makeText(this, "No ha realizado el cálculo de riesgo para realizar una llamada",
                        Toast.LENGTH_LONG).show();
                String str = getIntent().getStringExtra("Username");
                String userna = helper.searchPass(str);
                String named = helper.searchname(str);
                String edadd = helper.searchedad(str);
                String emaild = helper.searchemail(str);
                String teld = helper.searchtel(str);
                String cont1d = helper.searchcont1(str);
                String cont2d = helper.searchcont2(str);
                String gend = helper.searchgen(str);

                Intent i = new Intent(Fast2.this, Usuario.class);
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
            else
            {
                Toast.makeText(this, "Su puntaje de riesgo es bajo, no hay necesidad de una Emergencia",
                        Toast.LENGTH_LONG).show();
                String str = getIntent().getStringExtra("Username");
                String userna = helper.searchPass(str);
                String named = helper.searchname(str);
                String edadd = helper.searchedad(str);
                String emaild = helper.searchemail(str);
                String teld = helper.searchtel(str);
                String cont1d = helper.searchcont1(str);
                String cont2d = helper.searchcont2(str);
                String gend = helper.searchgen(str);

                Intent i = new Intent(Fast2.this, Usuario.class);
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
    }

    private void loadEmergencia()
    {
        String username = getIntent().getStringExtra("Username");
        Intent i = new Intent(Fast2.this, Emergencia.class);
        i.putExtra("Username", username);

        startActivity(i);
        finish();
    }

    public void onBackPressed()
    {

        String str = getIntent().getStringExtra("Username");
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

        Intent i = new Intent(Fast2.this, Usuario.class);
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
