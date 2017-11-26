package com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.activity.CRQueryActivity;
import com.activity.MapActivity;
import com.example.user.crmapp.R;
import com.model.Constant;

/**
 * 作者 ： Created by zjr on 2017/11/6 22:31.
 */

public class SimpleMapFragment extends BaseFragment{

    private WebView webView;

    private MapActivity.MyOnTouchListener onTouchListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         onTouchListener = new MapActivity.MyOnTouchListener() {
             @Override
             public boolean onTouch(MotionEvent event) {
                 float x = event.getRawX();
                 float y = event.getRawY();
                 Log.v("位置",x+","+y);
                 if (event.getAction() == MotionEvent.ACTION_DOWN){
                     if (Math.abs(x-225)<50 && Math.abs(y-580)<40){
                         Intent intent = new Intent(getActivity(), CRQueryActivity.class);
                         Bundle bundle = new Bundle();
                         bundle.putInt(Constant.Type,Constant.BUILD_CY);
                         intent.putExtras(bundle);
                         startActivity(intent);
                     }else if (Math.abs(x-350)<50 && Math.abs(y-750)<40){
                         Intent intent = new Intent(getActivity(),CRQueryActivity.class);
                         Bundle bundle = new Bundle();
                         bundle.putInt(Constant.Type,Constant.BUILD_ZZ);
                         intent.putExtras(bundle);
                         startActivity(intent);
                     }else if (Math.abs(x-600)<50 && Math.abs(y-590)<40){
                         Intent intent = new Intent(getActivity(),CRQueryActivity.class);
                         Bundle bundle = new Bundle();
                         bundle.putInt(Constant.Type,Constant.BUILD_ZX);
                         intent.putExtras(bundle);
                         startActivity(intent);
                     }
                 }

                 return false;
             }
         };
        ((MapActivity)getActivity()).registerMyOnTouchListener(onTouchListener);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_simple,container,false);

        webView = (WebView) view.findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("http://map.hit.edu.cn/");

        return view;
    }



    class MyWebChromeClient extends WebChromeClient{

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);

        }
    }

    class MyWebViewClient extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }




}
