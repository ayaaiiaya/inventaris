/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventaris.view;

/**
 *
 * @author Amy
 */

import com.mycompany.inventaris.model.Barang;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DetailPopup {
    
    public static void show(Barang barang) {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initStyle(StageStyle.UNDECORATED);
        
        VBox container = new VBox(1);
        container.setStyle(
            "-fx-background-color: white; " +
            "-fx-padding: 30; " +
            "-fx-background-radius: 15; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 20, 0, 0, 5);"
        );
        container.setAlignment(Pos.TOP_CENTER);
        container.setMaxWidth(600);
        
        // HEADER
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        
        ImageView logo = new ImageView(
            new Image(DetailPopup.class.getResourceAsStream("/assets/logoAsa.png"))
        );
        logo.setFitHeight(50);
        logo.setPreserveRatio(true);
        
        VBox logoText = new VBox(2);
        logoText.getChildren().addAll(
            createLabel("UNIVERSITAS", "-fx-font-size: 10px; -fx-text-fill: #333;"),
            createLabel("ASA INDONESIA", "-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #333;"),
            createLabel("Digital of Things", "-fx-font-size: 9px; -fx-text-fill: #666; -fx-font-style: italic;")
        );
        
        HBox logoBox = new HBox(10, logo, logoText);
        logoBox.setAlignment(Pos.CENTER_LEFT);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Button closeBtn = new Button("×");
        closeBtn.setStyle(
            "-fx-background-color: transparent; " +
            "-fx-text-fill: #666; " +
            "-fx-font-size: 28px; " +
            "-fx-cursor: hand;"
        );
        closeBtn.setOnAction(e -> popup.close());
        
        header.getChildren().addAll(logoBox, spacer, closeBtn);
        
        Label title = new Label("Detail Barang");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #2C3E50;");
        
        // FORM FIELDS
        VBox fields = new VBox(15);
        
        fields.getChildren().addAll(
            createField("ID Barang", barang.getKode()),
            createField("Nama Barang", barang.getNama()),
            createField("Jenis Barang", barang.getKategori()),
            createField("Stock Barang", String.valueOf(barang.getstok()) + " pack"),
            createField("Kondisi", barang.getKondisi()),
            createField("Lokasi Barang", barang.getLokasi()),
            createStatusField("Status", barang.getStatus())
        );
        
        // BUTTON PINJAM
        Button pinjamBtn = new Button("Ajukan Permintaan");
        pinjamBtn.setStyle(
            "-fx-background-color: #3C4C79; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 12 30; " +
            "-fx-background-radius: 20; " +
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-cursor: hand;"
        );
        pinjamBtn.setOnAction(e -> {
            popup.close();
            PeminjamanPopup.show(barang);
        });
        
        container.getChildren().addAll(header, title, fields, pinjamBtn);
        
        StackPane root = new StackPane(container);
        root.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        
        Scene scene = new Scene(root, 500, 650);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        
        popup.setScene(scene);
        popup.showAndWait();
    }
    
    private static VBox createField(String label, String value) {
        VBox field = new VBox(5);
        
        Label labelTxt = new Label(label);
        labelTxt.setStyle("-fx-font-size: 12px; -fx-text-fill: #555; -fx-font-weight: bold;");
        
        TextField textField = new TextField(value);
        textField.setEditable(false);
        textField.setStyle(
            "-fx-background-color: #f5f5f5; " +
            "-fx-border-color: #d0d0d0; " +
            "-fx-border-radius: 8; " +
            "-fx-background-radius: 8; " +
            "-fx-padding: 10 15; " +
            "-fx-font-size: 13px;"
        );
        
        field.getChildren().addAll(labelTxt, textField);
        return field;
    }
    
    private static VBox createStatusField(String label, String value) {
        VBox field = new VBox(5);
        
        Label labelTxt = new Label(label);
        labelTxt.setStyle("-fx-font-size: 12px; -fx-text-fill: #555; -fx-font-weight: bold;");
        
        Label statusLabel = new Label(value);
        statusLabel.setStyle(
            "-fx-background-color: #28a745; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 6 20; " +
            "-fx-background-radius: 15; " +
            "-fx-font-size: 12px; " +
            "-fx-font-weight: bold;"
        );
        
        HBox statusBox = new HBox(statusLabel);
        statusBox.setAlignment(Pos.CENTER_LEFT);
        
        field.getChildren().addAll(labelTxt, statusBox);
        return field;
    }
    
    private static Label createLabel(String text, String style) {
        Label l = new Label(text);
        l.setStyle(style);
        return l;
    }
}
