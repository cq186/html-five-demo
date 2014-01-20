package com.newbens.datepickertools;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

public class MyWebView extends WebView
{

	public MyWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		getSettings().setJavaScriptEnabled(true);
		setOnTouchListener(new ontoucherlistener());
	}



	private class ontoucherlistener implements OnTouchListener
	{

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

	private void loaddata(boolean istoup)
	{
		// 总的内容的高度
		float height = this.getContentHeight() * this.getScale();
		// 当前已经加载的高度
		// 当前页的内容高度<=控件的高度
		if (height <= this.getHeight()) {
			// 判断是向上 or 向下
			// 向上加载
			if (istoup) {
				this.loadUrl("javascript:onOverScroll('2')");
			} else {
				this.loadUrl("javascript:onOverScroll('4')");
			}
		} else {// 当前页面内容的高度大于的高度
			float xh = (this.getHeight() + this.getScrollY());
			System.out.println("content--" + this.getContentHeight()
					+ "getscale()-" + this.getScale() + "-getScrollY()-"
					+ this.getScrollY() + "--height--" + this.getHeight());
			if (height == xh && istoup == false) {// 加载到底
				this.loadUrl("javascript:onOverScroll('4')");
			} else if (this.getScrollY() == 0) { // 加载到顶部
				this.loadUrl("javascript:onOverScroll('2')");
			}
		}
	}

	private float oldy;
}
