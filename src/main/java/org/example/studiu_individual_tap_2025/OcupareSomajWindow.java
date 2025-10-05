package org.example.studiu_individual_tap_2025;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.text.Text;



public class OcupareSomajWindow {
    private VBox createDataRow(VBox item, String label, String value) {
        GridPane row = new GridPane();
        row.getStyleClass().add("data-row");

        Label lbl = new Label(label);
        lbl.setWrapText(true);

        Label val = new Label(value);
        GridPane.setHalignment(val, HPos.RIGHT);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(70);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(30);
        row.getColumnConstraints().addAll(col1, col2);

        row.add(lbl, 0, 0);
        row.add(val, 1, 0);


        // Linie
        Region line = new Region();
        line.setMinHeight(1);
        line.setMaxWidth(Double.MAX_VALUE);
        line.setStyle("-fx-background-color: #d6d6d6;");

        VBox container = new VBox(line, row);
        return container;
    }


    public void open() {
        Stage stage = new Stage();
        stage.setTitle("Ocupare și șomaj");
        stage.setMaximized(true);

        VBox root = new VBox();
        root.getStyleClass().add("root-container");
        Scene scene = new Scene(root, 1400, 700);
        scene.getStylesheets().add(getClass().getResource("/style4.css").toExternalForm());

        HBox row1 = new HBox(10);
        row1.getStyleClass().add("header-row");
        Image image1 = new Image(getClass().getResourceAsStream("/image1.2.png"), 70, 70, false, false);
        ImageView imageView1 = new ImageView(image1);
        Label label1 = new Label("Ocupare și șomaj");
        label1.getStyleClass().add("main-title");
        row1.getChildren().addAll(imageView1, label1);

        HBox row2 = new HBox();
        row2.getStyleClass().add("sub-header-row");
        Label label2 = new Label("Indicatori cheie");
        label2.getStyleClass().add("section-title");
        Label label3 = new Label("Actualizat la: [dd.mm.yyyy]");
        label3.getStyleClass().add("date-label");
        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        row2.getChildren().addAll(label2, spacer1, label3);

        GridPane row3 = new GridPane();
        row3.getStyleClass().add("content-row");
        HBox item1 = new HBox();
        HBox.setHgrow(item1, Priority.ALWAYS);
        item1.setMaxWidth(Double.MAX_VALUE);
        item1.setMinHeight(Region.USE_COMPUTED_SIZE);
        item1.getStyleClass().add("card");
        item1.getStyleClass().add("card1");
        Image fileIcon = new Image(getClass().getResourceAsStream("/fileIcon.png"), 50, 60, false, false);
        ImageView imageViewFileIcon1 = new ImageView(fileIcon);
                // === în loc de VBox content1 = new VBox(); ===
        VBox content1 = new VBox(10);
        Label title1 = new Label("Trim. I 2025");
        title1.setWrapText(true);
        title1.getStyleClass().add("card-title");

        // === Creăm tabelul ===
        TableView<Indicator> table = new TableView<>();

        TableColumn<Indicator, String> colIndicator = new TableColumn<>("Indicator");
        colIndicator.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDenumire()));
        colIndicator.setPrefWidth(250);

        TableColumn<Indicator, String> colValoare = new TableColumn<>("Valoare");
        colValoare.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValoare()));
        colValoare.setPrefWidth(100);

        table.getColumns().addAll(colIndicator, colValoare);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // === Citim datele din CSV ===
        ObservableList<Indicator> data = FXCollections.observableArrayList();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream("/csv_files/ocupare_somaj1.csv"),
                        java.nio.charset.StandardCharsets.UTF_8))) {

            String lineCsv;
            while ((lineCsv = br.readLine()) != null) {
                lineCsv = lineCsv.trim();
                if (lineCsv.isEmpty()) continue;

                int lastComma = Math.max(lineCsv.lastIndexOf(','), lineCsv.lastIndexOf(';'));
                if (lastComma != -1) {
                    String denumire = lineCsv.substring(0, lastComma).replace("\"", "").trim();
                    String valoare = lineCsv.substring(lastComma + 1).replace("\"", "").trim();
                    data.add(new Indicator(denumire, valoare));
                } else {
                    System.out.println("⚠️ Linie ignorată: " + lineCsv);
                }
            }
            System.out.println("✅ Am încărcat " + data.size() + " rânduri din CSV.");
        } catch (Exception e) {
            e.printStackTrace();
        }



        table.setItems(data);
        table.setFixedCellSize(25);

        // actualizează automat înălțimea doar dacă există rânduri
        table.prefHeightProperty().bind(
            Bindings.when(Bindings.isEmpty(table.getItems()))
                    .then(100) // înălțime minimă când nu sunt date
                    .otherwise(
                        table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.01))
                    )
        );
        // === Linie separator opțională ===
        Region line = new Region();
        line.setMinHeight(1);
        line.setMaxWidth(Double.MAX_VALUE);
        line.setStyle("-fx-background-color: #d6d6d6;");

        // === Adăugăm în content1 ===
        content1.getChildren().addAll(title1, table, line);
        item1.getChildren().addAll(imageViewFileIcon1, content1);




        HBox item2 = new HBox();
        HBox.setHgrow(item2, Priority.ALWAYS);
        item2.setMaxWidth(Double.MAX_VALUE);
        item2.setMinHeight(Region.USE_COMPUTED_SIZE);
        item2.getStyleClass().add("card");
        item2.getStyleClass().add("card2");
        ImageView imageViewFileIcon2 = new ImageView(fileIcon);
        VBox content2 = new VBox(10);
        Label title2 = new Label("Trim. I 2025");
        title2.setWrapText(true);
        title2.getStyleClass().add("card-title");

        // === Creăm al doilea tabel ===
        TableView<Indicator> table2 = new TableView<>();

        TableColumn<Indicator, String> colIndicator2 = new TableColumn<>("Indicator");
        colIndicator2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDenumire()));
        colIndicator2.setPrefWidth(250);

        TableColumn<Indicator, String> colValoare2 = new TableColumn<>("Valoare");
        colValoare2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValoare()));
        colValoare2.setPrefWidth(100);

        table2.getColumns().addAll(colIndicator2, colValoare2);
        table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // === Citim datele din al doilea CSV ===
        ObservableList<Indicator> data2 = FXCollections.observableArrayList();

        try (BufferedReader br2 = new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream("/csv_files/ocupare_somaj2.csv"),
                        java.nio.charset.StandardCharsets.UTF_8))) {

            String lineCsv2;
            while ((lineCsv2 = br2.readLine()) != null) {
                lineCsv2 = lineCsv2.trim();
                if (lineCsv2.isEmpty()) continue;

                int lastComma = Math.max(lineCsv2.lastIndexOf(','), lineCsv2.lastIndexOf(';'));
                if (lastComma != -1) {
                    String denumire = lineCsv2.substring(0, lastComma).replace("\"", "").trim();
                    String valoare = lineCsv2.substring(lastComma + 1).replace("\"", "").trim();
                    data2.add(new Indicator(denumire, valoare));
                } else {
                    System.out.println("⚠️ Linie ignorată în CSV2: " + lineCsv2);
                }
            }
            System.out.println("✅ Am încărcat " + data2.size() + " rânduri din CSV2.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        table2.setItems(data2);
        table2.setFixedCellSize(25);

        // actualizează automat înălțimea doar dacă există rânduri
        table2.prefHeightProperty().bind(
            Bindings.when(Bindings.isEmpty(table2.getItems()))
                    .then(100) // înălțime minimă când nu sunt date
                    .otherwise(
                        table2.fixedCellSizeProperty().multiply(Bindings.size(table2.getItems()).add(1.01))
                    )
        );

        // === Linie separator opțională ===
        Region line2 = new Region();
        line2.setMinHeight(1);
        line2.setMaxWidth(Double.MAX_VALUE);
        line2.setStyle("-fx-background-color: #d6d6d6;");

        // === Adăugăm în content2 ===
        content2.getChildren().addAll(title2, table2, line2);
        item2.getChildren().addAll(imageViewFileIcon2, content2);

        Image goalsImage = new Image(getClass().getResourceAsStream("/goalsImage.png"), 300, 150, false, false);
        ImageView imageViewGoalsImage = new ImageView(goalsImage);
        VBox goalsCard = new VBox(imageViewGoalsImage);
        goalsCard.setMaxWidth(Double.MAX_VALUE);
        goalsCard.getStyleClass().add("card");
        goalsCard.getStyleClass().add("card3");

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(33);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(34);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(33);
        row3.getColumnConstraints().addAll(col1, col2, col3);

        GridPane.setHalignment(item1, HPos.LEFT);
        GridPane.setHalignment(item2, HPos.CENTER);
        GridPane.setHalignment(goalsCard, HPos.RIGHT);
        goalsCard.setAlignment(Pos.CENTER);

        row3.add(item1, 0, 0);
        row3.add(item2, 1, 0);
        row3.add(goalsCard, 2, 0);

        GridPane.setMargin(item1, new Insets(0, 10, 0, 0));
        GridPane.setMargin(item2, new Insets(0, 10, 0, 10));
        GridPane.setMargin(goalsCard, new Insets(0, 0, 0, 10));

        VBox row4 = new VBox(10);
        row4.getStyleClass().add("section-card");
        row4.setFillWidth(true);
        Label title3 = new Label("Despre");
        title3.getStyleClass().add("section-title");
        Text text = new Text("Domeniul cuprind principalele caracteristici ale pieţei muncii cum ar fi forța de muncă, ocuparea, şomajul, inactivitatea, statutul profesional, activităţile economice, ocupaţiile, orele de lucru şi alte variabile privind forța de muncă, corelate și structurate după caracteristici socio-demografice: vârstă, sex, nivel de instruire, stare civilă, mediul de reşedinţă. Sursa datelor este Ancheta forței de muncă (AFM) – cercetarea continuă asupra gospodăriilor populației, cu diseminare trimestrială și anuală a rezultatelor. Ultima revizuire a metodologiei AFM a avut loc în 2024.");
        text.getStyleClass().add("section-text");
        TextFlow textFlow = new TextFlow(text);
        textFlow.prefWidthProperty().bind(row4.widthProperty().subtract(40));
        row4.getChildren().addAll(title3, textFlow);

        root.getChildren().addAll(row1, row2, row3, row4);

        stage.setScene(scene);
        stage.show();
    }
}
