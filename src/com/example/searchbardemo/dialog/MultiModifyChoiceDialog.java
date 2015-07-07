package com.example.searchbardemo.dialog;

import com.example.searchbardemo.MainActivity.MultiModifyChoiceCallBack;
import com.example.searchbardemo.R;

import android.R.dimen;
import android.app.Dialog;
import android.content.Context;
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
	private MultiModifyChoiceCallBack multiModifyChoiceCallBack;

	public MultiModifyChoiceDialog(Context context,MultiModifyChoiceCallBack multiModifyChoiceCallBack) {
		super(context);
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
	}

	private void setAction() {
		
		btnDefault.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				multiModifyChoiceCallBack.multitextSizeCallBack(Integer.parseInt("15"));
				multiModifyChoiceCallBack.multibackgroundCallBack(Color.WHITE);
				multiModifyChoiceCallBack.multitextColorCallBack(Color.BLACK);
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
						multiModifyChoiceCallBack.multitextSizeCallBack(Integer.parseInt(rd.getText().toString()));
						break;
					}
				}

				for (int i = 0; i < rgBGColor.getChildCount(); i++) {
					RadioButton rd = (RadioButton) rgBGColor.getChildAt(i);
					if (rd.isChecked()) {
						//背景顏色
						if (rd.getText().toString().toString().equals("Red")) {
							multiModifyChoiceCallBack.multibackgroundCallBack(Color.RED);
						} else if (rd.getText().toString().toString().equals("Green")) {
							multiModifyChoiceCallBack.multibackgroundCallBack(Color.GREEN);
						} else if (rd.getText().toString().toString().equals("Blue")) {
							multiModifyChoiceCallBack.multibackgroundCallBack(Color.BLUE);
						}
						break;
					}
				}

				for (int i = 0; i < rgTextColor.getChildCount(); i++) {
					RadioButton rd = (RadioButton) rgTextColor.getChildAt(i);
					if (rd.isChecked()) {
						//文字顏色
						if (rd.getText().toString().toString().equals("Red")) {
							multiModifyChoiceCallBack.multitextColorCallBack(Color.RED);
						} else if (rd.getText().toString().toString().equals("Green")) {
							multiModifyChoiceCallBack.multitextColorCallBack(Color.GREEN);
						} else if (rd.getText().toString().toString().equals("Blue")) {
							multiModifyChoiceCallBack.multitextColorCallBack(Color.BLUE);
						}
						break;
					}
				}
				dismiss();
			}

		});

	}

}
