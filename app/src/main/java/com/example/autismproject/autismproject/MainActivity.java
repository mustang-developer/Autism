package com.example.autismproject.autismproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button matchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matchBtn = (Button) findViewById(R.id.matchBtn);
        matchBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(v==matchBtn){
            Intent intentMain = new Intent(this ,
                    Match.class);
            this.startActivity(intentMain);
        }
    }
}
