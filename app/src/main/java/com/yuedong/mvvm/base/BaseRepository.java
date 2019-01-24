package com.yuedong.mvvm.base;

import android.util.Log;

import com.yuedong.mvvm.base.event.LiveBusData;
import com.yuedong.mvvm.model.ErrorModel;
import com.yuedong.mvvm.model.ResponseModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 作者：Yuedong Ma
 * 2019/1/17 9:25
 */
public class BaseRepository{
    private CompositeDisposable mCompositeDisposable;
    private LiveBusData<ErrorModel> errorModel;//通用error信息类封装，内部根据实际情况添加属性
    private LiveBusData<ResponseModel> response;//通用数据接收包装类
    private ErrorModel error;

    public LiveBusData<ResponseModel> getResponse() {
        return response;
    }


    public BaseRepository() {
        errorModel = new LiveBusData<ErrorModel>();
        response = new LiveBusData<>();
    }

    public LiveBusData<ErrorModel> getErrorModel() {

        return errorModel;
    }

    protected void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void unDisposable() {
        if (mCompositeDisposable != null && mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
    }

    public void sendError(int code, String err) {
        error = new ErrorModel(code, err);
        getErrorModel().postError(error);
    }

    public void sendData(String tag, Object o) {
        getResponse().postData(new ResponseModel(tag, o));
    }
}
