package com.example.searchbardemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;


import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContentActivity extends BaseActivity {
	
	Views views = new Views();
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_content);
		views.viewsInit();
	}
	
	
	class Views implements ViewsInterface{
		
		Button btnMap;
		TextView textView;
		AssetManager assets;
		LinearLayout llActivityContext;
		Bundle bundle;
		String content_city;
		int content_position;
		HashMap<Integer, Integer> hashMap;

		@Override
		public void viewsInit() {
			// TODO Auto-generated method stub
			findViews();
			setViews();
			setActions();
		}

		@Override
		public void findViews() {
			 btnMap = (Button)findViewById(R.id.btn_map);
			 textView = (TextView) findViewById(R.id.tv_content);
			 assets = getAssets();
			 llActivityContext = (LinearLayout)findViewById(R.id.ll_activity_content);
			 bundle = getIntent().getExtras();
			 content_city = bundle.getString("content_city");
			 content_position = bundle.getInt("content_position");
			 hashMap = (HashMap<Integer, Integer>) bundle.getSerializable("HashMap");
			 MapActivity.CITY = content_city ;
			
		}

		@Override
		public void setViews(){
			//內容文字設置
			Log.i("msg", "content_city:" + content_city);
			try {
				if (content_city.equals("台北")) {
					textView.setText(readStream(assets.open("content_taipei.txt")));
					MapActivity.CITY_CORRDINATE_X = 25.0485044;MapActivity.CITY_CORRDINATE_Y = 121.5171856;
				} else if (content_city.equals("桃園")) {
					textView.setText(readStream(assets.open("content_taoyuan.txt")));
					MapActivity.CITY_CORRDINATE_X = 24.989143;MapActivity.CITY_CORRDINATE_Y = 121.313617;
				} else if (content_city.equals("新竹")) {
					textView.setText(readStream(assets.open("content_hsinchu.txt")));
					MapActivity.CITY_CORRDINATE_X = 24.801596;MapActivity.CITY_CORRDINATE_Y = 120.971653;
				} else if (content_city.equals("苗栗")) {
					textView.setText(readStream(assets.open("content_moli.txt")));
					MapActivity.CITY_CORRDINATE_X = 24.570109;MapActivity.CITY_CORRDINATE_Y = 120.822429;
				} else if (content_city.equals("台中")) {
					textView.setText(readStream(assets.open("content_taichung.txt")));
					MapActivity.CITY_CORRDINATE_X = 24.136807;MapActivity.CITY_CORRDINATE_Y = 120.684875;
				} else if (content_city.equals("彰化")) {
					textView.setText(readStream(assets.open("content_changhua.txt")));
					MapActivity.CITY_CORRDINATE_X = 24.081551;MapActivity.CITY_CORRDINATE_Y = 120.538471;
				} else if (content_city.equals("台南")) {
					textView.setText(readStream(assets.open("content_tainan.txt")));
					MapActivity.CITY_CORRDINATE_X = 23.306828;MapActivity.CITY_CORRDINATE_Y = 120.323119;
				}
				textView.setTextColor(MainActivity.TEXT_COLOR);
				textView.setTextSize(MainActivity.TEXT_SIZE);
				llActivityContext.setBackgroundColor(MainActivity.BACKGROUND_COLOR);
			} catch (IOException e) {
				e.printStackTrace();
			}
			textView.setMovementMethod(ScrollingMovementMethod.getInstance()); //textView 上下滾動設定
			ImageView imageview = (ImageView) findViewById(R.id.iv_content);
			imageview.setImageResource(hashMap.get(content_position));
		}

		@Override
		public void setActions() {
				btnMap.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(ContentActivity.this,MapActivity.class);
						startActivity(intent);
					}
				});
			
		}
		
	}
	
	private String readStream(InputStream is) {//讀取檔案文字
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			int i = is.read();
			while (i != -1) {
				bo.write(i);
				i = is.read();
			}
			return bo.toString();
		} catch (IOException e) {
			return "";
		}
	}
	
	 @Override  
	    protected void onPause() {  
	        super.onPause();  
	        finish();
	 }
	@Override  
    protected void onDestroy() {  
        super.onDestroy();  
        finish();
    }  
}
