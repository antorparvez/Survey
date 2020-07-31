package com.assessmentV2tech.survey.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assessmentV2tech.survey.R;
import com.assessmentV2tech.survey.model.Answer;
import com.assessmentV2tech.survey.model.SavedAnswers;

import java.util.List;

public class ViewAnswerAdapter extends RecyclerView.Adapter<ViewAnswerAdapter.SingleAnswerViewHolder> {
    private List<Answer> answerList;

    public ViewAnswerAdapter(List<Answer> answerList) {
        this.answerList = answerList;
        Log.d("TAG", "YSH");
    }

    @NonNull
    @Override
    public SingleAnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.single_answer_row,parent,false);
        return new SingleAnswerViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull SingleAnswerViewHolder holder, int position) {

        holder.question.setText(answerList.get(position).getQuestion());
        holder.answer.setText(answerList.get(position).getAnswer());

    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    class SingleAnswerViewHolder extends RecyclerView.ViewHolder {
        TextView question, answer;
        public SingleAnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.singleQuestion);
            answer = itemView.findViewById(R.id.single_answer);

        }
    }
}
