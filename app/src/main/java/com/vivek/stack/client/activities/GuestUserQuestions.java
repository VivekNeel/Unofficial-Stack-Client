package com.vivek.stack.client.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.vivek.stack.client.R;
import com.vivek.stack.client.adapter.ViewPagerAdapter;
import com.vivek.stack.client.fragments.GuestUser.GuesUserUnAnsweredQuestionsFragment;
import com.vivek.stack.client.fragments.GuestUser.GuestUserFeaturedQuestionsFragment;
import com.vivek.stack.client.fragments.GuestUser.GuestUserQuestionsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vivek on 16-04-2016.
 */
public class GuestUserQuestions extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guestuser_tablayout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new GuestUserQuestionsFragment(), "Questions");
        viewPagerAdapter.addFragment(new GuesUserUnAnsweredQuestionsFragment(), "UnAnswered");
        viewPagerAdapter.addFragment(new GuestUserFeaturedQuestionsFragment(), "Featured");

        viewPager.setAdapter(viewPagerAdapter);
    }
}
