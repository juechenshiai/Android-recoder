package com.jesse.recordertest;

import com.example.recordertest.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SettingsDialog extends Activity {

	private Spinner prSpinnerEncodingFormat;
	private Spinner prSpinnerConainterFormat;
	private Spinner prSpinnerResolution;
	private Button prBtnOk;
	private Button prBtnCancel;

	// for folderSpinner: 1. original folder, 2. browse for a folder
//	public static final int cpuH263 = 0;
//	public static final int cpuMP4_SP = 1;
	public static final int cpuH264 = 2;

//	public static final int cpu3GP = 0;
	public static final int cpuMP4 = 1;

	public static final int cpuRes176 = 0;
	public static final int cpuRes320 = 1;
	public static final int cpuRes480 = 2;
	public static final int cpuRes640 = 3;
	public static final int cpuRes720 = 4;
	public static final int cpuRes7202 = 5;
	public static final int cpuRes1024 = 6;
	public static final int cpuRes1280 = 7;

	// public static final String cpuEncodingFormatOptionString =
	// "cpuEncodingFormatOption";
	// public static final String cpuContainerFormatOptionString =
	// "cpuContainerFormatOption";

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_settings_dialog);
		/*
		prSpinnerEncodingFormat = (Spinner) findViewById(R.id.dialog_settings_encoding_format_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.dialog_settings_encoding_format_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		prSpinnerEncodingFormat.setAdapter(adapter);
		prSpinnerEncodingFormat.setSelection(DirUtils.puEncodingFormat);
		prSpinnerEncodingFormat
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						int rowSelected = (int) arg3;
						DirUtils.puEncodingFormat = rowSelected;
					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// do nothing
					}
				});

		prSpinnerConainterFormat = (Spinner) findViewById(R.id.dialog_settings_container_format_spinner);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
				this, R.array.dialog_settings_container_format_array,
				android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		prSpinnerConainterFormat.setAdapter(adapter2);
		prSpinnerConainterFormat.setSelection(DirUtils.puContainerFormat);
		prSpinnerConainterFormat
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						int rowSelected = (int) arg3;
						DirUtils.puContainerFormat = rowSelected;
					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// do nothing
					}
				});*/
		prSpinnerResolution = (Spinner) findViewById(R.id.dialog_settings_resolution_spinner);
		ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
				this, R.array.dialog_settings_resolution_array,
				android.R.layout.simple_spinner_item);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		prSpinnerResolution.setAdapter(adapter3);
		prSpinnerResolution.setSelection(DirUtils.puResolutionChoice);
		prSpinnerResolution
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						int rowSelected = (int) arg3;
						DirUtils.puResolutionChoice = rowSelected;
					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// do nothing
					}
				});
		prBtnOk = (Button) findViewById(R.id.dialog_settings_btn1);
		prBtnOk.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// export picture to specified location with specified name
				setResult(RESULT_OK);
				finish();
			}
		});
		prBtnCancel = (Button) findViewById(R.id.dialog_settings_btn2);
		prBtnCancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// cancel export, just finish the activity
				SettingsDialog.this.finish();
			}
		});
		getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//		WindowManager m = getWindowManager();
//        Display d = m.getDefaultDisplay();
//        LayoutParams p = getWindow().getAttributes();
//        p.height = (int) (d.getHeight() * 0.8);
//        p.width = (int) (d.getWidth()*0.6);
//        getWindow().setAttributes(p);
	}
}
