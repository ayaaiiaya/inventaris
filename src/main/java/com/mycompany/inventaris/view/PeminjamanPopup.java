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

public class PeminjamanPopup {
    
    public static void show(Barang barang) {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initStyle(StageStyle.UNDECORATED);
        
        VBox container = new VBox(20);
        container.setStyle(
            "-fx-background-color: white; " +
            "-fx-padding: 30; " +
            "-fx-background-radius: 15; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 20, 0, 0, 5);"
        );
        container.setAlignment(Pos.TOP_CENTER);
        container.setPrefWidth(500);
        
        // HEADER
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        
        ImageView logo = new ImageView(
            new Image(PeminjamanPopup.class.getResourceAsStream("/assets/logoAsa.png"))
        );
        logo.setFitHeight(50);
        logo.setPreserveRatio(true);
        
        VBox logoText = new VBox(2);
        logoText.getChildren().addAll(
            createLabel("UNIVERSITAS", "-fx-font-size: 10px; -fx-text-fill: #333;"),
            createLabel("ASA INDONESIA", "-fx-font-size: 12px; -fx-font-weight: bold;"),
            createLabel("Digital of Things", "-fx-font-size: 9px; -fx-font-style: italic; -fx-text-fill: #666;")
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
        
        Label title = new Label("Form Peminjaman Barang");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2C3E50;");
        
        // FORM FIELDS
        VBox fields = new VBox(15);
        
        TextField namaPeminjam = new TextField();
        namaPeminjam.setPromptText("Masukkan nama");
        
        TextField jabatan = new TextField();
        jabatan.setPromptText("Masukkan jabatan");
        
        TextField idBarang = new TextField(barang.getKode());
        idBarang.setEditable(false);
        
        TextField namaBarang = new TextField(barang.getNama());
        namaBarang.setEditable(false);
        
        fields.getChildren().addAll(
            createFormField("Nama Peminjam", namaPeminjam),
            createFormField("Jabatan", jabatan),
            createFormField("ID Barang", idBarang),
            createFormField("Nama Barang", namaBarang)
        );
        
        // BUTTON SUBMIT
        Button submitBtn = new Button("Submit Peminjaman");
        submitBtn.setStyle(
            "-fx-background-color: #3C4C79; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 12 30; " +
            "-fx-background-radius: 20; " +
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-cursor: hand;"
        );
        submitBtn.setOnAction(e -> {
            if (namaPeminjam.getText().isEmpty() || jabatan.getText().isEmpty()) {
                showAlert("Error", "Semua field harus diisi!", Alert.AlertType.ERROR);
            } else {
                showAlert("Sukses", "Peminjaman berhasil diajukan!", Alert.AlertType.INFORMATION);
                popup.close();
            }
        });
        
        container.getChildren().addAll(header, title, fields, submitBtn);
        
        StackPane root = new StackPane(container);
        root.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        
        Scene scene = new Scene(root, 600, 550);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        
        popup.setScene(scene);
        popup.showAndWait();
    }
    
    private static VBox createFormField(String labelText, TextField textField) {
        VBox field = new VBox(5);
        
        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 12px; -fx-text-fill: #555; -fx-font-weight: bold;");
        
        textField.setStyle(
            "-fx-background-color: white; " +
            "-fx-border-color: #d0d0d0; " +
            "-fx-border-radius: 8; " +
            "-fx-background-radius: 8; " +
            "-fx-padding: 10 15; " +
            "-fx-font-size: 13px;"
        );
        
        field.getChildren().addAll(label, textField);
        return field;
    }
    
    private static Label createLabel(String text, String style) {
        Label label = new Label(text);
        label.setStyle(style);
        return label;
    }
    
    private static void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}