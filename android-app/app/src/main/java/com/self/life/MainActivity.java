package com.self.life;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Self 个人工作台 — 安卓外壳
 * 用系统 WebView 加载 assets/index.html（即单文件 HTML 应用本身）。
 * 所有业务逻辑、数据持久化（localStorage）都来自原 HTML，本类只负责承载与配置。
 */
public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);

        WebSettings ws = webView.getSettings();

        // 关键：开启 JS 与 DOM Storage，否则 HTML 里的 localStorage 无法持久化
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);

        // 允许读取本地资源（file:///android_asset）
        ws.setAllowFileAccess(true);
        ws.setAllowContentAccess(true);
        ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        // 缩放与缓存
        ws.setLoadWithOverviewMode(true);
        ws.setUseWideViewPort(true);
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);
        ws.setMediaPlaybackRequiresUserGesture(false);

        // 让页面内链接/跳转在 WebView 内打开，不弹出外部浏览器
        webView.setWebViewClient(new WebViewClient());

        // 加载应用本体
        webView.loadUrl("file:///android_asset/index.html");
    }

    /** 物理返回键：先在页面内后退，再退出 Activity */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        webView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onDestroy() {
        webView.destroy();
        super.onDestroy();
    }
}
