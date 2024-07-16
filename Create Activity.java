package com.codekiller.bankingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.codekiller.bankingsystem.DB.MyDataBase;
import com.codekiller.bankingsystem.Utils.UtilClass;
import com.google.android.material.textfield.TextInputLayout;

import static com.codekiller.bankingsystem.DB.MyDataBase.AGE;
import static com.codekiller.bankingsystem.DB.MyDataBase.INITAMT;
import static com.codekiller.bankingsystem.DB.MyDataBase.MAILID;
import static com.codekiller.bankingsystem.DB.MyDataBase.NAME;
import static com.codekiller.bankingsystem.DB.MyDataBase.PHNO;

public class CreateActivity extends AppCompatActivity {

    TextInputLayout nameLayout, ageLayout, ph_noLayout, mailLayout, amountLayout, accnoLayout;
    Button submit;

    String name, age, ph_no, mail, amount;
    String code = "";

    MyDataBase myDataBase;
    UtilClass utilClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        nameLayout = findViewById(R.id.name_text);
        ageLayout = findViewById(R.id.age_text);
        ph_noLayout = findViewById(R.id.phno_text);
        mailLayout = findViewById(R.id.mailid_text);
        amountLayout = findViewById(R.id.amount_text);
        accnoLayout = findViewById(R.id.accno_text);
        submit = findViewById(R.id.create_btn);

        myDataBase = new MyDataBase(this);
        utilClass = new UtilClass(this);

        code = getIntent().getStringExtra("activity_code");
        if( code.equals("update") ){
            accnoLayout.setVisibility(View.VISIBLE);
            accnoLayout.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @SuppressLint("Range")
                @Override
                public void afterTextChanged(Editable s) {
                    if( s.toString().length() != 0 ){
                        Cursor c = myDataBase.getDetails(s.toString());
                        if( c.getCount() != 0 ){
                            nameLayout.getEditText().setText(c.getString(c.getColumnIndex(NAME)));
                            ageLayout.getEditText().setText(c.getString(c.getColumnIndex(AGE)));
                            ph_noLayout.getEditText().setText(c.getString(c.getColumnIndex(PHNO)));
                            mailLayout.getEditText().setText(c.getString(c.getColumnIndex(MAILID)));
                            amountLayout.getEditText().setText(c.getString(c.getColumnIndex(INITAMT)));
                        }
                    }
                }
            });
        }
//5480
        //1119
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameLayout.getEditText().getText().toString();
                age = ageLayout.getEditText().getText().toString();
                ph_no = ph_noLayout.getEditText().getText().toString();
                mail = mailLayout.getEditText().getText().toString();
                amount = amountLayout.getEditText().getText().toString();
                if( code.equals("create") ){
                    if( len(name) != 0 && len(age) != 0 && len(ph_no) != 0 && len(mail) != 0 && len(amount) != 0 ){
                        String accno = utilClass.getACCNO()+"";
                        while( !myDataBase.add(name,age,ph_no,amount,mail,accno) ) accno = utilClass.getACCNO()+"";
                        utilClass.toast("account created successfully");
                        new AlertDialog.Builder(CreateActivity.this)
                                .setTitle("Your Account Number")
                                .setMessage(accno)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .show();
                    }else{
                        utilClass.toast("fill all the fields");
                    }
                }else{
                    if( len(name) != 0 && len(age) != 0 && len(ph_no) != 0 && len(mail) != 0 && len(amount) != 0 ){
                        if( myDataBase.updateAcc(name,age,ph_no,amount,mail,accnoLayout.getEditText().getText().toString()) ){
                            utilClass.toast("updated successfully");
                            finish();
                        }else{
                            utilClass.toast("something went wrong");
                        }
                    }
                }
            }
        });
    }
    public int len(String s){
        return s.length();
    }
}
