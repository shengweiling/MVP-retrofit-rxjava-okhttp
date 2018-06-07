package com.zztl.mvpdemo.util;


import android.app.Activity;

import java.util.Stack;

public class MyActivityManager {

    private static MyActivityManager instance;

    public Stack<Activity> getActivityStack() {
        return activityStack;
    }

    private Stack<Activity> activityStack = new Stack<>();//activity栈

    private MyActivityManager() {
    }

    //单例模式
    public static MyActivityManager getInstance() {
        if (instance == null) {
            instance = new MyActivityManager();
        }
        return instance;
    }

    //把一个activity压入栈中
    public void pushOneActivity(Activity actvity) {
        activityStack.add(actvity);
    }

    //获取栈顶的activity，先进后出原则
    public Activity getLastActivity() {
        return activityStack.lastElement();
    }

    //移除一个activity
    public void popOneActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activity.finish();
                activityStack.remove(activity);
            }

        }
    }


    //退出所有activity
    public void finishAllActivities() {
        if (activityStack != null) {
            while (activityStack.size() > 0) {
                Activity activity = getLastActivity();
                if (activity == null) {
                    break;
                }

                popOneActivity(activity);

            }
        }
    }

    public void finishAllToFirstActivities() {
        if (activityStack != null) {
            while (activityStack.size() > 1) {
                Activity activity = getLastActivity();
                if (activity == null) {
                    break;
                }
                if (!activity.getClass().getSimpleName().contains("MainActivity")) {
                    popOneActivity(activity);
                }

            }
        }
    }
}