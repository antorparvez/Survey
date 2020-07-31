package com.assessmentV2tech.survey.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.assessmentV2tech.survey.R;
import com.assessmentV2tech.survey.adapter.SurveyListAdapter;
import com.assessmentV2tech.survey.adapter.ViewAnswerAdapter;
import com.assessmentV2tech.survey.model.Answer;
import com.assessmentV2tech.survey.model.SavedAnswers;
import com.assessmentV2tech.survey.preference.AppPreference;
import com.assessmentV2tech.survey.preference.PrefKeys;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SingleSurveyActivity extends AppCompatActivity {

    private String savedAnswer;
    private int pos;
    List<SavedAnswers> savedAnswersList = new ArrayList<>();
    List<Answer> answerList = new ArrayList<>();

    private RecyclerView recyclerView;
    private ViewAnswerAdapter answerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVars();
        initViews();
        initFunctionalities();
        loadData();

    }

    public void initViews() {
        setContentView(R.layout.activity_single_survey);
        recyclerView = findViewById(R.id.singleRV);
    }

    private void initVars() {
        savedAnswer = AppPreference.getInstance(this).getString(PrefKeys.PREF_KEY_SAVED_ANSWER);
        pos = getIntent().getIntExtra("Data", 0);

    }

    private void initFunctionalities() {
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void loadData() {

        Gson gson = new Gson();
        savedAnswersList = gson.fromJson(savedAnswer, new TypeToken<List<SavedAnswers>>() {
        }.getType());
        answerList = savedAnswersList.get(pos).getAnswerList();
        answerAdapter = new ViewAnswerAdapter(answerList);

        recyclerView.setAdapter(answerAdapter);
        answerAdapter.notifyDataSetChanged();
    }
}