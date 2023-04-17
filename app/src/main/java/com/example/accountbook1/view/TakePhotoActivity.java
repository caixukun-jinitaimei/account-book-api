package com.example.accountbook1.view;

import com.example.accountbook1.R;
import com.example.accountbook1.utils.SharedPreferencesUtils;
import com.example.accountbook1.utils.sharedPreference.NewSharedPreferencesUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TakePhotoActivity extends AppCompatActivity {

    private ImageView iv_show_select;
    private int CAMERA_REQ_CODE = 1;
    private Uri uri;
    private int ALBUM_REQ_CODE = 2;
    private Uri uritempFile;
    private int CROP_REQ_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        iv_show_select = findViewById(R.id.iv_show_select);
    }

    public void camera(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String path = getFilesDir().getAbsolutePath() + File.separator + "test.jpg"; //这里只是当前目录，如果是多级文件夹 得通过 mkdirs 创建
        File file = new File(path);

        //由于 Android 文件安全机制 向第三方应用提供路径的时候得使用 FileProvider，注意需要在清单文件中注册
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            uri = FileProvider.getUriForFile(TakePhotoActivity.this, "com.wust.camerademo", file);
        } else {
            uri = Uri.fromFile(file);
        }

        //这里设置了 uri 那么后面就不能使用 data.getParcelableExtra("data") 获取图片了
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        startActivityForResult(intent, CAMERA_REQ_CODE);
    }

    public void album(View v) {
        //跳转相册 Intent.ACTION_PICK 、Intent.ACTION_GET_CONTENT 、Intent.ACTION_OPEN_DOCUMENT 三者任选其一
        Intent intent = new Intent(Intent.ACTION_PICK); //这个地方会导致 报 注意一 的错误
        intent.setType("image/*");
        startActivityForResult(intent, ALBUM_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("onActivityResult");
        if (requestCode == CAMERA_REQ_CODE && resultCode == RESULT_OK) {
            Bitmap picture = null;
            System.out.println(data);
            if (data != null && data.hasExtra("data")) {
                // 方法一：没有指定 uri 时图片资源由 data.getParcelableExtra("data") 获取
                picture = data.getParcelableExtra("data");
                iv_show_select.setImageBitmap(picture);
                System.out.println("data.getParcelableExtra(data) 方式获取");
            } else {
                // 方法二：通过 uri 方式获取
                try {
                    InputStream is = getContentResolver().openInputStream(uri);
                    picture = BitmapFactory.decodeStream(is);
                    iv_show_select.setImageBitmap(picture);
                    is.close();
                } catch (Exception e) {
                    System.out.println("uri方式获取图片发生错误："+e.getMessage());
                }
                System.out.println("uri 方式获取 =》 " + uri);
            }

            NewSharedPreferencesUtils.set(getApplicationContext(),"choosePicture",picture);
            Bitmap choosePicture = NewSharedPreferencesUtils.get(getApplicationContext(), "choosePicture", Bitmap.class);
        } else if (requestCode == CAMERA_REQ_CODE && resultCode == RESULT_CANCELED) {
            System.out.println("拍照取消");
        } else if (requestCode == ALBUM_REQ_CODE && resultCode == RESULT_OK) {
            Uri uri = data.getData();

            cropPhoto(uri);

            System.out.println("相册获取");
        } else if (requestCode == CROP_REQ_CODE && resultCode == RESULT_OK) {
            try {
                InputStream is = getContentResolver().openInputStream(uritempFile);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                iv_show_select.setImageBitmap(bitmap);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("图片裁剪完毕");
        }

    }

    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        //添加读写权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        //设置裁剪数据来源和类型
        intent.setDataAndType(uri, "image/*");
        //设置裁剪为true
        intent.putExtra("crop", "true");
        //设置大小
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        //不能以这种形式返回图片数据 因为现在图片很大 我们得以 uri 方式返回
        //intent.putExtra("return-data", true); 配合 onActivityResult
        // =》 Bundle bundle = intent.getExtras();
        // =》 final Bitmap image = bundle.getParcelable("data");
        //创建 uri getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) 这个地方会出现 注意点二 或 注意点三 的报错
        uritempFile = Uri.parse("file://" + "/" + getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + "/" + "small.jpg");
        System.out.println("uritempFile => " + uritempFile);
        //设置 uri
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        //设置格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, CROP_REQ_CODE);
    }
}