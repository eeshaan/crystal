/**
 * 
 */
package application;

import java.time.LocalDate;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * @author Ayaz Franz, Benjamin Tan, Bryan Lin, Devin Demirlika, Eeshaan Pirani
 *         https://github.com/eeshaan/crystal
 */
public class Main extends Application {

  private static final String APP_TITLE = "Crystal"; // Title of application

  private static final int WINDOW_WIDTH = 1180; // Width of calendar window
  private static final int WINDOW_HEIGHT = 880; // Height of calendar window

  private static VBox assignmentsPane; // Pane where assignments are listed

  private static JSONArray classesJSONArray = WelcomeWindow.getJSONClasses(); // JSON list of
                                                                              // classes
  private static JSONArray assignmentsJSONArray = WelcomeWindow.getJSONAssignments(); // JSON list
                                                                                      // of
                                                                                      // assignments

  private static HashTable<String, Class> classes;
  private static HashTable<LocalDate, LinkedList> assignmentsByDate;
  private static HashTable<Class, LinkedList> assignmentsByClass;
  private static HashTable<String, Assignment> assignments;
  private static PriorityQueue whatToDoNow;

  /**
   * getClasses - gets the classes in the calendar
   * 
   * @return classes - HashTable containing the classes
   */
  public static HashTable<String, Class> getClasses() {
    return classes;
  }

  /**
   * setClasses - sets the calendar's classes as the one provided
   * 
   * @param classes - HashTable of classes added to the calendar
   */
  public static void setClasses(HashTable<String, Class> classes) {
    Main.classes = classes;
  }

  /**
   * getAssignments - gets the assignments in the calendar
   * 
   * @return assignments - HashTable of assignments
   */
  public static HashTable<LocalDate, LinkedList> getAssignmentsByDate() {
    return assignmentsByDate;
  }

  public static void setAssignmentsByDate(HashTable<LocalDate, LinkedList> assignmentsByDate) {
    Main.assignmentsByDate = assignmentsByDate;
  }

  public static HashTable<Class, LinkedList> getAssignmentsByClass() {
    return assignmentsByClass;
  }

  public static void setAssignmentsByClass(HashTable<Class, LinkedList> assignmentsByClass) {
    Main.assignmentsByClass = assignmentsByClass;
  }

  public static HashTable<String, Assignment> getAssignments() {
    return assignments;
  }

  /**
   * setAssignments - sets the assignments in the calendar as the one provided
   * 
   * @param assignments - HashTable of assignments
   */
  public static void setAssignments(HashTable<String, Assignment> assignments) {
    Main.assignments = assignments;
  }

  /**
   * getWhatToDoNow - gets the queue containing the assignments sorted by their priority
   * 
   * @return whatToDoNow - the queue of assignments sorted by their priority
   */
  public static PriorityQueue getWhatToDoNow() {
    return whatToDoNow;
  }

  /**
   * setWhatDoToNow - sets the queue of assignments as the one provided
   * 
   * @param whatToDoNow - the quee of assignemtns sorted by their priority
   */
  public static void setWhatToDoNow(PriorityQueue whatToDoNow) {
    Main.whatToDoNow = whatToDoNow;
  }

  @Override
  public void start(Stage mainStage) throws Exception {

    classes = new HashTable<>(); // Initializes list of classes

    // Initializes list of assignments
    assignmentsByDate = new HashTable<>(); // Sorted by date
    assignments = new HashTable<>();

    whatToDoNow = new PriorityQueue(); // Initializes priority queue of assignments
    classes = new HashTable<>();
    assignmentsByDate = new HashTable<>();
    assignmentsByClass = new HashTable<>();
    assignments = new HashTable<>();
    whatToDoNow = new PriorityQueue();

    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(0, 0, 0, 0)); // Sets the margins of the window to 0

    // Due Today Header
    Text dueTodayHeader = new Text("Due Today");
    dueTodayHeader.setId("h1"); // Applies DM Serif Display font to the header

    // Continue working on Header
    Text workHeader = new Text("Continue working on");
    workHeader.setId("h1"); // Applies DM Serif Display font to the header

    // Create Left Pane
    FlowPane leftPane = new FlowPane();
    leftPane.setPrefWidth(750); // Sets the width of the left pane to 750 pixels

    // Create Assignments Pane
    assignmentsPane = new VBox();

    // Formats the Assignments Pane
    assignmentsPane.setPrefSize(750, 668);
    assignmentsPane.getStyleClass().add("assignmentsPane");
    assignmentsPane.setSpacing(5.0);
    assignmentsPane.setPadding(new Insets(0, 65, 0, 65));

