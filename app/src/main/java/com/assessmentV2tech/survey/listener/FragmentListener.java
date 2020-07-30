package com.assessmentV2tech.survey.listener;

import com.assessmentV2tech.survey.model.SurveyResponse;

import java.util.List;

public interface FragmentListener {
    void viewFirstFragment();
    void onCheckboxItem(List<SurveyResponse> responseList, int position);
    void onDropdownItem(List<SurveyResponse> responseList, int position);
    void onMultipleChoiceItem(List<SurveyResponse> responseList,int position);
    void onNumberItem(List<SurveyResponse> responseList,int position);
    void onTextItem(List<SurveyResponse> responseList,int position);
    void getAnswer(String answer, String question);
}
