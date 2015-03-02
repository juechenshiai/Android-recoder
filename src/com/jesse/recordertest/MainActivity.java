package com.jesse.recordertest;

import java.io.IOException;

import com.example.recordertest.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements SurfaceHolder.Callback{

	private SurfaceView prSurfaceView;
	private Button prStartBtn;
	private Button prSettingsBtn;
	private boolean prRecordInProcess;
	private SurfaceHolder prSurfaceHolder;
	private Camera prCamera;
	private final String cVideoFilePath = Environment.getExternalStorageDirectory().getPath()+"/vanlon/";
	private MyRecorder myrecorder;

	private Context prContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		prContext = this.getApplicationContext();
		setContentView(R.layout.activity_main);
		DirUtils.createDirIfNotExist(cVideoFilePath);
		prSurfaceView = (SurfaceView) findViewById(R.id.surface_camera);
		prStartBtn = (Button) findViewById(R.id.main_btn1);
		prSettingsBtn = (Button) findViewById(R.id.main_btn2);
		prRecordInProcess = false;
		
		prStartBtn.setOnClickListener(new View.OnClickListener() {
			// @Override
			public void onClick(View v) {
				myrecorder = new MyRecorder(prRecordInProcess, prCamera, prContext, cVideoFilePath, prSurfaceHolder);
				if (prRecordInProcess == false) {
					myrecorder.startRecording();
					prStartBtn.setText("stop");
					prRecordInProcess = true;
				} else {
					myrecorder.stopRecording();
					prStartBtn.setText("start");
					prRecordInProcess = false;
				}
			}
		});
		prSettingsBtn.setOnClickListener(new View.OnClickListener() {
			// @Override
			public void onClick(View v) {
				Intent lIntent = new Intent();
				lIntent.setClass(prContext,SettingsDialog.class);
				startActivityForResult(lIntent, REQUEST_DECODING_OPTIONS);
			}
		});
		prSurfaceHolder = prSurfaceView.getHolder();
		prSurfaceHolder.addCallback(this);
		prSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//		prMediaRecorder = new MediaRecorder();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		prCamera = Camera.open();
		if (prCamera == null) {
			Toast.makeText(this.getApplicationContext(), "摄像头不可用!",
					Toast.LENGTH_SHORT).show();
			finish();
		}
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		Camera.Parameters lParam = prCamera.getParameters();
		// //lParam.setPreviewSize(_width, _height);
		// //lParam.setPreviewSize(320, 240);
		// lParam.setPreviewFormat(PixelFormat.JPEG);
		prCamera.setParameters(lParam);
		try {
			prCamera.setPreviewDisplay(holder);
			prCamera.startPreview();
			// prPreviewRunning = true;
		} catch (IOException _le) {
			_le.printStackTrace();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		if (prRecordInProcess) {
			myrecorder.stopRecording();
			prStartBtn.setText("start");
			prRecordInProcess = false;
		} else {
			prCamera.stopPreview();
		}
		myrecorder.prMediaRecorder.release();
		myrecorder.prMediaRecorder = null;
		prCamera.release();
		prCamera = null;
	}
	
	private static final int REQUEST_DECODING_OPTIONS = 0;

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		switch (requestCode) {
		case REQUEST_DECODING_OPTIONS:
			if (resultCode == RESULT_OK) {
				myrecorder.updateEncodingOptions();
			}
			break;
		}
	}
}
