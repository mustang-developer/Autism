package com.example.autismproject.autismproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Match extends AppCompatActivity implements View.OnClickListener {

    private ImageView keyImg;
    private ImageView[] matchImgs;
    private int matchNum;
    LinearLayout matchLayout;
    ImageView clap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Display display = getWindowManager().getDefaultDisplay();

        // the key image:
        keyImg = (ImageView) findViewById(R.id.keyImg);
        keyImg.setImageResource(R.drawable.fish1);
        keyImg.setPadding(5, 5, 0, 0);

        // setting the keyImg_width & keyImg_height relative to the screen size
        keyImg.setLayoutParams(new LinearLayout.LayoutParams(((int) Math.abs(display.getWidth() * 0.8)), ((int) Math.abs(display.getHeight() * 0.35))));

        // setting the keyImg_x & keyImg_y relative to the screen size
        keyImg.setX((int) Math.abs((display.getWidth()) * 0.1));
        keyImg.setY(0);

        LinearLayout matchLayout = new LinearLayout(this);
        matchLayout.setGravity(LinearLayout.HORIZONTAL);
        matchLayout.setGravity(Gravity.CENTER);
        matchLayout.setLayoutParams(new LinearLayout.LayoutParams(display.getWidth(), ((int) Math.abs(display.getHeight() * 0.40))));

        matchImgs = new ImageView[4];
        matchNum = 2;
        for (int i = 0; i < matchNum; i++) {
            matchImgs[i] = new ImageView(this);
            matchImgs[i].setImageResource(getResources().getIdentifier("com.example.autismproject.autismproject:drawable/fish" + (i + 1), null, null));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(((int) Math.abs(display.getWidth() * 0.35)), ((int) Math.abs(display.getHeight() * 0.2)));
            //margin(left, top, right, bottom)
            if (i == 0)
                params.setMargins(0, 0, 100, 0);
            matchImgs[i].setOnClickListener(this);
            matchImgs[i].setLayoutParams(params);
            matchImgs[i].setX((int) Math.abs(matchLayout.getWidth() * 0.4));
            matchImgs[i].setY(0);
            matchLayout.addView(matchImgs[i]);
        }

        clap= new ImageView(this);
        clap.setImageResource(R.drawable.clap);
        clap.setVisibility(View.INVISIBLE);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.matchLinLayout);
        linearLayout.addView(matchLayout);
        linearLayout.addView(clap);

    }

    @Override
    public void onClick(View view) {
        if (view == matchImgs[0]) {
//            Toast.makeText(getApplicationContext(), "-" , Toast.LENGTH_SHORT).show();
            matchImgs[1].setVisibility(View.INVISIBLE);
            clap.setVisibility(View.VISIBLE);
//            Animation zoomin = AnimationUtils.loadAnimation(this, R.anim.zoomin);

        } else {
            AnimationSet as = new AnimationSet(true);
            as.setFillEnabled(true);

            RotateAnimation anim1 = new RotateAnimation(0.0f, 25.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim1.setInterpolator(new LinearInterpolator());
            anim1.setDuration(800); //in milliseconds
            as.addAnimation(anim1);

            RotateAnimation anim2 = new RotateAnimation(0.0f, -50.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim2.setInterpolator(new LinearInterpolator());
            anim2.setDuration(2000); //in milliseconds
            anim2.setStartOffset(700);
            as.addAnimation(anim2);

            RotateAnimation anim3 = new RotateAnimation(0.0f, 25.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim3.setInterpolator(new LinearInterpolator());
            anim3.setDuration(1000); //in milliseconds
            anim3.setStartOffset(2500);
            as.addAnimation(anim3);

            view.startAnimation(as);
        }

    }
}
