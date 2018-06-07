package com.zztl.mvpdemo.net;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zztl.mvpdemo.model.BaseResponseBean;
import com.zztl.mvpdemo.util.ToastUtils;
import com.zztl.mvpdemo.util.UiUtils;
import com.zztl.mvpdemo.view.mvpview.dialog.LoadingDialog;

import rx.Subscriber;

/**
 * @desc
 * @auth ${user
 * @time 2018/6/4  14:17
 */
public abstract class FilterSubscriber<T> extends Subscriber<T> {
    Context mContext;
    LoadingDialog mLoadingDialog;

    public FilterSubscriber(Context context) {
        this.mContext = context;
        if (null == mLoadingDialog) {
            LoadingDialog.Builder builder = new LoadingDialog.Builder(mContext)
                    .setShowMessage(false)
                    .setCancelable(true);
            mLoadingDialog = builder.create();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mLoadingDialog.show();
        if (!isNetworkAvailable()) {
            mLoadingDialog.cancle();
            ToastUtils.show(UiUtils.getContext(), "网络不可用");
            if (!isUnsubscribed()) {
                unsubscribe();
            }
            return;
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        mLoadingDialog.cancle();
        fail();
        if (e instanceof Exception) {
            //访问获得对应的Exception
            onError(ExceptionHandle.handleException(e));
        } else {
            //将Throwable 和 未知错误的status code返回
            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    @Override
    public void onNext(T t) {
        mLoadingDialog.cancle();
        if (t instanceof BaseResponseBean) {
            if (((BaseResponseBean) t).code != BaseResponseBean.OK) {
                if (((BaseResponseBean) t).code == -99995 || ((BaseResponseBean) t).code == -99996) {
                    ToastUtils.show(mContext, "该账户在其他地方登陆");
                } else {
                    ToastUtils.show(mContext, ((BaseResponseBean) t).message);
                }
                fail();
            } else {
                success(t);
            }
        }
    }

    protected abstract void success(T t);

    public abstract void fail();

    public void onError(ExceptionHandle.ResponeThrowable responeThrowable) {
        ToastUtils.show(mContext, responeThrowable.message);
    }


    /**
     * 判断当前网络是否可用
     *
     * @return
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) UiUtils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == cm) {
            return false;
        } else {
            NetworkInfo[] networkInfo = cm.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
