package com.yuedong.mvvm.base.event;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yuedong.mvvm.model.BaseReponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：Yuedong Ma
 * 2019/1/23 13:28
 */

public class LiveBusData<T extends BaseReponse> extends MutableLiveData<T> {

    private boolean isFirstSubscribe;//仅在第一次发送数据时监听者获取数据（在传递接口返回的错误信息时防止页面转屏时又再次发送保留的错误信息）
    Map<String, Object> map;
    private boolean isSendData;//仅在调用发送数据的时候接收方调用一次onChanged,若屏幕旋转导致的改变会遍历该试图下所有调用过的数据适配到页面

    public LiveBusData() {
        map = new HashMap<>();

    }

    public void postData(T value) {
        isSendData = true;
        postValue(value);
        map.put(value.tag, value);
    }

    public void postError(T value) {
        isFirstSubscribe = true;
        postValue(value);
    }

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        super.observe(owner, new ObserverWrapper<>(observer));
        isSendData = false;
    }

    class ObserverWrapper<T> implements Observer<T> {

        private Observer<T> observer;

        private ObserverWrapper(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onChanged(@Nullable T t) {
            if (isFirstSubscribe) {//发送一次性消息
                if (observer != null) {
                    observer.onChanged(t);
                }
                isFirstSubscribe = false;
            } else {
                if (observer != null) {
                    if (isSendData) {//发送页面数据
                        observer.onChanged(t);
                    } else {
                        if (map != null && map.size() > 0) {
                            for (Map.Entry<String, Object> data : map.entrySet()) {
                                observer.onChanged((T) data.getValue());
                            }
//                            for (int i = 0; i < map.size(); i++) {
//                                observer.onChanged((T) map.get(i));
//                            }
                        }
                    }

                }
            }
        }

    }
}


