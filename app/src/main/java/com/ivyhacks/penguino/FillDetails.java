package com.ivyhacks.penguino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class FillDetails extends AppCompatActivity {
EditText edtfirname;
    EditText edtlasname;
    EditText edtintRate;
    EditText edtloanamo;
    EditText edtloanter;
    Button btncal;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(
                getString(R.string.penguino_preferences),
                Context.MODE_PRIVATE
        );
        setContentView(R.layout.activity_fill_details);
        edtfirname=findViewById(R.id.edtFirName);
        edtlasname=findViewById(R.id.edtlasName);
        edtintRate=findViewById(R.id.edtintRate);
        edtloanter=findViewById(R.id.edtloanterm);
        edtloanamo=findViewById(R.id.edtloanamo);
        btncal=findViewById(R.id.btncal);

        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtfirname.length()<3){
                    edtfirname.setError("First Name should be greater than 2 characters");
                }
                if(edtlasname.length()<3){
                    edtlasname.setError("Last Name should be greater than 2 characters");
                }
                if(edtloanamo.length()<1){
                    edtloanamo.setError("Loan amount should be greater than 1");
                }
                if(edtloanter.length()<1){
                    edtloanter.setError("Loan Term should be greater than 1");
                }
                if(edtintRate.length()<=0){
                    edtintRate.setError("Interest Rate should be greater than 0");
                }else{
                    sharedPreferences.edit().putBoolean("loggedIn", true)
                            .apply();
                    sharedPreferences.edit().putString("name", edtfirname.getText().toString()+" "+edtlasname.getText().toString())
                            .apply();
                    sharedPreferences.edit().putString("amount", edtloanamo.getText().toString())
                            .apply();
                    sharedPreferences.edit().putString("loanterm", edtloanter.getText().toString())
                            .apply();
                    sharedPreferences.edit().putString("interestrate", edtintRate.getText().toString())
                            .apply();

                    startActivity(new Intent(FillDetails.this, LoanCalculate.class));
                    finish();
                }
            }
        });

    }
}