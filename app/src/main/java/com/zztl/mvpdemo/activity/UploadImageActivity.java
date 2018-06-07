package com.zztl.mvpdemo.activity;

import android.view.View;
import android.widget.Button;

import com.zztl.mvpdemo.R;
import com.zztl.mvpdemo.base.BaseMvpActivity;
import com.zztl.mvpdemo.presenter.UploadImagePresenter;
import com.zztl.mvpdemo.view.mvpview.UploadImageView;

public class UploadImageActivity extends BaseMvpActivity<UploadImagePresenter> implements UploadImageView {


    private Button mBtn;

    @Override
    protected UploadImagePresenter createPresenter() {
        return new UploadImagePresenter();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_upload_image;
    }

    @Override
    protected void initBase() {
        mBtn = findViewById(R.id.btn);
    }

    public void upload(View view) {
        presenter.loadImage(this);
    }

    @Override
    public void loadSuccess() {
        mBtn.setText("上传成功");
    }

    @Override
    public void loadFail() {
        mBtn.setText("上传失败");
    }
}
