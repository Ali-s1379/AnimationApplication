package com.example.animationapplication;

import androidx.fragment.app.Fragment;


public class MainActivity extends  SingleFragmentActivity {

    @Override
    public Fragment getFragment() {
        return DragAndDrawFragment.newInstance();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public int getResourceId() {
        return R.id.container;
    }

}
