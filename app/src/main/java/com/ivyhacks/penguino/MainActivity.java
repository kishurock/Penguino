package com.ivyhacks.penguino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgforward;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(
                getString(R.string.penguino_preferences),
                Context.MODE_PRIVATE
        );
        setContentView(R.layout.activity_main);
        final Boolean loggedIn = sharedPreferences.getBoolean("loggedIn", false);
        imgforward=findViewById(R.id.imgforward);
        imgforward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loggedIn) {
                    Intent intent = new Intent(MainActivity.this, LoanCalculate.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this, FillDetails.class);
                    startActivity(intent);
                }
                finish();
            }
        });
    }
}