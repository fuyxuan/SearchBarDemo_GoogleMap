package com.example.searchbardemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.searchbardemo.MainActivity.ModifyTextSizeCallBack;
import com.example.searchbardemo.R;
import com.example.searchbardemo.MainActivity.ModifyColorCallBack;

public class SizeDialog extends Dialog {

	private Context context;
	private Button btn15, btn20, btn25;
	private ModifyTextSizeCallBack modifyTextSizeCallBack;
	public SizeDialog(Context context , ModifyTextSizeCallBack modifyTextSizeCallBack) {
		super(context);
		this.context = context;
		this.modifyTextSizeCallBack = modifyTextSizeCallBack;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_size_choice);
		
		findView();
		setAction();
	}

	private void findView() {
		btn15 = (Button) findViewById(R.id.btn_dialog_size_15);
		btn20 = (Button) findViewById(R.id.btn_dialog_size_20);
		btn25 = (Button) findViewById(R.id.btn_dialog_size_25);

	}

	private void setAction() {
		btn15.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				modifyTextSizeCallBack.size(15);
				dismiss();

			}
		});
		btn20.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				modifyTextSizeCallBack.size(20);
				dismiss();

			}
		});
		btn25.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				modifyTextSizeCallBack.size(25);
				dismiss();

			}
		});

	}
}
