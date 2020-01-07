package com.codespeedy.projectMP.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codespeedy.projectMP.R;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        TextView version = findViewById(R.id.id_version);
        LinearLayout developer = findViewById(R.id.id_developer);

        version.setVisibility(View.VISIBLE);
        developer.setVisibility(View.VISIBLE);

        CheckedTextView dhruv = findViewById(R.id.dhruv_);
        dhruv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFacebookProfile();
            }
        });




    }

    private void gotoFacebookProfile()
    {
        String FACEBOOK_URL = "https://www.facebook.com/dhrubajit.sarkar.3";
        String FACEBOOK_PAGE_ID = "dhrubajit.sarkar.3";

        String url;
        PackageManager packageManager = this.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) {
                url = "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else {
                url = "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            url = FACEBOOK_URL; //normal web url
        }


        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        facebookIntent.setData(Uri.parse(url));
        startActivity(facebookIntent);

    }

}
