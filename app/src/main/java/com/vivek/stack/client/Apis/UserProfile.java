package com.vivek.stack.client.Apis;

import com.vivek.stack.client.model.LoggedInQuestionModel.QuestionData;
import com.vivek.stack.client.model.UserProfile.ProfileItem;
import com.vivek.stack.client.model.UserProfile.ProfileItems;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by vivekneel on 8/28/16.
 */
public interface UserProfile {

    @GET("/2.2/me?order=desc&sort=reputation&site=stackoverflow")
    Call<ProfileItems> getUserQuestion(@Query("key") String key , @Query("access_token") String token);

}
