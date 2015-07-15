package com.example.searchbardemo.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SharedPreferencesHelper {

	public static class SharedPreferencesKeys {
		public static final String key1 = "key1";
		public static final String key2 = "key2";

	}

	// public static void getSharedPreferences

	public static void putSharedPreferencesInt(Context context, String key,
			int value) {
		SharedPreferences preferences = context.getSharedPreferences("searchbardemo", 0);
		Editor edit = preferences.edit();
		edit.putInt(key, value);
		edit.commit();
	}

	public static void putSharedPreferencesString(Context context, String key,String val) {
		SharedPreferences preferences = context.getSharedPreferences("searchbardemo", 0);
		Editor edit = preferences.edit();
		edit.putString(key, val);
		edit.commit();
	}

	public static String getSharedPreferencesString(Context context,String key, String _default) {
		SharedPreferences preferences = context.getSharedPreferences("searchbardemo", 0);
		return preferences.getString(key, _default);
	}

	public static int getSharedPreferencesInt(Context context, String key,int _default) {
		SharedPreferences preferences = context.getSharedPreferences("searchbardemo", 0);
		return preferences.getInt(key, _default);
	}

}
