package com.agileinfoways.memorygame.activities;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import com.agileinfoways.memorygame.R;
import com.agileinfoways.memorygame.base.AppBaseActivity;
import com.agileinfoways.memorygame.utils.Consts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrivacyPolicyActivity extends AppBaseActivity {

    @BindView(R.id.tv_title_toolbar)
    AppCompatTextView mTvTitleToolbar;
    @BindView(R.id.wv_privacy_policy)
    WebView mWvPrivacyPolicy;
    private ProgressDialog pd;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        ButterKnife.bind(this);

        initialization();
    }

    private void initialization() {

        mTvTitleToolbar.setText(getString(R.string.action_privacy_policy));

        pd = new ProgressDialog(PrivacyPolicyActivity.this);
        pd.setMessage(getString(R.string.msg_web_view));
        pd.show();
        mWvPrivacyPolicy.setWebViewClient(new MyWebViewClient());
        mWvPrivacyPolicy.getSettings().setJavaScriptEnabled(true);

        mWvPrivacyPolicy.loadUrl(Consts.OtherConstant.PRIVACY_URL);

    }


    @OnClick(R.id.iv_back_toolbar)
    public void onViewClicked() {
       onBackPressed();
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            if (!pd.isShowing()) {
                pd.show();
            }

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            System.out.println("on finish");
            if (pd.isShowing()) {
                pd.dismiss();
            }

        }
    }

}

