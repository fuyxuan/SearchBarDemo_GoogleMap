package com.example.searchbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class BaseActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}
	
	
	public interface ViewsInterface{
		public void viewsInit();
		public void findViews();
		public void setViews();
		public void setActions();
	}

    @Override  
    protected void onRestart() {  
        super.onRestart();  
    }
    
    @Override  
    protected void onPause() {  
        super.onPause();  
    }
}
