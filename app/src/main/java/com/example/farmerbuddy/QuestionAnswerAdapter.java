package com.example.farmerbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAnswerAdapter extends RecyclerView.Adapter<QuestionAnswerAdapter.ViewHolder> {
    private List<QuestionAnswer> questionAnswerList;

    public QuestionAnswerAdapter(List<QuestionAnswer> questionAnswerList) {
        this.questionAnswerList = questionAnswerList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView questionTextView;
        private TextView answerTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.question_text_view);
            answerTextView = itemView.findViewById(R.id.answer_text_view);
        }

        public void bind(QuestionAnswer questionAnswer) {
            questionTextView.setText(questionAnswer.getQuestion());
            answerTextView.setText(questionAnswer.getAnswer());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.queriescard, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(questionAnswerList.get(position));
    }

    @Override
    public int getItemCount() {
        return questionAnswerList.size();
    }
}
