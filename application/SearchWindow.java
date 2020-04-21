package application;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchWindow {

  public static void newWindow(String title) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    Pane pane = new Pane();

    Scene scene = new Scene(pane, 500, 500);
    scene.getStylesheets().add(AddAssignmentWindow.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS
    
    window.setScene(scene);
    window.setTitle(title);
    window.showAndWait();
  }
}
