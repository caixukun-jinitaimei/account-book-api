package com.example.accountbook1.view;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.accountbook1.model.c2s.UserRegister;
import com.example.accountbook1.model.s2c.ErrorMessage;
import com.example.accountbook1.model.s2c.SuccessMessage;
import com.example.accountbook1.utils.MyOkHttpUtils;
import com.example.accountbook1.view.base.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.example.accountbook1.R;
import com.example.accountbook1.model.client.User;
import com.example.accountbook1.utils.AppManager;
import com.example.accountbook1.utils.Constants;
import com.example.accountbook1.utils.SharedPreferencesUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;

public class RegisterActivity extends BaseActivity implements OnClickListener {
    private String TITLE_NAME = "注册";
    private String TAG= "RegisterActivity";

    private View title_back;
    private TextView titleText;

    private Context mContext;
    private EditText et_username;
    private EditText et_password;
    private EditText et_repassword;
    private EditText et_job;
    private EditText et_income;
    private EditText et_target_management;
    private EditText et_age;
    private Button register_login;
    private RadioGroup radio_sex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById();
        initView();
    }

    @Override
    protected void initView() {
        mContext = this;
        this.title_back.setOnClickListener(this);
        this.titleText.setText(TITLE_NAME);
        this.register_login.setOnClickListener(this);


    }

    @Override
    protected void findViewById() {
        this.title_back = $(R.id.title_back);
        this.titleText = $(R.id.titleText);

        et_username = $(R.id.reg_et_username);
        et_password = $(R.id.reg_et_password);
        et_repassword = $(R.id.reg_et_repassword);
        et_job = $(R.id.reg_et_job);
        et_income = $(R.id.reg_et_income);
        et_target_management = $(R.id.reg_et_target_management);
        et_age = $(R.id.reg_et_age);

        this.radio_sex = (RadioGroup) findViewById(R.id.radio_sex);
        this.register_login = (Button) findViewById(R.id.reg_btn_register);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back: {
                Log.d(TAG,"返回按钮");
                this.finish();
            }
            break;
            case R.id.reg_btn_register:
                Log.d(TAG,"注册按钮");
                register();
                break;
        }
    }

    private void register() {

        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String repassword = et_repassword.getText().toString().trim();
        String job = et_job.getText().toString().trim();
        String income = et_income.getText().toString().trim();
        String target_management = et_target_management.getText().toString().trim();
        String age = et_age.getText().toString().trim();
        String sex = "女";
        if (radio_sex.getCheckedRadioButtonId() == R.id.reg_rd_male) {
            sex = "男";
        }
        //d.判断用户名密码是否为空，不为空请求服务器（省略，默认请求成功）i
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword) || TextUtils.isEmpty(job) || TextUtils.isEmpty(income)) {
            DisplayToast("信息不能为空");
            return;
        }
        // 判断两次密码
        if (!password.equals(repassword)) {
            DisplayToast("两次密码输入不一致");
            return;
        }
        UserRegister userRegister = new UserRegister(username, password, sex, job, income, target_management, age);

        System.out.println("啊？");
        // 服务端验证
        String url = Constants.BASE_URL + "/user/register";

        MyOkHttpUtils.POST(url, userRegister,  new MyCallBack());
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
                SuccessMessage successMessage = gson.fromJson(bodyStr, SuccessMessage.class);
                String successMassage = successMessage.getSuccessMassage();
                Log.d(TAG,successMassage);
                DisplayToast(successMassage);
                openActivity(LoginActivity.class);
                AppManager.getInstance().killAllActivity();
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
//    public class MyStringCallback extends StringCallback {
//
//
//        @Override
//        public void onResponse(String response, int id) {
//            System.out.println(response);
//            Gson gson = new Gson();
//            switch (id) {
//                case 1:
//                    try {
//                        SuccessMessage successMessage = gson.fromJson(response, SuccessMessage.class);
//                        String successMassage = successMessage.getSuccessMassage();
//                        System.out.println(successMassage);
//                        DisplayToast(successMassage);
//                        openActivity(LoginActivity.class);
//                        AppManager.getInstance().killAllActivity();
//                        break;
//                    } catch (JsonSyntaxException e) {
//                        try {
//                            ErrorMessage errorMessage = gson.fromJson(response, ErrorMessage.class);
//                            DisplayToast(errorMessage.toString());
//
//                        }catch (JsonSyntaxException e2){
//                            throw new RuntimeException(e2);
//
//                        }
//
//                        e.printStackTrace();
//                    }
//
//
//
//                default:
//                    DisplayToast("what?");
//                    break;
//            }
//        }
//
//        @Override
//        public void onError(Call arg0, Exception arg1, int arg2) {
//            DisplayToast("网络链接出错！");
//        }
//    }
}

