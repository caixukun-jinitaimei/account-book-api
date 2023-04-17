package com.example.accountbook1.view;


//import android.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.icu.util.Freezable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.accountbook1.R;

import com.example.accountbook1.fragment.GeneralFragment;
import com.example.accountbook1.fragment.MeFragment;
import com.example.accountbook1.fragment.RecordFragment;
import com.example.accountbook1.utils.AppManager;
import com.example.accountbook1.view.base.BaseActivity;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private LinearLayout tabRecord;
    private LinearLayout tabGeneral;
    private LinearLayout tabMe;


    private ImageView icoRecord;
    private ImageView icoGeneral;
    private ImageView icoMe;

    private TextView txtRecord;
    private TextView txtGeneral;
    private TextView txtMe;
    private TextView txtTitle;

    private GeneralFragment generalFragment;
    private RecordFragment recordFragment;
    private MeFragment meFragment;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        initView();
    }


    protected void findViewById() {
        tabRecord = findViewById(R.id.bottom_record);
        tabGeneral = findViewById(R.id.bottom_general);
        tabMe = findViewById(R.id.bottom_me);

        icoRecord = findViewById(R.id.bottom_ico_record);
        icoGeneral = findViewById(R.id.bottom_ico_general);
        icoMe = findViewById(R.id.bottom_ico_me);

        txtRecord = findViewById(R.id.bottom_txt_record);
        txtGeneral = findViewById(R.id.bottom_txt_general);
        txtMe = findViewById(R.id.bottom_txt_me);
        txtTitle = findViewById(R.id.titleText);
    }


    protected void initView() {
        tabRecord.setOnClickListener(this);
        tabGeneral.setOnClickListener(this);
        tabMe.setOnClickListener(this);

        recordFragment = new RecordFragment();
        generalFragment = new GeneralFragment();
        meFragment = new MeFragment();

        refreashFragment(R.id.bottom_record);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_record:
                changeTabState(R.id.bottom_record);
                changeTitle(R.string.title_record);
                refreashFragment(R.id.bottom_record);
                break;
            case R.id.bottom_general:
                changeTabState(R.id.bottom_general);
                changeTitle(R.string.title_found);
                refreashFragment(R.id.bottom_general);
                break;
            case R.id.bottom_me:
                changeTabState(R.id.bottom_me);
                changeTitle(R.string.title_me);
                refreashFragment(R.id.bottom_me);
                break;
        }
    }


    private void changeTitle(int stringId) {
        // txtTitle.setText(getResources().getString(stringId));
    }

    /**
     * 切换Fragment
     * @param btnId
     */
    private void refreashFragment(int btnId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (btnId) {
            case R.id.bottom_record:
                transaction.replace(R.id.fragment_container, recordFragment);
                break;
            case R.id.bottom_general:
                transaction.replace(R.id.fragment_container, generalFragment);
                break;
            case R.id.bottom_me:
                transaction.replace(R.id.fragment_container, meFragment);
                break;
        }
        transaction.commit();
    }

    private void changeTabState(int tabId) {
        if (tabId == R.id.bottom_record) {
            icoRecord.setImageResource(R.drawable.icon_train_pressed);
            txtRecord.setTextColor(getResources().getColor(R.color.bottom_tab_pressed));
        } else {
            icoRecord.setImageResource(R.drawable.icon_train_unpressed);
            txtRecord.setTextColor(getResources().getColor(R.color.bottom_tab_normal));
        }
        if (tabId == R.id.bottom_general) {
            icoGeneral.setImageResource(R.drawable.icon_found_pressed);
            txtGeneral.setTextColor(getResources().getColor(R.color.bottom_tab_pressed));
        } else {
            icoGeneral.setImageResource(R.drawable.icon_found_unpressed);
            txtGeneral.setTextColor(getResources().getColor(R.color.bottom_tab_normal));
        }

        if (tabId == R.id.bottom_me) {
            icoMe.setImageResource(R.drawable.icon_me_pressed);
            txtMe.setTextColor(getResources().getColor(R.color.bottom_tab_pressed));
        } else {
            icoMe.setImageResource(R.drawable.icon_me_unpressed);
            txtMe.setTextColor(getResources().getColor(R.color.bottom_tab_normal));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 800) {
                Toast.makeText(getApplicationContext(),"再按一次退出",Toast.LENGTH_SHORT);
                mExitTime = System.currentTimeMillis();
                return true;
            } else {
                AppManager.getInstance().killAllActivity();
                AppManager.getInstance().AppExit(this);
            }
        }
        return super.onKeyDown(keyCode, event);
    }



}