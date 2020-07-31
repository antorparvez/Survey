package com.assessmentV2tech.survey.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.assessmentV2tech.survey.R;
import com.assessmentV2tech.survey.listener.FragmentListener;
import com.assessmentV2tech.survey.model.Answer;
import com.assessmentV2tech.survey.model.SurveyResponse;

import java.util.List;


public class DropdownFragment extends Fragment {

    private List<SurveyResponse> surveyResponseList;
    private int position;
    private Spinner dropdown;
    private Button submitBtn;
    private TextView question;
    private String textAnswer;
    private FragmentListener fragmentListener;

    public DropdownFragment(List<SurveyResponse> surveyResponseList, int position) {
        this.surveyResponseList = surveyResponseList;
        this.position = position;
    }

    public DropdownFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dropdown, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dropdown = view.findViewById(R.id.dropdown);
        submitBtn = view.findViewById(R.id.submitBtn);
        question = view.findViewById(R.id.questionTV);

        String options =surveyResponseList.get(position).getOptions() ;
        String[] items = options.split(",");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, items);
        question.setText(surveyResponseList.get(position).getQuestion());

        fragmentListener = (FragmentListener) getActivity();
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

               textAnswer=  parent.getItemAtPosition(position).toString();
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentListener.getAnswer(textAnswer,surveyResponseList.get(position).getQuestion());
                submitBtn.setText("Submitted");
                submitBtn.setClickable(false);


            }
        });
    }
}