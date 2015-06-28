package com.example.searchbardemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity {
    static final LatLng MCU = new LatLng(25.086063, 121.539325);
    private GoogleMap map;

    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.fg_map)).getMap();
        Marker mcu = map.addMarker(new MarkerOptions().position(MCU).title("銘傳大學").snippet("csie"));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(MCU, 16));
        //http://cheng-min-i-taiwan.blogspot.tw/2013/04/google-maps-android-api-v2-android.html
    }
}
