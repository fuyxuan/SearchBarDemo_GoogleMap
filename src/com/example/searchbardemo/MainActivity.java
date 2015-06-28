package com.example.searchbardemo;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.searchbardemo.adapter.ImageAdapter;
import com.example.searchbardemo.dialog.ModifyStyleDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;
 
public class MainActivity extends Activity {
	
	public static int TEXT_SIZE = 20;
	public static int TEXT_COLOR = Color.RED;
	public static int BACKGROUND_COLOR = Color.WHITE;

	private GridView gridView;
	private EditText edittext;
	private Button btnModifyStyle;
	private String inputText;
	private int inputTextLength = 0;
	private Context context;
	private HashMap<Integer, Integer> hashMap;
	private String[] cityStrList = new String[] { "台北", "桃園","新竹","苗栗","台中","彰化", "台南",
											"台北", "桃園","新竹","苗栗","台中","彰化", "台南",
											"台北", "桃園","新竹","苗栗","台中","彰化","台南",
											"台北", "桃園","新竹","苗栗","台中","彰化" };
	private int[] imageCityList = { 
					R.drawable.image01, R.drawable.image02,
					R.drawable.image03, R.drawable.image04,
					R.drawable.image05, R.drawable.image06,
					R.drawable.image07, R.drawable.image08,
					R.drawable.image09, R.drawable.image10,
					R.drawable.image11, R.drawable.image12,
					R.drawable.image13, R.drawable.image14,
					R.drawable.image15, R.drawable.image01,
					R.drawable.image01, R.drawable.image02,
					R.drawable.image03, R.drawable.image04,
					R.drawable.image05, R.drawable.image06,
					R.drawable.image07, R.drawable.image08,
					R.drawable.image09, R.drawable.image10,
					R.drawable.image11, R.drawable.image12,
					R.drawable.image13, R.drawable.image14,
					R.drawable.image15, R.drawable.image01, 
					};
	private ArrayList<String> text_sort = new ArrayList<String>();
	private ArrayList<Integer> image_sort = new ArrayList<Integer>();
	private ImageAdapter  imageAdapter;
	private ModifyStyleDialog modifyStyleDialog ;
	private LinearLayout llActivityMain;
	private JSONArray jsonArray,jsonArray_sort;

	public interface ModifyColorCallBack{
		public void color(int colorInt);
	}
	
    public interface ModifyTextSizeCallBack{
    	public void size(int textSize);
    	
    }
	public interface ModifyCallBack{
		public void backgroundCallBack(int backgroundColor);
		public void textColorCallBack(int textColor);
		public void textSizeCallBack(int textSize);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.context = this;
		findView();
		setGridList();
		setAction();
		setGrid();
	}
	
