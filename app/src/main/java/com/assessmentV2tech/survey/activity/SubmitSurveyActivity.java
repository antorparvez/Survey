package com.assessmentV2tech.survey.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.assessmentV2tech.survey.R;
import com.assessmentV2tech.survey.model.SurveyResponse;
import com.assessmentV2tech.survey.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitSurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_survey);

        ApiClient.getClient().getSurveyResponseList().enqueue(new Callback<List<SurveyResponse>>() {
            @Override
            public void onResponse(Call<List<SurveyResponse>> call, Response<List<SurveyResponse>> response) {
                if (response.isSuccessful()){

                    List<SurveyResponse> myList = response.body();
                    Log.d("TAG", "onResponse: "+myList.size());
                    response.body().get(0).
                }
            }

            @Override
            public void onFailure(Call<List<SurveyResponse>> call, Throwable t) {
                Log.d("TAG", "onResponse failed: ");
            }
        });
    }
}