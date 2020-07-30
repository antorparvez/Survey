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
import com.assessmentV2tech.survey.fragment.BlankFragment;
import com.assessmentV2tech.survey.fragment.CheckboxFragment;
import com.assessmentV2tech.survey.fragment.DropdownFragment;
import com.assessmentV2tech.survey.fragment.MultipleChoiceFragment;
import com.assessmentV2tech.survey.fragment.NumberFragment;
import com.assessmentV2tech.survey.fragment.TextFragment;
import com.assessmentV2tech.survey.listener.FragmentListener;
import com.assessmentV2tech.survey.model.Answer;
import com.assessmentV2tech.survey.model.SavedAnswers;
import com.assessmentV2tech.survey.model.SurveyResponse;
import com.assessmentV2tech.survey.network.ApiClient;
import com.shuhart.stepview.StepView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitSurveyActivity extends AppCompatActivity implements FragmentListener {

    private Button surveyBackBTN,surveyNextBTN;
    private FrameLayout fragmentContainer;
    private StepView stepView;
    private int surveyPosition=0;
    private FragmentListener fragmentListener;
    private String answer,question;

    private List<SurveyResponse> surveyResponseList = new ArrayList<>();
    private List<Answer> answerList =new ArrayList<>();
    List<SavedAnswers> savedData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_survey);

        initView();
        getResponseFromServer();
       initFunctionalities();
        initListener();


    }


    private void initFunctionalities(){
        stepView.go(surveyPosition, true);
        surveyBackBTN.setVisibility(View.GONE);
        surveyNextBTN.setText("Start");
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer,new BlankFragment()).commit();
    }
    private void getResponseFromServer() {
        ApiClient.getClient().getSurveyResponseList().enqueue(new Callback<List<SurveyResponse>>() {
            @Override
            public void onResponse(Call<List<SurveyResponse>> call, Response<List<SurveyResponse>> response) {
                if (response.isSuccessful()){

                    surveyResponseList= response.body();
                    Log.d("TAG", "onResponse: "+surveyResponseList.size());




                }
            }

            @Override
            public void onFailure(Call<List<SurveyResponse>> call, Throwable t) {
                Log.d("TAG", "onResponse failed: ");
            }
        });
    }


    private void checkSurveyQuestionType(List<SurveyResponse> surveyResponseList, int surveyPosition) {
        switch (surveyResponseList.get(surveyPosition).getType()) {
            case "multiple choice":
                fragmentListener.onMultipleChoiceItem(surveyResponseList, surveyPosition);


                break;
            case "Checkbox":
                fragmentListener.onCheckboxItem(surveyResponseList, surveyPosition);


                break;
            case "dropdown":
                fragmentListener.onDropdownItem(surveyResponseList, surveyPosition);

                break;
            case "number":
                fragmentListener.onNumberItem(surveyResponseList, surveyPosition);

                break;
            case "text":
                fragmentListener.onTextItem(surveyResponseList, surveyPosition);

                break;
            default:
                Toast.makeText(this, "Invalid Item", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void initView() {
        surveyBackBTN = findViewById(R.id.surveyBackBtn);
        surveyNextBTN = findViewById(R.id.surveyNextBtn);
        stepView = findViewById(R.id.step_view);
        fragmentContainer = findViewById(R.id.fragmentContainer);
        fragmentListener = SubmitSurveyActivity.this;

    }

    @Override
    public void viewFirstFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer,new BlankFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCheckboxItem(List<SurveyResponse> responseList, int position) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer,new CheckboxFragment(responseList, position,answerList))
                .addToBackStack(null)
                .commit();


    }

    @Override
    public void onDropdownItem(List<SurveyResponse> responseList, int position) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer,new DropdownFragment(responseList, position,answerList))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onMultipleChoiceItem(List<SurveyResponse> responseList, int position) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer,new MultipleChoiceFragment(responseList, position,answerList))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onNumberItem(List<SurveyResponse> responseList, int position) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer,new NumberFragment(responseList, position,answerList))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onTextItem(List<SurveyResponse> responseList, int position) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer,new TextFragment(responseList, position,answerList))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void getAnswer(String answer, String question) {
        this.answer = answer;
        this.question = question;
    }

    public void initStepper(){
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

        stepView.go(surveyPosition, true);

    }

    private String getdateNode() {
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd MMM, yyyy");
        String todayString = formatDate.format(todayDate);
        return todayString;
    }

    private void initListener(){

        surveyNextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surveyBackBTN.setVisibility(View.VISIBLE);
                surveyNextBTN.setText("Start");
                stepView.go(surveyPosition, true);
                initStepper();

                if (surveyPosition <surveyResponseList.size() ){

                    if (surveyPosition>0){
                        if (surveyResponseList.get(surveyPosition).getRequired()){
                            if (answer==null){
                                Log.d("HERE", answer);
                                //Toast
                            }else{
                                answerList.add(new Answer(answer,question));
                                checkSurveyQuestionType(surveyResponseList, surveyPosition);
                                stepView.done(true);
                                surveyPosition++;
                            }
                        }
                        else {
                            if (answer !=null){
                                //Toast
                                answerList.add(new Answer(answer,question));
                                checkSurveyQuestionType(surveyResponseList, surveyPosition);
                                stepView.done(true);
                                surveyPosition++;
                            }
                        }
                    }else{
                        Log.d("HERE", "Here");
                        checkSurveyQuestionType(surveyResponseList, surveyPosition);
                        stepView.done(true);
                        surveyPosition++;
                    }



                }else {
                    //save answer list here
                    Toast.makeText(SubmitSurveyActivity.this, "survey complete", Toast.LENGTH_SHORT).show();
                    SavedAnswers savedAnswers = new SavedAnswers(getdateNode(), answerList);

                    //Check SharedPref
                    //if Empty direct convert the list entry the string
                    //if not empty, convert the string to list, add the variable to the list
                    //save the new list


                    savedData.add(savedAnswers);
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
}