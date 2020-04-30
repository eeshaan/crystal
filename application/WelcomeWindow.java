package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WelcomeWindow {

  private static final int WINDOW_WIDTH = 680;
  private static final int WINDOW_HEIGHT = 200;

  private static JSONArray classesJSONArray = new JSONArray();
  private static JSONArray assignmentsJSONArray = new JSONArray();

  private static boolean newUser = false;

  public static boolean newWindow(String title) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    Text welcomeText = new Text(title);
    welcomeText.setId("h2");

    HBox welcomeHeader = new HBox(welcomeText);

    Text subText = new Text("the intelligent assignment tracker");
    subText.setId("subText");

    HBox subHeader = new HBox(subText);

    Button load = new Button("I've used Crystal before. (Load saved state from JSON.)");
    load.setId("bigButton");

    HBox loadButtonHolder = new HBox(load);
    loadButtonHolder.setPadding(new Insets(12.5, 0, 0, 0));

    FileChooser fileChooser = new FileChooser();

    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));

    load.setOnAction(e -> {
      File selectedFile = fileChooser.showOpenDialog(window);
      boolean valid = parseJSON(selectedFile);
      if (valid) {
        window.close();
      }
    });

    Button addClasses =
        new Button("This is my first time. (Start adding classes and assignments from scratch.)");
    addClasses.setId("bigButton");

    addClasses.setOnAction(e -> {
      clearSaveState();
      window.close();
      newUser = true;
    });

    HBox addButtonHolder = new HBox(addClasses);
    addButtonHolder.setPadding(new Insets(10, 0, 0, 0));

    VBox pane = new VBox(welcomeHeader, subHeader, loadButtonHolder, addButtonHolder);
    pane.setPadding(new Insets(20, 20, 20, 20));

    Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
    scene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link
                                                                                         // CSS

    window.setScene(scene);
    window.setTitle(title);
    window.showAndWait();

    return newUser;
  }

  public static boolean parseJSON(File f) {
    JSONParser jsonParser = new JSONParser();

    try {
      FileReader jsonFileReader = null;
      jsonFileReader = new FileReader(f.getPath());

      Object obj = jsonParser.parse(jsonFileReader);
      JSONObject jo = (JSONObject) obj;
      Object classesObject = jo.get("classes");
      JSONArray classesJSONArrayToSet = (JSONArray) classesObject;

      HashTable<Class, LinkedList> assignmentsByClass = Main.getAssignmentsByClass();
      HashTable<String, Class> classes = Main.getClasses();
      if (classesJSONArrayToSet == null) {
        throw new ParseException(0);
      }
      for (int c = 0; c < classesJSONArrayToSet.size(); c++) {
        JSONObject jsonClass = (JSONObject) classesJSONArrayToSet.get(c);
        String className = (String) jsonClass.get("className");
        int red = (int) ((long) jsonClass.get("classColorRed"));
        int green = (int) ((long) jsonClass.get("classColorGreen"));
        int blue = (int) ((long) jsonClass.get("classColorBlue"));
        int classDifficulty = (int) ((long) jsonClass.get("difficulty"));

        Class newClass = new Class(className, red, green, blue, classDifficulty);
        classes.insert(className, newClass);
        assignmentsByClass.insert(newClass, new LinkedList());
      }

      Object assignmentsObject = jo.get("assignments");
      JSONArray assignmentsJSONArrayToSet = (JSONArray) assignmentsObject;

      HashTable<LocalDate, LinkedList> assignmentsByDate = Main.getAssignmentsByDate();
      HashTable<String, Assignment> assignments = Main.getAssignments();
      PriorityQueue whatToDoNow = Main.getWhatToDoNow();
      for (int a = 0; a < assignmentsJSONArrayToSet.size(); a++) {
        JSONObject jsonAssignment = (JSONObject) assignmentsJSONArrayToSet.get(a);
        String assignmentName = (String) jsonAssignment.get("assignmentName");
        String className = (String) jsonAssignment.get("class");

        if (classes.get(className) == null) {
          Alert alert = new Alert(AlertType.CONFIRMATION);
          alert.setTitle("Class Not Recognized");
          alert.setHeaderText("Unknown Class");
          Label error = new Label("The assignment \"" + assignmentName
              + "\" is trying to be added to the unknown class \"" + className
              + "\". Would you like to add this class?");
          error.setWrapText(true);
          alert.getDialogPane().setContent(error);

          Optional<ButtonType> result = alert.showAndWait();
          if (result.get() == ButtonType.OK) {

            // Create the custom dialog.
            Dialog<ButtonType> dialog = new Dialog<ButtonType>();
            dialog.setTitle("Add Class");
            dialog.setHeaderText("Please give the color and difficulty of the class.");


            // Set the button types.
            ButtonType addButtonType = new ButtonType("Add", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

            // Create the username and password labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));


            ColorPicker cp = new ColorPicker();
            Spinner<Integer> difficulty = new Spinner<Integer>(1, 5, 3, 1);

            grid.add(new Label("Class Name:"), 0, 0);
            grid.add(new Label(className), 1, 0);
            grid.add(cp, 1, 2);
            grid.add(difficulty, 1, 3);
            dialog.getDialogPane().setContent(grid);


            Optional<ButtonType> result1 = dialog.showAndWait();

            if (result1.get() == addButtonType) {

              Class newClass =
                  new Class(className, (int) (cp.getValue().getRed() * 255), (int) (cp.getValue().getGreen() * 255),
                      (int) (cp.getValue().getBlue() * 255), difficulty.getValue());
              JSONObject newJSONClass = new JSONObject();

              newJSONClass.put("className", className);
              newJSONClass.put("classColorRed", (int) cp.getValue().getRed());
              newJSONClass.put("classColorGreen", (int) cp.getValue().getGreen());
              newJSONClass.put("classColorBlue", (int) cp.getValue().getBlue());
              newJSONClass.put("difficulty", difficulty.getValue());

              classesJSONArrayToSet.add(newJSONClass);
              classes.insert(className, newClass);

            } else {
              throw new ParseException(a);
            }
          } else {
            throw new ParseException(a);

          }

        }
        int difficulty = (int) ((long) jsonAssignment.get("difficulty"));

        SimpleDateFormat sdformat = new SimpleDateFormat("MMMMM dd, yyyy");

        Date startDate = null;
        Date dueDate = null;
        try {
          startDate = sdformat.parse((String) jsonAssignment.get("startDate"));
          dueDate = sdformat.parse((String) jsonAssignment.get("dueDate"));
        } catch (java.text.ParseException e) {
          throw new ParseException(a);
        }

        LocalDate localStartDate =
            LocalDate.of(startDate.getYear() + 1900, startDate.getMonth() + 1, startDate.getDate());
        LocalDate localDueDate =
            LocalDate.of(dueDate.getYear() + 1900, dueDate.getMonth() + 1, dueDate.getDate());

        String dueTime = (String) jsonAssignment.get("dueTime");
        boolean completed = (boolean) jsonAssignment.get("completed");

        assignmentsJSONArray = assignmentsJSONArrayToSet;

        Class classObj = classes.get(className);
        Assignment newAssignment = new Assignment(assignmentName, classObj,
            difficulty, localStartDate, localDueDate, dueTime, completed);
        assignments.insert(assignmentName, newAssignment);
        whatToDoNow.insert(newAssignment);
        
        try {
        	LinkedList classAssignments = assignmentsByClass.get(classObj);
        	classAssignments.insert(newAssignment);
        	assignmentsByClass.insert(classObj, classAssignments);
        } catch (Exception e) {
        	System.out.println("oopsie daisy");
        }
        
        LinkedList list;


        if (assignmentsByDate.get(localDueDate) == null) {
          list = new LinkedList();
        } else {
          list = assignmentsByDate.get(localDueDate);
        }

        list.insert(newAssignment);
        assignmentsByDate.insert(localDueDate, list);

      }

      classesJSONArray = classesJSONArrayToSet;
      assignmentsJSONArray = assignmentsJSONArrayToSet;


      Main.setAssignments(assignments);
      Main.setAssignmentsByDate(assignmentsByDate);
      Main.setWhatToDoNow(whatToDoNow);
      Main.setClasses(classes);
      Main.setAssignmentsByClass(assignmentsByClass);
      
      return true;


    } catch (FileNotFoundException e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("File Error");
      alert.setContentText("File was not found.");
      alert.showAndWait();
      return false;
    } catch (IOException e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("File Error");
      alert.setContentText("Error importing this file.");
      alert.showAndWait();
      return false;
    } catch (ParseException e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("File Error");
      alert.setContentText("File formatted incorrectly.");
      alert.showAndWait();
      return false;
    } catch (NullPointerException e) {
      e.printStackTrace();
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("File Error");
      alert.setContentText("No file was selected.");
      alert.showAndWait();
      return false;
    }
  }

  public static JSONArray getJSONClasses() {
    return classesJSONArray;
  }

  public static JSONArray getJSONAssignments() {
    return assignmentsJSONArray;
  }

  public static void clearSaveState() {
    try {
      FileWriter fw;
      fw = new FileWriter("saved_state.json", false);
      PrintWriter pw = new PrintWriter(fw, false);
      pw.flush();
      pw.close();
      fw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
