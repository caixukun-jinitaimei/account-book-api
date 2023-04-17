package com.example.accountbook1.ui;

import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.accountbook1.R;
import com.example.accountbook1.utils.AppManager;
import com.example.accountbook1.view.MainActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.example.accountbook1.utils.Constants;
import com.example.accountbook1.view.HomepageActivity;
import com.example.accountbook1.view.LoginActivity;

import okhttp3.Call;

public class MeFragment extends Fragment implements View.OnClickListener {

    private LinearLayout homepage;
    private TextView exit;
    private TextView usernameTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_me, null);
        findViewById(v);
        initView();

        return v;
    }

    public void findViewById(View v) {
        homepage = (LinearLayout) v.findViewById(R.id.me_homepage);
        exit = (TextView) v.findViewById(R.id.me_item_exit);
        usernameTV = (TextView) v.findViewById(R.id.me_homepage_username);
    }

    public void initView() {
        homepage.setOnClickListener(this);
        exit.setOnClickListener(this);
        echo();
        Homepage();
    }

    private void echo() {
        usernameTV.setText(Constants.USER.getUsername());
    }

    private void Homepage() {
        String url = Constants.BASE_URL + "DailyCheck?method=Homepage";
        OkHttpUtils
                .post()
                .url(url)
                .addParams("userId", Constants.USER.getUserId() + "")
                .id(1)
                .build()
                .execute(new MyStringCallback());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_homepage:
                startActivity(new Intent(getActivity(), HomepageActivity.class));
                break;
            case R.id.me_item_exit:
                SystemClock.sleep(500);
                AppManager.getInstance().killAllActivity();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onResponse(String response, int id) {
            switch (id) {
                case 1:

                    break;
                case 2:

                    break;
                default:
                    Toast.makeText(getActivity(), "what?", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        @Override
        public void onError(Call arg0, Exception arg1, int arg2) {
            Toast.makeText(getActivity(), "网络链接出错！", Toast.LENGTH_SHORT).show();
        }
    }


}
