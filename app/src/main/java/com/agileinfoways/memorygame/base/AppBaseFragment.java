package com.agileinfoways.memorygame.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.agileinfoways.memorygame.utils.NetworkUtils;
import com.agileinfoways.memorygame.utils.PrefUtils;

/**
 * Created on 10/1/2018.
 */
public class AppBaseFragment extends Fragment {

    protected PrefUtils mPrefUtils;
    public NetworkUtils mNetworkUtils;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPrefUtils = new PrefUtils(getActivity());
        mNetworkUtils = new NetworkUtils(getActivity());

    }
    /**
     * this method calls {@link AppBaseActivity#replaceFragment(Fragment, int, boolean)}.
     * So, it will replace fragment in Activity's container
     */
    public void replaceFragment(Fragment fragment, int containerId, boolean addToBackStack) {
        ((AppBaseActivity) getActivity()).replaceFragment(fragment, containerId, addToBackStack);
    }

    public void hideKeyBoard(View view) {
        ((AppBaseActivity) getActivity()).hideKeyBoard(view);
    }

    public void showAlert(String msg) {
        ((AppBaseActivity) getActivity()).showAlert(msg);
    }

}
