package com.newbens.datepickertools;

import java.util.Calendar;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.DatePicker;

import com.example.testhtml5.MainActivity;

public class DatePickerTools
{
	private Context c;
	private WebView web;

	public DatePickerTools(Context c, WebView web) {
		this.c = c;
		this.web = web;
	}

	public Object getHtmlObject()
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
				c.startActivity(phoneIntent);

			}

			public void opendatepicker()
			{
				opendatepicker(3);
				
			}
			public void opendatepicker2(){
				opendatepicker(2);
			}
			
			private void opendatepicker(final int size)
			{

				Calendar calendar = Calendar.getInstance(Locale.CHINA);
				final DatePickerDialog dp = new DatePickerDialog(c,
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
							date.append(datePicker.getMonth() + "-");
							if (datePicker.getDayOfMonth() < 10) {
								date.append("0");
							}
							date.append(datePicker.getDayOfMonth());
						}else{
							date.append(datePicker.getMonth());
						}
						dp.dismiss();
					
						web.loadUrl("javascript:setdatetime('" + date.toString() + "')");

					}
				});
				dp.show();
			}

		};

		return insertObj;
	}

	

}
