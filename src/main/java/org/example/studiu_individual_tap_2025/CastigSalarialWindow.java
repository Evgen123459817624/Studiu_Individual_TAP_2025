package org.example.studiu_individual_tap_2025;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ResourceBundle.Control;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class CastigSalarialWindow {

    public void open() {
        Stage stage = new Stage();
        stage.setTitle("Câștigul salarial și costul forței de muncă");
        stage.setMaximized(true);

        VBox root = new VBox();
        root.getStyleClass().add("root-container");
        Scene scene = new Scene(root, 1400, 700);
        scene.getStylesheets().add(getClass().getResource("/style4.css").toExternalForm());

        HBox row1 = new HBox(10);
        row1.getStyleClass().add("header-row");
        Image image1 = new Image(getClass().getResourceAsStream("/image4.2.png"), 70, 70, false, false);
        ImageView imageView1 = new ImageView(image1);
        Label label1 = new Label("Câștigul salarial și costul forței de muncă");
        label1.getStyleClass().add("main-title");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        javafx.scene.control.Button homeButton = new javafx.scene.control.Button("🏠 Home");
        homeButton.getStyleClass().add("home-button");
        homeButton.setOnAction(e -> {
            stage.close();
        });
        homeButton.getStyleClass().add("custom-button");
        row1.getChildren().addAll(imageView1, label1, spacer, homeButton);

        HBox row2 = new HBox();
        row2.getStyleClass().add("sub-header-row");
        Label label2 = new Label("Indicatori cheie");
        label2.getStyleClass().add("section-title");
        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        row2.getChildren().addAll(label2, spacer1);

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
        VBox content1 = new VBox(10);
        Label title1 = new Label("Câștigul salarial mediu lunar brut, lei");
        title1.setWrapText(true);
        title1.getStyleClass().add("card-title");

        Label label4 = new Label("(la unitățile cu 4 și mai mulți salariați)");

        Region line3 = new Region();
        line3.setMinHeight(1);
        line3.setMaxWidth(Double.MAX_VALUE);
        line3.setStyle("-fx-background-color: #d6d6d6;");

        Label label5 = new Label("Trim. II 2025, lei");
        label5.getStyleClass().add("card-title");

        // === Creăm primul tabel pentru câștig salarial ===
        TableView<Indicator> table1 = new TableView<>();

        TableColumn<Indicator, String> colIndicator1 = new TableColumn<>("Indicator");
        colIndicator1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDenumire()));
        colIndicator1.setPrefWidth(250);

        // Wrap text în celulele coloanei "Indicator"
        colIndicator1.setCellFactory(tc -> {
            TableCell<Indicator, String> cell = new TableCell<>();
            Text text = new Text();
            text.wrappingWidthProperty().bind(colIndicator1.widthProperty().subtract(10));
            text.textProperty().bind(cell.itemProperty());
            cell.setGraphic(text);
            cell.setPrefHeight(Control.TTL_DONT_CACHE); // înălțime automată
            text.wrappingWidthProperty().addListener((obs, oldVal, newVal) -> {
                cell.requestLayout(); // actualizează înălțimea când se schimbă dimensiunea coloanei
            });
            return cell;
        });

        TableColumn<Indicator, String> colValoare1 = new TableColumn<>("Valoare");
        colValoare1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValoare()));
        colValoare1.setPrefWidth(100);

        table1.getColumns().addAll(colIndicator1, colValoare1);
        table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // === Citim datele din primul CSV ===
        ObservableList<Indicator> data1 = FXCollections.observableArrayList();

        try (BufferedReader br1 = new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream("/csv_files/castig_salarial1.csv"),
                        java.nio.charset.StandardCharsets.UTF_8))) {

            String lineCsv1;
            while ((lineCsv1 = br1.readLine()) != null) {
                lineCsv1 = lineCsv1.trim();
                if (lineCsv1.isEmpty()) continue;

                int lastComma = Math.max(lineCsv1.lastIndexOf(','), lineCsv1.lastIndexOf(';'));
                if (lastComma != -1) {
                    String denumire = lineCsv1.substring(0, lastComma).replace("\"", "").trim();
                    String valoare = lineCsv1.substring(lastComma + 1).replace("\"", "").trim();
                    data1.add(new Indicator(denumire, valoare));
                } else {
                    System.out.println("Linie ignorată în castig_salarial1.csv: " + lineCsv1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table1.setItems(data1);

        // Ajustăm înălțimea tabelului în funcție de numărul de rânduri
        double rowHeight = 50;
        table1.prefHeightProperty().bind(
            Bindings.createDoubleBinding(
                () -> rowHeight * data1.size() + 30,
                data1
            )
        );


        Region line = new Region();
        line.setMinHeight(1);
        line.setMaxWidth(Double.MAX_VALUE);
        line.setStyle("-fx-background-color: #d6d6d6;");

        content1.getChildren().addAll(title1, label4, line3, label5, table1, line);
        item1.getChildren().addAll(imageViewFileIcon1, content1);

        HBox item2 = new HBox();
        HBox.setHgrow(item2, Priority.ALWAYS);
        item2.setMaxWidth(Double.MAX_VALUE);
        item2.setMinHeight(Region.USE_COMPUTED_SIZE);
        item2.getStyleClass().add("card");
        item2.getStyleClass().add("card2");
        ImageView imageViewFileIcon2 = new ImageView(fileIcon);
        VBox content2 = new VBox(10);
        Label title2 = new Label("Câștigul salarial mediu lunar brut, lei");
        title2.setWrapText(true);
        title2.getStyleClass().add("card-title");

        Label label6 = new Label("(la unitățile cu 1 și mai mulți salariați)");

        Region line4 = new Region();
        line4.setMinHeight(1);
        line4.setMaxWidth(Double.MAX_VALUE);
        line4.setStyle("-fx-background-color: #d6d6d6;");

        Label label7 = new Label("Anul 2024, lei");
        label7.getStyleClass().add("card-title");

        // === Creăm al doilea tabel pentru câștig salarial ===
        TableView<Indicator> table2 = new TableView<>();

        TableColumn<Indicator, String> colIndicator2 = new TableColumn<>("Indicator");
        colIndicator2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDenumire()));
        colIndicator2.setPrefWidth(250);

        // Wrap text în celulele coloanei "Indicator"
        colIndicator2.setCellFactory(tc -> {
            TableCell<Indicator, String> cell = new TableCell<>();
            Text text = new Text();
            text.wrappingWidthProperty().bind(colIndicator2.widthProperty().subtract(10));
            text.textProperty().bind(cell.itemProperty());
            cell.setGraphic(text);
            cell.setPrefHeight(Control.TTL_DONT_CACHE); // înălțime automată
            text.wrappingWidthProperty().addListener((obs, oldVal, newVal) -> {
                cell.requestLayout(); // actualizează înălțimea când se schimbă dimensiunea coloanei
            });
            return cell;
        });

        TableColumn<Indicator, String> colValoare2 = new TableColumn<>("Valoare");
        colValoare2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValoare()));
        colValoare2.setPrefWidth(100);

        table2.getColumns().addAll(colIndicator2, colValoare2);
        table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // === Citim datele din al doilea CSV ===
        ObservableList<Indicator> data2 = FXCollections.observableArrayList();

        try (BufferedReader br2 = new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream("/csv_files/castig_salarial2.csv"),
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
                    System.out.println("Linie ignorată în castig_salarial2.csv: " + lineCsv2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table2.setItems(data2);

        // Ajustăm înălțimea tabelului în funcție de numărul de rânduri
        double rowHeight2 = 50;
        table2.prefHeightProperty().bind(
            Bindings.createDoubleBinding(
                () -> rowHeight2 * data2.size() + 30,
                data2
            )
        );


        Region line2 = new Region();
        line2.setMinHeight(1);
        line2.setMaxWidth(Double.MAX_VALUE);
        line2.setStyle("-fx-background-color: #d6d6d6;");

        content2.getChildren().addAll(title2, label6, line4, label7, table2, line2);
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
        Text text = new Text("Domeniul conține date trimestriale și anuale cu privire la câștigul salarial mediu și costul forței de muncă. Datele trimestriale sunt elaborate în baza cercetării ”Câștigurile salariale” și cuprind unitățile economice cu 4 și mai mulți salariați și toate instituțiile bugetare, indiferent de numărul de salariați. Rezultatele cercetării sunt dezagregate pe activități economice și sectoare (bugetar și real)." +
                "Datele anuale sunt elaborate în baza cercetării ”Câștigurile salariale și costul forței de muncă” care, spre deosebire de cercetarea trimestrială, cuprinde unitățile cu unu și mai mulți salariați și conține informații cu privire la câștigul salarial mediu brut și net, costul forței de muncă, structura câștigului salarial și al costului forței de muncă. Datele sunt dezagregate pe activități economice, forme de proprietate, sectoare, sexe și în profil teritorial.");
        text.getStyleClass().add("section-text");
        TextFlow textFlow = new TextFlow(text);
        textFlow.prefWidthProperty().bind(row4.widthProperty().subtract(40));
        row4.getChildren().addAll(title3, textFlow);

        // === Creăm graficul de bare pentru comparația 2024 vs 2025 ===
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Sector");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Câștig salarial mediu (lei)");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Comparație câștig salarial mediu — 2024 vs 2025");

        // === Extragem valorile din tabele ===
        double bugetar2025 = 0;
        double real2025 = 0;
        double bugetar2024 = 0;
        double real2024 = 0;

        try {
            
            bugetar2025 = Double.parseDouble(data1.get(1).getValoare().replace(",", "."));
            real2025 = Double.parseDouble(data1.get(2).getValoare().replace(",", "."));
            bugetar2024 = Double.parseDouble(data2.get(1).getValoare().replace(",", "."));
            real2024 = Double.parseDouble(data2.get(2).getValoare().replace(",", "."));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // === Seturile de date pentru fiecare an ===
        XYChart.Series<String, Number> series2025 = new XYChart.Series<>();
        series2025.setName("2025");
        series2025.getData().add(new XYChart.Data<>("Sector bugetar", bugetar2025));
        series2025.getData().add(new XYChart.Data<>("Sector real", real2025));

        XYChart.Series<String, Number> series2024 = new XYChart.Series<>();
        series2024.setName("2024");
        series2024.getData().add(new XYChart.Data<>("Sector bugetar", bugetar2024));
        series2024.getData().add(new XYChart.Data<>("Sector real", real2024));

        barChart.getData().addAll(series2025, series2024);

        // === Stilizare pentru aspect modern ===
        barChart.setCategoryGap(25);
        barChart.setBarGap(5);
        barChart.setLegendVisible(true);
        barChart.setAnimated(false);
        barChart.setStyle("-fx-background-color: transparent;");

        // === Afișăm valorile pe bare ===
        for (XYChart.Series<String, Number> series : barChart.getData()) {
            for (XYChart.Data<String, Number> data : series.getData()) {
                Label valueLabel = new Label(String.format("%.0f", data.getYValue()));
                valueLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
                StackPane bar = (StackPane) data.getNode();
                bar.getChildren().add(valueLabel);
                StackPane.setAlignment(valueLabel, Pos.TOP_CENTER);
                StackPane.setMargin(valueLabel, new Insets(0, 0, 10, 0));
            }
        }

        // === Adăugăm graficul într-un card (HBox sau VBox) ===
        barChart.getStyleClass().add("salary-chart");
        HBox chartCard = new HBox();
        chartCard.setAlignment(Pos.CENTER);
        chartCard.getStyleClass().add("card");
        chartCard.getChildren().add(barChart);
        // Adăugăm acest rând în GridPane, sub cardurile principale
        row3.add(chartCard, 0, 1, 3, 1); // ocupă 3 coloane
        GridPane.setMargin(chartCard, new Insets(20, 0, 0, 0));



        root.getChildren().addAll(row1, row2, row3, row4);

        stage.setScene(scene);
        stage.show();
    }
}
