package com.vivek.stack.client.fragments.GuestUser;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vivek.stack.client.Apis.LoggedInUserQuestionApi;
import com.vivek.stack.client.R;
import com.vivek.stack.client.adapter.GuestUserQuestionAdapter;
import com.vivek.stack.client.model.LoggedInQuestionModel.Item;
import com.vivek.stack.client.model.LoggedInQuestionModel.QuestionData;
import com.vivek.stack.client.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Vivek on 17-04-2016.
 */
public class GuestUserFeaturedQuestionsFragment extends Fragment {

    private static final String TAG = GuestUserFeaturedQuestionsFragment.class.getName();
    private List<Item> itemList;
    private GuestUserQuestionAdapter guestUserQuestionAdapter;
    private ProgressDialog progressDialog;
    @BindView(R.id.userquestion_rv)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_container)
    LinearLayout linearLayout;

    public GuestUserFeaturedQuestionsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.userquestion_recylerview_container, container, false);
        ButterKnife.bind(this, view);
        init();
        progressDialog.setMessage("Loading..");
        progressDialog.show();

        getGuestUserFeaturedQuestions();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private void getGuestUserFeaturedQuestions() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoggedInUserQuestionApi loggedInUserQuestionApi = retrofit.create(LoggedInUserQuestionApi.class);
        loggedInUserQuestionApi.getGuestUserFeaturedQuestions().enqueue(new Callback<QuestionData>() {
            @Override
            public void onResponse(Response<QuestionData> response, Retrofit retrofit) {
                Log.i(TAG, String.valueOf(response.code()));
                if (response.code() == 200) {

                    for (int i = 0; i < response.body().getItems().size(); i++) {
                        itemList.add(response.body().getItems().get(i));
                    }
                } else {
                    Snackbar.make(linearLayout, "Something went wrong", Snackbar.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
                guestUserQuestionAdapter = new GuestUserQuestionAdapter(itemList);
                recyclerView.setAdapter(guestUserQuestionAdapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Snackbar.make(linearLayout, "Something went wrong", Snackbar.LENGTH_LONG).show();
            }
        });
    }


    private void init() {
        itemList = new ArrayList<>();
        progressDialog = new ProgressDialog(getActivity());
    }
}
