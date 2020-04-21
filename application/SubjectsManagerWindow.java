package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SubjectsManagerWindow {

  private static final int WINDOW_WIDTH = 680;
  private static final int WINDOW_HEIGHT = 600;
  
  static int i = 1;
  
  public static void newWindow(String title) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(20, 20, 20, 20));
    
    HBox headerBox = new HBox();
    Text header = new Text("Welcome to Crystal!");
    headerBox.getChildren().add(header);
    
    root.setTop(headerBox);
    

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
        difficulty[i].getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        
        pane.add(new Text("Class Name:"), 0, (i*2)-1);
        pane.add(textField[i], 0, i*2);
        pane.add(new Text("Color:"), 5, (i*2)-1);
        pane.add(cp[i], 5, i*2);
        pane.add(new Text("Difficulty (1-5):"), 7, (i*2)-1);
        pane.add(difficulty[i], 7, i*2);
        i++;

    });
    
    root.setCenter(pane);
    
    HBox bottom = new HBox();
    Button submit = new Button("Submit classes and launch");
    Button skip = new Button("Skip adding classes and launch");
    bottom.getChildren().addAll(submit, skip);
    
    root.setBottom(bottom);
    
    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    scene.getStylesheets().add(AddAssignmentWindow.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS
    
    window.setScene(scene);
    window.setTitle(title);
    window.showAndWait();
  }
}
