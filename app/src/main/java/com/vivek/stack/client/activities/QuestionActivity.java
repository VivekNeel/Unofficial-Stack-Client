package com.vivek.stack.client.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.scribejava.apis.StackExchangeApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.vivek.stack.client.R;
import com.vivek.stack.client.util.CheckConnection;
import com.vivek.stack.client.util.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Vivek on 16-04-2016.
 */
public class QuestionActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private boolean isLoggedIn;
    private String consumerKey = null;
    private String consumerSecret = null;
    private String callbackUrl = null;
    private String oAuthVerifier = null;


    private CheckConnection checkConnection;
    final OAuth20Service service = new ServiceBuilder()
            .apiKey(Constants.API_ID)
            .apiSecret(Constants.API_SECRET)
            .state(Constants.API_STATE)
            .scope(Constants.API_STATE)
            .callback(Constants.API_REDIRECT_URI)

            .build(StackExchangeApi.instance());

    private static final String PREF_NAME = "stack_pref";
    public static final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
    private static final String PREF_KEY = "oauth_key";
    private static final String PREF_KEY_STACK_LOGIN = "is_stack_loggedin";
    public static SharedPreferences mSharedPreferences;


    @Bind(R.id.login_button)
    Button login;
    @Bind(R.id.guest_user_button)
    Button guest;
    @Bind(R.id.loggedin_user)
    Button loggedin;
    @Bind(R.id.container)
    LinearLayout linearLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_two);
        ButterKnife.bind(this);
        initStackConfigs();
        mSharedPreferences = getSharedPreferences(PREF_NAME, 0);

        progressDialog = new ProgressDialog(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkConnection.isConnected()) {
                    Snackbar.make(linearLayout, "you are not connected to the internet!", Snackbar.LENGTH_LONG).
                            setAction("Try again", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                    startActivity(intent);
                                }
                            }).show();
                } else {
                    login();
                }
            }
        });

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkConnection.isConnected()) {
                    Snackbar.make(linearLayout, "you are not connected to the internet!", Snackbar.LENGTH_LONG).
                            setAction("Try again", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                    startActivity(intent);
                                }
                            }).show();
                } else {
                    Intent intent = new Intent(QuestionActivity.this, GuestUserQuestions.class);
                    startActivity(intent);

                }
            }
        });

        isLoggedIn = mSharedPreferences.getBoolean(PREF_KEY_STACK_LOGIN, false);


        loggedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!checkConnection.isConnected()) {
                    Snackbar.make(linearLayout, "you are not connected to the internet!", Snackbar.LENGTH_LONG).
                            setAction("Try again", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                    startActivity(intent);
                                }
                            }).show();
                } else {
                    Intent intent = new Intent(QuestionActivity.this, UserLoggedInQuestions.class);
                    startActivity(intent);

                }
            }
        });


    }


    public class GetAcessToken extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            String verifier = params[0];
            OAuth2AccessToken oAuth2AccessToken = service.getAccessToken(params[0]);
            saveStackInfo(oAuth2AccessToken, verifier);


            return oAuth2AccessToken.toString();
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
//        if (isLoggedIn) {
//            loggedin.setVisibility(View.VISIBLE);
//        } else {
//            loggedin.setVisibility(View.GONE);
//        }


    }


    private void saveStackInfo(OAuth2AccessToken accessToken, String verifier) {
        try {

			/* Storing oAuth tokens to shared preferences */
            SharedPreferences.Editor e = mSharedPreferences.edit();
            e.putString(PREF_KEY_OAUTH_TOKEN, accessToken.getAccessToken());

            e.putString(PREF_KEY, verifier);
            e.putBoolean(PREF_KEY_STACK_LOGIN, true);
            e.commit();


        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void initStackConfigs() {

        consumerKey = getString(R.string.stack_consumer_key);
        consumerSecret = getString(R.string.stack_consumer_secret);
        callbackUrl = getString(R.string.stack_callback);
        oAuthVerifier = getString(R.string.stack_code);
        checkConnection = new CheckConnection(getApplicationContext());

    }


    private void login() {

        Intent intent = new Intent(QuestionActivity.this, OuthActivity.class);
        startActivityForResult(intent, 20);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            String verifier = data.getExtras().getString(getString(R.string.stack_code));
            try {
                new GetAcessToken().execute(verifier);
                //      button.setText(oAuth2AccessToken.toString());
            } catch (Exception e) {
                Snackbar.make(linearLayout, "Stack login failed", Snackbar.LENGTH_LONG).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}
