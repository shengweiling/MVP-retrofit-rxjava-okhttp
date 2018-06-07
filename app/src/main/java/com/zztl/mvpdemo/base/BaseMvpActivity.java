package com.zztl.mvpdemo.base;

import android.os.Bundle;

import com.zztl.mvpdemo.impl.MvpView;

/**
 * @desc
 * @auth ${user}
 * @time 2018/6/4  10:58
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements MvpView{
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=createPresenter();
        presenter.attachView(this);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter=null;
    }
}
