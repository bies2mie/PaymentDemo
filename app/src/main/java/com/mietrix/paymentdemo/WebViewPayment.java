
package com.mietrix.paymentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.json.JSONException;

import java.net.HttpURLConnection;

public class WebViewPayment extends AppCompatActivity {

    private WebView wv1;

    String details;
    String amount;
    String name;
    String email;
    String phone;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_payment);
        SharedPreferences sharedPreferences = getSharedPreferences("PAYMENT_DEMO", Context.MODE_PRIVATE);
        details = sharedPreferences.getString("DETAILS", "Not Available");
        amount = sharedPreferences.getString("AMOUNT", "Not Available");
        name = sharedPreferences.getString("NAME", "Not Available");
        email = sharedPreferences.getString("EMAIL", "Not Available");
        phone = sharedPreferences.getString("PHONE", "Not Available");

        wv1 = (WebView) findViewById(R.id.webview);
        wv1.setWebViewClient(new MyBrowser());
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        url = "https://myqr.qniti.com/paymentdemo/paymentregister.php?detail=" + details + "&amount=" + amount + "&name=" + name + "&email=" + email+"&phone="+phone;
        wv1.loadUrl(url);
/*
        wv1.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

                Toast.makeText(WebViewPayment.this, message, Toast.LENGTH_LONG).show();

                return super.onJsAlert(view, url, message, result);
            }
        });*/
    }



    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void onBackPressed() {
        Intent i = new Intent(WebViewPayment.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

/*
    public class WebViewResultListener {
        @JavascriptInterface
        public void onResult(int code, String response) {
            if (code == HttpURLConnection.HTTP_OK) {

                if (response.equalsIgnoreCase("Success")) {

                    Toast.makeText(WebViewPayment.this, "Successfully Pay", Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(WebViewPayment.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();

                } else if (response.equalsIgnoreCase("Cancelled")) {

                    Toast.makeText(WebViewPayment.this, "Payment Cancelled", Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(WebViewPayment.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();

                } else {

                    Toast.makeText(WebViewPayment.this, "Cannot Pay.Please try again later", Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(WebViewPayment.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                }

            } else {
                Log.e("WebViewResultListener", "Error code=" + code);
            }
        }

    }*/

}