package com.yuedong.mvvm.base.state;

import com.yuedong.mvvm.R;
import com.yuedong.view.stateview.BaseStateControl;

/**
 * 作者：Yuedong Ma
 * 2019/1/17 14:31
 */
public class LoadingState extends BaseStateControl {
    @Override
    protected int onCreateView() {
        return R.layout.item_state_loading;
    }
    @Override
    public boolean isVisible() {
        return super.isVisible();
    }
}
