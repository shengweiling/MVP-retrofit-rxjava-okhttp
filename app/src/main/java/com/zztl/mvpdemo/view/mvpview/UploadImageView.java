package com.zztl.mvpdemo.view.mvpview;

import com.zztl.mvpdemo.impl.MvpView;

/**
 * @desc
 * @auth ${user}
 * @time 2018/6/5  14:13
 */
public interface UploadImageView extends MvpView {
    void loadSuccess();
    void loadFail();
}