	private void findView(){
		hashMap = new HashMap<Integer,Integer>();
		jsonArray = new JSONArray();
		modifyStyleDialog = new ModifyStyleDialog(context ,new ModifyCallBack() {
			
			@Override
			public void textSizeCallBack(int textSize) {
				TEXT_SIZE = textSize;
				imageAdapter.notifyDataSetChanged();
				setAction();
				Log.i("msg","Main_textSize"+textSize);
			}
			
			@Override
			public void textColorCallBack(int textColor) {
				TEXT_COLOR = textColor;
				imageAdapter.notifyDataSetChanged();
				Log.i("msg","Main_textSize"+textColor);
				setAction();
			}
			
			@Override
			public void backgroundCallBack(int backgroundColor) {
				BACKGROUND_COLOR = backgroundColor;
				llActivityMain.setBackgroundColor(backgroundColor);
			}
		} );
		gridView = (GridView) findViewById(R.id.gridView1);
		edittext = (EditText) findViewById(R.id.editText);
		btnModifyStyle = (Button)findViewById(R.id.btn_modify_style);
		llActivityMain = (LinearLayout)findViewById(R.id.ll_activity_main);
	}

	
	private void setGridList(){
		
		try {
			for (int i = 0; i < cityStrList.length; i++) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("jsonCityStr", cityStrList[i]);
				jsonObject.put("jsonImageStr", imageCityList[i]);
				jsonArray.put(jsonObject);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<cityStrList.length;i++){
			text_sort.add(cityStrList[i]);
			image_sort.add(imageCityList[i]);
			hashMap.put(i, imageCityList[i]);
		}
		
		imageAdapter = new ImageAdapter(context,jsonArray, TEXT_COLOR , TEXT_SIZE);
		imageAdapter.notifyDataSetChanged();
		Log.i("msg","TEXT_COLOR:"+TEXT_COLOR+"   TEXT_SIZE:"+TEXT_SIZE);
		gridView.setAdapter(imageAdapter);

		
	}
	private void setAction(){
	
 
		
		edittext.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count,int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,int count) {
				
				
				editChange();
				
				
			}
		});
		
		editChange();
		
		
		btnModifyStyle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				modifyStyleDialog.show();
			//	imageAdapter.notifyDataSetChanged();
			}
		});
		
	}
	
	private void setGrid(){
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@SuppressLint("NewApi") @Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {
				
				Log.i("msg","grid:"+position);
				Toast.makeText(getApplicationContext(),((TextView) view.findViewById(R.id.grid_item_label)).getText(), Toast.LENGTH_SHORT).show();
				
				String str = ((TextView) view.findViewById(R.id.grid_item_label)).getText().toString();
				Intent intent = new Intent();
				intent.setClass(MainActivity.this , ContentActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("content_city",str);
				bundle.putInt("content_position",position); 
				bundle.putSerializable("HashMap",hashMap);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
		
	}
	
	private void editChange(){
		try {
			jsonArray_sort= new JSONArray("[]");
			
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		inputText = edittext.getText().toString();
		Log.i("msg","inputText:"+inputText);
		inputTextLength = edittext.getText().length();
		text_sort.clear();
		image_sort.clear();
		hashMap.clear();
//		
//		
		for (int i = 0; i < cityStrList.length; i++) {
			if (inputTextLength <= cityStrList[i].length()) {
				if (inputText.equals(cityStrList[i].subSequence(0,inputTextLength))) {
					text_sort.add(cityStrList[i]);
					image_sort.add(imageCityList[i]);
					hashMap.put(i, imageCityList[i]);

					try {
						
							JSONObject jsonObject = new JSONObject();
							jsonObject.put("jsonCityStr", cityStrList[i]);
							jsonObject.put("jsonImageStr", imageCityList[i]);
							jsonArray_sort.put(jsonObject);

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}
//		
		hashMap.clear();
//		
//		
		ImageAdapter imageAdapter = new ImageAdapter(context, jsonArray_sort,TEXT_COLOR , TEXT_SIZE);
		imageAdapter.notifyDataSetChanged();
		gridView.setAdapter(imageAdapter);
//		
		for(int i=0;i<image_sort.size();i++){
			hashMap.put(i, image_sort.get(i));
		}
		
		//===
//		try {
//			for (int i = 0; i < cityStrList.length; i++) {
//				JSONObject jsonObject = new JSONObject();
//				jsonObject.put("jsonCityStr", cityStrList[i]);
//				jsonObject.put("jsonImageStr", imageCityList[i]);
//				jsonArray.put(jsonObject);
//
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
//		for(int i=0;i<cityStrList.length;i++){
//			text_sort.add(cityStrList[i]);
//			image_sort.add(imageCityList[i]);
//			hashMap.put(i, imageCityList[i]);
//		}
//		
//		imageAdapter = new ImageAdapter(context,jsonArray, text_sort,image_sort, TEXT_COLOR , TEXT_SIZE);
//		imageAdapter.notifyDataSetChanged();
//		Log.i("msg","TEXT_COLOR:"+TEXT_COLOR+"   TEXT_SIZE:"+TEXT_SIZE);
//		gridView.setAdapter(imageAdapter);
		
		//==
	}
 
}