package com.yuedong.mvvm.api;

import com.yuedong.base.basepresenter.BaseResponse;
import com.yuedong.mvvm.config.ApiConfig;
import com.yuedong.mvvm.model.VersionBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 作者：Yuedong Ma
 * 2019/1/17 10:48
 */
public interface MainApi {

    @FormUrlEncoded
    @POST(ApiConfig.GET_VERSION)
    Observable<BaseResponse<VersionBean>> appVersion(@FieldMap Map<String, Object> map);

}