    // Adds the header for the assignments due today
    assignmentsPane.getChildren().add(dueTodayHeader);

    // Setting up regions for spacing
    // Horizontal region for spacing

    Region hRegionHeader = new Region();
    hRegionHeader.setPrefWidth(120);
    HBox.setHgrow(hRegionHeader, Priority.ALWAYS);
    // footer
    Region hRegionFooter = new Region();
    HBox.setHgrow(hRegionFooter, Priority.ALWAYS);

    // Sets up date display
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMMM d, yyyy");
    Date now = new Date(System.currentTimeMillis());
    Text dateText = new Text(dateFormat.format(now));
    dateText.setId("dateText");

    // Sets up "What should I do now" button
    Button wtdBtn = new Button("What should I do now?");
    wtdBtn.setId("wtdBtn"); // Formats the button
    wtdBtn.setOnAction(e -> WhatToDoNowWindow.newWindow("What To Do Now")); // What Should I Do
                                                                            // Window Opens

    // Creates ImageView instance of the Search Image
    Image searchImage = new Image("/application/src/img/search-icon.png", 26, 26, false, true);
    ImageView search = new ImageView();
    search.setImage(searchImage);

    // Creates and sets up the Search Button
    Button searchBtn = new Button("", search);
    searchBtn.getStyleClass().add("iconBtn");
    searchBtn.setOnAction(e -> SearchWindow.newWindow("Search")); // Search Window Opens


    // Creates ImageView instance of Layout Icon
    Image layoutImage = new Image("/application/src/img/layout-icon.png", 26, 26, false, false);
    ImageView layout = new ImageView();
    layout.setImage(layoutImage);

    // Creates and sets up the Window Button
    Button windowBtn = new Button("", layout);
    windowBtn.getStyleClass().add("iconBtn");
    windowBtn.setOnAction(e -> ClassManagerWindow.newWindow("Class Manager")); // Class Manager
                                                                               // WIndow Opens


    // Setting up exit button
    Button exit = new Button("Exit Crystal.");
    exit.setId("exit"); // Formats button
    exit.setOnAction(e -> {
      exitDialog();
    }); // Exits the application

    // Creates an ImageView of the addIcon Image
    Image addImage = new Image("/application/src/img/add-icon.png", 56, 56, false, false);
    ImageView add = new ImageView();
    add.setImage(addImage);

    // Creates and sets up the Add Button with the ImageView
    Button addBtn = new Button("", add);
    addBtn.getStyleClass().add("iconBtn");
    addBtn.setId("addBtn");

    // When clicked, the AddAssignmentWindow is open
    addBtn.setOnAction(e -> AddAssignmentWindow.newWindow("Add Assignment"));

    // setting up header HBox
    HBox leftPaneHeader = new HBox(dateText, hRegionHeader, wtdBtn, searchBtn, windowBtn);

    // Formats the left pane's header
    leftPaneHeader.setPrefHeight(106);
    leftPaneHeader.setPadding(new Insets(40, 65, 40, 65));
    leftPaneHeader.setAlignment(Pos.CENTER);
    leftPaneHeader.setId("leftPaneHeader");

    // Initializes the left pane's footer
    HBox leftPaneFooter = new HBox(exit, hRegionFooter, addBtn);

    // Formats the left pane's footer
    leftPaneFooter.setPrefSize(750, 106);
    leftPaneFooter.setPadding(new Insets(0, 65, 40, 65));
    leftPaneFooter.setAlignment(Pos.BOTTOM_CENTER);

    // Adds header, footer, and assignment pane to border pane's left pane
    leftPane.getChildren().addAll(leftPaneHeader, assignmentsPane, leftPaneFooter);
    root.setLeft(leftPane);

    // Create rightPane
    VBox rightPane = new VBox();
    rightPane.setPrefWidth(394);
    rightPane.setId("rightPane");

    // Creates and formats the right pane's header
    HBox rightPaneHeader = new HBox();
    rightPaneHeader.setPrefHeight(106);
    rightPaneHeader.setId("rightPaneHeader");
    rightPane.getChildren().add(rightPaneHeader); // adds header to right pane

    // Creates and formats the calendar
    Pane calendarView = new Pane();
    calendarView.setPrefHeight(427.59);
    calendarView.setId("calendarView");

    rightPane.getChildren().add(calendarView); // adds calendar to right pane

    // Creates and formats DatePicker
    DatePicker dp = new DatePicker(LocalDate.now());
    DatePickerSkin datePickerSkin = new DatePickerSkin(dp);
    Node popupContent = datePickerSkin.getPopupContent();

