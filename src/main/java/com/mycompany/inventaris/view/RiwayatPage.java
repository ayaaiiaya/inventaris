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
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class RiwayatPage extends StackPane {
    
    public RiwayatPage() {
        initializeUI();
    }

    private void initializeUI() {

        // Background Shape
        Pane bg = new Pane();

        Circle topRed = new Circle(170, Color.web("#931717"));
        topRed.setLayoutX(500);
        topRed.setLayoutY(-40);

        Circle smallBlue = new Circle(35, Color.web("#3C4C79"));
        smallBlue.setLayoutX(300);
        smallBlue.setLayoutY(140);

        Circle topRight = new Circle(150, Color.web("#A42323"));
        topRight.setLayoutX(1250);
        topRight.setLayoutY(40);

        bg.getChildren().addAll(topRed, smallBlue, topRight);


        // Navbar
        BorderPane navbar = new BorderPane();
        navbar.setStyle("-fx-padding: 25 60; -fx-font-family: 'Poppins';");

        ImageView logo = new ImageView(
                new Image(getClass().getResourceAsStream("/assets/logoAsa.png"))
        );
        logo.setFitHeight(80);
        logo.setPreserveRatio(true);
        navbar.setLeft(logo);

        HBox menu = new HBox(
                new Label("Home"),
                new Label("About"),
                new Label("Guide"),
                new Label("Contact")
        );
        menu.setSpacing(40);
        menu.setStyle("-fx-font-size: 16px; -fx-text-fill: #334155; -fx-font-weight: bold; -fx-padding: 0 0 0 300; -fx-font-family: 'Poppins';");
        menu.setAlignment(Pos.CENTER);
        navbar.setCenter(menu);


        // Header
        Label hello = new Label("Hello, User !!");
        hello.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #334155;");

        Label title = new Label("Riwayat Peminjaman");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #1e293b;");

        VBox header = new VBox(hello, title);
        header.setSpacing(8);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-padding: 20 60 10 60;");


        // Tabel Riwayat
        TableView<PeminjamanData> table = new TableView<>();
        table.setPrefWidth(1000);

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
        statusCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));

        TableColumn<PeminjamanData, Void> actionCol = new TableColumn<>("Action");
        actionCol.setPrefWidth(120);
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Detail");

            {
                btn.setStyle(
                    "-fx-background-color: #A42323; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-padding: 6 20; " +
                    "-fx-background-radius: 5; " +
                    "-fx-cursor: hand;"
                );
                btn.setOnAction(e -> {
                    PeminjamanData data = getTableView().getItems().get(getIndex());
                    // Nanti bisa buka detail popup
                    System.out.println("Detail peminjaman: " + data.getIdPeminjaman());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        table.getColumns().addAll(noCol, idCol, namaCol, barangCol, tglCol, statusCol, actionCol);


        // Riwayat Data Smeentara
        table.getItems().addAll(
            new PeminjamanData("PMJ001", "Ahmad Fauzi", "Laptop Asus", "2025-11-20", "Dipinjam"),
            new PeminjamanData("PMJ002", "Siti Nurhaliza", "Proyektor", "2025-11-21", "Dikembalikan"),
            new PeminjamanData("PMJ003", "Budi Santoso", "Marker", "2025-11-22", "Dipinjam"),
            new PeminjamanData("PMJ004", "Rina Kartika", "Kertas HVS", "2025-11-23", "Dikembalikan"),
            new PeminjamanData("PMJ005", "Doni Pratama", "Computer PC", "2025-11-24", "Dipinjam")
        );


        // Tabel
        HBox tableWrapper = new HBox(table);
        tableWrapper.setAlignment(Pos.CENTER);
        tableWrapper.setStyle("-fx-padding: 10 60 40 60;");


        // Konten Stack
        VBox content = new VBox(navbar, header, tableWrapper);
        content.setSpacing(10);
        content.setAlignment(Pos.TOP_CENTER);

        this.getChildren().addAll(bg, content);
    }
    
    
    // ===== INNER CLASS untuk Data Peminjaman =====
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