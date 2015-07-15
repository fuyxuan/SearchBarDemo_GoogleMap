package com.example.searchbardemo.adapter;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.searchbardemo.MainActivity;
import com.example.searchbardemo.MainActivity.ModifyTextSizeStyle;
import com.example.searchbardemo.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private Context context;
	private JSONArray jsonArray;

	public ImageAdapter() {

	}
	
	public ImageAdapter(Context context,JSONArray jsonArray,int textColor , int textSize){

		this.context = context;
		this.jsonArray = jsonArray;
		Log.i("msg","imageAdapter:"+jsonArray.length());
	}




	public View getView(int position, View convertView, ViewGroup parent) {


		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater mInflater = LayoutInflater.from(context);
			convertView = mInflater.inflate(R.layout.grid_item, null);
			holder.textView = (TextView) convertView.findViewById(R.id.grid_item_label);
			holder.imageview = (ImageView) convertView.findViewById(R.id.grid_item_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.textView.setTextColor(MainActivity.TEXT_COLOR);
		holder.textView.setTextSize(MainActivity.TEXT_SIZE);
		
		JSONObject obj;
		try {
			obj = jsonArray.getJSONObject(position);
			holder.textView.setText(obj.getString("jsonCityStr"));
			holder.imageview.setImageResource(Integer.parseInt(obj.getString("jsonImageStr")));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return convertView;
	}
	
	@Override
	public int getCount() {
		return jsonArray.length();
	}
	@Override
	public Object getItem(int position) {
		return null;
	}
	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	private class ViewHolder {
		ImageView imageview;
		TextView textView;
	}
}