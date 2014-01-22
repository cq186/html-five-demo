package com.newbens.datepickertools;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebView;

public class MyWebviewtouchlistener implements OnTouchListener
{
	private WebView web;

	public MyWebviewtouchlistener(WebView _web) {
		web = _web;
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
			if (height == xh && istoup == false) {// 加载到底
				web.loadUrl("javascript:onOverScroll('4')");
			} else if (web.getScrollY() == 0) { // 加载到顶部
				web.loadUrl("javascript:onOverScroll('2')");
			}
		}
	}

	private float oldy;

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
	
	
	
}
