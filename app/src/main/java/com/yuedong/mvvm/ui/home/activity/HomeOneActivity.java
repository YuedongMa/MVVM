package com.yuedong.mvvm.ui.home.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.yuedong.mvvm.R;
import com.yuedong.mvvm.base.BaseActivity;

import com.yuedong.mvvm.model.ResponseModel;
import com.yuedong.view.button.buttonstyle.ButtonStyle;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeOneActivity extends BaseActivity {
    @BindView(R.id.btUpDate)
    ButtonStyle btUpDate;
    @BindView(R.id.tvText)
    TextView tvText;

    @Override
    public int initLayout(Bundle bundle) {
        return R.layout.activity_home_one;
    }

    @Override
    protected void initData(Bundle bundle) {
//        viewModel.getVersion().observe(this, new Observer<VersionBean>() {
//            @Override
//            public void onChanged(@Nullable VersionBean versionBean) {
//                tvText.setText(versionBean.toString());
//            }
//        });
    }

    @Override
    protected void onDataChage(ResponseModel response) {

    }

    @OnClick({R.id.btUpDate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btUpDate:
               // viewModel.getVersion().setValue(new VersionBean());
                break;
        }
    }
}
