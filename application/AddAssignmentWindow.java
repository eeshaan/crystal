package application;

import javafx.geometry.Insets;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;

public class AddAssignmentWindow {

  public static void newWindow(String title) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    Pane pane = new Pane();

    Scene scene = new Scene(pane, 500, 500);
    window.setScene(scene);
    window.setTitle(title);

    Text titleText = new Text(title);
    titleText.setId("h2");

    // name, subject, dueTime, dueDate
    Label assignmentName = new Label("Name:");
    TextField nameField = new TextField();
    nameField.getText();
    HBox nameBox = new HBox();

    Label assignmentClass = new Label("Class:");
    ComboBox<String> classField = new ComboBox<>();
    // TODO: populate this ComboBox with Class names
    
    
    HBox subBox = new HBox();

    Label assignmentTime = new Label("Due Time:");
    Spinner<LocalTime> spinner = new Spinner<>();
    SpinnerValueFactory<LocalTime> timeField = new SpinnerValueFactory<LocalTime>() {
      {
        setConverter(new LocalTimeStringConverter(DateTimeFormatter.ofPattern("hh:mm a"), null));
      }

      @Override
      public void decrement(int steps) {
        if (getValue() == null) {
          setValue(LocalTime.now());
        } else {
          LocalTime value = (LocalTime) getValue();
          setValue(value.minusMinutes(steps));
        }
      }

      @Override
      public void increment(int steps) {
        if (getValue() == null) {
          setValue(LocalTime.now());
        } else {
          LocalTime value = (LocalTime) getValue();
          setValue(value.plusMinutes(steps));
        }
      }
    };
    spinner.setValueFactory(timeField);
    spinner.setEditable(true);
    HBox timeBox = new HBox();
    timeBox.getChildren().add(spinner);

    Label assignmentDate = new Label("Due Date:");
    DatePicker dateField = new DatePicker();

    Label assignmentDifficulty = new Label("Assignment Difficulty");
    Spinner<Integer> difficulty = new Spinner<>(1, 5, 1);
    difficulty.setEditable(true);



    Button submit = new Button("Submit");
    submit.setId("bigButton");
    submit.setOnAction(e -> {
      AddAssignmentWindow.addAssignment(nameField.getText(), title,
          timeField.getValue().format(DateTimeFormatter.ofPattern("hh:mm a")),
          dateField.getValue().format(DateTimeFormatter.ofPattern("EEEE, MMMM d")));


      JSONArray assignmentsJSONArray = Main.getJSONAssignments();

      JSONObject newAssignment = new JSONObject();
      newAssignment.put("assignmentName", nameField.getText());
      newAssignment.put("class", classField.getValue().toString());
      newAssignment.put("difficulty", difficulty.getValue());
      // newAssignment.put("startDate", "");
      newAssignment.put("dueDate", dateField.getValue().toString());
      newAssignment.put("dueTime", timeField.getValue().toString());
      newAssignment.put("completed", false);

      assignmentsJSONArray.add(newAssignment);
      Main.setJSONAssignments(assignmentsJSONArray);



      // also add assignment to the data structures that we choose
    });

    HBox submitHolder = new HBox(submit);
    submitHolder.setPadding(new Insets(20, 0, 0, 0));

    VBox formBox =
        new VBox(titleText, assignmentName, nameField, assignmentClass, classField, assignmentTime,
            timeBox, assignmentDate, dateField, assignmentDifficulty, difficulty, submitHolder);
    formBox.setPadding(new Insets(20, 20, 20, 20));
    formBox.setSpacing(5);

    Scene vScene = new Scene(formBox, 500, 500);
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
