package com.newbens.datepickertools;

import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.DatePicker;

public class DatePickerTools
{
	private WebView web;
	private Activity ac;
	private DatePickerDialog dp;
	private DatePicker datePicker;
	private View day;

	public DatePickerTools(Activity _ac,WebView web) {
		this.ac = _ac;
		this.web = web;
	}

	public Object insertObj = new Object() {
		@JavascriptInterface
		public void OpenDatepicker()
		{
			ac.runOnUiThread(new Runnable() {

				@Override
				public void run()
				{
					// TODO Auto-generated method stub
					opendatepicker(3);
				}
			});

		}

		@JavascriptInterface
		public void OpenDatepickerNoDay()
		{
			ac.runOnUiThread(new Runnable() {

				@Override
				public void run()
				{
					opendatepicker(2);
				}
			});
		}

		private void opendatepicker(final int size)
		{
			Calendar calendar = Calendar.getInstance(Locale.CHINA);
			if (null == dp) {
				dp = new DatePickerDialog(ac, new OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth)
					{

					}
				}, calendar.get(Calendar.YEAR),
						calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.DAY_OF_MONTH));

			} else {
				dp.getDatePicker().updateDate(calendar.get(Calendar.YEAR),
						calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.DAY_OF_MONTH));
			}

			datePicker = dp.getDatePicker();
			datePicker.setCalendarViewShown(false);

			if (size == 2) {
				if (null == day) {
					ViewGroup vgGroup = (ViewGroup) datePicker.getChildAt(0);
					ViewGroup vGroup2 = (ViewGroup) vgGroup.getChildAt(0);
					day = vGroup2.getChildAt(2);
				}
				day.setVisibility(View.GONE);
			} else {
				if(null!=day){
					if(day.getVisibility()==View.GONE)
						day.setVisibility(View.VISIBLE);
				}

			}
			dp.setButton("确 定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
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
		}
	};

}
