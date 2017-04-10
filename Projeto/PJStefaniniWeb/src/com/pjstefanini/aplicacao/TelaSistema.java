package com.pjstefanini.aplicacao;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class TelaSistema extends Application {
	
	private Scene scene;

	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		
		scene = new Scene(new Tela(), 900, 600);
		
		stage.setTitle("S . G . F - Sistema de Gestão de Funcionarios");
		stage.setFullScreen(true);
		stage.setScene(scene);
		stage.show();
	}
	
}

class Tela extends Region{
	
	final WebView wv = new WebView();
	final WebEngine we = wv.getEngine();
	
	public Tela() {
		getStyleClass().add("wv");
		we.load("http://localhost:8080/PJStefaniniWeb/index.jsf");
		getChildren().add(wv);
	}
	
	@Override
	protected void layoutChildren() {
		double w = getWidth();
		double h = getHeight();
		layoutInArea(wv,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
	}
	
}