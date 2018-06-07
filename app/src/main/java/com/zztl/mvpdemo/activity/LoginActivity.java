package com.zztl.mvpdemo.activity;

import android.view.View;
import android.widget.Button;

import com.zztl.mvpdemo.R;
import com.zztl.mvpdemo.base.BaseMvpActivity;
import com.zztl.mvpdemo.model.LoginBean;
import com.zztl.mvpdemo.net.bean.LoginRequest;
import com.zztl.mvpdemo.presenter.LoginPresenter;
import com.zztl.mvpdemo.storage.BlockChainPreference;
import com.zztl.mvpdemo.storage.PreferenceKeys;
import com.zztl.mvpdemo.view.mvpview.LoginView;


public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements View.OnClickListener, LoginView<LoginBean> {

    Button mTv;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initBase() {
        mTv = findViewById(R.id.tv);
        mTv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        presenter.login(new LoginRequest("13480150374", "123456"), this);
    }

    @Override
    public void loginSuccess(LoginBean loginBean) {
        BlockChainPreference.putApp(PreferenceKeys.TOKEN.name(), loginBean.data.getToken());
        showActivity(this, UploadImageActivity.class);
    }

    @Override
    public void loginFail() {
        mTv.setText("登陆失败");
    }

}
