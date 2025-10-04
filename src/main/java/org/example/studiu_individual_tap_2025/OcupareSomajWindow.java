package org.example.studiu_individual_tap_2025;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.text.Text;


public class OcupareSomajWindow {

    private HBox createDataRow(String label, String value) {
        Label lbl = new Label(label);
        Label val = new Label(value);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setPrefWidth(30);
        HBox row = new HBox(lbl, spacer, val);
        row.getStyleClass().add("data-row");
        return row;
    }


    public void open() {
        Stage stage = new Stage();
        stage.setTitle("Ocupare și șomaj");
        stage.setMaximized(true);

        VBox root = new VBox();
        root.getStyleClass().add("root-container");
        Scene scene = new Scene(root, 1400, 700);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

            HBox row1 = new HBox(10);
            row1.getStyleClass().add("header-row");
                Image image1 = new Image(getClass().getResourceAsStream("/image1.2.png"), 50, 50, false, false);
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

            HBox row3 = new HBox(10);
            row3.getStyleClass().add("content-row");
            row3.setFillHeight(true);
                HBox item1 = new HBox();
                item1.getStyleClass().add("card");
                    Image fileIcon = new Image(getClass().getResourceAsStream("/fileIcon.png"), 50, 60, false, false);
                    ImageView imageViewFileIcon1 = new ImageView(fileIcon);
                    VBox content1 = new VBox();
                        Label title1 = new Label("Trim. I 2025");
                        title1.getStyleClass().add("card-title");
                    content1.getChildren().addAll(
                        title1,
                        createDataRow("Forța de muncă, mii", "[0,00]"),
                        createDataRow("Rata de participare, %", "[0,00]"),
                        createDataRow("Populaţie ocupată, mii", "[0,00]"),
                        createDataRow("Rata de ocupare, %", "[0,00]"),
                        createDataRow("Şomeri BIM, mii", "[0,00]"),
                        createDataRow("Rata şomajului BIM, %", "[0,00]")
                    );
                item1.getChildren().addAll(imageViewFileIcon1, content1);

                HBox item2 = new HBox();
                item2.getStyleClass().add("card");
                    ImageView imageViewFileIcon2 = new ImageView(fileIcon);
                    VBox content2 = new VBox();
                        Label title2 = new Label("Trim. I 2025");
                        title2.getStyleClass().add("card-title");
                    content2.getChildren().addAll(
                            title2,
                            createDataRow("Tinerii NEET, 15-24 ani, mii", "[0,00]"),
                            createDataRow("Rata tinerilor NEET, 15-24 ani, %", "[0,00]"),
                            createDataRow("Tinerii NEET, 15-29 ani, mii", "[0,00]"),
                            createDataRow("Rata tinerilor NEET, 15-29 ani, %", "[0,00]"),
                            createDataRow("Tinerii NEET, 15-34 ani, mii", "[0,00]"),
                            createDataRow("Rata tinerilor NEET, 15-34 ani, %", "[0,00]")
                    );
                item2.getChildren().addAll(imageViewFileIcon2, content2);

                Image goalsImage = new Image(getClass().getResourceAsStream("/goalsImage.png"), 300, 150, false, false);
                ImageView imageViewGoalsImage = new ImageView(goalsImage);
                VBox goalsCard = new VBox(imageViewGoalsImage);
                goalsCard.getStyleClass().add("card");

                Region spacerLeft = new Region();
                Region spacerRight = new Region();
                HBox.setHgrow(spacerLeft, Priority.ALWAYS);
                HBox.setHgrow(spacerRight, Priority.ALWAYS);

            row3.getChildren().addAll(item1, spacerLeft, item2, spacerRight, goalsCard);

            VBox row4 = new VBox(10);
            row4.getStyleClass().add("card");
            row4.setFillWidth(true);
                Label title3 = new Label("Despre");
                title3.getStyleClass().add("section-title");
                Text text = new Text(
                        "Domeniul cuprinde principalele caracteristici ale pieţei muncii cum ar fi forța de muncă, " +
                                "ocuparea, şomajul, inactivitatea, statutul profesional, activităţile economice, ocupaţiile, " +
                                "orele de lucru şi alte variabile privind forța de muncă, corelate și structurate după " +
                                "caracteristici socio-demografice: vârstă, sex, nivel de instruire, stare civilă, mediul de reşedinţă. " +
                                "Sursa datelor este Ancheta forței de muncă (AFM) – cercetarea continuă asupra gospodăriilor populației, " +
                                "cu diseminare trimestrială și anuală a rezultatelor. Ultima revizuire a metodologiei AFM a avut loc în 2024."
                );
                TextFlow textFlow = new TextFlow(text);
                textFlow.prefWidthProperty().bind(row4.widthProperty().subtract(40));
            row4.getChildren().addAll(title3, textFlow);

        root.getChildren().addAll(row1, row2, row3, row4);

        stage.setScene(scene);
        stage.show();
    }
}
