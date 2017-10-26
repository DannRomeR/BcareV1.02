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

public class Fast2 extends AppCompatActivity  implements View.OnClickListener{

    private ViewPager mPager;
    private int[] layouts = {R.layout.face, R.layout.arms, R.layout.speak, R.layout.time};
    private MpagerAdapter mpagerAdapter;

    private LinearLayout Dost_Layout;
    private ImageView[] dots;
    private Button BnNext,BnSkip;



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
        Intent i = new Intent(Fast2.this, Usuario.class);
        i.putExtra("Username", username);

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
            loadEmergencia();
            new PreferenceManager(this).writePreference();
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
}
