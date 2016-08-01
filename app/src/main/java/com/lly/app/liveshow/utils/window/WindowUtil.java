package com.lly.app.liveshow.utils.window;

import android.content.Context;
import android.view.WindowManager;

public class WindowUtil {
	
	public static MyWindow getWindow(Context context){
	       WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
	       MyWindow window = new MyWindow();
	       window.winth = wm.getDefaultDisplay().getWidth();
	       window.height = wm.getDefaultDisplay().getHeight();
	       return window;
	}
	
}
 