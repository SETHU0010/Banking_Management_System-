package com.codekiller.bankingsystem.Utils;

import android.content.Context;
import android.widget.Toast;

import java.util.Random;

public class UtilClass {
    Context context;
    Random rn;
    public UtilClass(Context context){
        this.context = context;
        rn = new Random();
    }
    public int getACCNO(){
        return ( 1000 + rn.nextInt() % (9999 - 1000 + 1) );
    }
    public void toast(String s){
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }
}

