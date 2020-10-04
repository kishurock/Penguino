package com.ivyhacks.penguino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LoanCalculate extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    PieChart pieChart;
    TextView txtName;
    TextView txtInterest;
    TextView txtRate;
    TextView txtTotal;
    TextView txtAmount;
    TextView txtterm;
    TextView changeData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(
                getString(R.string.penguino_preferences),
                Context.MODE_PRIVATE
        );
        setContentView(R.layout.activity_loan_calculate);

        pieChart = findViewById(R.id.pieChart);
        txtName = findViewById(R.id.txtName);
        txtInterest = findViewById(R.id.txtInterest);
        txtRate = findViewById(R.id.txtRate);
        txtAmount = findViewById(R.id.txtamount);
        txtterm = findViewById(R.id.txtTerm);
        txtTotal = findViewById(R.id.txtTotal);
        changeData=findViewById(R.id.changedata);
        String name = sharedPreferences.getString("name", "null");
        String loanamount = sharedPreferences.getString("amount", "null");
        String interest = sharedPreferences.getString("interestrate", "null");
        String loanterm = sharedPreferences.getString("loanterm", "null");

        txtName.setText(name);

        float amount = Float.valueOf(loanamount);
        float interestrate = Float.valueOf(interest);
        float term = Float.valueOf(loanterm);
        float simpleinterest = (amount * interestrate * term) / 100;
        float total = simpleinterest + amount;
        String totalamount = String.valueOf(total);
        txtterm.setText("for " + loanterm + " years");
        txtAmount.setText(loanamount);
        txtInterest.setText(String.valueOf(simpleinterest));
        txtRate.setText(interest + "%");
        txtTotal.setText("+ "+totalamount);

        ArrayList<PieEntry> members = new ArrayList<>();
        members.add(new PieEntry(simpleinterest, "Interest"));
        members.add(new PieEntry(amount, "Amount"));
        int color1 = ContextCompat.getColor(this, R.color.maincolor);
        int color2 = ContextCompat.getColor(this, R.color.colorAccent);
        PieDataSet pieDataSet = new PieDataSet(members, " ");
        pieDataSet.setColors(color1, color2);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.setHoleRadius(55);
        pieChart.setTransparentCircleRadius(60);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText(String.valueOf(total));
        pieChart.setCenterTextSize(22f);
        pieChart.setCenterTextColor(color1);
        pieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD);
        pieChart.animate();

changeData.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        sharedPreferences.edit().putBoolean("loggedIn", false)
                .apply();
        startActivity(new Intent(LoanCalculate.this, FillDetails.class));
        finish();
    }
});
    }
}