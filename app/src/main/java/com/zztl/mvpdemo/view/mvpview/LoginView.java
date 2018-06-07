package com.zztl.mvpdemo.view.mvpview;

import com.zztl.mvpdemo.impl.MvpView;
import com.zztl.mvpdemo.model.BaseResponseBean;

/**
 * @desc
 * @auth ${user}
 * @time 2018/6/4  16:48
 */
public interface LoginView <T extends BaseResponseBean> extends MvpView{
    void loginSuccess(T t);
    void loginFail();
}
