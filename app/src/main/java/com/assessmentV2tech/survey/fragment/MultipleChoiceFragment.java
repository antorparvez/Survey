package com.assessmentV2tech.survey.fragment;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.assessmentV2tech.survey.R;
import com.assessmentV2tech.survey.listener.FragmentListener;
import com.assessmentV2tech.survey.model.Answer;
import com.assessmentV2tech.survey.model.SurveyResponse;

import java.util.Arrays;
import java.util.List;


public class MultipleChoiceFragment extends Fragment {


    private List<SurveyResponse> surveyResponseList;
    private int position;

    private TextView questionTV;
    private RadioGroup optionContainer;


    private FragmentListener fragmentListener;
    public MultipleChoiceFragment(List<SurveyResponse> surveyResponseList, int position) {
        this.surveyResponseList = surveyResponseList;
        this.position = position;
    }



    public MultipleChoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multiple_choice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        optionContainer= view.findViewById(R.id.option_container);
        questionTV = view.findViewById(R.id.questionTV);

        fragmentListener = (FragmentListener) getActivity();
        questionTV.setText(surveyResponseList.get(position).getQuestion());

        String options =surveyResponseList.get(position).getOptions() ;
        String[] optionArray = options.split(",");
        //create radio buttons
        for (int i = 0; i < optionArray.length; i++) {
            RadioButton radioButton = new RadioButton(getActivity());
            radioButton.setText(optionArray[i]);
            radioButton.setId(i);
            optionContainer.addView(radioButton);
        }
            optionContainer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int checkedRadioButtonId = optionContainer.getCheckedRadioButtonId();
                    RadioButton radioBtn =  view.findViewById(checkedRadioButtonId);

                    String textAnswer= radioBtn.getText().toString();
                    fragmentListener.getAnswer(textAnswer,surveyResponseList.get(position).getQuestion());

                }
            });

    }
}