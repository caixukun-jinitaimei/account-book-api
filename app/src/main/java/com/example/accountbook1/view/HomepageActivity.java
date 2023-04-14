package com.example.accountbook1.view;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.accountbook1.model.client.User;
import com.example.accountbook1.utils.Constants;
import com.example.accountbook1.utils.SharedPreferencesUtils;
import com.example.accountbook1.view.base.BaseActivity;
import com.example.accountbook1.R;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class HomepageActivity extends BaseActivity implements View.OnClickListener {

    private String TITLE_NAME = "关于我";
    private View title_back;
    private TextView titleText;

    private Context mContext;
    private TextView username;
    private RadioGroup sexGroup;
    private RadioButton maleRadio;
    private RadioButton femaleRadio;

    private EditText job;
    private EditText income;
    private EditText target_management;
    private EditText age;



    private Button update;

    private String jobStr;
    private String incomeStr;
    private String targetManagementStr;
    private String ageStr;
    private String sex;



    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_homepage);
        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        this.title_back = $(R.id.title_back);
        this.titleText = $(R.id.titleText);
        sexGroup = $(R.id.homepage_radio_sex);
        update = $(R.id.homepage_btn_update);
        job = $(R.id.homepage_et_job);
        income = $(R.id.homepage_et_income);
        target_management= $(R.id.homepage_et_wealth_management);
        age = $(R.id.homepage_et_age);
        username = $(R.id.homepage_tv_username);



        maleRadio = $(R.id.homepage_rd_male);
        femaleRadio = $(R.id.homepage_rd_female);
    }

    @Override
    protected void initView() {
        mContext = this;
        this.titleText.setText(TITLE_NAME);

        this.title_back.setOnClickListener(this);
        update.setOnClickListener(this);

        echo();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back: {
                this.finish();
            }
            break;
            case R.id.homepage_btn_update:
                checkInfo();
                break;
        }
    }

    private void echo() {
        username.setText(Constants.USER.getUsername());
        job.setText(Constants.USER.getJob() + "");
        income.setText(Constants.USER.getIncome() + "");
        target_management.setText(Constants.USER.getTarget_management() + "");
        age.setText(Constants.USER.getAge() + "");
        if (Constants.USER.getSex().equals("女")) {
            femaleRadio.setChecked(true);
        } else {
            maleRadio.setChecked(true);
        }
    }

    private void checkInfo() {
        jobStr = job.getText().toString().trim();
        incomeStr = income.getText().toString().trim();
        targetManagementStr = target_management.getText().toString().trim();
        ageStr = age.getText().toString().trim();
        sex = "男";
        if (sexGroup.getCheckedRadioButtonId() == R.id.homepage_rd_female) {
            sex = "女";
        }

        if (TextUtils.isEmpty(jobStr) || TextUtils.isEmpty(incomeStr) || TextUtils.isEmpty(ageStr)) {
            DisplayToast("不可留空！");
            return;
        }

        update();

    }

    private void update() {
        String url = Constants.BASE_URL + "User?method=update";
        OkHttpUtils
                .post()
                .url(url)
                .addParams("username", Constants.USER.getUsername())
                .addParams("job", jobStr)
                .addParams("income", incomeStr)
                .addParams("target_management", targetManagementStr)
                .addParams("age", ageStr)
                .addParams("sex", sex)
                .id(1)
                .build()
                .execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback {

        @Override
        public void onResponse(String response, int id) {
            Gson gson = new Gson();
            switch (id) {
                case 1:
                    User user = null;
                    try {
                        user = gson.fromJson(response, User.class);
                    } catch (JsonSyntaxException e) {
                        user = null;
                    }
                    if (user == null) {
                        DisplayToast(response);
                        return;
                    } else {
                        // 存储用户
                        Constants.USER.setJob(user.getJob());
                        Constants.USER.setIncome(user.getIncome());
                        Constants.USER.setSex(user.getSex());
                        Constants.USER.setTarget_management(user.getTarget_management());
                        Constants.USER.setAge(user.getAge());
                        user.setPassword(Constants.USER.getPassword());
                        user.setUserId(Constants.USER.getUserId());
                        boolean result = SharedPreferencesUtils.saveUserInfo(mContext, user);
                        if (result) {
                            Toast.makeText(mContext, "更新成功！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, "用户信息存储失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    finish();
                    break;
                case 2:

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