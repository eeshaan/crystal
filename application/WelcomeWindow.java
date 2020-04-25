package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

  private static File file;


  public static void newWindow(String title) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    Text welcomeText = new Text(title);
    welcomeText.setId("h2");

    HBox welcomeHeader = new HBox();
    welcomeHeader.getChildren().add(welcomeText);

    Text subText = new Text("the intelligent assignment tracker");
    subText.setId("subText");

    HBox subHeader = new HBox();
    subHeader.getChildren().add(subText);

    Button load = new Button("I've used Crystal before. (Load saved state from JSON.)");
    load.setId("bigButton");

    HBox loadButtonHolder = new HBox();
    loadButtonHolder.setPadding(new Insets(12.5, 0, 0, 0));
    loadButtonHolder.getChildren().add(load);


    FileChooser fileChooser = new FileChooser();

    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));

    load.setOnAction(e -> {
      File selectedFile = fileChooser.showOpenDialog(window);
      parseJson(selectedFile);
      file = selectedFile;
    });


    Button addClasses =
        new Button("This is my first time. (Start adding classes and assignments from scratch.)");
    addClasses.setId("bigButton");

    HBox addButtonHolder = new HBox();
    addButtonHolder.setPadding(new Insets(10, 0, 0, 0));
    addButtonHolder.getChildren().add(addClasses);

    VBox pane = new VBox();
    pane.setPadding(new Insets(20, 20, 20, 20));

    pane.getChildren().addAll(welcomeHeader, subHeader, loadButtonHolder, addButtonHolder);

    Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
    scene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS

    window.setScene(scene);
    window.setTitle(title);
    window.showAndWait();
  }

  public static void parseJson(File f) {
    JSONParser jsonParser = new JSONParser();
    try {
      FileReader jsonFileReader = new FileReader(f.getName());
      Object obj = jsonParser.parse(jsonFileReader);
      JSONObject jo = (JSONObject) obj;
      Object classesObject = jo.get("classes");
      JSONArray classesJSONArray = (JSONArray) classesObject;

      for (int c = 0; c < classesJSONArray.size(); c++) {
        JSONObject jsonClass = (JSONObject) classesJSONArray.get(c);
        String className = (String) jsonClass.get("className");
        JSONObject classColor = (JSONObject) jsonClass.get("classColor");

        int red = Integer.parseInt((String) classColor.get("r"));
        int green = Integer.parseInt((String) classColor.get("g"));
        int blue = Integer.parseInt((String) classColor.get("b"));
        int classDifficulty = Integer.parseInt((String) jsonClass.get("difficulty"));

        Class newClass = new Class(className, red, green, blue, classDifficulty);

      }

      Object assignmentsObject = jo.get("assignments");
      JSONArray assignmentsJSONArray = (JSONArray) assignmentsObject;

      for (int a = 0; a < assignmentsJSONArray.size(); a++) {
        JSONObject jsonAssignment = (JSONObject) assignmentsJSONArray.get(a);
        String assignmentName = (String) jsonAssignment.get("assignmentName");
        String className = (String) jsonAssignment.get("class");
        int difficulty = Integer.parseInt((String) jsonAssignment.get("difficulty"));
        String startDate = (String) jsonAssignment.get("startDate");
        String dueDate = (String) jsonAssignment.get("dueDate");
        String dueTime = (String) jsonAssignment.get("dueTime");
        boolean completed = (boolean) jsonAssignment.get("completed");

        // cannot create assignment until classes are stored in datatype
        // Assignment newAssignment = new Assignment(assignmentName, className, difficulty,
        // startDate, dueDate, dueTime, completed);
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public File getJSON() {
    return file;
  }
}
