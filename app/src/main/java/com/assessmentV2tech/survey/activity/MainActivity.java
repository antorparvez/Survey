package com.assessmentV2tech.survey.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.assessmentV2tech.survey.R;

public class MainActivity extends AppCompatActivity {
    private Button viewSurveyBtn,submitSurveyBtn;
    private ImageView main_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //View survey button
        viewSurveyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ViewSurveyActivity.class));
                finish();


            }
        });   //Submit survey button
        submitSurveyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SubmitSurveyActivity.class));
                finish();
            }
        });




    }


    private void initView() {
        viewSurveyBtn = findViewById(R.id.viewSurveyBtn);
        submitSurveyBtn = findViewById(R.id.surveyBtn);
        main_logo = findViewById(R.id.main_logo);


    }
}