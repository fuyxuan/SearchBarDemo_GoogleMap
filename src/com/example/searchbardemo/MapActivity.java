package com.example.searchbardemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends BaseActivity {
	
	private LatLng cityCoordinate ;
    private GoogleMap map;
    public static String CITY="city";
    private Marker cityMarker;
    public static double CITY_CORRDINATE_X=25.051269,CITY_CORRDINATE_Y=121.512386;

    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setContentView(R.layout.activity_map);
        findView();
        setAction();
    
    }
    
    private void findView(){
    	cityCoordinate = new LatLng( CITY_CORRDINATE_X,CITY_CORRDINATE_Y);
    	
    }
    private void setAction(){
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.fg_map)).getMap();
        cityMarker = map.addMarker(new MarkerOptions().position(cityCoordinate).title(CITY));
        
     
        map.setMyLocationEnabled(true);
        
        UiSettings uis = map.getUiSettings();
        map.setMyLocationEnabled(true); //顯示自己位置
        uis.setZoomControlsEnabled(true); //顯示縮放按鈕
        uis.setCompassEnabled(true); //顯示指北針
        uis.setMyLocationButtonEnabled(true); //顯示自己位置按鈕

        uis.setScrollGesturesEnabled(true); //開啟地圖捲動手勢
        uis.setZoomGesturesEnabled(true); //開啟地圖縮放手勢
        uis.setTiltGesturesEnabled(true); //開啟地圖傾斜手勢
        uis.setRotateGesturesEnabled(true); 
       
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(cityCoordinate, 15));
//        btnReturnHome.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(MapActivity.this,MainActivity.class);
//				startActivity(intent);
//				finish();
//			}
//		});
//        
//        btnReturnCity.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				//指定坐標地址
//				map.moveCamera(CameraUpdateFactory.newLatLngZoom(cityCoordinate, 15));
//			}
//		});
//        
//        btnBigger.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				//放大地圖
//				map.animateCamera(CameraUpdateFactory.zoomIn());
//			}
//		});
//        
//        btnMinner.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				//縮小地圖
//				map.animateCamera(CameraUpdateFactory.zoomOut());
//			}
//		});
        
    	
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
