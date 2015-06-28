package com.example.searchbardemo.dialog;

import com.example.searchbardemo.MainActivity.ModifyColorCallBack;
import com.example.searchbardemo.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ColorDialog extends Dialog {

	private Context context;
	private Button btnRed, btnYellow, btnGreen, btnBlue, btnBlack;
	private int[] colorTable;
	private ModifyColorCallBack modifyColorCallBack;
	public ColorDialog(Context context , ModifyColorCallBack modifyColorCallBack) {
		
		super(context);
		this.context = context;
		this.modifyColorCallBack = modifyColorCallBack;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_color_choice);
		
		findView();
		setAction();
	}

	private void findView() {
		btnRed = (Button) findViewById(R.id.btn_dialog_red);
		btnYellow = (Button) findViewById(R.id.btn_dialog_yellow);
		btnGreen = (Button) findViewById(R.id.btn_dialog_green);
		btnBlue = (Button) findViewById(R.id.btn_dialog_blue);
		btnBlack = (Button) findViewById(R.id.btn_dialog_black);
		colorTable = new int[]{Color.RED,Color.YELLOW,Color.GREEN,Color.BLUE,Color.BLACK};

	}

	private void setAction() {
		btnRed.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("msg","red");
				modifyColorCallBack.color(Color.RED);
				dismiss();

			}
		});
		btnYellow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("msg","yellow");
				modifyColorCallBack.color(Color.YELLOW);
				dismiss();

			}
		});
		btnGreen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("msg","green");
				modifyColorCallBack.color(Color.GREEN);
				dismiss();

			}
		});
		btnBlue.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("msg","blue");
				modifyColorCallBack.color(Color.BLUE);
				dismiss();

			}
		});
		
		btnBlack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("msg","black");
				modifyColorCallBack.color(Color.BLACK);
				dismiss();

			}
		});

	}
}
