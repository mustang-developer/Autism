package com.example.autismproject.autismproject;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.annotation.IntRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class Match extends AppCompatActivity implements View.OnClickListener {

    private ImageView keyImg;
    private ImageView[] matchImgs;
    private int matchNum;
    private String[] shapeList = {"circle", "fish", "flower", "moon", "square", "star", "triangle"};
    LinearLayout matchLayout;
    GifImageView clap;
    Random rand;
    int keyIndex;

    private Rect mBounds;
    private Paint mBorderPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        rand = new Random();
        Display display = getWindowManager().getDefaultDisplay();

        // the key image:
        keyImg = (ImageView) findViewById(R.id.keyImg);
        String keySource = randomShape();
        keyImg.setImageResource(getResources().getIdentifier("com.example.autismproject.autismproject:drawable/" + keySource, null, null));
        Toast.makeText(getApplicationContext(), keySource, Toast.LENGTH_SHORT).show();
        keyImg.setPadding(5, 5, 0, 0);

        // setting the keyImg_width & keyImg_height relative to the screen size
        keyImg.setLayoutParams(new LinearLayout.LayoutParams(((int) Math.abs(display.getWidth() * 0.75)), ((int) Math.abs(display.getHeight() * 0.35))));

        // setting the keyImg_x & keyImg_y relative to the screen size
        keyImg.setX((int) Math.abs((display.getWidth()) * 0.1));
        keyImg.setY(0);

        matchLayout = new LinearLayout(this);
        matchLayout.setGravity(LinearLayout.HORIZONTAL);
        matchLayout.setGravity(Gravity.CENTER);
        matchLayout.setLayoutParams(new LinearLayout.LayoutParams(display.getWidth(), ((int) Math.abs(display.getHeight() * 0.40))));

        matchImgs = new ImageView[4];
        matchNum = 2;
        String imageSource;
        keyIndex = rand.nextInt(matchNum);
        for (int i = 0; i < matchNum; i++) {
            matchImgs[i] = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(((int) Math.abs(display.getWidth() * 0.35)), ((int) Math.abs(display.getHeight() * 0.2)));
            if (i == keyIndex) {
                params.setMargins(0, 0, 100, 0);
                imageSource = keySource;
            } else {
                do {
                    imageSource = randomShape();
                }while(imageSource==keySource);
            }
            matchImgs[i].setImageResource(getResources().getIdentifier("com.example.autismproject.autismproject:drawable/" + imageSource, null, null));

            matchImgs[i].setOnClickListener(this);
            matchImgs[i].setLayoutParams(params);
            matchImgs[i].setX((int) Math.abs(matchLayout.getWidth() * 0.4));
            matchImgs[i].setY(0);
            matchLayout.addView(matchImgs[i]);
        }

        clap = new GifImageView(this);
        clap.setImageResource(R.drawable.clapping);
        clap.setVisibility(View.INVISIBLE);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.matchLinLayout);
        linearLayout.addView(matchLayout);
        linearLayout.addView(clap);

    }

    @Override
    public void onClick(View view) {

        if (((ImageView) view).getDrawable().getConstantState() == keyImg.getDrawable().getConstantState()) {
            Animation zoom_out = new ScaleAnimation(
                    1f, 0f, // Start and end values for the X axis scaling
                    1f, 0f, // Start and end values for the Y axis scaling
                    Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                    Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
            zoom_out.setFillAfter(true); // Needed to keep the result of the animation
            zoom_out.setDuration(800);
            for (int i = 0; i < matchNum; i++) {
                if (i != keyIndex)
//                    matchImgs[i].setVisibility(View.INVISIBLE);
                    matchImgs[i].startAnimation(zoom_out);
            }

            clap.setVisibility(View.VISIBLE);
            Animation zoom_in = new ScaleAnimation(
                    1f, 1.3f, // Start and end values for the X axis scaling
                    1f, 1.3f, // Start and end values for the Y axis scaling
                    Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                    Animation.RELATIVE_TO_SELF, 1.2f); // Pivot point of Y scaling
            zoom_in.setFillAfter(true); // Needed to keep the result of the animation
            zoom_in.setDuration(1500);
            clap.startAnimation(zoom_in);
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.afarinclap);
            mp.start();

        } else {
            AnimationSet waggle = new AnimationSet(true);
            waggle.setFillEnabled(true);

            RotateAnimation anim1 = new RotateAnimation(0.0f, 17.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim1.setInterpolator(new LinearInterpolator());
            anim1.setDuration(500); //in milliseconds
            waggle.addAnimation(anim1);

            RotateAnimation anim2 = new RotateAnimation(0.0f, -34.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim2.setInterpolator(new LinearInterpolator());
            anim2.setDuration(1000); //in milliseconds
            anim2.setStartOffset(500);
            waggle.addAnimation(anim2);

            RotateAnimation anim3 = new RotateAnimation(0.0f, 17.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim3.setInterpolator(new LinearInterpolator());
            anim3.setDuration(700); //in milliseconds
            anim3.setStartOffset(1500);
            waggle.addAnimation(anim3);
            view.startAnimation(waggle);
        }
    }

    String randomShape() {
        return shapeList[rand.nextInt(6)] + (rand.nextInt(4) + 1);
    }
}
