package com.example.itakg.musicapp.Activities;

import android.support.v4.app.Fragment;

import com.example.itakg.musicapp.Fragments.ListFragment;
import com.example.itakg.musicapp.Messages.MyMessage;
import com.example.itakg.musicapp.R;

public class MainActivity extends FragmentActivity {
    private static final String TITLE = "PLAY MUSIC";
    private int flag = 0;

    @Override
    protected boolean hasToolbar() {
        return true;
    }

    @Override
    protected String setToolbarTitle() {
        return TITLE;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.container;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected Fragment getFragmentInstance() {
        return new ListFragment();
    }

    @Override
    public void onBackPressed() {
        if (flag == 0) {
            MyMessage.showToast(this, "Press again to exit");
            flag = 1;
        } else {
            super.onBackPressed();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }
}
