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
public class TermsAndConditionsActivity extends AppBaseActivity {

    @BindView(R.id.tv_title_toolbar)
    AppCompatTextView mTvTitleToolbar;
    @BindView(R.id.wv_terms_conditions)
    WebView mWvTermsCondition;
    private ProgressDialog pd;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);
        ButterKnife.bind(this);

        initialization();
    }

    private void initialization() {

        mTvTitleToolbar.setText(getString(R.string.action_terms_and_conditions));
        pd = new ProgressDialog(TermsAndConditionsActivity.this);
        pd.setMessage(getString(R.string.msg_web_view));
        pd.show();

        mWvTermsCondition.setWebViewClient(new TermsAndConditionsActivity.MyWebViewClient());
        mWvTermsCondition.getSettings().setJavaScriptEnabled(true);

        mWvTermsCondition.loadUrl(Consts.OtherConstant.TERMS_AND_CONDITION_URL);

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
            if (pd.isShowing()) {
                pd.dismiss();
            }

        }
    }
}
