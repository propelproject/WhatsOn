package com.example.asus.whatson;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.asus.whatson.Fragment.Email;
import com.example.asus.whatson.Fragment.Phone;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements Phone.OnFragmentInteractionListener,
    Email.OnFragmentInteractionListener{

    private Toolbar registerToolbar;
    private TabLayout registerTabLayout;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerToolbar = (Toolbar)findViewById(R.id.registerToolbar);
        registerTabLayout = (TabLayout)findViewById(R.id.registerTabLayout);
        viewPager = (ViewPager)findViewById(R.id.registerViewPager);

        registerToolbar.setTitle("REGISTER");
        setupViewPager(viewPager);
        registerTabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {

        RegisterViewPagerAdapter registerViewPagerAdapter = new RegisterViewPagerAdapter(getSupportFragmentManager());
        registerViewPagerAdapter.addFragment(new Phone(), "USING PHONE NUMBER");
        registerViewPagerAdapter.addFragment(new Email(), "USING EMAIL");
        viewPager.setAdapter(registerViewPagerAdapter);

    }

    class RegisterViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mStringList = new ArrayList<>();

        public RegisterViewPagerAdapter (FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mStringList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mStringList.get(position);
        }
    }

    @Override
    public void onEmailFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPhoneFragmentInteraction(Uri uri) {

    }
}
