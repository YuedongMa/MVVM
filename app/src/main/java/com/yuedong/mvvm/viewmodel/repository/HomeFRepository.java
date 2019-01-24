package com.yuedong.mvvm.viewmodel.repository;

import android.arch.lifecycle.MutableLiveData;

import com.yuedong.base.basepresenter.BaseResponse;
import com.yuedong.base.basepresenter.MTransformer;
import com.yuedong.mvvm.api.MainApi;
import com.yuedong.mvvm.base.BaseObserver;
import com.yuedong.mvvm.base.BaseRepository;
import com.yuedong.mvvm.model.ErrorModel;
import com.yuedong.mvvm.model.VersionBean;
import com.yuedong.net.retrofit.http.MHttp;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * 作者：Yuedong Ma
 * 2019/1/17 10:23
 */
public class HomeFRepository extends BaseRepository {
    private MainApi api;
    private MutableLiveData<VersionBean> data = new MutableLiveData<>();//别放在方法内，否则重新new新对象，原数据不会保留

    public HomeFRepository() {
        api = MHttp.createApi(MainApi.class);
    }

    //普通用法
    public MutableLiveData<VersionBean> getVersion() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "android");
        api.appVersion(map).compose(MTransformer.<BaseResponse<VersionBean>>switchSchedulers())
                .subscribe(new BaseObserver<VersionBean>() {
                    @Override
                    public void doOnSubscribe(Disposable d) {
                        addDisposable(d);

                    }

                    @Override
                    public void responseSuccess(VersionBean response, int code) {
                        data.setValue(response);
                    }

                    @Override
                    public void responseError(String errorMsg) {
                        getErrorModel().setValue(new ErrorModel(0, errorMsg));
                    }
                });
        return data;
    }

    //改造后架空viewmodel,直接从repository调取数据
    public void getVersions() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "android");
        api.appVersion(map).compose(MTransformer.<BaseResponse<VersionBean>>switchSchedulers())
                .subscribe(new BaseObserver<VersionBean>() {
                    @Override
                    public void doOnSubscribe(Disposable d) {
                        addDisposable(d);

                    }

                    @Override
                    public void responseSuccess(VersionBean response, int code) {
                        sendData("version", response);
                    }

                    @Override
                    public void responseError(String errorMsg) {
                        sendError(0, errorMsg);
                    }
                });
    }
}
