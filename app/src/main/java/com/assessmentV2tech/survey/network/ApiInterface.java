package com.assessmentV2tech.survey.network;


import com.assessmentV2tech.survey.model.SurveyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET(HttpParams.API_GET_SURVEY)
    Call<List<SurveyResponse>> getSurveyResponseList();



}
