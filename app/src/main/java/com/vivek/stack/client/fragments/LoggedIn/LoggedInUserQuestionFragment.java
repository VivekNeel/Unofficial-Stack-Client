package com.vivek.stack.client.fragments.LoggedIn;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vivek.stack.client.Apis.LoggedInUserQuestionApi;
import com.vivek.stack.client.R;
import com.vivek.stack.client.activities.QuestionActivity;
import com.vivek.stack.client.adapter.LoggedInQuestionAdapter;
import com.vivek.stack.client.model.LoggedInQuestionModel.Item;
import com.vivek.stack.client.model.LoggedInQuestionModel.QuestionData;
import com.vivek.stack.client.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Vivek on 17-04-2016.
 */
public class LoggedInUserQuestionFragment extends Fragment {

    private List<Item> itemList;
    @Bind(R.id.userquestion_rv)
    RecyclerView recyclerView;
    private LoggedInQuestionAdapter loggedInQuestionAdapter;
    private ProgressDialog progressDialog;
    private AnsweredQuestionFragment answeredQuestionFragment;
    @Bind(R.id.fragment_container)
    LinearLayout linearLayout;


    public LoggedInUserQuestionFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.userquestion_recylerview_container, container, false);
        ButterKnife.bind(this, view);
        init();
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        getLoggedInUserQuestion();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private void getLoggedInUserQuestion() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()
        ).build();

        final LoggedInUserQuestionApi loggedInUserQuestionApi = retrofit.create(LoggedInUserQuestionApi.class);

        loggedInUserQuestionApi.getUserQuestion(Constants.API_KEY, QuestionActivity.mSharedPreferences.getString(
                QuestionActivity.PREF_KEY_OAUTH_TOKEN, ""
        )).enqueue(new Callback<QuestionData>() {
            @Override
            public void onResponse(Response<QuestionData> response, Retrofit retrofit) {
                int res = response.code();
                if (res == 200) {
                    for (int i = 0; i < response.body().getItems().size(); i++) {
                        itemList.add(response.body().getItems().get(i));

                    }

                } else {
                    Snackbar.make(linearLayout, "Something went wrong", Snackbar.LENGTH_LONG).show();
                }
                loggedInQuestionAdapter = new LoggedInQuestionAdapter(itemList);
                recyclerView.setAdapter(loggedInQuestionAdapter);

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
