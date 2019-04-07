package com.abhigyan.user.testapp.StackExchangeAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StackExAnswersAPI {

    // https://api.stackexchange.com/2.2/questions/4?order=desc&sort=activity&site=stackoverflow&filter=!-y(KwOdKR5Ga7mmruVArx2SJykc-M)3jKiDQBk1fq

    @GET("/2.2/questions/{ids}")
    Call<AnswersInfoClass> getAnswers(

            @Path("ids") int ids,
            @Query("order") String order,
            @Query("sort") String sort,
            @Query("site") String site,
            @Query("filter") String filter
    );
}
