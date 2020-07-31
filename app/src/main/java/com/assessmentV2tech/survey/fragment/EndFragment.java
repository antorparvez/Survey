package com.assessmentV2tech.survey.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.assessmentV2tech.survey.R;
import com.assessmentV2tech.survey.activity.MainActivity;
import com.assessmentV2tech.survey.activity.ViewSurveyActivity;
import com.assessmentV2tech.survey.model.SavedAnswers;

import java.util.List;


public class EndFragment extends Fragment {
    private List<SavedAnswers> savedAnswers;
    private Button viewBtn, homeBtn;


    public EndFragment(List<SavedAnswers> savedAnswers) {
        this.savedAnswers = savedAnswers;
    }

    public EndFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_end, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewBtn = view.findViewById(R.id.viewSurvey);
        homeBtn = view.findViewById(R.id.backHome);

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ViewSurveyActivity.class));

            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MainActivity.class));

            }
        });


    }
}