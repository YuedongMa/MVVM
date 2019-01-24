package com.yuedong.mvvm.base;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yuedong.base.baseui.SupperActivity;
import com.yuedong.base.util.ClassUtil;
import com.yuedong.base.util.ToastUtil;
import com.yuedong.mvvm.model.ErrorModel;
import com.yuedong.mvvm.base.state.ErrorState;
import com.yuedong.mvvm.base.state.LoadingState;
import com.yuedong.mvvm.model.ResponseModel;
import com.yuedong.mvvm.ui.home.HomeFragment;
import com.yuedong.view.stateview.BaseStateControl;
import com.yuedong.view.stateview.LoadManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：Yuedong Ma
 * 2019/1/17 9:18
 */
public abstract class BaseActivity<VM extends BaseViewModel> extends SupperActivity {
    protected VM viewModel;
    protected Unbinder unbinder;
    protected LoadManager loadManager;

    protected VM vmProvider(SupperActivity activity, Class<VM> vmClass) {
        return ViewModelProviders.of(activity).get(vmClass);
    }


    @Override
    public void initSupperData(Bundle bundle) {
        try {
            unbinder = ButterKnife.bind(this);
            viewModel = vmProvider(this, (Class<VM>) ClassUtil.getInstance(this, 0));
        } catch (Exception e) {

        }
        initData(bundle);
        registerObserve();
    }

    protected abstract void initData(Bundle bundle);

    @Override
    public void netTry(Bundle bundle) {

    }

    private void registerObserve() {
        if (null != viewModel)
            viewModel.getErrorModle().observe(this, new Observer<ErrorModel>() {
                @Override
                public void onChanged(@Nullable ErrorModel errorModle) {
                    onError(errorModle);
                    ToastUtil.showToast(BaseActivity.this.getClass().getSimpleName() + "通用error处理=>" + errorModle.errorMsg);

                }
            });
        if (null != viewModel)
            viewModel.getmRespository().getResponse().observe(this, new Observer<ResponseModel>() {
                @Override
                public void onChanged(@Nullable ResponseModel responseModel) {
                    onDataChage(responseModel);
                }
            });
    }

    protected void registerStateView(View view) {//此view为添加状态布局的容器
        loadManager = new LoadManager.Builder().setViewParams(view).setListener(new BaseStateControl.OnRefreshListener() {
            @Override
            public void onRefresh(View view) {
                onStateRefresh();
            }
        }).build();
    }
//子类重写改该方法获取数据
    protected abstract void onDataChage(ResponseModel response) ;

    protected void onError(ErrorModel error) {

    }

    protected void onStateRefresh() {
    }

    ;

    protected void showError(Class<? extends BaseStateControl> stateView, Object tag) {
        if (null != loadManager) loadManager.showStateView(stateView, tag);
    }

    protected void showError(Class<? extends BaseStateControl> stateView) {
        showError(stateView, null);
    }

    protected void showError(Object tag) {
        showError(ErrorState.class, tag);
    }

    protected void showSuccess() {
        if (null != loadManager) loadManager.showSuccess();
    }

    protected void showStateLoading() {
        if (null != loadManager) loadManager.showStateView(LoadingState.class);
    }

    @Override
    public void onActivityDestroy() {
        if (unbinder != null) unbinder.unbind();
    }
}
