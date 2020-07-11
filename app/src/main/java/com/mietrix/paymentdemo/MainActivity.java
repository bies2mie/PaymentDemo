package com.mietrix.paymentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    String details ;
    String amount ;
    String name ;
    String email ;
    String phone ;
    DecimalFormat df;
    EditText proDetail,proAmount,uName,uEmail,uPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        df = new DecimalFormat("0.00");

        Button pay = findViewById(R.id.payment);
        proDetail = findViewById(R.id.productDetail);
        proAmount  = findViewById(R.id.price);
        uName = findViewById(R.id.uname);
        uEmail = findViewById(R.id.uemail);
        uPhone = findViewById(R.id.uphone);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                details = proDetail.getText().toString().trim();
                amount = proAmount.getText().toString().trim();
                Double price = Double.valueOf(amount);
                name = uName.getText().toString().trim();
                email = uEmail.getText().toString().trim();
                phone = uPhone.getText().toString().trim();

                if (details.length() < 1) {
                    Toast.makeText(getApplicationContext(), "Please product details",
                            Toast.LENGTH_LONG).show();
                } else if (price < 3.0) {
                    Toast.makeText(getApplicationContext(), "Please more than RM 3",
                            Toast.LENGTH_LONG).show();
                }else if (name.length() < 3) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter proper name", Toast.LENGTH_LONG).show();
                }else if (email.length() < 3) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter proper email", Toast.LENGTH_LONG).show();
                }else if (phone.length() < 3) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter proper phone number", Toast.LENGTH_LONG).show();
                }else {

                    SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("PAYMENT_DEMO", Context.MODE_PRIVATE);

                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //Adding values to editor
                    editor.putString("DETAILS", details);
                    editor.putString("AMOUNT", df.format(price));
                    editor.putString("NAME", name);
                    editor.putString("EMAIL", email);
                    editor.putString("PHONE", phone);

                    //Saving values to editor
                    editor.commit();

                    Intent intent = new Intent(MainActivity.this, WebViewPayment.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}