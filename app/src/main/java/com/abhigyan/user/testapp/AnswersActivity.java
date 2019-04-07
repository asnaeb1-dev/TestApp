package com.abhigyan.user.testapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.abhigyan.user.testapp.StackExchangeAPI.AnswersInfoClass;
import com.abhigyan.user.testapp.StackExchangeAPI.StackExAnswersAPI;
import com.abhigyan.user.testapp.StackExchangeAPI.StackExInfo;
import com.abhigyan.user.testapp.StackExchangeAPI.StackExchangeAPIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnswersActivity extends AppCompatActivity {

    Intent intent;
    int questionID;
    private ArrayList<Answers> answersList = new ArrayList<>();
    RecyclerView recyclerView1;
    TextView quesTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);

        intent = getIntent();
        questionID = intent.getIntExtra("id", -1);
        Log.i("id", String.valueOf(questionID));
        recyclerView1 = findViewById(R.id.answersRV);
        quesTitle = findViewById(R.id.questionTitle);

        if(questionID == -1)
        {
            Toast.makeText(this, "Sorry failed to fetch answers. Try again later!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            getAnswers(questionID);

            AnswersRVAdapter adapter = new AnswersRVAdapter(this,answersList);
            recyclerView1.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView1.setLayoutManager(linearLayoutManager);
        }
    }

    private void getAnswers(int questionID)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StackExAnswersAPI stackExchangeAPIInterface = retrofit.create(StackExAnswersAPI.class);

        Call<AnswersInfoClass> call = stackExchangeAPIInterface.getAnswers(questionID,"desc","activity", "stackoverflow","!-y(KwOdKR5Ga7mmruVArx2SJykc-M)3jKiDQBk1fq");
        call.enqueue(new Callback<AnswersInfoClass>() {
            @Override
            public void onResponse(@NonNull Call<AnswersInfoClass> call, @NonNull Response<AnswersInfoClass> response) {
                AnswersInfoClass stackExInfo = response.body();
                if(stackExInfo!=null) {
                    List<AnswersInfoClass.Item> info = stackExInfo.getItems();
                    if(info!=null) {
                        for (AnswersInfoClass.Item item : info) {
                            quesTitle.setText(item.getTitle());
                           List<AnswersInfoClass.Answer> iinfo = item.getAnswers();
                            int j = 1;
                            for (AnswersInfoClass.Answer loop : iinfo) {
                                Log.i("check ", loop.getBody());
                                answersList.add(new Answers(loop.getBody(), "Answers " + String.valueOf(j)));
                                j++;
                            }
                        }
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<AnswersInfoClass> call, @NonNull Throwable t) {

                Log.e("abhigyans error", t.getMessage());
                Toast.makeText(AnswersActivity.this, "Failed to fetch results!", Toast.LENGTH_SHORT).show();
              //  progressBar.setVisibility(View.INVISIBLE);
              //  textView.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
