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

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WelcomeWindow {

  private static final int WINDOW_WIDTH = 680;
  private static final int WINDOW_HEIGHT = 200;

  private static JSONArray classesJSONArray = new JSONArray();
  private static JSONArray assignmentsJSONArray = new JSONArray();

  public static void newWindow(String title) {
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
      parseJSON(selectedFile);
    });

    Button addClasses =
        new Button("This is my first time. (Start adding classes and assignments from scratch.)");
    addClasses.setId("bigButton");

    addClasses.setOnAction(e -> clearSaveState());

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
  }

  public static void parseJSON(File f) {
    JSONParser jsonParser = new JSONParser();
    try {
      FileReader jsonFileReader = new FileReader(f.getName());
      Object obj = jsonParser.parse(jsonFileReader);
      JSONObject jo = (JSONObject) obj;
      Object classesObject = jo.get("classes");
      JSONArray classesJSONArrayToSet = (JSONArray) classesObject;

      HashTable<String, Class> classes = Main.getClasses();
      for (int c = 0; c < classesJSONArrayToSet.size(); c++) {
        JSONObject jsonClass = (JSONObject) classesJSONArrayToSet.get(c);
        String className = (String) jsonClass.get("className");
        JSONObject classColor = (JSONObject) jsonClass.get("classColor");

        int red = Integer.parseInt((String) classColor.get("r"));
        int green = Integer.parseInt((String) classColor.get("g"));
        int blue = Integer.parseInt((String) classColor.get("b"));
        int classDifficulty = Integer.parseInt((String) jsonClass.get("difficulty"));

        classesJSONArray = classesJSONArrayToSet;

        Class newClass = new Class(className, red, green, blue, classDifficulty);
        classes.insert(className, newClass);
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
        int difficulty = Integer.parseInt((String) jsonAssignment.get("difficulty"));

        SimpleDateFormat sdformat = new SimpleDateFormat("MMMMM dd, yyyy");
        Date startDate = null;
        Date dueDate = null;
        try {
          startDate = sdformat.parse((String) jsonAssignment.get("startDate"));
          dueDate = sdformat.parse((String) jsonAssignment.get("dueDate"));
        } catch (java.text.ParseException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        String dueTime = (String) jsonAssignment.get("dueTime");
        boolean completed = (boolean) jsonAssignment.get("completed");

        assignmentsJSONArray = assignmentsJSONArrayToSet;

        Assignment newAssignment = new Assignment(assignmentName, classes.get(className),
            difficulty, startDate, dueDate, dueTime, completed);
        assignments.insert(assignmentName, newAssignment);
        whatToDoNow.insert(newAssignment);

        LinkedList list;
        LocalDate localDueDate = LocalDate.of(dueDate.getYear(), dueDate.getMonth(), dueDate.getDate()); 
        if (assignmentsByDate.get(localDueDate) == null)
          list = new LinkedList();
        else
          list = assignmentsByDate.get(localDueDate);

        list.insert(newAssignment);
        assignmentsByDate.insert(localDueDate, list);

      }

      Main.setAssignments(assignments);
      Main.setAssignmentsByDate(assignmentsByDate);
      Main.setWhatToDoNow(whatToDoNow);
      Main.setClasses(classes);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    } catch (NullPointerException e) {
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
