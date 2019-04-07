package com.abhigyan.user.testapp;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContentRVAdapter extends RecyclerView.Adapter<ContentRVAdapter.Viewholder> {
    private Context context;
    private ArrayList<Question> contentArrayList;

    public ContentRVAdapter(Context context, ArrayList<Question> contentArrayList) {
        this.context = context;
        this.contentArrayList = contentArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //responsible for inflating the view
        View view = LayoutInflater.from(context).inflate(R.layout.unit_content_ui, parent, false);
        //create the object of the Viewholder class down below
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {
        //changes wrt to what the layout are and add a new item
        //takes the content and shows it on the imageView
        if(contentArrayList.get(holder.getAdapterPosition()).getTitle()!=null)
        {
            holder.questionTitleTV.setText(contentArrayList.get(holder.getAdapterPosition()).getTitle());
        }
        else
        {
            holder.questionTitleTV.setText("Unavailable");
        }

        if(contentArrayList.get(holder.getAdapterPosition()).getOwnerName()!= null)
        {
            holder.ownerNameTV.setText(contentArrayList.get(holder.getAdapterPosition()).getOwnerName());
        }
        else
        {
            holder.ownerNameTV.setText("N/A");
        }

        if(contentArrayList.get(holder.getAdapterPosition()).getIsAnswered())
        {
            holder.cardView.setBackgroundColor(context.getColor(android.R.color.holo_green_light));
        }
        else
        {
            holder.cardView.setBackgroundColor(context.getColor(android.R.color.holo_red_light));
        }

        if(contentArrayList.get(holder.getAdapterPosition()).getOwnerImage()!=null)
        {
            Glide.with(context)
                        .asBitmap()
                        .load(contentArrayList.get(holder.getAdapterPosition()).getOwnerImage())
                        .into(holder.circleImageView);
        }
        else
        {
            holder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.smile));
        }

        holder.answerCountTV.setText(String.valueOf(contentArrayList.get(holder.getAdapterPosition()).getAnswerCount()));
        holder.viewCountTV.setText(String.valueOf(contentArrayList.get(holder.getAdapterPosition()).getViewVount()));
        holder.voteCountTV.setText(String.valueOf(contentArrayList.get(holder.getAdapterPosition()).getVoteCount()));
        holder.answerCountTV.setText(String.valueOf(contentArrayList.get(holder.getAdapterPosition()).getAnswerCount()));
        holder.creationDateTV.setText(String.valueOf(contentArrayList.get(holder.getAdapterPosition()).getCreationDate()));
        holder.lastModifiedTV.setText(String.valueOf(contentArrayList.get(holder.getAdapterPosition()).getDateModified()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contentArrayList.get(holder.getAdapterPosition()).getIsAnswered()) {
                    Toast.makeText(context, "Clicked!" + String.valueOf(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, AnswersActivity.class);
                    intent.putExtra("id", contentArrayList.get(holder.getAdapterPosition()).getQuestionID());
                    context.startActivity(intent);
                }
                else
                {
                    Toast.makeText(context, "Sorry! This question is unanswered!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        //this sets the size of the recycler view
        //without this the recycler view will show 0 items
        return contentArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView questionTitleTV, ownerNameTV, voteCountTV,
                scoreCOuntTV, answerCountTV, viewCountTV, creationDateTV, lastModifiedTV ;
        CardView cardView;

        public Viewholder(View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.ownerImage);

            questionTitleTV = itemView.findViewById(R.id.questionTitle);
            ownerNameTV = itemView.findViewById(R.id.ownerName);
            //acceptedAnswerLinkTV = itemView.findViewById(R.id.);
            voteCountTV = itemView.findViewById(R.id.voteCount);
            scoreCOuntTV = itemView.findViewById(R.id.score);
            answerCountTV = itemView.findViewById(R.id.answerCount);
            viewCountTV = itemView.findViewById(R.id.viewCount);
            creationDateTV = itemView.findViewById(R.id.creationDate);
            lastModifiedTV = itemView.findViewById(R.id.lastModi);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
