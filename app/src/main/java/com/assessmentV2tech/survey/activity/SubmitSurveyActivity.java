package com.assessmentV2tech.survey.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.assessmentV2tech.survey.R;
import com.assessmentV2tech.survey.model.SurveyResponse;
import com.assessmentV2tech.survey.network.ApiClient;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitSurveyActivity extends AppCompatActivity {

    private Button surveyBackBTN,surveyNextBTN;
    private FrameLayout fragmentContainer;
    private StepView stepView;
    private int surveyPosition=1;

    private List<SurveyResponse> surveyResponseList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_survey);

        initView();
        getResponseFromServer();


        stepView.go(0, true);

        surveyNextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (surveyPosition < surveyResponseList.size() ){
                    stepView.go(surveyPosition, true);

                    Toast.makeText(SubmitSurveyActivity.this, "sdfa"+surveyPosition, Toast.LENGTH_SHORT).show();
                    surveyPosition++;
                    stepView.done(true);
                }else {
                    Toast.makeText(SubmitSurveyActivity.this, "survey complete", Toast.LENGTH_SHORT).show();
                }
            }
        });


        surveyBackBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (surveyPosition > 0) {
                    Toast.makeText(SubmitSurveyActivity.this, ""+surveyPosition, Toast.LENGTH_SHORT).show();
                    stepView.done(false);
                    surveyPosition--;
                }else {
                    finish();
                }

            }
        });


    }

    private void getResponseFromServer() {
        ApiClient.getClient().getSurveyResponseList().enqueue(new Callback<List<SurveyResponse>>() {
            @Override
            public void onResponse(Call<List<SurveyResponse>> call, Response<List<SurveyResponse>> response) {
                if (response.isSuccessful()){

                    surveyResponseList= response.body();
                    Log.d("TAG", "onResponse: "+surveyResponseList.size());
                    stepView.getState()
                            .selectedTextColor(ContextCompat.getColor(SubmitSurveyActivity.this, R.color.colorGreen))
                            .animationType(StepView.ANIMATION_CIRCLE)
                            .selectedCircleColor(ContextCompat.getColor(SubmitSurveyActivity.this, R.color.colorGreen))
                            .selectedCircleRadius(getResources().getDimensionPixelSize(R.dimen.dp14))
                            .selectedStepNumberColor(ContextCompat.getColor(SubmitSurveyActivity.this, R.color.colorWhite))
                            .stepsNumber(surveyResponseList.size())
                            .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                            .stepLineWidth(getResources().getDimensionPixelSize(R.dimen.dp1))
                            .textSize(getResources().getDimensionPixelSize(R.dimen.sp14))
                            .stepNumberTextSize(getResources().getDimensionPixelSize(R.dimen.sp16))
                            // other state methods are equal to the corresponding xml attributes
                            .commit();

                }
            }

            @Override
            public void onFailure(Call<List<SurveyResponse>> call, Throwable t) {
                Log.d("TAG", "onResponse failed: ");
            }
        });
    }

    private void initView() {
        surveyBackBTN = findViewById(R.id.surveyBackBtn);
        surveyNextBTN = findViewById(R.id.surveyNextBtn);
        stepView = findViewById(R.id.step_view);
        fragmentContainer = findViewById(R.id.fragmentContainer);


    }
}