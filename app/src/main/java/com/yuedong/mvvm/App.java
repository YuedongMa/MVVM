package com.yuedong.mvvm;

import android.app.Application;


import com.yuedong.base.BaseApplication;
import com.yuedong.base.util.SLogTool;
import com.yuedong.down.DownLoadApplication;
import com.yuedong.mvvm.base.state.ErrorState;
import com.yuedong.mvvm.base.state.LoadingState;
import com.yuedong.net.retrofit.http.HttpInit;
import com.yuedong.view.stateview.LoadState;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BaseApplication.init(this,true);
        DownLoadApplication.init(this);
        HttpInit.init(this,"https://m.cc/api/v1/",10,true);
        SLogTool.init(this,true,"mvvmooo");
        new LoadState.Builder()
                .register(new ErrorState())
                .register(new LoadingState())
                .setDefaultStateView(LoadingState.class)
                .build();
    }
}
