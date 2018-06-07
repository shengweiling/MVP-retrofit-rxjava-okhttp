package com.zztl.mvpdemo.presenter;


import android.content.Context;
import android.text.TextUtils;

import com.zztl.mvpdemo.base.BasePresenter;
import com.zztl.mvpdemo.model.LoginBean;
import com.zztl.mvpdemo.net.FilterSubscriber;
import com.zztl.mvpdemo.net.RetorfitServiceManger;
import com.zztl.mvpdemo.net.bean.LoginRequest;
import com.zztl.mvpdemo.util.DataConverter;
import com.zztl.mvpdemo.util.ToastUtils;
import com.zztl.mvpdemo.util.UiUtils;
import com.zztl.mvpdemo.view.mvpview.LoginView;


/**
 * @desc
 * @auth ${user}
 * @time 2018/6/4  14:25
 */
public class LoginPresenter extends BasePresenter<LoginView> {
    public void check(String phone, String password) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            ToastUtils.show(UiUtils.getContext(), "账号或密码不能为空");
        }
    }

    public void login(LoginRequest loginRequest, Context context) {
        RetorfitServiceManger.observe(RetorfitServiceManger.getInstance().getApiService().login(DataConverter.toJson(loginRequest)))
                .subscribe(new FilterSubscriber<LoginBean>(context) {


                    @Override
                    protected void success(LoginBean loginBean) {
                        getView().loginSuccess(loginBean);
                    }

                    @Override
                    public void fail() {
                        getView().loginFail();
                    }
                });
    }
}
