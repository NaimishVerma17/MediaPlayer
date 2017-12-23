package com.example.itakg.musicapp.Activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.itakg.musicapp.Information.AddFragment;
import com.example.itakg.musicapp.Messages.MyMessage;
import com.example.itakg.musicapp.R;
import com.example.itakg.musicapp.Views.MyLightTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class FragmentActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tool_bar_title)
    MyLightTextView title;

    protected abstract boolean hasToolbar();

    protected abstract String setToolbarTitle();

    protected abstract int getFragmentContainerId();

    protected abstract int getLayoutResourceId();

    protected abstract Fragment getFragmentInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.GONE);
        if (hasToolbar()) {
            toolbar.setVisibility(View.VISIBLE);
            title.setText(setToolbarTitle());
        }
        AddFragment.addFragment(FragmentActivity.this, getFragmentInstance(), getFragmentContainerId());
    }

}
