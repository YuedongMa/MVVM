package com.yuedong.mvvm.model;

/**
 * 作者：Yuedong Ma
 * 2019/1/17 13:24
 */
public class ErrorModel extends BaseReponse {
    public int code;
    public String errorMsg;
    public ErrorModel(int code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }
}
