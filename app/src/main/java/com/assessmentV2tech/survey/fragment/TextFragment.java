package com.assessmentV2tech.survey.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.assessmentV2tech.survey.R;
import com.assessmentV2tech.survey.listener.FragmentListener;
import com.assessmentV2tech.survey.model.Answer;
import com.assessmentV2tech.survey.model.SurveyResponse;

import java.util.List;

public class TextFragment extends Fragment {


    private List<SurveyResponse> surveyResponseList;
    private int position;
    private TextView quztionTV;
    private Button submitButton;
    private EditText answerET;
    private FragmentListener fragmentListener;

    public TextFragment(List<SurveyResponse> surveyResponseList, int position) {
        this.surveyResponseList = surveyResponseList;
        this.position = position;
    }

    public TextFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quztionTV = view.findViewById(R.id.questionTV);
        answerET = view.findViewById(R.id.text_answerET);
        submitButton = view.findViewById(R.id.submitBtn);
        fragmentListener = (FragmentListener) getActivity();

        quztionTV.setText(surveyResponseList.get(position).getQuestion());

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textAnswer= answerET.getText().toString();
                fragmentListener.getAnswer(textAnswer,surveyResponseList.get(position).getQuestion());
                submitButton.setText("Submitted");
                submitButton.setClickable(false);
            }
        });


    }
}