package com.assessmentV2tech.survey.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.assessmentV2tech.survey.R;
import com.assessmentV2tech.survey.adapter.SurveyListAdapter;
import com.assessmentV2tech.survey.listener.ItemClickListener;
import com.assessmentV2tech.survey.model.Answer;
import com.assessmentV2tech.survey.model.SavedAnswers;
import com.assessmentV2tech.survey.preference.AppPreference;
import com.assessmentV2tech.survey.preference.PrefKeys;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewSurveyActivity extends AppCompatActivity implements Serializable {
    String savedAnswer;
    List <SavedAnswers> savedAnswersList = new ArrayList<>();
    List <Answer> singleAnswer = new ArrayList<>();

    private RecyclerView recyclerView;
    private RelativeLayout relativeLayout;
    private Button addSurveyBtn, homeBtn;
    private SurveyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_survey);


        addSurveyBtn = findViewById(R.id.addSurvey);
        homeBtn = findViewById(R.id.backHome);
        recyclerView = findViewById(R.id.surveyRV);
        relativeLayout = findViewById(R.id.no_surveyRL);

        int pos = getIntent().getIntExtra("Data",0);
        //String to ArrayList


        if (AppPreference.getInstance(this).getString(PrefKeys.PREF_KEY_SAVED_ANSWER ) !=null){
            recyclerView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
            homeBtn.setClickable(false);
            addSurveyBtn.setClickable(false);
            savedAnswer= AppPreference.getInstance(this).getString(PrefKeys.PREF_KEY_SAVED_ANSWER);
        }else {

            relativeLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

        Gson gson = new Gson();
        savedAnswersList=gson.fromJson(savedAnswer, new TypeToken<List<SavedAnswers>>() {}.getType());


        addSurveyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewSurveyActivity.this, SubmitSurveyActivity.class));

            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewSurveyActivity.this,MainActivity.class));

            }
        });

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new SurveyListAdapter(savedAnswersList);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Toast.makeText(ViewSurveyActivity.this, ""+savedAnswersList.get(position).getAnswerList().get(3).getQuestion(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ViewSurveyActivity.this,SingleSurveyActivity.class);

                intent.putExtra("Data", position);
                startActivity(intent);

            /*  //  SavedAnswers pos = (SavedAnswers) ;

                Intent intent = new Intent(ViewSurveyActivity.this,SingleSurveyActivity.class);
                intent.putExtra("answer", (Serializable)singleAnswer);
                startActivity(intent);*/

            }
        });
    }
}