package com.example.searchbardemo.dialog;

import com.example.searchbardemo.MainActivity.ModifyCallBack;
import com.example.searchbardemo.MainActivity.ModifyColorCallBack;
import com.example.searchbardemo.MainActivity.ModifyTextSizeCallBack;
import com.example.searchbardemo.R;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ModifyStyleDialog extends Dialog {
	private Context context;
	private Button btnTextSize, btnBackgroundColor, btnTextColor;
	private ColorDialog textColorDialog,backgroundColorDialog;
	private SizeDialog sizeDialog;
	private ModifyCallBack modifyCallBack;
	public ModifyStyleDialog(Context context,ModifyCallBack modifyCallBack) {
		super(context);
		this.context = context;
		this.modifyCallBack = modifyCallBack;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_modify_style);
		findView();
		setAction();
	}

	private void findView() {
		btnTextSize = (Button) findViewById(R.id.btn_dialog_text_size);
		btnBackgroundColor = (Button) findViewById(R.id.btn_dialog_background_color);
		btnTextColor = (Button) findViewById(R.id.btn_dialog_text_color);
		sizeDialog = new SizeDialog(context, new ModifyTextSizeCallBack() {
			
			@Override
			public void size(int textSize) {
				modifyCallBack.textSizeCallBack(textSize);
			}
		});
		textColorDialog = new ColorDialog(context , new ModifyColorCallBack() {
			@Override
			public void color(int colorInt) {
				modifyCallBack.textColorCallBack(colorInt);
			}
		}
				
				);
		backgroundColorDialog  = new ColorDialog(context , new ModifyColorCallBack() {
			@Override
			public void color(int colorInt) {
				modifyCallBack.backgroundCallBack(colorInt);
			}
		});
	}

	private void setAction() {
		btnTextSize.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dismiss();
				sizeDialog.show();
			}
		});
		btnBackgroundColor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dismiss();
				backgroundColorDialog.show();
			}
		});
		btnTextColor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dismiss();
				textColorDialog.show();
			}
		});

	}
}
