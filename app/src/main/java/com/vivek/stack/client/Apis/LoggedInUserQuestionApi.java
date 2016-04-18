package com.vivek.stack.client.Apis;

import com.vivek.stack.client.model.LoggedInQuestionModel.QuestionData;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Vivek on 16-04-2016.
 */
public interface LoggedInUserQuestionApi {

    @GET("me/questions?order=desc&sort=activity&site=stackoverflow")
    Call<QuestionData> getUserQuestion(@Query("key") String key, @Query("access_token") String token);


    @GET("me/questions/no-answers?order=desc&sort=activity&site=stackoverflow")
    Call<QuestionData> getUserUnAnsweredQuestion(@Query("key") String key, @Query("access_token") String token);

    @GET("me/questions/unaccepted?order=desc&sort=activity&site=stackoverflow")
    Call<QuestionData> getUserAnsweredQuestion(@Query("key") String key, @Query("access_token") String token);


    @GET("questions?order=asc&sort=hot&site=stackoverflow")
    Call<QuestionData> getGuestUserQuestions();

    @GET("questions/no-answers?order=desc&sort=votes&site=stackoverflow")
    Call<QuestionData> getGuestUserUnAnsweredQuestions();

    @GET("questions/featured?order=desc&sort=votes&site=stackoverflow")
    Call<QuestionData> getGuestUserFeaturedQuestions();


}
