package com.example.accountbook1.view;

import static com.example.accountbook1.utils.SimpleDateFormatUtils.sdf;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import okhttp3.Call;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.accountbook1.model.request.UserLogin;
import com.example.accountbook1.model.response.ErrorResponse;
import com.example.accountbook1.utils.ErrorResponseUtils;
import com.example.accountbook1.utils.GsonUtils;
import com.example.accountbook1.view.base.BaseActivity;
import com.google.gson.Gson;
import com.example.accountbook1.R;
import com.example.accountbook1.model.client.User;
import com.example.accountbook1.utils.Constants;
import com.example.accountbook1.utils.SharedPreferencesUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class LoginActivity extends BaseActivity implements OnClickListener {
    private EditText et_username;
    private EditText et_password;

    private Button bt_login;
    private Button bt_register;
    private Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;

        findViewById();
        initView();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        System.out.println("onClick");
        switch (v.getId()) {
            case R.id.login_bt_login:
                login();
                break;
            case R.id.login_bt_register:
                openActivity(RegisterActivity.class);
                Date date = new Date();
                String format = sdf.format(date);
                System.out.println(format);
                Date parse = null;
                try {
                    parse = sdf.parse(format);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println(parse.getTime());
                System.out.println(parse);
                break;

            default:
                break;
        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            openActivity(ConfigActivity.class);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void findViewById() {
        et_username = $(R.id.login_et_username);
        et_password = $(R.id.login_et_password);

        bt_login = $(R.id.login_bt_login);
        bt_register = $(R.id.login_bt_register);
    }

    @Override
    protected void initView() {
        bt_login.setOnClickListener(this);
        bt_register.setOnClickListener(this);
        echo();
    }

    /**
     * 回显
     */
    private void echo() {
        Map<String, String> map = SharedPreferencesUtils.getUserInfo(mContext);//获取用户名密码
        if (map != null) {
            String username = map.get("username");
            String password = map.get("password");
            et_username.setText(username);
            et_password.setText(password);
        }
    }

    private void login() {
        System.out.println("66666");
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            String text = "不可留空";
            System.out.println(text);

            Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
            return;
        }
        String url = Constants.BASE_URL + "/user/login";
        System.out.println(url);
        OkHttpUtils
                .post()
                .url(url)
                .id(2)
                .addParams("body", GsonUtils.gson.toJson(new UserLogin(username,password)))
                .build()
                .execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onResponse(String response, int id) {
            System.out.println("onResponse");
            Gson gson = new Gson();
            switch (id) {
                case 2:
                    ErrorResponse errorResponse = ErrorResponseUtils.parseError(response);
                    if (errorResponse != null) {
                        Toast.makeText(mContext, errorResponse.toString(), Toast.LENGTH_SHORT).show();
                        System.out.println(errorResponse);
                        break;
                    }
                    User user = gson.fromJson(response, User.class);
                    if (user.getUserId() == 0) {
                        DisplayToast("用户名或者密码错误");
                        return;
                    } else {
                        // 存储用户
                        Constants.USER = user;
                        boolean result = SharedPreferencesUtils.saveUserInfo(mContext, user);
                        Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                    }
                    openActivity(MainActivity.class);
                    finish();
                    break;

                default:
                    DisplayToast("what?");
                    break;
            }
        }

        @Override
        public void onError(Call arg0, Exception arg1, int arg2) {
            DisplayToast("网络链接出错！");
        }
    }

}
