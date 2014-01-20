package com.example.testhtml5;

import java.util.Calendar;
import java.util.Locale;

import com.newbens.datepickertools.DatePickerTools;
import com.newbens.datepickertools.MyWebView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.DatePicker;

public class MainActivity extends Activity
{

	MyWebView web;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		web = (MyWebView) findViewById(R.id.webview);
		DatePickerTools mDatePickerTools = new DatePickerTools(this, web);
		web.addJavascriptInterface(mDatePickerTools.getHtmlObject(), "android");

		// java调用js的函数
//		 web.loadUrl("http://192.168.2.108:8182/web_html/");
		web.loadUrl("http://www.591.com.cn/index/m_report.html?restaurantId=10001&&managerId=3&&deviceCode=00:98:08:00:7a:27");
		// web.loadUrl("http://www.591.com.cn/index/report_test.html");
	}


	private void loaddata(boolean istoup)
	{
		// 总的内容的高度
		float height = web.getContentHeight() * web.getScale();
		// 当前已经加载的高度
		// 当前页的内容高度<=控件的高度
		if (height <= web.getHeight()) {
			// 判断是向上 or 向下
			// 向上加载
			if (istoup) {
				web.loadUrl("javascript:onOverScroll('2')");
			} else {
				web.loadUrl("javascript:onOverScroll('4')");
			}
		} else {// 当前页面内容的高度大于的高度
			float xh = (web.getHeight() + web.getScrollY());
			System.out.println("content--" + web.getContentHeight()
					+ "getscale()-" + web.getScale() + "-getScrollY()-"
					+ web.getScrollY() + "--height--" + web.getHeight());
			if (height == xh && istoup == false) {// 加载到底
				web.loadUrl("javascript:onOverScroll('4')");
			} else if (web.getScrollY() == 0) { // 加载到顶部
				web.loadUrl("javascript:onOverScroll('2')");
			}
		}
	}

	private float oldy;

}
