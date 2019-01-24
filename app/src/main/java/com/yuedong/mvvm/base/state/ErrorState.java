package com.yuedong.mvvm.base.state;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuedong.mvvm.R;
import com.yuedong.view.stateview.BaseStateControl;

/**
 * 作者：Yuedong Ma
 * 2019/1/17 14:34
 */
public class ErrorState extends BaseStateControl {
    @Override
    protected int onCreateView() {
        return R.layout.item_state_error;
    }
    @Override
    protected void onViewCreate(Context context, View view) {
        TextView errorDesc = view.findViewById(R.id.tv_error_desc);
        ImageView errorIcon = view.findViewById(R.id.iv_error_icon);
        if (view.getTag() != null) {
            if (view.getTag().equals("1")) {
                errorDesc.setText("网络不给力，点击重试～_~");
                errorIcon.setImageResource(R.mipmap.empty_server);
            } else if (view.getTag().equals("2")) {
                errorDesc.setText("服务器异常,点击重试");
                errorIcon.setImageResource(R.mipmap.empty_server);
            }

        }
    }
    @Override
    public boolean isVisible() {
        return super.isVisible();
    }
}
