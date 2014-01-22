package com.example.testhtml5;

import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.DatePicker;

import com.newbens.datepickertools.DatePickerTools;
import com.newbens.datepickertools.MyWebviewtouchlistener;

public class MainActivity extends Activity
{

	WebView web;
	Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		web = (WebView) findViewById(R.id.webview);

		WebSettings webSettings = web.getSettings();
		webSettings.setJavaScriptEnabled(true);
		MyWebviewtouchlistener listener = new MyWebviewtouchlistener(web);
		web.setOnTouchListener(listener);
		 DatePickerTools dTools = new DatePickerTools(this, web);
		// web.addJavascriptInterface(dTools.getHtmlObject(), "android");
		web.addJavascriptInterface(dTools.insertObj, "android");
		web.setWebChromeClient(new WebChromeClient() {
			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					JsResult result)
			{
				// TODO Auto-generated method stub
				System.out.println("url--" + url + "msg--" + message + "re--");

				result.confirm();
				return super.onJsAlert(view, url, message, result);
			}
		});
		// java调用js的函数
		web.loadUrl("http://192.168.2.108:8182/web_html/index.jsp");
		// web.loadUrl("http://www.591.com.cn/index/m_report.html?restaurantId=10001&managerId=3&deviceCode=00:98:08:00:7a:27");

		// String url =
//		 "http://www.591.com.cn/Index/report.html?merId= 10001&&deviceCode=00:98:08:00:7a:27";
//		 web.loadUrl("http://www.591.com.cn/index/report_test.html");
		// web.loadUrl(url);
	}

	Object insertObj = new Object() {
		@JavascriptInterface
		public void OpenDatepicker()
		{

			runOnUiThread(new Runnable() {
				public void run()
				{
					opendatepicker(3);

				}
			});

		}

		@JavascriptInterface
		public void OpenDatepickerNoDay()
		{
			runOnUiThread(new Runnable() {
				public void run()
				{
					System.out.println("22222222222");
					opendatepicker(2);
				}
			});
		}

		private void opendatepicker(final int size)
		{
			System.out.println("--onpen--");

			Calendar calendar = Calendar.getInstance(Locale.CHINA);
			final DatePickerDialog dp = new DatePickerDialog(MainActivity.this,
					new OnDateSetListener() {
						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth)
						{

						}
					}, calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.DAY_OF_MONTH));
			final DatePicker datePicker = dp.getDatePicker();
			datePicker.setCalendarViewShown(false);
			if (size == 2) {
				ViewGroup vgGroup = (ViewGroup) datePicker.getChildAt(0);
				vgGroup.getChildAt(0).setBackgroundColor(Color.CYAN);
				ViewGroup vGroup2 = (ViewGroup) vgGroup.getChildAt(0);
				vGroup2.getChildAt(2).setVisibility(View.GONE);
			}
			dp.setButton("确 定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					// TODO Auto-generated method stub

					StringBuilder date = new StringBuilder();
					date.append(datePicker.getYear() + "-");
					int month = datePicker.getMonth() + 1;
					if (month < 10) {
						date.append("0");
					}
					if (size == 3) {
						date.append(month + "-");
						if (datePicker.getDayOfMonth() < 10) {
							date.append("0");
						}
						date.append(datePicker.getDayOfMonth());
					} else {
						date.append(month);
					}
					dp.dismiss();
					web.loadUrl("javascript:setdatetime('" + date.toString()
							+ "')");

				}
			});
			dp.show();

			System.out.println("-----show");

		}

	};

}
