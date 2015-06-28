package com.example.searchbardemo;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContentActivity extends Activity {
	
	private Button btnMap;
	private TextView textView ;
	private AssetManager assets ;
	private LinearLayout llActivityContext ;
	private Bundle bundle ;
	private String content_city ;
	private int content_position ;
	private HashMap<Integer, Integer> hashMap ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);
		findView();
		setAction();
	}
	
	private void findView(){
		 btnMap = (Button)findViewById(R.id.btn_map);
		 textView = (TextView) findViewById(R.id.tv_content);
		 assets = getAssets();
		 llActivityContext = (LinearLayout)findViewById(R.id.ll_activity_content);
		 bundle = getIntent().getExtras();
		 content_city = bundle.getString("content_city");
		 content_position = bundle.getInt("content_position");
		 hashMap = (HashMap<Integer, Integer>) bundle.getSerializable("HashMap");
		 
	}
	
	private void setAction(){
		Log.i("msg", "content_city:" + content_city);
		try {
			if (content_city.equals("台北")) {
				textView.setText(readStream(assets.open("content_taipei.txt")));
			} else if (content_city.equals("桃園")) {
				textView.setText(readStream(assets.open("content_taoyuan.txt")));
			} else if (content_city.equals("新竹")) {
				textView.setText(readStream(assets.open("content_hsinchu.txt")));
			} else if (content_city.equals("苗栗")) {
				textView.setText(readStream(assets.open("content_moli.txt")));
			} else if (content_city.equals("台中")) {
				textView.setText(readStream(assets.open("content_taichung.txt")));
			} else if (content_city.equals("彰化")) {
				textView.setText(readStream(assets.open("content_changhua.txt")));
			} else if (content_city.equals("台南")) {
				textView.setText(readStream(assets.open("content_tainan.txt")));
			}
			textView.setTextColor(MainActivity.TEXT_COLOR);
			textView.setTextSize(MainActivity.TEXT_SIZE);
			llActivityContext.setBackgroundColor(MainActivity.BACKGROUND_COLOR);

		} catch (IOException e) {
			e.printStackTrace();
		}
		textView.setMovementMethod(ScrollingMovementMethod.getInstance());
		ImageView imageview = (ImageView) findViewById(R.id.iv_content);
		imageview.setImageResource(hashMap.get(content_position));
		Log.i("msg",content_position+"content_position:"+(hashMap.get(content_position)==null));
		
		
		btnMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ContentActivity.this,MapActivity.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	private String readStream(InputStream is) {
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
}
