package com.abhigyan.user.testapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class AnswersRVAdapter extends RecyclerView.Adapter<AnswersRVAdapter.Viewholder> {
    private Context context;
    private ArrayList<Answers> answersArrayList;

    public AnswersRVAdapter(Context context, ArrayList<Answers> answersArrayList) {
        this.context = context;
        this.answersArrayList = answersArrayList;
    }

    @NonNull
    @Override
    public AnswersRVAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //responsible for inflating the view
        View view = LayoutInflater.from(context).inflate(R.layout.answers_unit_ui, parent, false);
        //create the object of the Viewholder class down below
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AnswersRVAdapter.Viewholder holder, int position) {
        //changes wrt to what the layout are and add a new item
        //takes the content and shows it on the imageView
        if(answersArrayList.get(holder.getAdapterPosition()).getAnswerTitles()!=null)
        {
            holder.answerTitleTV.setText(answersArrayList.get(holder.getAdapterPosition()).getAnswerTitles());
        }
        else
        {
            holder.answerTitleTV.setText("N/A");
        }

        if(answersArrayList.get(holder.getAdapterPosition()).getAnswerBody()!=null)
        {
             holder.answerBodyTV.setText(answersArrayList.get(holder.getAdapterPosition()).getAnswerBody());
        }
        else
        {
            holder.answerBodyTV.setText("N/A");
        }
    }

    @Override
    public int getItemCount() {
        //this sets the size of the recycler view
        //without this the recycler view will show 0 items
        return answersArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView answerTitleTV, answerBodyTV;
        public Viewholder(View itemView) {
            super(itemView);
            answerTitleTV = itemView.findViewById(R.id.answerIndex);
            answerBodyTV = itemView.findViewById(R.id.ansBody);
        }
    }
}
