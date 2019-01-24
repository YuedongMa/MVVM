package com.yuedong.mvvm.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.yuedong.base.util.ClassUtil;
import com.yuedong.mvvm.base.event.LiveBusData;
import com.yuedong.mvvm.model.ErrorModel;

/**
 * 作者：Yuedong Ma
 * 2019/1/17 9:20
 */
public class BaseViewModel<T extends BaseRepository> extends AndroidViewModel {
    protected T mRespository;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        mRespository = ClassUtil.getNewInstance(this, 0);
    }

    public T getmRespository() {
        return mRespository;
    }


    public LiveBusData<ErrorModel> getErrorModle() {
        return mRespository.getErrorModel();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (null != mRespository) {
            mRespository.unDisposable();
        }
    }
}
