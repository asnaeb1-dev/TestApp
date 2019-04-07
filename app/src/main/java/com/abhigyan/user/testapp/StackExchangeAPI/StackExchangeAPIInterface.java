package com.abhigyan.user.testapp.StackExchangeAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StackExchangeAPIInterface {
    /*order=desc&sort=activity&tagged=c&site=stackoverflow*/

    @GET("/2.2/questions")
    Call<StackExInfo> getQuestionInfo(

            @Query("order") String order,
            @Query("sort") String sort,
            @Query("tagged") String tagged,
            @Query("site") String site
    );
}
