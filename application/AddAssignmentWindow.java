package application;

import javafx.geometry.Insets;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    textInput.setSpacing(5);

    Text titleText = new Text(title);
    titleText.setId("h2");
    textInput.getChildren().add(titleText);

    // name, subject, dueTime, dueDate
    Label assName = new Label("Name:");
    TextField nameField = new TextField();
    nameField.getText();
    HBox nameBox = new HBox();
    textInput.getChildren().addAll(assName, nameField);

    Label assClass = new Label("Class:");
    ComboBox classField = new ComboBox();
    HBox subBox = new HBox();
    textInput.getChildren().addAll(assClass, classField);

    Label assTime = new Label("Due Time:");
    TextField timeField = new TextField();
    HBox timeBox = new HBox();
    textInput.getChildren().addAll(assTime, timeField);

    Label assDate = new Label("Due Date:");
    DatePicker dateField = new DatePicker();
    HBox dateBox = new HBox();
    textInput.getChildren().addAll(assDate, dateField);

    Button submit = new Button("Submit");
    submit.setId("bigButton");
    submit.setOnAction(e -> {
      AddAssignmentWindow.addAssignment(nameField.getText(),
        classField.getValue().toString(), timeField.getText(), dateField.getValue().toString());

     
        JSONArray assignmentsJSONArray = Main.getJSONAssignments();

        JSONObject newAssignment = new JSONObject();
        newAssignment.put("assignmentName", nameField.getText());
        newAssignment.put("class", classField.getValue().toString());
        // newAssignment.put("difficulty", "1");
        // newAssignment.put("startDate", "");
        newAssignment.put("dueDate", dateField.getValue().toString());
        newAssignment.put("dueTime", timeField.getText());
        newAssignment.put("completed", false);
  
        assignmentsJSONArray.add(newAssignment);
        Main.setJSONAssignments(assignmentsJSONArray);
  
        
  
        // also add assignment to the data structures that we choose   
    });

    HBox submitHolder = new HBox();
    submitHolder.setPadding(new Insets(20, 0, 0, 0));
    submitHolder.getChildren().add(submit);
    textInput.getChildren().addAll(submitHolder);

    Scene vScene = new Scene(textInput, 500, 500);
    vScene.getStylesheets()
    .add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS

    window.setScene(vScene);
    window.show();
  }

  public static void addAssignment(String nameField, String classField, String timeField,
      String dateField) {
    Main.createAssignmentBox(nameField, classField, timeField, dateField);
  }
}
