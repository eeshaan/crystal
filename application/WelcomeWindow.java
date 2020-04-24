package application;

import java.io.File;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WelcomeWindow {

  
  private static final int WINDOW_WIDTH = 680;
  private static final int WINDOW_HEIGHT = 200;
  
  
  public static void newWindow(String title) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    Text welcomeText = new Text(title);
    welcomeText.setId("h2");

    HBox welcomeHeader = new HBox();
    welcomeHeader.getChildren().add(welcomeText);

    Text subText = new Text("the intelligent assignment tracker");
    subText.setId("subText");
    
    HBox subHeader = new HBox();
    subHeader.getChildren().add(subText);
    
    Button load = new Button("I've used Crystal before. (Load saved state from JSON.)");
    load.setId("bigButton");

    HBox loadButtonHolder = new HBox();
    loadButtonHolder.setPadding(new Insets(12.5, 0, 0, 0));
    loadButtonHolder.getChildren().add(load);


    FileChooser fileChooser = new FileChooser();

    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));

    load.setOnAction(e -> {
      File selectedFile = fileChooser.showOpenDialog(window);
    });


    Button addClasses =
        new Button("This is my first time. (Start adding classes and assignments from scratch.)");
    addClasses.setId("bigButton");
    
    HBox addButtonHolder = new HBox();
    addButtonHolder.setPadding(new Insets(10, 0, 0, 0));
    addButtonHolder.getChildren().add(addClasses);

    VBox pane = new VBox();
    pane.setPadding(new Insets(20, 20, 20, 20));

    pane.getChildren().addAll(welcomeHeader, subHeader, loadButtonHolder, addButtonHolder);

    Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
    scene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS

    window.setScene(scene);
    window.setTitle(title);
    window.showAndWait();
  }
}
