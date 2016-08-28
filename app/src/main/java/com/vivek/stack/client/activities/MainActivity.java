package com.vivek.stack.client.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.scribejava.apis.StackExchangeApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.vivek.stack.client.Apis.UserProfile;
import com.vivek.stack.client.R;
import com.vivek.stack.client.model.UserProfile.ProfileItems;
import com.vivek.stack.client.network.Client;
import com.vivek.stack.client.util.CheckConnection;
import com.vivek.stack.client.util.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Vivek on 16-04-2016.
 */
public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private boolean isLoggedIn;
    private String consumerKey = null;
    private String consumerSecret = null;
    private String callbackUrl = null;
    private String oAuthVerifier = null;
    private Retrofit retrofit;
    private UserProfile userProfileImplementation;
    private Drawer drawer;
    private AccountHeader accountHeader;
    private PrimaryDrawerItem questionPrimaryItem ,tagsPrimaryItem , userPrimaryItem , askQuestionItem , myProfilePrimaryItem;
    private SecondaryDrawerItem badgeHelpSecondaryItem;



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


    @BindView(R.id.login_button)
    Button login;
    @BindView(R.id.guest_user_button)
    Button guest;
    @BindView(R.id.container)
    LinearLayout loginViewContainer;
    @BindView(R.id.login_layout)
    RelativeLayout loginViewSubContainer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private SharedPreferences.Editor editor;
    private static String userName  , userProfileImage;
    public static Drawable bitmapDrawable;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initStackConfigs();
        setSupportActionBar(toolbar);
        mSharedPreferences = getSharedPreferences(PREF_NAME, 0);
        editor = mSharedPreferences.edit();
        isLoggedIn = mSharedPreferences.getBoolean(PREF_KEY_STACK_LOGIN , false);
        checkLoggenInStatus();
        loggedInUserDrawerItems();

        userName = mSharedPreferences.getString("userName" , "Guest");
        userProfileImage = mSharedPreferences.getString("imageUrl" , "");
        accountHeader = new AccountHeaderBuilder().withActivity(MainActivity.this)
                .addProfiles(new ProfileDrawerItem().withName("Hi " +userName)
                        .withIcon(userProfileImage))
                .build();
        drawer = new DrawerBuilder().withActivity(this)
                .addDrawerItems(questionPrimaryItem, tagsPrimaryItem , userPrimaryItem
                ,askQuestionItem , myProfilePrimaryItem , new DividerDrawerItem() ,
                        badgeHelpSecondaryItem)
                .withAccountHeader(accountHeader)
                .withToolbar(toolbar)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if(drawerItem.getIdentifier() == 1){
                            buildUserLoggedInIntent();
                        }
                        return false;
                    }
                })
                .build();

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

        progressDialog = new ProgressDialog(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkConnection.isConnected()) {
                    Snackbar.make(loginViewContainer, "you are not connected to the internet!", Snackbar.LENGTH_LONG).
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
                    Snackbar.make(loginViewContainer, "you are not connected to the internet!", Snackbar.LENGTH_LONG).
                            setAction("Try again", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                    startActivity(intent);
                                }
                            }).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, GuestUserQuestions.class);
                    startActivity(intent);

                }
            }
        });

        isLoggedIn = mSharedPreferences.getBoolean(PREF_KEY_STACK_LOGIN, false);

    }

    private void loggedInUserDrawerItems() {
        questionPrimaryItem = new PrimaryDrawerItem().withName("Questions")
                .withDescription("View Questions").withIdentifier(1)
                 .withIcon(R.drawable.ic_keyboard_arrow_up);
        tagsPrimaryItem = new PrimaryDrawerItem().withName("Tags")
                .withDescription("Search for tags").withIdentifier(2)
                .withIcon(R.drawable.ic_tags_24dp);
        userPrimaryItem = new PrimaryDrawerItem().withName("Users")
                .withDescription("View users on stack").withIdentifier(3)
                .withIcon(R.drawable.ic_users_24dp);
        askQuestionItem  = new PrimaryDrawerItem().withName("Ask Questions")
                .withDescription("Ask your question").withIcon(R.drawable.ic_create_question_24dp);
        myProfilePrimaryItem = new PrimaryDrawerItem().withIcon(R.drawable.ic_profile_24dp)
                .withName("My profile").withDescription("View your profile");

        badgeHelpSecondaryItem = new SecondaryDrawerItem().withName("Badges")
                .withDescription("About badges").withIcon(R.drawable.material_drawer_badge);


    }

    private void getUserProfileInfo() {
        retrofit = Client.getInstance();
        userProfileImplementation = retrofit.create(UserProfile.class);

        userProfileImplementation.getUserQuestion(Constants.API_KEY ,mSharedPreferences.getString(PREF_KEY_OAUTH_TOKEN , ""))
                .enqueue(new Callback<ProfileItems>() {
                    @Override
                    public void onResponse(Response<ProfileItems> response, Retrofit retrofit) {
                         if(response != null){
                             if(response.isSuccess() && response.code() == 200){
                                 String userName = response.body().getItems().get(0).getDisplayName();
                                 String userProfileImage = response.body().getItems().get(0).getProfileImage();
                                 editor = mSharedPreferences.edit();
                                 editor.putString("userName" , userName);
                                 editor.putString("imageUrl" , userProfileImage);
                                 editor.commit();

                             }
                         }
                        else{
                             Snackbar.make(loginViewContainer, "Failed to load user info!", Snackbar.LENGTH_LONG).
                                     setAction("Try again", new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             getUserProfileInfo();
                                         }
                                     }).show();
                         }
                    }
                    @Override
                    public void onFailure(Throwable t) {
                        Snackbar.make(loginViewContainer, "Failed to load user info!", Snackbar.LENGTH_LONG).
                                setAction("Try again", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getUserProfileInfo();
                                    }
                                }).show();
                    }
                });

    }

    private void buildUserLoggedInIntent() {
        Intent intent = new Intent(MainActivity.this , UserLoggedInQuestions.class);
        startActivity(intent);
    }


    public class GetAccessToken extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String verifier = params[0];
            OAuth2AccessToken oAuth2AccessToken = service.getAccessToken(params[0]);
            saveStackInfo(oAuth2AccessToken, verifier);
            return oAuth2AccessToken.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s != null && !s.isEmpty()){

                Intent intent = new Intent(MainActivity.this , UserLoggedInQuestions.class);
                startActivity(intent);
            }
        }
    }

    private void saveStackInfo(OAuth2AccessToken accessToken, String verifier) {
        try {

			/* Storing oAuth tokens to shared preferences */
            editor.putString(PREF_KEY_OAUTH_TOKEN, accessToken.getAccessToken());
            editor.putString(PREF_KEY, verifier);
            editor.putBoolean(PREF_KEY_STACK_LOGIN, true);
            editor.commit();


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
        userName = "";
        userProfileImage = "";

    }


    private void login() {
        Intent intent = new Intent(MainActivity.this, OuthActivity.class);
        startActivityForResult(intent, 20);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            String verifier = data.getExtras().getString(getString(R.string.stack_code));
            try {
                new GetAccessToken().execute(verifier);
            } catch (Exception e) {
                Snackbar.make(loginViewContainer, "Stack login failed", Snackbar.LENGTH_LONG).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLoggenInStatus();
    }

    private void checkLoggenInStatus() {
        if(isLoggedIn){
            loginViewSubContainer.setVisibility(View.GONE);
            getUserProfileInfo();
        } else{
            loginViewSubContainer.setVisibility(View.VISIBLE);
        }
    }
    private class convertUrlToDrawableAsyncTask extends AsyncTask<String , Void , Drawable>{

        @Override
        protected Drawable doInBackground(String... strings) {
            return getDrawableFromUrl(strings[0]);
        }

        @Override
        protected void onPostExecute(Drawable drawable) {
            super.onPostExecute(drawable);
            if(drawable != null){
                bitmapDrawable = drawable;
            }
        }
    }
    public static Drawable getDrawableFromUrl(String url) {
        Bitmap x = null;

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.connect();
            InputStream input = null;
            input = connection.getInputStream();
            x = BitmapFactory.decodeStream(input);
        }catch (IOException e){

        }
        return new BitmapDrawable(x);
    }

}
