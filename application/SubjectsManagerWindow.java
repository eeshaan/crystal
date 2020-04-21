package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SubjectsManagerWindow {

  static int i = 1;
  
  public static void newWindow(String title) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    GridPane pane = new GridPane();

    pane.setHgap(10);
    pane.setVgap(10);
    TextField textField[] = new TextField[15];
    ColorPicker cp[] = new ColorPicker[15];
    Spinner<Integer> difficulty[] = new Spinner[15];
    Button btn = new Button("Add another class");
    pane.add(btn, 0, 0);
    btn.setOnAction(e -> {
        textField[i] = new TextField();
        cp[i] = new ColorPicker();
        difficulty[i] = new Spinner<>(1, 5, 3, 1);
        
        pane.add(textField[i], 0, i);
        pane.add(cp[i], 5, i);
        pane.add(difficulty[i], 7, i);
        i++;

    });
    
    Scene scene = new Scene(pane, 500, 400);
    scene.getStylesheets().add(AddAssignmentWindow.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS
    
    window.setScene(scene);
    window.setTitle(title);
    window.showAndWait();
  }
}
