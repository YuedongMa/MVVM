package com.yuedong.mvvm.ui.home;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuedong.mvvm.R;
import com.yuedong.mvvm.base.BaseFrament;
import com.yuedong.mvvm.base.state.LoadingState;
import com.yuedong.mvvm.model.ResponseModel;
import com.yuedong.mvvm.model.VersionBean;
import com.yuedong.mvvm.viewmodel.MainViewModel;
import com.yuedong.view.stateview.SuccessState;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFrament<MainViewModel> {
    @BindView(R.id.tvVer)
    TextView tvVer;
    @BindView(R.id.lcontainer)
    LinearLayout lcontainer;
    @BindView(R.id.tvA)
    TextView tvA;
    @BindView(R.id.tvB)
    TextView tvB;


    @Override
    protected int initLayout(Bundle bundle) {
        return R.layout.fragment_home;
    }


    @Override
    protected void initData(Bundle bundle) {
        registerStateView(lcontainer);//测试状态view
        showError("2");
        viewModel.getmRespository().getResponse().observe(this, new Observer<ResponseModel>() {
            @Override
            public void onChanged(@Nullable ResponseModel responseModel) {
                if (responseModel.tag.equals("a")) {
                    tvA.setText(responseModel.data.toString());
                } else if (responseModel.tag.equals("b")) {
                    tvB.setText(responseModel.data.toString());
                } else if (responseModel.tag.equals("version")) {
                    tvVer.setText("来自改造后版本更新返回的数据==》" + responseModel.data.toString());
                }
            }
        });

    }

    @Override
    protected MainViewModel getViewModel() {
        return ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    protected void onStateRefresh() {//状态刷新调用
        super.onStateRefresh();
        loadManager.showStateView(LoadingState.class);
        //基本mvvm用法
        viewModel.getVersion().observe(this, new Observer<VersionBean>() {
            @Override
            public void onChanged(@Nullable VersionBean versionBean) {
                tvVer.setText("来自普通MVVM调用版本更新返回的数据==》" + versionBean.toString());
                loadManager.showStateView(SuccessState.class);
            }
        });
    }

    @OnClick({R.id.btA, R.id.btErr, R.id.btB, R.id.btVer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btA:
                viewModel.getmRespository().sendData("a", new ArrayList<>());
                break;
            case R.id.btErr:
                viewModel.getmRespository().sendError(0, "error Msg");
                break;
            case R.id.btB:
                viewModel.getmRespository().sendData("b", "ggggggggg");
                break;
            case R.id.btVer: //改造后用法,基本架空viewmodel
                viewModel.getmRespository().getVersions();
                break;
        }
    }
}
