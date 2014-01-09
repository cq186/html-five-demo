package com.example.testhtml5;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
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

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		web = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = web.getSettings();
		webSettings.setJavaScriptEnabled(true);

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
		web.loadUrl("http://192.168.2.108:8182/web1/");
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
}