    popupContent.setScaleX(1.3);
    popupContent.setLayoutX(50);

    popupContent.setScaleY(1.3);
    popupContent.setLayoutY(-13);

    calendarView.getChildren().add(popupContent);

    // Gets the assignments that are due today or the closest day to today
    dp.setOnAction(e -> {
      LocalDate date = dp.getValue();

      updateAssignments(date);
    });

    // Creates and sets up the subjects pane
    FlowPane subjectsPane = new FlowPane();
    subjectsPane.setPrefHeight(346.41);
    subjectsPane.setPadding(new Insets(60, 50, 60, 50));
    rightPane.getChildren().add(subjectsPane); // Adds the subjects pane to the right pane of border
                                               // pane

    // Creates and formats subjects title
    Text yourClasses = new Text("Your classes");
    yourClasses.setId("yourSubjects");

    // Creates and adds class header to header
    HBox classesHeader = new HBox();
    classesHeader.getChildren().add(yourClasses);

    FlowPane classesPane = new FlowPane();
    classesPane.setPrefWidth(294);
    classesPane.getStyleClass().add("subjects");



    subjectsPane.getChildren().addAll(classesHeader, classesPane);

    // Sets classes pane to right of borderpane
    root.setRight(rightPane);

    // Creates scene
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Formats scene with css file
    mainScene.getStylesheets()
        .add(getClass().getResource("/application/src/css/style.css").toExternalForm());

    // Opens welcome screen
    boolean newUser = WelcomeWindow.newWindow("Welcome to Crystal!");

    // Gets the classes and assignments
    if (newUser) {
      ClassManagerWindow.newWindow("Add your classes!");
    }

    Iterator<String> classNames = classes.iterator();
    while (classNames.hasNext()) {
      Class currentClass = classes.get(classNames.next());
      int[] classColor = currentClass.getClassColor();
      Button classButton = new Button(currentClass.getClassName());
      classButton.setStyle("-fx-background-color: rgb(" + classColor[0] + ", " + classColor[1]
          + ", " + classColor[2] + "); -fx-text-fill: #fff;");

      int yiq = ((classColor[0] * 299) + (classColor[1] * 587) + (classColor[2] * 114)) / 1000; // https://en.wikipedia.org/wiki/YIQ

      if (yiq >= 150)
        classButton.getStyleClass().add("dark-text");

      classButton.setOnAction(e -> ClassAssignmentsWindow.newWindow(currentClass));
      classesPane.getChildren().addAll(classButton);
    }

    classesJSONArray = WelcomeWindow.getJSONClasses();
    assignmentsJSONArray = WelcomeWindow.getJSONAssignments();

    updateAssignments(dp.getValue());

