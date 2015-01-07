package com.example.zhangzhilai.cordovademo;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewClient;
import org.apache.cordova.CordovaInterface;


public class MyActivity extends CordovaActivity {

    private CordovaWebView mCordovaWebView;
    private WebSettings mWebSetting;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mContext = MyActivity.this;
        findviews();
        initWebView();
    }

    private void findviews(){
        mCordovaWebView = (CordovaWebView)findViewById(R.id.cordova_webview);

    }

    private void initWebView() {
        mWebSetting = mCordovaWebView.getSettings();
        String originUA = mWebSetting.getUserAgentString();
        String userAgent = "HJApp/Android/" + DeviceUtils.getSystemVersion() + "/tlk_st/"
                + DeviceUtils.getVersionName(this) + "/cordova/" + originUA;

        mWebSetting.setUserAgentString(userAgent);
        mWebSetting.setJavaScriptEnabled(true);
        mWebSetting.setBlockNetworkImage(true);
        CordovaInterface cordovaInterface = (CordovaInterface) mContext;
        CordovaWebViewClient mWebViewClient = new CordovaWebViewClient(cordovaInterface, mCordovaWebView){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        };
        mCordovaWebView.setWebViewClient(mWebViewClient);
        mCordovaWebView.loadUrl("http://ms.hujiang.com/st/app/topic/161885288910/");
    }
}
