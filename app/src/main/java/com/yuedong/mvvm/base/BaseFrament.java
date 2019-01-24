package com.yuedong.mvvm.base;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yuedong.base.baseui.SupperActivity;
import com.yuedong.base.baseui.SupperFragment;
import com.yuedong.base.util.ClassUtil;
import com.yuedong.base.util.ToastUtil;
import com.yuedong.mvvm.R;
import com.yuedong.mvvm.model.ErrorModel;
import com.yuedong.mvvm.ui.home.HomeFragment;
import com.yuedong.mvvm.base.state.ErrorState;
import com.yuedong.mvvm.base.state.LoadingState;
import com.yuedong.view.stateview.BaseStateControl;
import com.yuedong.view.stateview.LoadManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：Yuedong Ma
 * 2019/1/17 10:12
 */
public abstract class BaseFrament<VM extends BaseViewModel> extends SupperFragment {
    protected VM viewModel;
    protected Unbinder unbinder;
    private SupperActivity activity;
    protected List<BaseStateControl> stateControlList;
    protected LoadManager loadManager;
    private List<Object> eventKeys = new ArrayList<>();

    protected VM vmProvider(SupperFragment fragment, Class<VM> vmClass) {
        return ViewModelProviders.of(fragment).get(vmClass);
    }

    @Override
    protected void initSupperData(Bundle bundle, View view) {
        stateControlList = new ArrayList<>();
        stateControlList.add(new LoadingState());
        stateControlList.add(new ErrorState());
        try {
            unbinder = ButterKnife.bind(this, view);
            viewModel = getViewModel();//子类可重写该方法来决定是否监听其依托的activity的viewmodle
            if (viewModel == null)
                viewModel = vmProvider(this, (Class<VM>) ClassUtil.getInstance(this, 0));

        } catch (Exception e) {

        }
        initData(bundle);
        if (null != viewModel)
            viewModel.getErrorModle().observe(this, new Observer<ErrorModel>() {
                @Override
                public void onChanged(@Nullable ErrorModel errorModle) {
                    if (errorModle.code == HomeFragment.class.hashCode()) {
                        //  showLoading();
                        ToastUtil.showToast("针对来自 HomeFrament error 的特殊处理");
                    } else {
                        ToastUtil.showToast(BaseFrament.this.getClass().getSimpleName() + "通用error处理=>" + errorModle.errorMsg);
                    }
                }
            });
    }

    protected VM getViewModel() {
        return viewModel;
    }

    protected void registerStateView(View view) {//此view为添加状态布局的容器
        loadManager = new LoadManager.Builder().setViewParams(view).setListener(new BaseStateControl.OnRefreshListener() {
            @Override
            public void onRefresh(View view) {
                onStateRefresh();
            }
        }).build();
    }

    protected void onStateRefresh() {

    }

    protected abstract void initData(Bundle bundle);

    @Override
    protected void onLayLoad() {

    }

    @Override
    protected void onDetachActivity() {
        if (null != unbinder) unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SupperActivity) {
            activity = (SupperActivity) context;
        }
    }


    public SupperActivity getSupperActivity() {
        return activity;
    }

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
    protected void onEvent() {

    }


}
