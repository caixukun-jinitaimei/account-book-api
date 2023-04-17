package com.example.accountbook1.view;

import static com.example.accountbook1.utils.SimpleDateFormatUtils.sdf;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.accountbook1.model.c2s.UserLogin;
import com.example.accountbook1.model.s2c.ErrorMessage;
import com.example.accountbook1.model.s2c.SuccessMessage;
import com.example.accountbook1.utils.AppManager;
import com.example.accountbook1.utils.ErrorResponseUtils;
import com.example.accountbook1.utils.GsonUtils;
import com.example.accountbook1.utils.MyOkHttpUtils;
import com.example.accountbook1.view.base.BaseActivity;
import com.google.gson.Gson;
import com.example.accountbook1.R;
import com.example.accountbook1.model.client.User;
import com.example.accountbook1.utils.Constants;
import com.example.accountbook1.utils.SharedPreferencesUtils;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class LoginActivity extends BaseActivity implements OnClickListener {
    private String TITLE_NAME = "登录";
    private String TAG= "LoginActivity";
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
        MyOkHttpUtils.POST(url,new UserLogin(username,password),new MyCallBack());
    }

    public class MyCallBack implements Callback {

        @Override
        public void onFailure(Call call, IOException e) {
            String message = "网络链接出错！";
            System.out.println(message);
            DisplayToast(message);

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            Gson gson = new Gson();

            String bodyStr = response.body().string();
            try {
                com.example.accountbook1.model.server.User user = gson.fromJson(bodyStr,com.example.accountbook1.model.server.User.class);

                Log.d(TAG,"login success,"+user.toString());
//                DisplayToast("欢迎你，"+user.getUid());
                openActivity(MainActivity.class);
//                finish();
            } catch (JsonSyntaxException e) {
                try {
                    ErrorMessage errorMessage = gson.fromJson(bodyStr, ErrorMessage.class);
                    DisplayToast(errorMessage.toString());
                    Log.d(TAG,errorMessage.toString());

                }catch (JsonSyntaxException e2){
                    throw new RuntimeException(e2);

                }

                e.printStackTrace();
            }

        }
    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onResponse(String response, int id) {
            System.out.println("onResponse");
            Gson gson = new Gson();
            switch (id) {
                case 2:
                    ErrorMessage errorMessage = ErrorResponseUtils.parseError(response);
                    if (errorMessage != null) {
                        Toast.makeText(mContext, errorMessage.toString(), Toast.LENGTH_SHORT).show();
                        System.out.println(errorMessage);
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
