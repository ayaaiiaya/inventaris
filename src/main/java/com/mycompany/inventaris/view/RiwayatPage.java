package com.mycompany.inventaris.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class RiwayatPage extends BorderPane {
    
    public RiwayatPage() {
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
        // SIDEBAR
        VBox sidebar = createSidebar();

        // MAIN CONTENT
        VBox mainContent = new VBox(20);
        mainContent.getStyleClass().add("main-content");

        // Header
        Label hello = new Label("Hello, User !!");
        hello.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #334155;");

        Label title = new Label("Riwayat Peminjaman");
        title.getStyleClass().add("page-title");

        VBox header = new VBox(hello, title);
        header.setSpacing(8);

        // Search Bar
        TextField searchField = new TextField();
        searchField.setPromptText("🔍 Cari Riwayat...");
        searchField.getStyleClass().add("search-field");

        // Tabel Riwayat
        TableView<PeminjamanData> table = new TableView<>();
        table.getStyleClass().add("table-view");

        TableColumn<PeminjamanData, String> noCol = new TableColumn<>("No.");
        noCol.setPrefWidth(60);
        noCol.setCellValueFactory(data -> {
            int index = table.getItems().indexOf(data.getValue()) + 1;
            return new SimpleStringProperty(String.valueOf(index));
        });

        TableColumn<PeminjamanData, String> idCol = new TableColumn<>("ID Peminjaman");
        idCol.setPrefWidth(150);
        idCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIdPeminjaman()));

        TableColumn<PeminjamanData, String> namaCol = new TableColumn<>("Nama Peminjam");
        namaCol.setPrefWidth(200);
        namaCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNamaPeminjam()));

        TableColumn<PeminjamanData, String> barangCol = new TableColumn<>("Nama Barang");
        barangCol.setPrefWidth(200);
        barangCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNamaBarang()));

        TableColumn<PeminjamanData, String> tglCol = new TableColumn<>("Tanggal Pinjam");
        tglCol.setPrefWidth(150);
        tglCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTanggalPinjam()));

        TableColumn<PeminjamanData, String> statusCol = new TableColumn<>("Status");
        statusCol.setPrefWidth(120);
        statusCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setGraphic(null);
                } else {
                    Label statusLabel = new Label(status);
                    if (status.equals("Dipinjam")) {
                        statusLabel.getStyleClass().add("status-dipinjam");
                    } else {
                        statusLabel.getStyleClass().add("status-dikembalikan");
                    }
                    setGraphic(statusLabel);
                }
            }
        });
        statusCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));

        TableColumn<PeminjamanData, Void> actionCol = new TableColumn<>("Action");
        actionCol.setPrefWidth(120);
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Detail");

            {
                btn.getStyleClass().add("btn-danger");
                btn.setOnAction(e -> {
                    PeminjamanData data = getTableView().getItems().get(getIndex());
                    System.out.println("Detail peminjaman: " + data.getIdPeminjaman());
                    // TODO: Buka detail popup
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        table.getColumns().addAll(noCol, idCol, namaCol, barangCol, tglCol, statusCol, actionCol);

        // Dummy data
        table.getItems().addAll(
            new PeminjamanData("PMJ001", "Ahmad Fauzi", "Laptop Asus", "2025-11-20", "Dipinjam"),
            new PeminjamanData("PMJ002", "Siti Nurhaliza", "Proyektor", "2025-11-21", "Dikembalikan"),
            new PeminjamanData("PMJ003", "Budi Santoso", "Marker", "2025-11-22", "Dipinjam"),
            new PeminjamanData("PMJ004", "Rina Kartika", "Kertas HVS", "2025-11-23", "Dikembalikan"),
            new PeminjamanData("PMJ005", "Doni Pratama", "Computer PC", "2025-11-24", "Dipinjam")
        );

        // Search functionality
        searchField.textProperty().addListener((obs, old, newVal) -> {
            // TODO: Implement search
        });

        mainContent.getChildren().addAll(header, searchField, table);

        this.setLeft(sidebar);
        this.setCenter(mainContent);
    }
    
    private VBox createSidebar() {
        VBox sidebar = new VBox(20);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setAlignment(Pos.TOP_CENTER);

        ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/assets/logoAsa.png")));
        logo.getStyleClass().add("sidebar-logo");

        VBox logoText = new VBox(2);
        logoText.getStyleClass().add("sidebar-logo-text");
        Label uni = new Label("UNIVERSITAS");
        uni.getStyleClass().add("sidebar-uni-label");
        Label asa = new Label("ASA INDONESIA");
        asa.getStyleClass().add("sidebar-asa-label");
        logoText.getChildren().addAll(uni, asa);

        Circle userCircle = new Circle(30, Color.web("#94a3b8"));
        StackPane userIcon = new StackPane();
        Label userLabel = new Label("U");
        userLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");
        userIcon.getChildren().addAll(userCircle, userLabel);

        Label guestLabel = new Label("Guest");
        guestLabel.getStyleClass().add("sidebar-user-label");

        VBox userBox = new VBox(8, userIcon, guestLabel);
        userBox.getStyleClass().add("sidebar-user-box");

        VBox menuBox = new VBox(10);
        menuBox.getStyleClass().add("sidebar-menu");
        
        Button dashboardBtn = createMenuButton("🏠  Dashboard", false);
        Button riwayatBtn = createMenuButton("🕐  Riwayat", true);
        
        dashboardBtn.setOnAction(e -> {
            Stage currentStage = (Stage) dashboardBtn.getScene().getWindow();
            Scene newScene = new Scene(new UserPage(), 1280, 720);
            currentStage.setScene(newScene);
        });
        
        menuBox.getChildren().addAll(dashboardBtn, riwayatBtn);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Button logoutBtn = new Button("⎋  Logout");
        logoutBtn.getStyleClass().add("logout-button");

        sidebar.getChildren().addAll(logo, logoText, userBox, menuBox, spacer, logoutBtn);
        return sidebar;
    }

    private Button createMenuButton(String text, boolean active) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        if (active) {
            btn.getStyleClass().add("menu-button-active");
        } else {
            btn.getStyleClass().add("menu-button");
        }
        return btn;
    }
    
    // Inner class untuk data peminjaman
    public static class PeminjamanData {
        private String idPeminjaman;
        private String namaPeminjam;
        private String namaBarang;
        private String tanggalPinjam;
        private String status;
        
        public PeminjamanData(String idPeminjaman, String namaPeminjam, String namaBarang, 
                             String tanggalPinjam, String status) {
            this.idPeminjaman = idPeminjaman;
            this.namaPeminjam = namaPeminjam;
            this.namaBarang = namaBarang;
            this.tanggalPinjam = tanggalPinjam;
            this.status = status;
        }
        
        public String getIdPeminjaman() { return idPeminjaman; }
        public String getNamaPeminjam() { return namaPeminjam; }
        public String getNamaBarang() { return namaBarang; }
        public String getTanggalPinjam() { return tanggalPinjam; }
        public String getStatus() { return status; }
    }
}