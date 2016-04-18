package com.vivek.stack.client.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vivek.stack.client.R;
import com.vivek.stack.client.adapter.ViewPagerAdapter;
import com.vivek.stack.client.fragments.LoggedIn.AnsweredQuestionFragment;
import com.vivek.stack.client.fragments.LoggedIn.LoggedInUserQuestionFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Vivek on 16-04-2016.
 */
public class UserLoggedInQuestions extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new LoggedInUserQuestionFragment(), "Questions");
        viewPagerAdapter.addFragment(new AnsweredQuestionFragment(), "UnAnswered");
        viewPagerAdapter.addFragment(new AnsweredQuestionFragment(), "Answered");

        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
