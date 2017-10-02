package com.github.smk7758.testOpenCV2;

import java.io.IOException;

import org.opencv.core.Core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	//final -> snake Capital, other fields -> snake Small?
	public static final String SOFTWARE_NAME = "testOpenCV2";
	public final String FXML_FILE_NAME = SOFTWARE_NAME + ".fxml";
	static {
//		System.out.println(System.getProperty("java.library.path"));
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource(FXML_FILE_NAME)));
			// Set ico
//			primaryStage.getIcons().add(new Image((getClass().getResource("OnlyDownloader_x16.ico").toString())));
//			primaryStage.getIcons().add(new Image((getClass().getResource("OnlyDownloader_x32.ico").toString())));
			// Set Title
			primaryStage.setTitle(SOFTWARE_NAME);
//			// Set Window
			primaryStage.setResizable(false);
			// Set Scene
			primaryStage.setScene(scene);
			primaryStage.show();
//			primaryStage.onShownProperty();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
