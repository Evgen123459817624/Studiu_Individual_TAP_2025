package org.example.studiu_individual_tap_2025;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class NrSalariatiWindow {
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
        stage.setTitle("Numărul salariaților și locurile vacante");
        stage.setMaximized(true);

        VBox root = new VBox();
        root.getStyleClass().add("root-container");
        Scene scene = new Scene(root, 1400, 700);
        scene.getStylesheets().add(getClass().getResource("/style4.css").toExternalForm());

        HBox row1 = new HBox(10);
        row1.getStyleClass().add("header-row");
        Image image1 = new Image(getClass().getResourceAsStream("/image2.2.png"), 70, 70, false, false);
        ImageView imageView1 = new ImageView(image1);
        Label label1 = new Label("Numărul salariaților și locurile vacante");
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
        VBox content1 = new VBox();
        Label title1 = new Label("Numărul salariaților (la unitățile cu 1 și mai mulți salariați)\n" +
                "Anul 2024");
        title1.setWrapText(true);
        title1.getStyleClass().add("card-title");

        Region line = new Region();
        line.setMinHeight(1);
        line.setMaxWidth(Double.MAX_VALUE);
        line.setStyle("-fx-background-color: #d6d6d6;");

        content1.getChildren().addAll(
                title1,
                createDataRow(content1, "Numărul salariaților la sfârșitul anului (inclusiv salariați cu contract de muncă suspendat), persoane", "[0,00]"),
                createDataRow(content1, "Numărul mediu al salariaților (în echivalent timp complet), persoane", "[0,00]"),
                createDataRow(content1, "Ponderea femeilor în numărul mediu al salariaților, %", "[0,00]"),
                createDataRow(content1, "Ponderea bărbaților în numărul mediu al salariaților, %", "[0,00]"),
                line
        );
        item1.getChildren().addAll(imageViewFileIcon1, content1);

        HBox item2 = new HBox();
        HBox.setHgrow(item2, Priority.ALWAYS);
        item2.setMaxWidth(Double.MAX_VALUE);
        item2.setMinHeight(Region.USE_COMPUTED_SIZE);
        item2.getStyleClass().add("card");
        item2.getStyleClass().add("card2");
        ImageView imageViewFileIcon2 = new ImageView(fileIcon);
        VBox content2 = new VBox();
        Label title2 = new Label("Locuri de muncă vacante (la unitățile cu 4 și mai mulți salariați)\n" +
                "Trimestrul II 2025");
        title2.setWrapText(true);
        title2.getStyleClass().add("card-title");

        Region line2 = new Region();
        line2.setMinHeight(1);
        line2.setMaxWidth(Double.MAX_VALUE);
        line2.setStyle("-fx-background-color: #d6d6d6;");

        content2.getChildren().addAll(
                title2,
                createDataRow(content1, "Locuri de muncă vacante la sfârșitul trimestrului, unități", "[0,00]"),
                createDataRow(content1, "Rata locurilor de muncă vacante (raportul dintre numărul locurilor de muncă vacante și numărul total al locurilor de muncă), %\t", "[0,00]"),
                line2
        );
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
        Text text = new Text("Domeniul conține date cu privire la numărul salariaților dezagregat pe activități economice, forme de proprietate, sectoare, sexe și în profil teritorial. \n" +
                "Datele cu privire la numărul salariaților sunt elaborate în baza cercetării anuale ”Câștigurile salariale și costul forței de muncă” care include unitățile economice și instituțiile bugetare care angajează cel puțin un salariat.  \n" +
                "Datele cu privire la indicii numărului mediu al salariaților sunt elaborate în baza cercetării trimestriale ”Câștigurile salariale” care include unitățile economice cu 4 și mai mulți salariați și toate instituțiile bugetare, indiferent de numărul de salariați.");
        text.getStyleClass().add("section-text");
        TextFlow textFlow = new TextFlow(text);
        textFlow.prefWidthProperty().bind(row4.widthProperty().subtract(40));
        row4.getChildren().addAll(title3, textFlow);

        root.getChildren().addAll(row1, row2, row3, row4);

        stage.setScene(scene);
        stage.show();
    }
}
