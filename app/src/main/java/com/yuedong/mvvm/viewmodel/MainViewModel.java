package com.yuedong.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.yuedong.mvvm.base.BaseViewModel;
import com.yuedong.mvvm.model.VersionBean;
import com.yuedong.mvvm.viewmodel.repository.HomeFRepository;


/**
 * 作者：Yuedong Ma
 * 2019/1/17 10:19
 */
public class MainViewModel extends BaseViewModel<HomeFRepository> {
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<VersionBean> verData;
//普通mvvm用法
    public MutableLiveData<VersionBean> getVersion() {
        if (null == verData) verData = mRespository.getVersion();
        return verData;
    }
}
