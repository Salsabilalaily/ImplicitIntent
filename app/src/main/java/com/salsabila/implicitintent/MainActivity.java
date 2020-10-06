package com.salsabila.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText= (EditText) findViewById(R.id.website_edittext);
        mLocationEditText= (EditText) findViewById(R.id.location_edittext);
        mShareTextEditText= (EditText) findViewById(R.id.share_edittext);
    }
    public void openWebsite(View view){
        String url=mWebsiteEditText.getText().toString();
        Uri webpage=Uri.parse(url);
        Intent intent= new Intent(Intent.ACTION_VIEW,webpage);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Log.d("ImplicitIntents","Can't Handle This!");
        }

    }
    public void openLocation(View view){
        String loc=mLocationEditText.getText().toString();
        Uri addressUri= Uri.parse("geo:0,0?q=" + loc);
        Intent intent= new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else {
            Log.d("ImplicitIntents", "Can't Handle This!");
        }
    }
    public void shareText(View view){
        String txt=mShareTextEditText.getText().toString();
        //String mimeType="text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                //.setType(mimeType)
                .setChooserTitle("Share This Text With: ")
                .setText(txt)
                .setType("Text/Plain")
                .startChooser();

    }

}