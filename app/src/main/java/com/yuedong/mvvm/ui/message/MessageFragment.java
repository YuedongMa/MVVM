package com.yuedong.mvvm.ui.message;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;

import com.yuedong.base.util.ToastUtil;
import com.yuedong.mvvm.R;
import com.yuedong.mvvm.base.BaseFrament;
import com.yuedong.mvvm.base.state.LoadingState;
import com.yuedong.mvvm.model.ResponseModel;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends BaseFrament {
    @BindView(R.id.fl)
    LinearLayout fl;

    @Override
    protected int initLayout(Bundle bundle) {
        return R.layout.fragment_message;
    }

    @Override
    protected void onDataChage(ResponseModel response) {
ToastUtil.showToast("MessageFragment");
    }

    @Override
    protected void initData(Bundle bundle) {
            registerStateView(fl);
          showError("d");


    }

}
