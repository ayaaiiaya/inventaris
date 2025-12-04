package com.mycompany.inventaris.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class LoginPage extends StackPane {

    private Stage stage;

    public LoginPage(Stage stage) {
        this.stage = stage;
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

        // ===== LOAD CSS =====
        loadStylesheet();

        // ===== BACKGROUND =====
        Pane background = createBackground();

        // ===== LOGO POJOK KIRI =====
        ImageView cornerLogo = new ImageView(
                new Image(getClass().getResourceAsStream("/assets/logoAsa.png"))
        );
        cornerLogo.setFitHeight(60);
        cornerLogo.setPreserveRatio(true);

        StackPane.setAlignment(cornerLogo, Pos.TOP_LEFT);
        StackPane.setMargin(cornerLogo, new Insets(25, 0, 0, 30));

        // ===== LOGIN CARD =====
        VBox loginCard = new VBox(18);
        loginCard.getStyleClass().add("login-card");
        loginCard.setAlignment(Pos.CENTER);
        loginCard.setPadding(new Insets(40, 40, 40, 40));


        // Title
        Label title = new Label("Login");
        title.getStyleClass().add("login-title");

        Label subtitle = new Label("Masuk ke akun Anda");
        subtitle.getStyleClass().add("login-subtitle");

        VBox titleBox = new VBox(5, title, subtitle);
        titleBox.setAlignment(Pos.CENTER);

        // ===== FORM =====
        VBox formFields = new VBox(15);

        Label usernameLabel = new Label("Username");
        usernameLabel.getStyleClass().add("login-label");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Masukkan username");
        usernameField.getStyleClass().add("login-textfield");

        VBox usernameBox = new VBox(8, usernameLabel, usernameField);

        Label passwordLabel = new Label("Password");
        passwordLabel.getStyleClass().add("login-label");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Masukkan password");
        passwordField.getStyleClass().add("login-textfield");

        VBox passwordBox = new VBox(8, passwordLabel, passwordField);

        formFields.getChildren().addAll(usernameBox, passwordBox);

        // ===== BUTTON =====
        Button loginBtn = new Button("Login");
        loginBtn.getStyleClass().add("login-button");

        loginBtn.setOnAction(e ->
                handleLogin(usernameField.getText(), passwordField.getText())
        );

        Hyperlink backLink = new Hyperlink("← Kembali ke Beranda");
        backLink.getStyleClass().add("login-back-link");
        backLink.setOnAction(e -> {
            Scene newScene = new Scene(new MainPage(stage), 1280, 720);
            stage.setScene(newScene);
        });

        HBox backBox = new HBox(backLink);
        backBox.setAlignment(Pos.CENTER);

        // Tambah semua ke card
        loginCard.getChildren().addAll(
                titleBox,
                formFields,
                loginBtn,
                backBox
        );

        // ===== STACK SEMUA =====
        StackPane.setAlignment(loginCard, Pos.CENTER);

        this.getChildren().addAll(background, cornerLogo, loginCard);
    }

    private Pane createBackground() {
        Pane bgShapes = new Pane();

        Circle topLeft = new Circle();
        topLeft.radiusProperty().bind(this.widthProperty().multiply(0.10));
        topLeft.centerXProperty().bind(this.widthProperty().multiply(0.38));
        topLeft.centerYProperty().bind(this.heightProperty().multiply(-0.05));
        topLeft.setFill(Color.web("#931717"));
        topLeft.setMouseTransparent(true);

        Circle smallBlue = new Circle();
        smallBlue.radiusProperty().bind(this.widthProperty().multiply(0.02));
        smallBlue.centerXProperty().bind(this.widthProperty().multiply(0.52));
        smallBlue.centerYProperty().bind(this.heightProperty().multiply(0.20));
        smallBlue.setFill(Color.web("#3C4C79"));
        smallBlue.setMouseTransparent(true);

        Circle topRight = new Circle();
        topRight.radiusProperty().bind(this.widthProperty().multiply(0.10));
        topRight.centerXProperty().bind(this.widthProperty().multiply(0.97));
        topRight.centerYProperty().bind(this.heightProperty().multiply(0.07));
        topRight.setFill(Color.web("#A42323"));
        topRight.setMouseTransparent(true);

        Circle bottomLeft = new Circle();
        bottomLeft.radiusProperty().bind(this.widthProperty().multiply(0.15));
        bottomLeft.centerXProperty().bind(this.widthProperty().multiply(0.13));
        bottomLeft.centerYProperty().bind(this.heightProperty().multiply(1.05));
        bottomLeft.setFill(Color.web("#A42323"));
        bottomLeft.setMouseTransparent(true);

        bgShapes.getChildren().addAll(topLeft, smallBlue, topRight, bottomLeft);

        return bgShapes;
    }

    private void handleLogin(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Username dan password harus diisi!");
            alert.showAndWait();
            return;
        }

        if (username.equals("user") && password.equals("user123")) {
            Scene newScene = new Scene(new UserPage(), 1280, 720);
            stage.setScene(newScene);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Username atau password salah!");
            alert.showAndWait();
        }
    }
}
