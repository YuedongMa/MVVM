package com.yuedong.mvvm.model;

/**
 * 作者：Yuedong Ma
 * 2019/1/23 14:42
 */
public class ResponseModel<T> extends BaseReponse {
    public T data;

    public ResponseModel(String tag, T data) {
        this.tag = tag;
        this.data = data;
    }


}
