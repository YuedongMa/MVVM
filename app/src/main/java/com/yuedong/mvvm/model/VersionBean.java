package com.yuedong.mvvm.model;

public class VersionBean {

    /**
     * client_download_status : 1
     * client_force_update : 0
     * client_version : 1.0.5
     * client_file : http://mccmcc.oss-cn-beijing.aliyuncs.com/MCC_V1.0.5.apk
     * client_tips : #MCC正式公测1.0.4版本
     #修复了几个已知问题
     #感谢每一位亲给予我们的鞭策与鼓励
     #来见证我们的一路成长
     * client_url : http://mccmcc.oss-cn-beijing.aliyuncs.com/MCC_V1.0.5.apk
     */

    private String client_download_status;
    private int client_force_update;
    private String client_version;
    private String client_file;
    private String client_tips;
    private String client_url;

    public String getClient_download_status() {
        return client_download_status;
    }

    public void setClient_download_status(String client_download_status) {
        this.client_download_status = client_download_status;
    }

    public int getClient_force_update() {
        return client_force_update;
    }

    public void setClient_force_update(int client_force_update) {
        this.client_force_update = client_force_update;
    }

    public String getClient_version() {
        return client_version;
    }

    @Override
    public String toString() {
        return "VersionBean{" +
                "client_download_status='" + client_download_status + '\'' +
                ", client_force_update='" + client_force_update + '\'' +
                ", client_version='" + client_version + '\'' +
                ", client_file='" + client_file + '\'' +
                ", client_tips='" + client_tips + '\'' +
                ", client_url='" + client_url + '\'' +
                '}';
    }

    public void setClient_version(String client_version) {
        this.client_version = client_version;
    }

    public String getClient_file() {
        return client_file;
    }

    public void setClient_file(String client_file) {
        this.client_file = client_file;
    }

    public String getClient_tips() {
        return client_tips;
    }

    public void setClient_tips(String client_tips) {
        this.client_tips = client_tips;
    }

    public String getClient_url() {
        return client_url;
    }

    public void setClient_url(String client_url) {
        this.client_url = client_url;
    }
}
