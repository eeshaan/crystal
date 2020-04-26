package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddAssignmentWindow {

  public static void newWindow(String title) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    Pane pane = new Pane();

    Scene scene = new Scene(pane, 500, 500);
    window.setScene(scene);
    window.setTitle(title);
    // window.showAndWait();
    
    VBox textInput = new VBox();
    textInput.setPadding(new Insets(20, 20, 20, 20));
    
    Text titleText = new Text(title);
    titleText.setId("h2");
    textInput.getChildren().add(titleText);
    
    // name, subject, dueTime, dueDate
    Label assName = new Label("Name:");
    TextField nameField = new TextField ();
    nameField.getText();
    HBox nameBox = new HBox();
    textInput.getChildren().addAll(assName, nameField);
    
    Label assSub = new Label("Subject:");
    TextField subField = new TextField ();
    HBox subBox = new HBox();
    textInput.getChildren().addAll(assSub, subField);
    
    Label assTime = new Label("DueTime:");
    TextField timeField = new TextField ();
    HBox timeBox = new HBox();
    textInput.getChildren().addAll(assTime, timeField);
    
    Label assDate = new Label("DueDate:");
    TextField dateField = new TextField ();
    HBox dateBox = new HBox();
    textInput.getChildren().addAll(assDate, dateField);
    
    Button finished = new Button("Submit");
    finished.setId("bigButton");
    finished.setOnAction(e -> AddAssignmentWindow.addAssignment(nameField, subField, timeField, dateField));
    textInput.getChildren().addAll(finished);
    
    Scene vScene = new Scene(textInput, 500, 500);
    vScene.getStylesheets().add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS

    
    window.setScene(vScene);
    window.show();
  }
  
  public static void addAssignment(TextField nameField, TextField subField, TextField timeField, TextField dateField) {
    String name = nameField.getText();
    String subject = subField.getText();
    String time = timeField.getText();
    String date = dateField.getText();
    
    Main.createAssignmentBox(name, subject, time, date);
  }
}