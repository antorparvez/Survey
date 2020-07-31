package com.assessmentV2tech.survey.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.assessmentV2tech.survey.R;
import com.assessmentV2tech.survey.listener.FragmentListener;
import com.assessmentV2tech.survey.model.Answer;
import com.assessmentV2tech.survey.model.SurveyResponse;

import java.util.List;

public class CheckboxFragment extends Fragment {
    private List<SurveyResponse> surveyResponseList;
    private int position;
    private TextView question;
    private RadioGroup radioGP;
    private TextView quztionTV;
    private Button submitButton;

    private FragmentListener fragmentListener;
    private String textAnswer;

    public CheckboxFragment(List<SurveyResponse> surveyResponseList, int position) {
        this.surveyResponseList = surveyResponseList;
        this.position = position;

    }

    public CheckboxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkbox, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        radioGP = view.findViewById(R.id.radioGP);
        question = view.findViewById(R.id.questionTV);
        submitButton = view.findViewById(R.id.submitBtn);

        fragmentListener = (FragmentListener) getActivity();

        question.setText(surveyResponseList.get(position).getQuestion());


        String options = surveyResponseList.get(position).getOptions();
        String[] optionArray = options.split(",");

        for (int row = 0; row < 1; row++) {
            LinearLayout ll = new LinearLayout(getActivity());
            ll.setOrientation(LinearLayout.VERTICAL);

            for (int i = 1; i <= optionArray.length; i++) {
                final CheckBox ch = new CheckBox(getActivity());
                ch.setId((row * 2) + i);
                if (i != optionArray.length)
                    ch.setText(optionArray[i - 1]);
                else {
                    ch.setText("No options");
                }

                    ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (ch.isChecked()){
                                if (textAnswer==null){
                                    textAnswer = ch.getText().toString();
                                }else {
                                    textAnswer=textAnswer+","+ch.getText().toString();
                                }
                            }

                        }
                    });

                ll.addView(ch);
            }
            radioGP.addView(ll);

        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentListener.getAnswer(textAnswer, surveyResponseList.get(position).getQuestion());
                submitButton.setText("Submitted");
                submitButton.setClickable(false);
            }
        });



    }

}