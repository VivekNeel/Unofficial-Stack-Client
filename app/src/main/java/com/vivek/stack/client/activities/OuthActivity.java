package com.vivek.stack.client.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.github.scribejava.apis.StackExchangeApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.vivek.stack.client.R;
import com.vivek.stack.client.util.Constants;

/**
 * Created by Vivek on 16-04-2016.
 */
public class OuthActivity extends AppCompatActivity {


    private static final String TAG = OuthActivity.class.getName();
    final OAuth20Service service = new ServiceBuilder()
            .apiKey(Constants.API_ID)
            .apiSecret(Constants.API_SECRET)
            .state(Constants.API_STATE)
            .callback(Constants.API_REDIRECT_URI)
            .build(StackExchangeApi.instance());


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        String url = service.getAuthorizationUrl();

        LinearLayout root = new LinearLayout(this);
        root.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

        WebView wv = new WebView(this);
        wv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        wv.getSettings().setJavaScriptEnabled(true);


        progressDialog.show();
        progressDialog.setMessage("Loading..");
        wv.loadUrl(url);
        root.addView(wv);

        setContentView(root);
        wv.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.contains(getResources().getString(R.string.stack_callback))) {
                    Uri uri = Uri.parse(url);

                    String verifier = uri.getQueryParameter(getString(R.string.stack_code));
                    Log.i(TAG, verifier);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(getString(R.string.stack_code), verifier);
                    setResult(RESULT_OK, resultIntent);

                    progressDialog.dismiss();
                    finish();
                    return true;
                }
                return false;
            }
        });
    }


}
