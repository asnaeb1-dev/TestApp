package com.abhigyan.user.testapp;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.abhigyan.user.testapp.StackExchangeAPI.StackExInfo;
import com.abhigyan.user.testapp.StackExchangeAPI.StackExchangeAPIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextInputEditText searchField;
    Button button;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView textView;
    private ArrayList<Question> questionList;
    String sortBy, orderBy;
    ContentRVAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.filter:
               new DialogBox(MainActivity.this).startMakingDailog("Filter options","Filter", "Cancel" );
                Toast.makeText(this, "show result", Toast.LENGTH_SHORT).show();
                break;

            case R.id.sort:
                new SortDialogBox(MainActivity.this).startMakingDailog("Sort by", "Sort", "Cancel");
                break;

                default:
                    break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.searchField);
        button = findViewById(R.id.button);
        recyclerView = findViewById(R.id.contentRV);
        progressBar = findViewById(R.id.progress_circular);
        textView = findViewById(R.id.progressText);
        questionList = new ArrayList<>();
    }

    private void checkForSettings()
    {
        FilterOptionsClass filterOptionsClass = new FilterOptionsClass(this);
        if(filterOptionsClass.getOrderOptions() == 0)
        {
            orderBy = "activity";
        }
        else if(filterOptionsClass.getOrderOptions() == 1)
        {
            orderBy = "hot";
        }
        else
        {
            orderBy = "votes";
        }

        if(filterOptionsClass.getSortOptions()== 0)
        {
            sortBy = "desc";
        }
        else
        {
            sortBy = "asc";
        }
    }

    public void searchFunction(View view)
    {
        checkForSettings();
        if(!searchField.getText().toString().equals(""))
        {
            questionList.clear();
            getSearchResults(searchField.getText().toString());
            adapter = new ContentRVAdapter(this,questionList);
            recyclerView.setAdapter(adapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            adapter.notifyDataSetChanged();

        }
        else
        {
            Toast.makeText(this, "Please fill the search bar!", Toast.LENGTH_SHORT).show();
        }
    }
    private void getSearchResults(String searchText)
    {
        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(Config.MAIN_URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

        StackExchangeAPIInterface stackExchangeAPIInterface = retrofit.create(StackExchangeAPIInterface.class);

        Call<StackExInfo> call = stackExchangeAPIInterface.getQuestionInfo("desc","activity", searchText, "stackoverflow");
        call.enqueue(new Callback<StackExInfo>() {
            @Override
            public void onResponse(@NonNull Call<StackExInfo> call, @NonNull Response<StackExInfo> response) {
                StackExInfo stackExInfo = response.body();
                if(stackExInfo!=null) {
                    List<StackExInfo.Item> info = stackExInfo.getItems();
                    for(StackExInfo.Item item : info)
                    {
                        questionList.add(new Question(item.getIsAnswered(),
                                                        item.getTitle(),
                                                        item.getViewCount(),
                                                        item.getLink(),
                                                        item.getScore(),
                                                        item.getViewCount(),
                                                        item.getAnswerCount(),
                                                        item.getOwner().getDisplayName(),
                                                        item.getOwner().getProfileImage(),
                                                        new Config().reformatDate(item.getCreationDate()),
                                                        new Config().reformatDate(item.getLastActivityDate()),
                                                        item.getQuestionId()));
                    }
                }
                progressBar.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onFailure(@NonNull Call<StackExInfo> call, @NonNull Throwable t) {

                Log.e("abhigyans error", t.getMessage());
                Toast.makeText(MainActivity.this, "Failed to fetch results!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void clearFunction(View view) {

        //recyclerView.removeAllViews();
        questionList.clear();
        searchField.setText(null);
    }
}
