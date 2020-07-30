package com.assessmentV2tech.survey.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.assessmentV2tech.survey.R;
import com.assessmentV2tech.survey.model.Answer;
import com.assessmentV2tech.survey.model.SurveyResponse;

import java.util.List;


public class DropdownFragment extends Fragment {

    private List<SurveyResponse> surveyResponseList;
    private int position;
    private List<Answer>answerList;

    public DropdownFragment(List<SurveyResponse> surveyResponseList, int position, List<Answer> answerList) {
        this.surveyResponseList = surveyResponseList;
        this.position = position;
        this.answerList = answerList;
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

        Toast.makeText(getActivity(), "List size is :"+answerList.size(), Toast.LENGTH_SHORT).show();
    }
}