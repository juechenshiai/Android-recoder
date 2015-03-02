package com.jesse.recordertest;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.media.MediaRecorder.AudioEncoder;
import android.media.MediaRecorder.VideoEncoder;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;

public class MyRecorder {
	
	public MediaRecorder prMediaRecorder = new MediaRecorder();;
	private final int cMaxRecordDurationInMs = 30000;
	private final long cMaxFileSizeInBytes = 5000000;
	private final int cFrameRate = 20;
	private File prRecordedFile;
	
	private boolean prRecordInProcess;
	private Camera prCamera;
	private Context prContext;
	private String cVideoFilePath;
	private SurfaceHolder prSurfaceHolder;
	
	

	public MyRecorder(boolean prRecordInProcess, Camera prCamera,
			Context prContext, String cVideoFilePath,
			SurfaceHolder prSurfaceHolder) {
		super();
		this.prRecordInProcess = prRecordInProcess;
		this.prCamera = prCamera;
		this.prContext = prContext;
		this.cVideoFilePath = cVideoFilePath;
		this.prSurfaceHolder = prSurfaceHolder;
	}

	public void updateEncodingOptions() {
		if (prRecordInProcess) {
			stopRecording();
			startRecording();
			Toast.makeText(prContext, "Recording restarted with new options!",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(prContext, "Recording options updated!",
					Toast.LENGTH_SHORT).show();
		}
	}

	public boolean startRecording() {
		prCamera.stopPreview();
		try {
			prCamera.unlock();
			prMediaRecorder.setCamera(prCamera);
			// set audio source as Microphone, video source as camera
			// state: Initial=>Initialized
			prMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			prMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
			// set the file output format: 3gp or mp4
			// state: Initialized=>DataSourceConfigured
			String lVideoFileFullPath;
			String lDisplayMsg = "Current container format: ";
			/*if (DirUtils.puContainerFormat == SettingsDialog.cpu3GP) {
				lDisplayMsg += "3GP\n";
				lVideoFileFullPath = ".3gp";
				prMediaRecorder
						.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			} else if (DirUtils.puContainerFormat == SettingsDialog.cpuMP4) {
				lDisplayMsg += "MP4\n";
				lVideoFileFullPath = ".mp4";
				prMediaRecorder
						.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
			} else {
				lDisplayMsg += "3GP\n";
				lVideoFileFullPath = ".3gp";
				prMediaRecorder
						.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			}*/
			lDisplayMsg += "MP4\n";
			lVideoFileFullPath = ".mp4";
			prMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
			// the encoders:
			// audio: AMR-NB
			// prMediaRecorder.setAudioEncoder(AudioEncoder.AMR_NB);
			// prMediaRecorder.setAudioEncoder(AudioEncoder.AMR_WB);
			prMediaRecorder.setAudioEncoder(AudioEncoder.AAC);
			// prMediaRecorder.setAudioEncoder(AudioEncoder.AAC_ELD);
			// prMediaRecorder.setAudioEncoder(AudioEncoder.HE_AAC);
			// video: H.263, MP4-SP, or H.264
			// prMediaRecorder.setVideoEncoder(VideoEncoder.H263);
			// prMediaRecorder.setVideoEncoder(VideoEncoder.MPEG_4_SP);
			lDisplayMsg += "Current encoding format: ";
			/*if (DirUtils.puEncodingFormat == SettingsDialog.cpuH263) {
				lDisplayMsg += "H263\n";
				prMediaRecorder.setVideoEncoder(VideoEncoder.H263);
			} else if (DirUtils.puEncodingFormat == SettingsDialog.cpuMP4_SP) {
				lDisplayMsg += "MPEG4-SP\n";
				prMediaRecorder.setVideoEncoder(VideoEncoder.MPEG_4_SP);
			} else if (DirUtils.puEncodingFormat == SettingsDialog.cpuH264) {
				lDisplayMsg += "H264\n";
				prMediaRecorder.setVideoEncoder(VideoEncoder.H264);
			} else {
				lDisplayMsg += "H264\n";
				prMediaRecorder.setVideoEncoder(VideoEncoder.H264);
			}*/
			lDisplayMsg += "H264\n";
			prMediaRecorder.setVideoEncoder(VideoEncoder.H264);
			lVideoFileFullPath = cVideoFilePath
					+ String.valueOf(System.currentTimeMillis())
					+ lVideoFileFullPath;
			prRecordedFile = new File(lVideoFileFullPath);
			prMediaRecorder.setOutputFile(prRecordedFile.getPath());
			if (DirUtils.puResolutionChoice == SettingsDialog.cpuRes176) {
				prMediaRecorder.setVideoSize(176, 144);
			} else if (DirUtils.puResolutionChoice == SettingsDialog.cpuRes320) {
				prMediaRecorder.setVideoSize(320, 240);
			} else if (DirUtils.puResolutionChoice == SettingsDialog.cpuRes480) {
				prMediaRecorder.setVideoSize(480, 320);
			} else if (DirUtils.puResolutionChoice == SettingsDialog.cpuRes640) {
				prMediaRecorder.setVideoSize(640, 480);
			} else if (DirUtils.puResolutionChoice == SettingsDialog.cpuRes720) {
				prMediaRecorder.setVideoSize(720, 480);
			} else if (DirUtils.puResolutionChoice == SettingsDialog.cpuRes7202) {
				prMediaRecorder.setVideoSize(720, 640);
			} else if (DirUtils.puResolutionChoice == SettingsDialog.cpuRes1024) {
				prMediaRecorder.setVideoSize(1024, 720);
			} else if (DirUtils.puResolutionChoice == SettingsDialog.cpuRes1280) {
				prMediaRecorder.setVideoSize(1280, 1080);
			} 
			Toast.makeText(prContext, lDisplayMsg, Toast.LENGTH_LONG).show();
			prMediaRecorder.setVideoFrameRate(cFrameRate);
			prMediaRecorder.setPreviewDisplay(prSurfaceHolder.getSurface());
			prMediaRecorder.setMaxDuration(cMaxRecordDurationInMs);
			prMediaRecorder.setMaxFileSize(cMaxFileSizeInBytes);
			// prepare for capturing
			// state: DataSourceConfigured => prepared
			prMediaRecorder.prepare();
			// start recording
			// state: prepared => recording
			try {
				prMediaRecorder.start();
			} catch (Exception e) {
				// TODO: handle exception
				Log.e("´íÎó", e.toString()+"---"+e.getMessage());
			}
			
//			prStartBtn.setText("Stop");
			prRecordInProcess = true;
			return true;
		} catch (IOException _le) {
			_le.printStackTrace();
			return false;
		}
	}

	public void stopRecording() {
		try {
			prMediaRecorder.stop();
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("´íÎó", e.toString()+"---"+e.getMessage()+"");
		}
		
		prMediaRecorder.reset();
		try {
			prCamera.reconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		prStartBtn.setText("Start");
		prRecordInProcess = false;
		prCamera.startPreview();
	}

}
