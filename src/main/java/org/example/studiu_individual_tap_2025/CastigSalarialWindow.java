package org.example.studiu_individual_tap_2025;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CastigSalarialWindow {
    public void open() {
        Stage stage = new Stage();
        stage.setTitle("Câștigul salarial și costul forței de muncă");
        stage.setMaximized(true);

        StackPane root = new StackPane(new Button("Button in New Window"));
        Scene scene = new Scene(root, 300, 300);

        stage.setScene(scene);
        stage.show();
    }
}
