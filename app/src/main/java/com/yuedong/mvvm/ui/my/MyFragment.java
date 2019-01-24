package com.yuedong.mvvm.ui.my;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;

import com.yuedong.mvvm.R;
import com.yuedong.mvvm.base.BaseFrament;
import com.yuedong.mvvm.base.state.LoadingState;
import com.yuedong.mvvm.model.ResponseModel;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseFrament {
@BindView(R.id.fm)
LinearLayout fm;
    @Override
    protected int initLayout(Bundle bundle) {
        return R.layout.fragment_my;
    }

    @Override
    protected void onDataChage(ResponseModel response) {

    }

    @Override
    protected void initData(Bundle bundle) {
registerStateView(fm);
    }
}
