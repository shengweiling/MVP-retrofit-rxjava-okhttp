package com.zztl.mvpdemo.presenter;

import android.content.Context;
import android.os.Environment;

import com.zztl.mvpdemo.base.BasePresenter;
import com.zztl.mvpdemo.impl.RequestKeys;
import com.zztl.mvpdemo.model.BaseResponseBean;
import com.zztl.mvpdemo.net.FilterSubscriber;
import com.zztl.mvpdemo.net.RetorfitServiceManger;
import com.zztl.mvpdemo.view.mvpview.UploadImageView;

import java.io.File;

/**
 * @desc
 * @auth ${user}
 * @time 2018/6/5  14:10
 */
public class UploadImagePresenter extends BasePresenter<UploadImageView> {
    public void loadImage(Context context) {
        File mHeadPortraitFileDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/CTZ/Image");
        if (!mHeadPortraitFileDir.exists()) {
            mHeadPortraitFileDir.mkdirs();
        }
        File file = new File(mHeadPortraitFileDir.getAbsolutePath(), "/invite.png");

        RetorfitServiceManger
                .observe(RetorfitServiceManger.getInstance().getApiService()
                .uploadImage(RetorfitServiceManger.parseImageRequestBody(RequestKeys.PHOTO,file),RetorfitServiceManger.parseRequestBody("icon")))
                .subscribe(new FilterSubscriber<BaseResponseBean>(context) {
                    @Override
                    protected void success(BaseResponseBean bean) {
                        getView().loadSuccess();
                    }

                    @Override
                    public void fail() {
                        getView().loadFail();
                    }
                });


    }
}
