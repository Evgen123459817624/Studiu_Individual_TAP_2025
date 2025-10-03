package org.example.studiu_individual_tap_2025;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class OcupareSomajWindow {

    private HBox createDataRow(String label, String value) {
        Label lbl = new Label(label);
        Label val = new Label(value);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setPrefWidth(50);
        HBox row = new HBox(lbl, spacer, val);
        row.getStyleClass().add("data-row");
        return row;
    }


    public void open() {
        Stage stage = new Stage();
        stage.setTitle("Ocupare și șomaj");
        stage.setMaximized(true);

        VBox root = new VBox();
        Scene scene = new Scene(root, 300, 300);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

            HBox row1 = new HBox(10);
                Image image1 = new Image(getClass().getResourceAsStream("/image1.png"), 50, 50, false, false);
                ImageView imageView1 = new ImageView(image1);
                Label label1 = new Label("Ocupare și șomaj");
            row1.getChildren().addAll(imageView1, label1);

            HBox row2 = new HBox();
                Label label2 = new Label("Indicatori cheie");
                Label label3 = new Label("Actualizat la: [dd.mm.yyyy]");
            Region spacer1 = new Region();
            HBox.setHgrow(spacer1, Priority.ALWAYS);
            row2.getChildren().addAll(label2, spacer1, label3);

            HBox row3 = new HBox();
                HBox item1 = new HBox();
                    Image fileIcon = new Image(getClass().getResourceAsStream("/fileIcon.png"), 50, 100, false, false);
                    ImageView imageViewFileIcon1 = new ImageView(fileIcon);
                    VBox content1 = new VBox();
                        Label title1 = new Label("Trim. I 2025");
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
            row3.getChildren().addAll(item1);

        root.getChildren().addAll(row1, row2, row3);

        stage.setScene(scene);
        stage.show();
    }
}
