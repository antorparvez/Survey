package com.assessmentV2tech.survey.model;

import java.util.List;

public class SavedAnswers {
    private String timeStmp;
    private List<Answer> answerList ;

    public SavedAnswers(String timeStmp, List<Answer> answerList) {
        this.timeStmp = timeStmp;
        this.answerList = answerList;
    }

    public String getTimeStmp() {
        return timeStmp;
    }

    public void setTimeStmp(String timeStmp) {
        this.timeStmp = timeStmp;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }
}
