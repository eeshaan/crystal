package application;

import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WelcomeWindow {

  public static void newWindow(String title) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    Text welcomeText = new Text(title);
    welcomeText.setTextAlignment(TextAlignment.CENTER);
    welcomeText.setId("h1");

    HBox welcomeHeader = new HBox();
    welcomeHeader.setAlignment(Pos.CENTER);
    welcomeHeader.getChildren().add(welcomeText);

    Button load = new Button("I've used Crystal before. (Load saved state from JSON.)");

    HBox loadButtonHolder = new HBox();
    loadButtonHolder.setAlignment(Pos.CENTER);
    loadButtonHolder.getChildren().add(load);



    FileChooser fileChooser = new FileChooser();

    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));

    load.setOnAction(e -> {
      File selectedFile = fileChooser.showOpenDialog(window);
    });


    Button addClasses =
        new Button("This is my first time. (Start adding classes and assignments from scratch.)");

    HBox addButtonHolder = new HBox();
    addButtonHolder.setAlignment(Pos.CENTER);
    addButtonHolder.getChildren().add(addClasses);

    VBox pane = new VBox();
    pane.setPadding(new Insets(20, 20, 20, 20));

    pane.getChildren().addAll(welcomeHeader, loadButtonHolder, addButtonHolder);

    Scene scene = new Scene(pane, 500, 500);
    scene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS

    window.setScene(scene);
    window.setTitle(title);
    window.showAndWait();
  }
}
