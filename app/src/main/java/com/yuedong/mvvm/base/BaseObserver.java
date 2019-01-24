package com.yuedong.mvvm.base;

import android.util.Log;

import com.yuedong.base.basepresenter.BaseResponse;
import com.yuedong.base.basepresenter.ISub;
import com.yuedong.base.exception.MExceptionManager;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 作者：Yuedong Ma
 * 2019/1/17 11:16
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>>, ISub<T> {
    public BaseObserver() {
    }

    public void onSubscribe(@NonNull Disposable d) {
        this.doOnSubscribe(d);
    }

    public void onNext(@NonNull BaseResponse<T> tBaseResponse) {
        if (tBaseResponse.getStatus() == 1) {
            this.responseSuccess(tBaseResponse.getData(), 0);
        } else {
            this.responseError(tBaseResponse.getInfo());
        }

    }

    public void onError(@NonNull Throwable e) {
        this.responseError(MExceptionManager.handleException(e).getErrorMsg());
    }

    public void onComplete() {
    }
}