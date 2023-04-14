package com.example.accountbook1.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.accountbook1.model.client.Record;
import com.example.accountbook1.utils.Constants;
import com.example.accountbook1.utils.DateUtils;
import com.example.accountbook1.utils.PopWindows;
import com.example.accountbook1.utils.SharedPreferencesUtils;
import com.example.accountbook1.utils.StatusBarUtils;
import com.example.accountbook1.view.base.BaseActivity;
import com.example.accountbook1.R;
import com.example.accountbook1.utils.SP;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class IncreaseActivity extends BaseActivity implements View.OnClickListener {
    private Context mContext;
    private Switch DateSwitch, TimeSwitch;
    private TextView SelectDate, SelectTime, TypeText, TextLabel, NameNum, ShopsNum, PriceNum;
    private EditText GoodsName, GoodsPrice, GoodsShops;
    private ImageView NameDelete, PriceDelete, ShopsDelete;
    private LinearLayout DateLayout, TimeLayout;
    private CalendarView mCalendarView;
    private TimePicker mTimePicker;
    private static final String TAG = "IncreaseActivity";
    private String CNDate;
    private PopWindows poPWindows;
    private RadioGroup mGroup;
    private RadioButton mIncome, mPay;

    private static String _goodsTime = "GoodsTime";
    private static String _goodsDate = "GoodsDate";
    private static String _goodsType = "GoodsType";
    private static String _goodsName = "GoodsName";
    private static String _goodsShops = "GoodsShops";
    private static String _goodsPrice = "GoodsPrice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getViewData(savedInstanceState);
        super.onCreate(savedInstanceState);
        StatusBarUtils.setStatusBarHide(getWindow());
        StatusBarUtils.setStatusBarLightMode(getWindow());
        StatusBarUtils.setStatusBarColor(getWindow(), IncreaseActivity.this, R.color.grey);
        setContentView(R.layout.activity_increase);
        findViewById();
        initView();
        getDate();
        getTime();
        Receive();
        getContent();
    }

    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        //saveViewData(outState);
        super.onSaveInstanceState(outState, outPersistentState);
    }


    @Override
    protected void findViewById() {
        DateSwitch = findViewById(R.id.DateSwitch);
        TimeSwitch = findViewById(R.id.TimeSwitch);
        DateLayout = findViewById(R.id.DateLayout);
        TimeLayout = findViewById(R.id.TimeLayout);
        mCalendarView = findViewById(R.id.mCalendarView);
        mTimePicker = findViewById(R.id.mTimePicker);
        SelectDate = findViewById(R.id.SelectDate);
        SelectTime = findViewById(R.id.SelectTime);
        TypeText = findViewById(R.id.TypeText);
        TextLabel = findViewById(R.id.TextLabel);
        NameNum = findViewById(R.id.NameNum);
        ShopsNum = findViewById(R.id.ShopsNum);
        PriceNum = findViewById(R.id.PriceNum);
        GoodsName = findViewById(R.id.GoodsName);
        GoodsShops = findViewById(R.id.shops);
        GoodsPrice = findViewById(R.id.GoodsPrice);
        NameDelete = findViewById(R.id.NameDelete);
        ShopsDelete = findViewById(R.id.Shop_Delete);
        PriceDelete = findViewById(R.id.PriceDelete);
        poPWindows = new PopWindows(IncreaseActivity.this);
        poPWindows.Init(R.layout.goods_type);
        mGroup = poPWindows.getView().findViewById(R.id.mGroup);
        mIncome = poPWindows.getView().findViewById(R.id.mIncome);
        mPay = poPWindows.getView().findViewById(R.id.mPay);
    }

    @Override
    protected void initView() {
        mContext = this;
        DateSwitch.setOnClickListener(this);
        TimeSwitch.setOnClickListener(this);
        NameDelete.setOnClickListener(this);
        PriceDelete.setOnClickListener(this);
        SelectTime.setText(DateUtils.getCurrentTime());
        String date = DateUtils.getLongToString(System.currentTimeMillis());
        String week = DateUtils.getWeekOfDate(DateUtils.getStringToDate(date));
        SelectDate.setText(date + " " + week);
    }

    /**
     * 监听用户输入的商品名称和商品价格，并显示字符长度
     */
    private void getContent() {
        GoodsName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                NameNum.setText(s.length() + "");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        GoodsShops.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ShopsNum.setText(s.length() + "");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        GoodsPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                PriceNum.setText(s.length() + "");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 显示选择的年月日，并将选择的年月日转为星期
     */
    private void getDate() {
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month = month + 1;
                CNDate = year + "年" + month + "月" + dayOfMonth + "日";
                //String date = year + "-" + month + "-" + dayOfMonth;
                //Log.d(TAG, "date=" + date);
                //Log.d(TAG, "CNdate=" + CNDate);
                //string日期转date日期，在转为星期
                String week = DateUtils.getWeekOfDate(DateUtils.getStringToDate(CNDate));
                Log.d(TAG, "week =" + week);
                SelectDate.setText(CNDate + " " + week);
            }
        });
    }

    /**
     * 显示选择的时间
     */
    private void getTime() {
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                SelectTime.setText(time);
                Log.d(TAG, time);
            }
        });
    }

    /**
     * PopupWindows弹窗，收入和支出单选
     */
    public void SelectType(View view) {
        poPWindows.DisplayWindows();
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.mIncome:
                        TypeText.setText(mIncome.getText());
                        break;
                    case R.id.mPay:
                        TypeText.setText(mPay.getText());
                        break;
                }
                poPWindows.Dismiss();
            }
        });
    }

    public void SelectLabel(View view) {
        startActivity(new Intent(IncreaseActivity.this, LabelActivity.class));
    }



    /**
     * SWitch点击事件监听
     */
    class SwitchListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.DateSwitch:
                    if (isChecked)
                        DateLayout.setVisibility(View.VISIBLE);
                    else
                        DateLayout.setVisibility(View.GONE);
                    break;
                case R.id.TimeSwitch:
                    if (isChecked)
                        TimeLayout.setVisibility(View.VISIBLE);
                    else
                        TimeLayout.setVisibility(View.GONE);
                    break;
            }
        }
    }



    /**
     * 一键删除
     */

      @Override
      public void onClick(View v) {
            switch (v.getId()) {
                case R.id.NameDelete:
                    GoodsName.setText("");
                    NameNum.setText("0");
                    break;
                case R.id.Shop_Delete:
                    GoodsShops.setText("");
                    ShopsNum.setText("0");
                    break;
                case R.id.PriceDelete:
                    GoodsPrice.setText("");
                    PriceNum.setText("0");
                case R.id.BtnSave:
                    save();
            }
        }


    /**
     * 利用SharedPreferences接受来自标签页面的数据，数据由用户选择
     */
    private void Receive() {
        String tag = (String) SP.getInstance().GetData(IncreaseActivity.this, "Label", "");
        if (TextUtils.isEmpty(tag)) {
            Log.d(TAG, "null");
        } else {
            TextLabel.setText(tag);
            Log.d(TAG, tag);
        }
    }

    private void save(){
        String url = Constants.BASE_URL + "hello";
        OkHttpUtils
                .post()
                .url(url)
                .id(1)
                .addParams("mIncome", TypeText.getText().toString().trim())
                .addParams("mPay", TypeText.getText().toString().trim())
                .addParams("GoodsName",GoodsName.getText().toString().trim())
                .addParams("NameNum",NameNum.getText().toString().trim())
                .addParams("GoodsShops",GoodsShops.getText().toString().trim())
                .addParams("ShopsNum",ShopsNum.getText().toString().trim())
                .addParams("GoodsPrice",GoodsPrice.getText().toString().trim())
                .addParams("PriceNum",PriceNum.getText().toString().trim())
                .build()
                .execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback {

        @Override
        public void onResponse(String response, int id) {
            Gson gson = new Gson();
            switch (id) {
                case 1:
                    Record record = null;
                    try {
                        record = gson.fromJson(response, Record.class);
                    } catch (JsonSyntaxException e) {
                        record = null;
                    }
                    if (record == null) {
                        DisplayToast(response);
                        return;
                    } else {
                        // 存储账单
                        Constants.RECORD.setType(record.getType());
                        Constants.RECORD.setDate(record.getDate());
                        Constants.RECORD.setTime(record.getTime());
                        Constants.RECORD.setLabel(record.getLabel());
                        Constants.RECORD.setGoodsName(record.getGoodsName());
                        Constants.RECORD.setGoodsPrice(record.getGoodsPrice());
                        Constants.RECORD.setGoodsShops(record.getGoodsShops());
                        boolean result = SharedPreferencesUtils.saveRecordInfo(mContext, record);
                        if (result) {
                            Toast.makeText(mContext, "保存成功！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, "保存失败", Toast.LENGTH_SHORT).show();
                        }
                    }

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

