package org.example.studiu_individual_tap_2025;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private HBox createRow(String imageAddress, String buttonString, Runnable action) {
        HBox row = new HBox(5);
        row.getStyleClass().add("hbox-row");

        Image image = new Image(getClass().getResourceAsStream(imageAddress), 50, 50, false, false);
        ImageView imageView = new ImageView(image);
        imageView.getStyleClass().add("image-view");

        Button button = new Button(buttonString);
        button.getStyleClass().add("custom-button");

        row.getChildren().addAll(imageView, button);

        button.setOnAction(e -> action.run());

        return row;
    }

    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setTitle("Forţa de muncă și câștigul salarial");

        HBox row1 = createRow("/image1.png", "Ocupare și șomaj", () -> {
            OcupareSomajWindow newWindow = new OcupareSomajWindow();
            newWindow.open();
        });

        HBox row2 = createRow("/image2.png", "Numărul salariaților și locurile vacante", () -> {
            NrSalariatiWindow newWindow = new NrSalariatiWindow();
            newWindow.open();
        });

        HBox row3 = createRow("/image3.png", "Formarea profesională continuă", () -> {
            FormareProfesionalaWindow newWindow = new FormareProfesionalaWindow();
            newWindow.open();
        });

        HBox row4 = createRow("/image4.png", "Câștigul salarial și costul forței de muncă", () -> {
            CastigSalarialWindow newWindow = new CastigSalarialWindow();
            newWindow.open();
        });

        HBox row5 = createRow("/image5.png", "Accidentele de muncă", () -> {
            AccidenteMuncaWindow newWindow = new AccidenteMuncaWindow();
            newWindow.open();
        });

        root.getChildren().addAll(row1, row2, row3, row4, row5);

        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}