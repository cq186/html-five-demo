package com.example.testhtml5;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.View.OnTouchListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity
{

	private Object getHtmlObject()
	{
		Object insertObj = new Object() {
			public String HtmlcallJava()
			{
				return "Html call Java";
			}

			public String HtmlcallJava2(final String param)
			{
				return "Html call Java : " + param;
			}

			public void JavacallHtml()
			{

				web.loadUrl("javascript:showFromHtml()");
				System.out.println("--JavacallHtml--");

			}

			public void JavacallHtml2()
			{
				web.loadUrl("javascript:showFromHtml2('IT-homer blog')");
				System.out.println("-JavacallHtml2---");

			}

			public void Callphone(String phone)
			{
				System.out.println("打电话。。" + phone);
				Intent phoneIntent = new Intent("android.intent.action.CALL",
						Uri.parse("tel:" + phone));
				startActivity(phoneIntent);

			}

		};

		return insertObj;
	}

	private float oldy;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		web = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = web.getSettings();
		webSettings.setJavaScriptEnabled(true);

		web.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				// TODO Auto-generated method stub
			
				switch (event.getAction()) {

				case MotionEvent.ACTION_DOWN:
					oldy = event.getY();
					break;

				case MotionEvent.ACTION_UP:
					boolean istop = true;
					if (event.getY() > oldy) {
						istop = true;
					} else {
						istop = false;
					}
					loaddata(istop);
					break;
				}
				return false;
			}
		});

		web.setWebChromeClient(new WebChromeClient() {

			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					JsResult result)
			{
				System.out.println("onjsAlert---" + url + "==message:"
						+ message + "---result:" + result.toString());
				result.confirm();
				return true;
				// TODO Auto-generated method stub
				// return super.onJsAlert(view, url, message, result);
			}

			@Override
			@Deprecated
			public void onConsoleMessage(String message, int lineNumber,
					String sourceID)
			{
				// TODO Auto-generated method stub
				super.onConsoleMessage(message, lineNumber, sourceID);
				System.out.println("--onconsolemessage-" + message + "--"
						+ lineNumber + "---" + sourceID);
			}
		});
		web.addJavascriptInterface(getHtmlObject(), "android");

		// java调用js的函数
		web.loadUrl("http://192.168.2.108:8182/web_html/");
	}

	Handler mhander;
	WebView web;

	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
			web.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
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
		} else {//当前页面内容的高度大于的高度
			float xh = (web.getHeight() + web.getScrollY());
			System.out.println("content--" + web.getContentHeight() + "getscale()-"
					+ web.getScale() + "-getScrollY()-" + web.getScrollY()
					+ "--height--" + web.getHeight());
			if (height == xh&& istoup==false ) {//加载到底 
				web.loadUrl("javascript:onOverScroll('4')");
			} else if (web.getScrollY() == 0) {  //加载到顶部
				web.loadUrl("javascript:onOverScroll('2')");
			}
		}
	}

	private boolean istotop(MotionEvent event)
	{
		boolean istop = true;

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			oldy = event.getY();

			break;

		case MotionEvent.ACTION_UP:
			if (event.getY() > oldy) {
				istop = true;
			} else {
				istop = false;
			}
			break;
		}

		return istop;
	}

	{

	}
}
