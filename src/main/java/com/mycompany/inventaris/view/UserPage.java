package com.mycompany.inventaris.view;

import com.mycompany.inventaris.model.Barang;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class UserPage extends StackPane {

    public UserPage() {
        initializeUI();
    }

    private void initializeUI() {

        // =Background Shape
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
        
        Label riwayatLabel = new Label("Riwayat");
        riwayatLabel.setOnMouseClicked(e -> {
        Stage stage = (Stage) riwayatLabel.getScene().getWindow();
        Scene newScene = new Scene(new RiwayatPage(), 1280, 720);
        stage.setScene(newScene);
        });
        riwayatLabel.setStyle("-fx-cursor: hand;");

        HBox menu = new HBox(
                new Label("Home"),
                riwayatLabel,
                new Label("About"),
                new Label("Guide"),
                new Label("Contact")
        );
        menu.setSpacing(40);
        menu.setStyle("-fx-font-size: 16px; -fx-text-fill: #334155; -fx-font-weight: bold; -fx-padding: 0 0 0 300; -fx-font-family: 'Poppins';");
        menu.setAlignment(Pos.CENTER);
        navbar.setCenter(menu);


        // Text
        Label hello = new Label("Hello, User !!");
        hello.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #334155;");

        Label title = new Label("Daftar Barang");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #1e293b;");

        VBox header = new VBox(hello, title);
        header.setSpacing(8);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-padding: 20 60 10 60;");


        // Search Bar
        HBox searchBox = new HBox(15);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        searchBox.setStyle("-fx-padding: 10 60;");
        
        TextField searchField = new TextField();
        searchField.setPromptText("🔍 Cari barang...");
        searchField.setPrefWidth(350);
        searchField.setStyle(
            "-fx-background-color: white; " +
            "-fx-border-color: #cbd5e1; " +
            "-fx-border-radius: 8; " +
            "-fx-background-radius: 8; " +
            "-fx-padding: 10 15; " +
            "-fx-font-size: 14px;"
        );
        
        Button searchBtn = new Button("Cari");
        searchBtn.setStyle(
            "-fx-background-color: #3C4C79; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 10 30; " +
            "-fx-background-radius: 8; " +
            "-fx-font-weight: bold; " +
            "-fx-cursor: hand;"
        );
        
        Button resetBtn = new Button("Reset");
        resetBtn.setStyle(
            "-fx-background-color: #64748b; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 10 30; " +
            "-fx-background-radius: 8; " +
            "-fx-cursor: hand;"
        );
        
        searchBox.getChildren().addAll(searchField, searchBtn, resetBtn);


        // Tabel
        TableView<Barang> table = new TableView<>();
        table.setPrefWidth(1000);

        TableColumn<Barang, String> noCol = new TableColumn<>("No.");
        noCol.setPrefWidth(60);
        noCol.setCellValueFactory(data -> {
            int index = table.getItems().indexOf(data.getValue()) + 1;
            return new SimpleStringProperty(String.valueOf(index));
        });

        TableColumn<Barang, String> idCol = new TableColumn<>("ID Barang");
        idCol.setPrefWidth(200);
        idCol.setCellValueFactory(data -> 
            new SimpleStringProperty(data.getValue().getKode()));

        TableColumn<Barang, String> nameCol = new TableColumn<>("Nama Barang");
        nameCol.setPrefWidth(300);
        nameCol.setCellValueFactory(data -> 
            new SimpleStringProperty(data.getValue().getNama()));

        TableColumn<Barang, String> catCol = new TableColumn<>("Kategori Barang");
        catCol.setPrefWidth(250);
        catCol.setCellValueFactory(data -> 
            new SimpleStringProperty(data.getValue().getKategori()));

        TableColumn<Barang, Void> actionCol = new TableColumn<>("Action");
        actionCol.setPrefWidth(160);
        actionCol.setStyle("-fx-alignment: CENTER;");

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
                    Barang barang = getTableView().getItems().get(getIndex());
                    DetailPopup.show(barang);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        table.getColumns().addAll(noCol, idCol, nameCol, catCol, actionCol);
        
        
        // Data Sementara
        List<Barang> allData = List.of(
            new Barang(1, "CL001", "Kertas Folio", "Consumable", 25, "Baik", "Ruang A", "Tersedia"),
            new Barang(2, "NC001", "Proyektor", "Non-Consumable", 5, "Baik", "Ruang B", "Tersedia"),
            new Barang(3, "RL001", "Marker", "Reusable", 50, "Baik", "Ruang A", "Tersedia"),
            new Barang(4, "CL002", "Kertas HVS", "Consumable", 30, "Baik", "Ruang C", "Tersedia"),
            new Barang(5, "NC002", "Computer PC", "Non-Consumable", 10, "Baik", "Lab", "Tersedia")
        );
        
        table.getItems().addAll(allData);
        
        
        // Function Search
        searchBtn.setOnAction(e -> {
            String keyword = searchField.getText().toLowerCase();
            table.getItems().clear();
            
            if (keyword.isEmpty()) {
                table.getItems().addAll(allData);
            } else {
                allData.stream()
                    .filter(b -> 
                        b.getKode().toLowerCase().contains(keyword) ||
                        b.getNama().toLowerCase().contains(keyword) ||
                        b.getKategori().toLowerCase().contains(keyword)
                    )
                    .forEach(b -> table.getItems().add(b));
            }
        });
        
        resetBtn.setOnAction(e -> {
            searchField.clear();
            table.getItems().clear();
            table.getItems().addAll(allData);
        });
        
        // REAL TIME SEARCH
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            table.getItems().clear();
            if (newVal.isEmpty()) {
                table.getItems().addAll(allData);
            } else {
                String keyword = newVal.toLowerCase();
                allData.stream()
                    .filter(b -> 
                        b.getKode().toLowerCase().contains(keyword) ||
                        b.getNama().toLowerCase().contains(keyword) ||
                        b.getKategori().toLowerCase().contains(keyword)
                    )
                    .forEach(b -> table.getItems().add(b));
            }
        });


        // Wrap Tabel
        HBox tableWrapper = new HBox(table);
        tableWrapper.setAlignment(Pos.CENTER);
        tableWrapper.setStyle("-fx-padding: 10 60 40 60;");


        // Konten Stack
        VBox content = new VBox(navbar, header, searchBox, tableWrapper);
        content.setSpacing(10);
        content.setAlignment(Pos.TOP_CENTER);

        this.getChildren().addAll(bg, content);
    }
}