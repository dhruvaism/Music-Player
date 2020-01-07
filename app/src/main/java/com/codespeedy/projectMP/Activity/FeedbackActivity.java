package com.codespeedy.projectMP.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.codespeedy.projectMP.Model.ModelFeedback;
import com.codespeedy.projectMP.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class FeedbackActivity extends AppCompatActivity {

    EditText editText;
    CoordinatorLayout coordinatorLayout;
    String modelName;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Toolbar toolbar = findViewById(R.id.toolbar_feedback);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        coordinatorLayout = findViewById(R.id.coordinator_feedback);
        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        LinearLayout linearLayout = findViewById(R.id.lll);
        Button button = findViewById(R.id.btn);
        LinearLayout linearLayout1 = findViewById(R.id.llll);
        editText = findViewById(R.id.addr_edittext);


        SharedPreferences themePref = PreferenceManager.getDefaultSharedPreferences(MainActivity.activity);
        String theme = themePref.getString("theme",null);
        if(theme!=null && theme.equals("dark"))
        {
            appBarLayout.setBackgroundColor(getResources().getColor(R.color.toolbar_color));
            toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_color));
            coordinatorLayout.setBackgroundColor(getResources().getColor(R.color.colorDark));
            linearLayout.setBackgroundColor(getResources().getColor(R.color.colorDark));
            button.setBackgroundColor(getResources().getColor(R.color.dimgrey));
            linearLayout1.setBackgroundColor(getResources().getColor(R.color.dimgrey));
            editText.setBackgroundColor(getResources().getColor(R.color.dimgrey));
            editText.setTextColor(getResources().getColor(R.color.colorLight ));
            editText.setHintTextColor(getResources().getColor(R.color.darkgrey));


        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidity();
            }
        });




    }

    public void checkValidity()
    {
        str=editText.getText().toString().trim();
        if(str.equals(null)|| str.length()<10)
        {
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "write something more!", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else
        {

            getDeviceName();
            sendFeedback();

            Toast.makeText(this, "Feedback Sent Successfully \u2713", Toast.LENGTH_SHORT).show();
            onBackPressed();

        }
    }


    public void getDeviceName()
    {

        modelName = android.os.Build.MANUFACTURER + " " + android.os.Build.MODEL;

    }

    public void sendFeedback()
    {
        ModelFeedback modelFeedback = new ModelFeedback(modelName,str);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Feedback");
        databaseReference.push().setValue(modelFeedback);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
