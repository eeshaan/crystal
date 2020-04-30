package application;

import javafx.geometry.Insets;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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

    Iterator<String> iterator = Main.getClasses().iterator();
    while (iterator.hasNext()) {
      classField.getItems().add(iterator.next());
    }

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

    Label assignmentStartDate = new Label("Start Date:");
    DatePicker startDateField = new DatePicker();

    Label assignmentDueDate = new Label("Due Date:");
    DatePicker dueDateField = new DatePicker();

    Label assignmentDifficulty = new Label("Assignment Difficulty");
    Spinner<Integer> difficulty = new Spinner<>(1, 5, 1);
    difficulty.setEditable(true);



    Button submit = new Button("Submit");
    submit.setId("bigButton");
    submit.setOnAction(e -> {
      try {

        JSONArray assignmentsJSONArray = Main.getJSONAssignments();
        JSONObject newJSONAssignment = new JSONObject();

        if (nameField.getText().equalsIgnoreCase("")) {
          throw new NullPointerException();
        }

        newJSONAssignment.put("assignmentName", nameField.getText());
        newJSONAssignment.put("class", classField.getValue().toString());
        newJSONAssignment.put("difficulty", difficulty.getValue());
        newJSONAssignment.put("startDate", startDateField.getValue().toString());
        newJSONAssignment.put("dueDate", dueDateField.getValue().toString());
        newJSONAssignment.put("dueTime", timeField.getValue().toString());
        newJSONAssignment.put("completed", false);

        assignmentsJSONArray.add(newJSONAssignment);
        Main.setJSONAssignments(assignmentsJSONArray);

        Assignment newAssignment = new Assignment(nameField.getText(),
            Main.getClasses().get(classField.getValue().toString()), difficulty.getValue(),
            startDateField.getValue(), dueDateField.getValue(), timeField.getValue().toString(),
            false);

        HashTable<String, Assignment> assignments = Main.getAssignments();
        assignments.insert(nameField.getText(), newAssignment);
        Main.setAssignments(assignments);

        AddAssignmentWindow.addAssignment(nameField.getText(), title,
            timeField.getValue().format(DateTimeFormatter.ofPattern("hh:mm a")),
            startDateField.getValue().format(DateTimeFormatter.ofPattern("EEEE, MMMM d")));
        dueDateField.getValue().format(DateTimeFormatter.ofPattern("EEEE, MMMM d"));

        window.close();

      } catch (NullPointerException e1) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Input Error");
        alert.setContentText("One or more fields are empty");
        alert.showAndWait();
      }

    });

    HBox submitHolder = new HBox(submit);
    submitHolder.setPadding(new Insets(20, 0, 0, 0));

    VBox formBox = new VBox(titleText, assignmentName, nameField, assignmentClass, classField,
        assignmentTime, timeBox, assignmentStartDate, startDateField, assignmentDueDate,
        dueDateField, assignmentDifficulty, difficulty, submitHolder);
    formBox.setPadding(new Insets(20, 20, 20, 20));
    formBox.setSpacing(5);

    Scene vScene = new Scene(formBox, 500, 650);
    vScene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link
                                                                                         // CSS

    window.setScene(vScene);
    window.show();

  }

  public static void addAssignment(String nameField, String classField, String timeField,
      String dateField) {
    Main.createAssignmentBox(nameField, classField, timeField, dateField);
  }
}
