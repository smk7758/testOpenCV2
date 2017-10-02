package com.github.smk7758.testOpenCV2;

import java.io.ByteArrayInputStream;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Capture extends Thread {
	VideoCapture vc = null;
	Mat mat_capture = null, mat_first = null, mat_export = new Mat();
	ImageView iv = null;
	Image image_export = null;

	public Capture(VideoCapture vc, Mat mat, ImageView iv) {
		this.vc = vc;
		this.mat_capture = mat;
		this.iv = iv;
	}

	public void run() {
		if (vc.isOpened()) {
			vc.read(mat_capture);
			if (!mat_capture.empty()) mat_first = mat_capture.clone();
			while (Controller.capturing) {
				vc.read(mat_capture);
				if (!mat_capture.empty()) {
					//get the diff.
					Core.absdiff(mat_first, mat_capture, mat_export);

					//convent bit size(?)
					mat_first.convertTo(mat_first, CvType.CV_8U);
					mat_export.convertTo(mat_export, CvType.CV_32F);

					//What's this? -> 差分した動体の軌跡が残る程度
					Imgproc.accumulateWeighted(mat_first, mat_export, 0.022);

					// mat → image
					MatOfByte byteMat = new MatOfByte();
					Highgui.imencode(".bmp", mat_export, byteMat);
					image_export = new Image(new ByteArrayInputStream(byteMat.toArray()));

					// set(show) ImageView(GUI)
					iv.setImage(image_export);


				} else {
					System.out.println("[Error] Can't capture the camera.");
				}
			}
			System.out.println("Stop.(Capture)");
		}
	}
}
