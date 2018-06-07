/**
 * @(#) ToastUtils 2015/7/6
 * <p>
 * Copyright (c), 2009 深圳孔方兄金融信息服务有限公司（Shenzhen kfxiong
 * Financial Information Service Co. Ltd.）
 * <p>
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.zztl.mvpdemo.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtils {
    public static void show(Context context, String promt) {
        Toast toast = Toast.makeText(context, promt, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


}
