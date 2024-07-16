package com.codekiller.bankingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codekiller.bankingsystem.DB.MyDataBase;

public class MainActivity extends AppCompatActivity {

    Button createBtn, updateBtn, withdrawBtn, depositBtn, transactionBtn, balanceBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createBtn = findViewById(R.id.create_btn);
        updateBtn = findViewById(R.id.update_btn);
        withdrawBtn = findViewById(R.id.withdrwal_btn);
        depositBtn = findViewById(R.id.deposit_btn);
        transactionBtn = findViewById(R.id.transaction_btn);
        balanceBtn = findViewById(R.id.balance_btn);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateActivity.class).putExtra("activity_code", "create"));
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateActivity.class).putExtra("activity_code", "update"));
            }
        });
        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MoneyActivity.class).putExtra("activity_code", "withdraw"));
            }
        });
        depositBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MoneyActivity.class).putExtra("activity_code", "deposit"));
            }
        });
        transactionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MoneyActivity.class).putExtra("activity_code", "transact"));
            }
        });

        balanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MoneyActivity.class).putExtra("activity_code", "balance"));
            }
        });
    }
}
