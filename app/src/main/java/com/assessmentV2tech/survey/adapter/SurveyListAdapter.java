package com.assessmentV2tech.survey.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assessmentV2tech.survey.R;
import com.assessmentV2tech.survey.listener.ItemClickListener;
import com.assessmentV2tech.survey.model.SavedAnswers;

import java.util.List;

public class SurveyListAdapter extends RecyclerView.Adapter<SurveyListAdapter.SurveyViewHolder>  {
    List<SavedAnswers> surveyList;
    private ItemClickListener itemClickListener;

    public SurveyListAdapter(List<SavedAnswers> surveyList) {
        this.surveyList = surveyList;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public SurveyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.survey_list_row,parent,false);
        return new SurveyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyViewHolder holder, int position) {
        holder.date.setText(surveyList.get(position).getTimeStmp());

    }

    @Override
    public int getItemCount() {
        return surveyList.size();
    }

    class SurveyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView date;
        public SurveyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.surveyDate);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
}
