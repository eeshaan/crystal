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

/**
 * 
 * AddAssignmentWindow - Window that adds assignments to the calendar
 * @author Ayaz Franz, Benjamin Tan, Bryan Lin, Devin Demirlika, Eeshaan Pirani
 */
public class AddAssignmentWindow {

  /**
   * newWindow - creates AddAssignment Window
   * @param title - title of the window
   */
  public static void newWindow(String title) {
    // Creates stage of the window and formats it
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL); 

    // Creates title of the window and formats it
    Text titleText = new Text(title);
    titleText.setId("h2");

    // Creates the Assignment Name text field for input
    Label assignmentName = new Label("Name:");
    TextField nameField = new TextField();
    nameField.getText();
    HBox nameBox = new HBox();

    // Creates the Assignment Class input field
    Label assignmentClass = new Label("Class:");
    ComboBox<String> classField = new ComboBox<>();
    // TODO: populate this ComboBox with Class names

    // Iterates through the classes and adds them
    Iterator<String> iterator = Main.getClasses().iterator();
    while (iterator.hasNext()) {
      classField.getItems().add(iterator.next());
    }

    // Creates an input method for the Assignment due time
    Label assignmentTime = new Label("Due Time:");
    Spinner<LocalTime> spinner = new Spinner<>();
    SpinnerValueFactory<LocalTime> timeField = new SpinnerValueFactory<LocalTime>() {
      {
        setConverter(new LocalTimeStringConverter(DateTimeFormatter.ofPattern("hh:mm a"), null));
      }

      /**
       * decrement - decrements the local time by the steps
       * @param steps - amount of time to decrement the local time
       */
      @Override
      public void decrement(int steps) {
        if (getValue() == null) {
          setValue(LocalTime.now());
        } else {
          LocalTime value = (LocalTime) getValue();
          setValue(value.minusMinutes(steps));
        }
      }

      /**
       * increment - increments the local time by the steps
       * @param steps - amount of time to increment the local time
       */
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
    
    // Formats the spinner for the Assignment due date and prepares to add it to the scene
    spinner.setValueFactory(timeField);
    spinner.setEditable(true);
    HBox timeBox = new HBox();
    timeBox.getChildren().add(spinner);
    
    // Creates DatePicker for the Assignment start date
    Label assignmentStartDate = new Label("Start Date:");
    DatePicker startDateField = new DatePicker();

    // Creates DatePicker for the Assignment due date
    Label assignmentDueDate = new Label("Due Date:");
    DatePicker dueDateField = new DatePicker();

    // Creates spinner for the assignment difficulty
    Label assignmentDifficulty = new Label("Assignment Difficulty");
    Spinner<Integer> difficulty = new Spinner<>(1, 5, 1);
    difficulty.setEditable(true);

    // Creates submit button and formats it
    Button submit = new Button("Submit");
    submit.setId("bigButton");
    
    // When clicked, submit button creates the assignment and saves the info
    submit.setOnAction(e -> {
      try {

        // Writes to JSON file
        JSONArray assignmentsJSONArray = Main.getJSONAssignments();
        JSONObject newJSONAssignment = new JSONObject();

        // Checks if the Assignment has a name
        if (nameField.getText().equalsIgnoreCase("")) {
          throw new NullPointerException();
        }

        // Gets the inputed info of the Assignment
        newJSONAssignment.put("assignmentName", nameField.getText());
        newJSONAssignment.put("class", classField.getValue().toString());
        newJSONAssignment.put("difficulty", difficulty.getValue());
        newJSONAssignment.put("startDate", startDateField.getValue().toString());
        newJSONAssignment.put("dueDate", dueDateField.getValue().toString());
        newJSONAssignment.put("dueTime", timeField.getValue().toString());
        newJSONAssignment.put("completed", false);

        // Writes to JSON file
        assignmentsJSONArray.add(newJSONAssignment);
        Main.setJSONAssignments(assignmentsJSONArray);

        // Creates new Assignment object
        Assignment newAssignment = new Assignment(nameField.getText(),
            Main.getClasses().get(classField.getValue().toString()), difficulty.getValue(),
            startDateField.getValue(), dueDateField.getValue(), timeField.getValue().toString(),
            false);

        // Stores Assignment object in Lists
        HashTable<String, Assignment> assignments = Main.getAssignments();
        assignments.insert(nameField.getText(), newAssignment);
        Main.setAssignments(assignments);

        // Adds Assignment to display on menu of calendar
        AddAssignmentWindow.addAssignment(nameField.getText(), title,
            timeField.getValue().format(DateTimeFormatter.ofPattern("hh:mm a")),
            startDateField.getValue().format(DateTimeFormatter.ofPattern("EEEE, MMMM d")));
        dueDateField.getValue().format(DateTimeFormatter.ofPattern("EEEE, MMMM d"));

        // Closes this window
        window.close();

      } catch (NullPointerException e1) { // If an Error occurs, warns the user
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Input Error");
        alert.setContentText("One or more fields are empty");
        alert.showAndWait();
      }

    });

    // Creates box and formats it
    HBox submitHolder = new HBox(submit);
    submitHolder.setPadding(new Insets(20, 0, 0, 0));

    // makes VBox of assignment into and formats it
    VBox formBox = new VBox(titleText, assignmentName, nameField, assignmentClass, classField,
        assignmentTime, timeBox, assignmentStartDate, startDateField, assignmentDueDate,
        dueDateField, assignmentDifficulty, difficulty, submitHolder);
    formBox.setPadding(new Insets(20, 20, 20, 20));
    formBox.setSpacing(5);

    // Creates new scene and formats it with css file
    Scene vScene = new Scene(formBox, 425, 550);
    vScene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm());
    
    // Adds scene to stage and makes it visible
    window.setScene(vScene);
    window.show();

  }

  /**
   * addAssignment - adds an Assignment to the calendar
   * @param nameField - name of the new Assignment
   * @param classField - class of the new Assignment
   * @param timeField - due time of the new Assignment
   * @param dateField - due date of the new Assignment
   */
  public static void addAssignment(String nameField, String classField, String timeField,
      String dateField) {
    Main.createAssignmentBox(nameField, classField, timeField, dateField);
  }
}
