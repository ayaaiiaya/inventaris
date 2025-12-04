package com.mycompany.inventaris.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class UserPage extends BorderPane {

    private Stage stage;

    public UserPage() {
        loadStylesheet();
        initializeUI();
    }

    private void loadStylesheet() {
        try {
            String css = getClass().getResource("/css/main.css").toExternalForm();
            this.getStylesheets().add(css);
        } catch (Exception e) {
            System.err.println("Failed to load CSS: " + e.getMessage());
        }
    }

    private void initializeUI() {
        // SIDEBAR KIRI
        VBox sidebar = new VBox(20);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setAlignment(Pos.TOP_CENTER);

        // Logo
        ImageView logo = new ImageView(
            new Image(getClass().getResourceAsStream("/assets/logoAsa.png"))
        );
        logo.setFitHeight(80);
        logo.setPreserveRatio(true);

        VBox logoBox = new VBox(3, logo);
        logoBox.getStyleClass().add("logo-box");

        // ===============================
        // User Profile (DIBUAT GANTI FOTO)
        // ===============================

        // foto user
        Image userPhoto = new Image(getClass().getResourceAsStream("/assets/user.png"));
        ImageView userImage = new ImageView(userPhoto);
        userImage.setFitWidth(45);
        userImage.setFitHeight(45);
        userImage.setPreserveRatio(true);
        userImage.getStyleClass().add("sidebar-user-avatar");

        // bikin foto jadi lingkaran
        Circle clipCircle = new Circle(22.5, 22.5, 22.5);
        userImage.setClip(clipCircle);

        // label "User"
        Label guestLabel = new Label("Amy");
        guestLabel.getStyleClass().add("sidebar-user-label");

        // posisi foto kiri - tulisan kanan (gantinya Vbox jadi HBox)
        HBox userBox = new HBox(12, userImage, guestLabel);
        userBox.getStyleClass().add("sidebar-user-box");

        // Menu Items
        VBox menuBox = new VBox(8);
        menuBox.getStyleClass().add("sidebar-menu");

        Button dashboardBtn = createMenuButton("🏠  Dashboard", true);
        Button riwayatBtn = createMenuButton("🕐  Riwayat", false);
        Button logoutBtn = new Button("↩  Logout");
        logoutBtn.getStyleClass().add("logout-button");
        riwayatBtn.setOnAction(e -> {
            Stage currentStage = (Stage) riwayatBtn.getScene().getWindow();
            Scene newScene = new Scene(new RiwayatPage(), 1280, 720);
            currentStage.setScene(newScene);
        });
        logoutBtn.setOnAction(e -> {
            Stage currentStage = (Stage) logoutBtn.getScene().getWindow();
            Scene newScene = new Scene(new MainPage(currentStage), 1280, 720);
            currentStage.setScene(newScene);
        });

        menuBox.getChildren().addAll(dashboardBtn, riwayatBtn);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // gabungkan sidebar
        sidebar.getChildren().addAll(logoBox, userBox, menuBox, spacer, logoutBtn);

        // KONTEN UTAMA (KANAN)
        StackPane mainContent = new StackPane();
        mainContent.setStyle("-fx-background-color: #f8fafc;");

        VBox centerBox = new VBox(40);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(80));

        // Title
        Label halo = new Label("HALO, AMY!!");
        halo.getStyleClass().add("welcome-title");

        // Buttons
        Button peminjamanBtn = new Button("PEMINJAMAN BARANG");
        peminjamanBtn.getStyleClass().add("btn-peminjaman");
        peminjamanBtn.setOnAction(e -> {
            Stage currentStage = (Stage) peminjamanBtn.getScene().getWindow();
            Scene newScene = new Scene(new PeminjamanBarangPage(), 1280, 720);
            currentStage.setScene(newScene);
        });

        Button pengembalianBtn = new Button("PENGEMBALIAN BARANG");
        pengembalianBtn.getStyleClass().add("btn-pengembalian");

        HBox buttonBox = new HBox(30, peminjamanBtn, pengembalianBtn);
        buttonBox.getStyleClass().add("dashboard-button-box");

        centerBox.getChildren().addAll(halo, buttonBox);
        mainContent.getChildren().add(centerBox);

        // Set Layout
        this.setLeft(sidebar);
        this.setCenter(mainContent);
    }

    private Button createMenuButton(String text, boolean isActive) {
        Button btn = new Button(text);

        if (isActive) {
            btn.getStyleClass().add("menu-button-active");
        } else {
            btn.getStyleClass().add("menu-button");
        }

        btn.setMaxWidth(Double.MAX_VALUE);
        return btn;
    }
}
