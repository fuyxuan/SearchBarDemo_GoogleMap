package com.example.searchbardemo.dialog;

import com.example.searchbardemo.MainActivity.MultiModifyChoiceStyle;
import com.example.searchbardemo.R;
import com.example.searchbardemo.helper.SharedPreferencesHelper;

import android.R.dimen;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


//多選修改Dialog
public class MultiModifyChoiceDialog extends Dialog {

	private RadioGroup rgTextSize, rgBGColor, rgTextColor;
	private Button btnSubmit,btnDefault,btnCancel;
	private MultiModifyChoiceStyle multiModifyChoiceCallBack;
	private Editor editor;
	private SharedPreferences sharedPreferences;
	private Context context;

	public MultiModifyChoiceDialog(Context context,MultiModifyChoiceStyle multiModifyChoiceCallBack) {
		super(context);
		this.context = context;
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_multi_modify_choice);
		this.multiModifyChoiceCallBack = multiModifyChoiceCallBack;
		findView();
		setAction();
	}

	private void findView() {
		rgTextSize = (RadioGroup) findViewById(R.id.rg_text_size);
		rgBGColor = (RadioGroup) findViewById(R.id.rg_bg_color);
		rgTextColor = (RadioGroup) findViewById(R.id.rg_text_color);
		btnSubmit = (Button) findViewById(R.id.btn_multi_submit);
		btnDefault =(Button) findViewById(R.id.btn_multi_default);
		btnCancel = (Button) findViewById(R.id.btn_multi_cancel);
		sharedPreferences = context.getSharedPreferences("searchbardemo",0);
		editor = sharedPreferences.edit();
	}

	private void setAction() {
		
		btnDefault.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				multiModifyChoiceCallBack.multitextSize(Integer.parseInt("15"));
				multiModifyChoiceCallBack.multibackground(Color.WHITE);
				multiModifyChoiceCallBack.multitextColor(Color.BLACK);
				
				  editor.putInt("TEXT_SIZE", Integer.parseInt("30"))
		           		.putInt("TEXT_COLOR", Color.WHITE)
		           		.putInt("BG_COLOR", Color.BLACK).commit();
				dismiss();
			}
			
		});
		btnCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dismiss();
				
			}
			
		});

		btnSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				for (int i = 0; i < rgTextSize.getChildCount(); i++) {
					RadioButton rd = (RadioButton) rgTextSize.getChildAt(i);

					if (rd.isChecked()) {
						//文字大小
						multiModifyChoiceCallBack.multitextSize(Integer.parseInt(rd.getText().toString()));
						 editor.putInt("TEXT_SIZE", Integer.parseInt(rd.getText().toString())).commit();
				         
						break;
					}
				}

				for (int i = 0; i < rgBGColor.getChildCount(); i++) {
					RadioButton rd = (RadioButton) rgBGColor.getChildAt(i);
					if (rd.isChecked()) {
						//背景顏色
						if (rd.getText().toString().toString().equals("Red")) {
							multiModifyChoiceCallBack.multibackground(Color.RED);
							//editor.putInt("BG_COLOR", Color.RED).commit();
							SharedPreferencesHelper.putSharedPreferencesInt(context,"BG_COLOR",Color.RED);
						} else if (rd.getText().toString().toString().equals("Green")) {
							multiModifyChoiceCallBack.multibackground(Color.GREEN);
							//editor.putInt("BG_COLOR", Color.GREEN).commit();
							SharedPreferencesHelper.putSharedPreferencesInt(context,"BG_COLOR",Color.GREEN);
						} else if (rd.getText().toString().toString().equals("Blue")) {
							multiModifyChoiceCallBack.multibackground(Color.BLUE);
							//editor.putInt("BG_COLOR", Color.BLUE).commit();
							SharedPreferencesHelper.putSharedPreferencesInt(context,"BG_COLOR",Color.BLUE);
						}
						
						break;
					}
				}

				for (int i = 0; i < rgTextColor.getChildCount(); i++) {
					RadioButton rd = (RadioButton) rgTextColor.getChildAt(i);
					if (rd.isChecked()) {
						//文字顏色
						if (rd.getText().toString().toString().equals("Red")) {
							multiModifyChoiceCallBack.multitextColor(Color.RED);
							SharedPreferencesHelper.putSharedPreferencesInt(context,"TEXT_COLOR",Color.RED);
//							editor.putInt("TEXT_COLOR", Color.RED).commit();
						} else if (rd.getText().toString().toString().equals("Green")) {
							multiModifyChoiceCallBack.multitextColor(Color.GREEN);
							SharedPreferencesHelper.putSharedPreferencesInt(context,"TEXT_COLOR",Color.GREEN);
							editor.putInt("TEXT_COLOR", Color.GREEN).commit();
						} else if (rd.getText().toString().toString().equals("Blue")) {
							multiModifyChoiceCallBack.multitextColor(Color.BLUE);
							SharedPreferencesHelper.putSharedPreferencesInt(context,"TEXT_COLOR",Color.BLUE);
							editor.putInt("TEXT_COLOR", Color.BLUE).commit();
						}
						 
						break;
					}
				}
				
				
				
				dismiss();
			}

		});

	}
	
	
	

}
