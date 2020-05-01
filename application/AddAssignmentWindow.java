package application;

import javafx.geometry.Insets;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
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
        newJSONAssignment.put("dueDate", dueDateField.getValue().format(DateTimeFormatter.ofPattern("LLLL dd, yyyy")));
        newJSONAssignment.put("dueTime", timeField.getValue().toString());
        newJSONAssignment.put("completed", false);

        assignmentsJSONArray.add(newJSONAssignment);
        Main.setJSONAssignments(assignmentsJSONArray);
        
        HashTable<String, Class> classes = Main.getClasses();
        Class className = classes.get(classField.getValue().toString());

        Assignment newAssignment = new Assignment(nameField.getText(),
            className, difficulty.getValue(),
            startDateField.getValue(), dueDateField.getValue(), timeField.getValue().format(DateTimeFormatter.ofPattern("h:mm a")),
            false);

        HashTable<LocalDate, LinkedList> assignmentsByDate = Main.getAssignmentsByDate();
        HashTable<String, Assignment> assignments = Main.getAssignments();
        PriorityQueue whatToDoNow = Main.getWhatToDoNow();
        HashTable<Class, LinkedList> assignmentsByClass = Main.getAssignmentsByClass();
        
        assignments.insert(nameField.getText(), newAssignment);
        whatToDoNow.insert(newAssignment);
        
        LinkedList temp = assignmentsByClass.get(className);
        temp.insert(newAssignment);
        assignmentsByClass.insert(className, temp);
        
        if (assignmentsByDate.get(newAssignment.getDueDate()) == null) {
          temp = new LinkedList();
        } else {
          temp = assignmentsByDate.get(newAssignment.getDueDate());
        }

        temp.insert(newAssignment);
        assignmentsByDate.insert(newAssignment.getDueDate(), temp);
        
        
        Main.setAssignments(assignments);
        Main.setAssignmentsByDate(assignmentsByDate);
        Main.setWhatToDoNow(whatToDoNow);
        Main.setClasses(classes);
        Main.setAssignmentsByClass(assignmentsByClass);

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

    Scene vScene = new Scene(formBox, 425, 550);
    vScene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link
                                                                                         // CSS

    window.setScene(vScene);
    window.show();

  }
}