    // Add all the components and set the primary stage
    mainStage.setTitle(APP_TITLE);
    mainStage.setScene(mainScene);
    mainStage.show();
    mainStage.setOnCloseRequest(e -> updateSaveState());
  }

  private void updateAssignments(LocalDate date) {
    assignmentsPane.getChildren().clear();

    Text dueTodayHeader = new Text("Due Today");
    dueTodayHeader.setId("h1"); // apply DM Serif Display font
    if (date.getDayOfYear() == LocalDate.now().getDayOfYear()
        && date.getYear() == LocalDate.now().getYear()) {
      dueTodayHeader.setText("Due Today");
    } else {
      SimpleDateFormat dueDateFormat = new SimpleDateFormat("EEEE, MMMMM d, yyyy");
      Date due = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
      dueTodayHeader.setText("Due on " + dueDateFormat.format(due));
      // dueTodayHeader.setText("Due on " + date);
    }
    assignmentsPane.getChildren().add(dueTodayHeader);

    Iterator<Assignment> today;
    // Calendar calendar =Calendar.getInstance();
    if (assignmentsByDate.get(date) == null)
      today = null;
    else
      today = assignmentsByDate.get(date).iterator();

    while (today != null && today.hasNext()) {
      Assignment assignment = today.next();
      String name = assignment.getAssignmentName();

      HBox assignmentBox = new HBox();
      assignmentBox.getStyleClass().add("assignmentBox");

      int[] classColor = assignment.getClassName().getClassColor();
      assignmentBox.setStyle("-fx-background-color: rgba(" + classColor[0] + ", " + classColor[1]
          + ", " + classColor[2] + ", 0.15); -fx-border-color: rgb(" + classColor[0] + ", "
          + classColor[1] + ", " + classColor[2] + ");");

      if (assignment.isCompleted())
        assignmentBox.getStyleClass().add("completed");

      assignmentBox.setOnMouseClicked(e -> {
        assignmentOptions(assignmentBox, assignment);
      });

      Text time = new Text(assignment.getDueTime());
      time.setId("time");

      Text desc = new Text(" - " + name);

      assignmentBox.getChildren().addAll(time, desc);

      assignmentsPane.getChildren().add(assignmentBox);
    }

    Text workHeader = new Text("Continue working on");
    workHeader.setId("h1");

    HBox workHeaderHolder = new HBox();
    workHeaderHolder.setPadding(new Insets(17.5, 0, 0, 0));
    workHeaderHolder.getChildren().add(workHeader);

    assignmentsPane.getChildren().add(workHeaderHolder);

    ///// Add the rest of the tasks/the next few tasks. Also must be a for loop
    ///// /////
    LocalDate datePlusOne = date.plusDays(1);
    LocalDate endDate = date.plusDays(30);
    SimpleDateFormat dueDateFormat = new SimpleDateFormat("EEEE, MMMMM d,");

    while (!datePlusOne.equals(endDate)) {
      Iterator<Assignment> day;

      if (assignmentsByDate.get(datePlusOne) == null)
        day = null;
      else
        day = assignmentsByDate.get(datePlusOne).iterator();

      while (day != null && day.hasNext()) {
        Assignment assignment = day.next();
        String name = assignment.getAssignmentName();

        if (assignment.getStartDate().isAfter(date))
          continue;

        HBox assignmentBox = new HBox();
        assignmentBox.getStyleClass().add("assignmentBox");

        int[] classColor = assignment.getClassName().getClassColor();
        assignmentBox.setStyle("-fx-background-color: rgba(" + classColor[0] + ", " + classColor[1]
            + ", " + classColor[2] + ", 0.15); -fx-border-color: rgb(" + classColor[0] + ", "
            + classColor[1] + ", " + classColor[2] + ");");


        if (assignment.isCompleted())
          assignmentBox.getStyleClass().add("completed");

        assignmentBox.setOnMouseClicked(e -> {
          assignmentOptions(assignmentBox, assignment);
        });

        Text due = new Text("Due ");

        // "Friday, April 17 at 11:59 PM" - intended format
        Text time =
            new Text(assignment.getDueDate().format(DateTimeFormatter.ofPattern("EEEE, MMMM d"))
                + " at " + assignment.getDueTime());
        time.setId("time");

        Text desc = new Text(" - " + name);
        assignmentBox.getChildren().addAll(due, time, desc);

        assignmentsPane.getChildren().add(assignmentBox);
      }

      datePlusOne = datePlusOne.plusDays(1);
    }
  }

  /**
   * assignmentOptions - creates a window to set assignment as completed or delete it
   * 
   * @param assignmentBox - pane of assignments
   */
  public static void assignmentOptions(HBox assignmentBox, Assignment assignment) {
    // Initializes and formats title of window
    Text manage = new Text("Manage this assignment");
    manage.setId("h2");

    // Adds title to box with formatting
    HBox manageHolder = new HBox(manage);
    manageHolder.setPadding(new Insets(0, 0, 17.5, 0));

    // Initializes and formats completed button
    Button completed = new Button("Mark as completed");
    completed.setId("bigButton");

    // Initializes and formats delete button
    Button delete = new Button("Delete this assignment");
    delete.setId("bigButton");
    delete.setStyle("-fx-background-color: red;");

    // Adds buttons to box with spacing
    HBox buttonOptions = new HBox(completed, delete);
    buttonOptions.setSpacing(10);

    // Adds title and buttons to box and sets margins
    VBox assignmentOptions = new VBox(manageHolder, buttonOptions);
    assignmentOptions.setPadding(new Insets(10, 10, 10, 10));

    // Initializes and formats scene with title and buttons
    Scene dialogScene = new Scene(assignmentOptions, 500, 115);
    dialogScene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm());

    // Sets up stage with scene
    Stage dialogStage = new Stage();
    dialogStage.setScene(dialogScene);
    dialogStage.show();

    // Completed button sets assignment as completed when clicked
    completed.setOnAction(e -> {
      assignmentBox.getStyleClass().add("completed");

      String name = assignment.getAssignmentName();
      whatToDoNow.remove(name);

      for (int x = 0; x < assignmentsJSONArray.size(); x++) {
        JSONObject current = (JSONObject) assignmentsJSONArray.get(x);
        if (current.get("assignmentName").equals(name)) {
          current.remove("completed");
          current.put("completed", true);
        }
      }

      dialogStage.close();
    });

    // Delete button deletes assignment when clicked
    delete.setOnAction(e -> {
      String name = assignment.getAssignmentName();
      LocalDate date = assignment.getDueDate();
      Class className = assignment.getClassName();

      assignments.remove(name);

      LinkedList temp = assignmentsByDate.get(date);
      temp.remove(name);
      assignmentsByDate.insert(date, temp);

      temp = assignmentsByClass.get(className);
      temp.remove(name);
      assignmentsByClass.insert(className, temp);

      whatToDoNow.remove(name);

      assignmentsPane.getChildren().remove(assignmentBox);

      for (int x = 0; x < assignmentsJSONArray.size(); x++) {
        JSONObject current = (JSONObject) assignmentsJSONArray.get(x);
        if (current.get("assignmentName").equals(name)) {
          assignmentsJSONArray.remove(current);
        }
      }

      dialogStage.close();
    });
  }

  /**
   * exitDialog - Saves changes to calendar and exits application
   */
  private static void exitDialog() {

    // Initializes and formats title of exit window
    Text exitHeader = new Text("Exit Crystal");
    exitHeader.setId("h2");

    // Initializes the exit window's header with bottom margin
    HBox exitHeaderHolder = new HBox(exitHeader);
    exitHeaderHolder.setPadding(new Insets(0, 0, 17.5, 0));

    // Reminder text about save files when exiting application
    Text whenText =
        new Text("When you close Crystal, your changes will automatically be added to ");
    Text fileText = new Text("saved_state.json");
    Text loadText =
        new Text("You can load this file back into Crystal next time you run the application.");

    // Formats saved json file title
    fileText.setId("mono");

    // Adds reminder to pane and formats it
    FlowPane textHolder = new FlowPane(whenText, fileText, loadText);
    textHolder.setPadding(new Insets(0, 0, 27.5, 0));
    textHolder.setPrefWrapLength(500);

    // Creates and formats cancel button
    Button cancel = new Button("Cancel");
    cancel.setId("bigButton");

    // Creates and formats exit button
    Button close = new Button("Close Crystal");
    close.setId("bigButton");
    close.setStyle("-fx-background-color: red;");

    // Adds cancel and exit button to pane with formatting
    HBox buttonOptions = new HBox(cancel, close);
    buttonOptions.setAlignment(Pos.BOTTOM_LEFT);
    buttonOptions.setSpacing(10);

    // Adds title, exit reminder, and buttons together with padding
    VBox exitOptions = new VBox(exitHeaderHolder, textHolder, buttonOptions);
    exitOptions.setPadding(new Insets(10, 10, 10, 10));

    // Creates and formats exit scene
    Scene dialogScene = new Scene(exitOptions, 600, 200);
    dialogScene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm());

    // Sets up exit stage
    Stage dialogStage = new Stage();
    dialogStage.setScene(dialogScene);
    dialogStage.show();

    // When cancel button is clicked, the exit screen is closed
    cancel.setOnAction(e -> {
      dialogStage.close();
    });

    // When exit button is clicked, changes to the json file are saved and the application ends
    close.setOnAction(e -> {
      updateSaveState();
      System.exit(0);
    });
  }

  /**
   * main - launches the GUI
   * 
   * @param args - arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * getJSONClasses - gets the classes JSON Array
   * 
   * @return JSON Array holding the classes
   */
  public static JSONArray getJSONClasses() {
    return classesJSONArray;
  }

  /**
   * getJSONAssignments - gets the assignments JSON Array
   * 
   * @return JSON Array holding the assignments
   */
  public static JSONArray getJSONAssignments() {
    return assignmentsJSONArray;
  }

  /**
   * setJSONClasses - sets the class JSONArray as the one provided
   * 
   * @param newJSONArray - class JSON Array to be added
   */
  public static void setJSONClasses(JSONArray newJSONArray) {
    classesJSONArray = newJSONArray;
  }

  /**
   * setJSONAssignments - sets the assignment JSONArray as the one provided
   * 
   * @param newJSONArray - assignment JSON Array to be added
   */
  public static void setJSONAssignments(JSONArray newJSONArray) {
    assignmentsJSONArray = newJSONArray;
  }

  /**
   * updatesSaveState - writes class and assignment updates to the json file
   */
  public static void updateSaveState() {
    try (FileWriter file = new FileWriter("saved_state.json")) {

      // Writes the class and assignment info to the json file
      file.write("{");
      file.write("\"classes\": ");
      file.write(classesJSONArray.toJSONString()); // class info
      file.write(", \"assignments\": ");
      file.write(assignmentsJSONArray.toJSONString()); // assignment info
      file.write("}");
      file.flush();

    } catch (IOException e1) {
      e1.printStackTrace();
    } // Catches exception if file can't be found
  }
}
