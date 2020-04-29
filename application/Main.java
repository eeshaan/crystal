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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.json.simple.JSONArray;
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
 * @author eeshaan
 *
 */
public class Main extends Application {

  private static final String APP_TITLE = "Crystal";
  private static final int WINDOW_WIDTH = 1144; // divide Figma by 2.039335664
  private static final int WINDOW_HEIGHT = 880;

  private static VBox assignmentsPane;

  private static JSONArray classesJSONArray = WelcomeWindow.getJSONClasses();
  private static JSONArray assignmentsJSONArray = WelcomeWindow.getJSONAssignments();

  private static HashTable<String, Class> classes;
  private static HashTable<Date, LinkedList> assignmentsByDate;
  private static HashTable<String, Assignment> assignments;
  private static PriorityQueue whatToDoNow;

  public static HashTable<String, Class> getClasses() {
    return classes;
  }

  public static void setClasses(HashTable<String, Class> classes) {
    Main.classes = classes;
  }

  public static HashTable<Date, LinkedList> getAssignmentsByDate() {
    return assignmentsByDate;
  }

  public static void setAssignmentsByDate(HashTable<Date, LinkedList> assignmentsByDate) {
    Main.assignmentsByDate = assignmentsByDate;
  }

  public static HashTable<String, Assignment> getAssignments() {
    return assignments;
  }

  public static void setAssignments(HashTable<String, Assignment> assignments) {
    Main.assignments = assignments;
  }

  public static PriorityQueue getWhatToDoNow() {
    return whatToDoNow;
  }

  public static void setWhatToDoNow(PriorityQueue whatToDoNow) {
    Main.whatToDoNow = whatToDoNow;
  }

  @Override
  public void start(Stage mainStage) throws Exception {

    classes = new HashTable<>();
    assignmentsByDate = new HashTable<>();
    assignments = new HashTable<>();
    whatToDoNow = new PriorityQueue();

    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(0, 0, 0, 0));

    // Due Today Header
    Text dueTodayHeader = new Text("Due Today");
    dueTodayHeader.setId("h1"); // apply DM Serif Display font

    // Continue working on Header
    Text workHeader = new Text("Continue working on");
    workHeader.setId("h1");

    // Create Left Pane
    FlowPane leftPane = new FlowPane();
    leftPane.setPrefWidth(750);

    // Create Assignments Pane
    assignmentsPane = new VBox();
    assignmentsPane.setPrefSize(750, 668);
    assignmentsPane.getStyleClass().add("assignmentsPane");
    assignmentsPane.setSpacing(5.0);
    assignmentsPane.setPadding(new Insets(0, 65, 0, 65));
    assignmentsPane.getChildren().add(dueTodayHeader);

    ///// Add today's tasks. Will need to be put into a for loop eventually /////
    // task 1
    HBox assignmentBox1 = new HBox();
    assignmentBox1.getStyleClass().add("assignmentBox");
    assignmentBox1.setId("ass_MATH222");

    assignmentBox1.setOnMouseClicked(e -> {
      assigmentOptions(assignmentBox1);
    });

    Text time1 = new Text("11:00 AM");
    time1.setId("time");

    Text desc1 = new Text(" - 11.6 little-o notation");

    assignmentBox1.getChildren().addAll(time1, desc1);

    assignmentsPane.getChildren().add(assignmentBox1);

    // task 2
    HBox assignmentBox2 = new HBox();
    assignmentBox2.getStyleClass().add("assignmentBox");
    assignmentBox2.setId("ass_CS400");

    assignmentBox2.setOnMouseClicked(e -> {
      assigmentOptions(assignmentBox2);
    });

    Text time2 = new Text("11:59 PM");
    time2.setId("time");

    Text desc2 = new Text(" - p6");
    assignmentBox2.getChildren().addAll(time2, desc2);

    assignmentsPane.getChildren().add(assignmentBox2);

    ///// Add the rest of the tasks/the next few tasks. Also must be a for loop /////

    HBox workHeaderHolder = new HBox();
    workHeaderHolder.setPadding(new Insets(17.5, 0, 0, 0));
    workHeaderHolder.getChildren().add(workHeader);

    assignmentsPane.getChildren().add(workHeaderHolder);

    // task 3
    HBox assignmentBox3 = new HBox();
    assignmentBox3.getStyleClass().add("assignmentBox");
    assignmentBox3.setId("ass_CS252");

    assignmentBox3.setOnMouseClicked(e -> {
      assigmentOptions(assignmentBox3);
    });

    Text time3 = new Text("Friday, April 17 at 11:59 PM");
    time3.setId("time");

    Text desc3 = new Text(" - Worksheet 12");
    assignmentBox3.getChildren().addAll(new Text("Due "), time3, desc3);

    assignmentsPane.getChildren().add(assignmentBox3);

    // task 4
    HBox assignmentBox4 = new HBox();
    assignmentBox4.getStyleClass().add("assignmentBox");
    assignmentBox4.setId("ass_PHILOS101");

    assignmentBox4.setOnMouseClicked(e -> {
      assigmentOptions(assignmentBox4);
    });

    Text time4 = new Text("Sunday, April 19 at 8:00 PM");
    time4.setId("time");

    Text desc4 = new Text(" - Meaning of Life Essay");
    assignmentBox4.getChildren().addAll(new Text("Due "), time4, desc4);

    assignmentsPane.getChildren().add(assignmentBox4);



    // Setting up regions for spacing
    // Horizontal region for spacing

    Region hRegionHeader = new Region();
    hRegionHeader.setPrefWidth(120);
    HBox.setHgrow(hRegionHeader, Priority.ALWAYS);

    Region hRegionFooter = new Region();
    HBox.setHgrow(hRegionFooter, Priority.ALWAYS);

    ///// Setting Children for leftPaneHeader /////

    // date display
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMMM d, yyyy");
    Date now = new Date(System.currentTimeMillis());
    Text dateText = new Text(dateFormat.format(now));
    dateText.setId("dateText");

    // "What should I do now" button
    Button wtdBtn = new Button("What should I do now?");
    wtdBtn.setId("wtdBtn");
    wtdBtn.setOnAction(e -> WhatToDoNowWindow.newWindow("What To Do Now"));


    // search button
    Image searchImage = new Image("/application/src/img/search-icon.png", 26, 26, false, true);
    ImageView search = new ImageView();
    search.setImage(searchImage);
    Button searchBtn = new Button("", search);
    searchBtn.getStyleClass().add("iconBtn");
    searchBtn.setOnAction(e -> SearchWindow.newWindow("Search"));


    // window button
    Image layoutImage = new Image("/application/src/img/layout-icon.png", 26, 26, false, false);
    ImageView layout = new ImageView();
    layout.setImage(layoutImage);
    Button windowBtn = new Button("", layout);
    windowBtn.getStyleClass().add("iconBtn");
    windowBtn.setOnAction(e -> ClassManagerWindow.newWindow("Class Manager"));


    // setting up exit button
    Button exit = new Button("Exit Crystal.");
    exit.setId("exit");
    exit.setOnAction(e -> {
      exitDialog();
    });

    // setting up add button
    Image addImage = new Image("/application/src/img/add-icon.png", 56, 56, false, false);
    ImageView add = new ImageView();
    add.setImage(addImage);
    Button addBtn = new Button("", add);
    addBtn.getStyleClass().add("iconBtn");
    addBtn.setId("addBtn");
    addBtn.setOnAction(e -> AddAssignmentWindow.newWindow("Add Assignment"));

    // setting up header HBox
    HBox leftPaneHeader = new HBox(dateText, hRegionHeader, wtdBtn, searchBtn, windowBtn);
    leftPaneHeader.setPrefHeight(106);
    leftPaneHeader.setPadding(new Insets(40, 65, 40, 65));
    leftPaneHeader.setAlignment(Pos.CENTER);
    leftPaneHeader.setId("leftPaneHeader");

    HBox leftPaneFooter = new HBox(exit, hRegionFooter, addBtn);
    leftPaneFooter.setPrefSize(750, 106);
    leftPaneFooter.setPadding(new Insets(0, 65, 40, 65));
    leftPaneFooter.setAlignment(Pos.BOTTOM_CENTER);

    leftPane.getChildren().addAll(leftPaneHeader, assignmentsPane, leftPaneFooter);

    root.setLeft(leftPane);


    // Create rightPane
    VBox rightPane = new VBox();
    rightPane.setPrefWidth(394);
    rightPane.setId("rightPane");

    HBox rightPaneHeader = new HBox();
    rightPaneHeader.setPrefHeight(106);
    rightPaneHeader.setId("rightPaneHeader");
    rightPane.getChildren().add(rightPaneHeader);

    Pane calendarView = new Pane();
    calendarView.setPrefHeight(427.59);
    calendarView.setId("calendarView");
    rightPane.getChildren().add(calendarView);

    DatePicker dp = new DatePicker(LocalDate.now());
    DatePickerSkin datePickerSkin = new DatePickerSkin(dp);
    Node popupContent = datePickerSkin.getPopupContent();

    popupContent.setScaleX(1.3);
    popupContent.setLayoutX(50);

    popupContent.setScaleY(1.3);
    popupContent.setLayoutY(-13);

    calendarView.getChildren().add(popupContent);

    dp.setOnAction(e -> {
      LocalDate date = dp.getValue();
      if (date.getDayOfYear() == date.now().getDayOfYear()
          && date.getYear() == date.now().getYear()) {
        dueTodayHeader.setText("Due Today");
      } else {
        SimpleDateFormat dueDateFormat = new SimpleDateFormat("EEEE, MMMMM d, yyyy");
        Date due = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        dueTodayHeader.setText("Due on " + dueDateFormat.format(due));
        // dueTodayHeader.setText("Due on " + date);
      }

    });


    FlowPane subjectsPane = new FlowPane();
    subjectsPane.setPrefHeight(346.41);
    subjectsPane.setPadding(new Insets(60, 50, 60, 50));
    rightPane.getChildren().add(subjectsPane);

    Text yourClasses = new Text("Your classes");
    yourClasses.setId("yourSubjects");

    HBox classesHeader = new HBox();
    classesHeader.getChildren().add(yourClasses);

    FlowPane classes = new FlowPane();
    classes.setPrefWidth(294);
    classes.getStyleClass().add("subjects");

    Button class1 = new Button("MATH 222");
    class1.setId("MATH222");
    Button class2 = new Button("CS 400");
    class2.setId("CS400");
    Button class3 = new Button("PHILOS 101");
    class3.setId("PHILOS101");
    Button class4 = new Button("CS 252");
    class4.setId("CS252");

    class1.setOnAction(e -> CourseAssignmentsWindow.newWindow(class1.getId() + " Assignments"));
    class2.setOnAction(e -> CourseAssignmentsWindow.newWindow(class2.getId() + " Assignments"));
    class3.setOnAction(e -> CourseAssignmentsWindow.newWindow(class3.getId() + " Assignments"));
    class4.setOnAction(e -> CourseAssignmentsWindow.newWindow(class4.getId() + " Assignments"));

    classes.getChildren().addAll(class1, class2, class3, class4);

    subjectsPane.getChildren().addAll(classesHeader, classes);

    root.setRight(rightPane);

    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    mainScene.getStylesheets()
        .add(getClass().getResource("/application/src/css/style.css").toExternalForm());

    WelcomeWindow.newWindow("Welcome to Crystal!");

    classesJSONArray = WelcomeWindow.getJSONClasses();
    assignmentsJSONArray = WelcomeWindow.getJSONAssignments();


    ClassManagerWindow.newWindow("Add your classes!");

    // Add the stuff and set the primary stage
    mainStage.setTitle(APP_TITLE);
    mainStage.setScene(mainScene);
    mainStage.show();
    mainStage.setOnCloseRequest(e -> updateSaveState());
  }

  public static void createAssignmentBox(String name, String subject, String dueTime,
      String dueDate) {
    HBox newAssignment = new HBox();
    newAssignment.getStyleClass().add("assignmentBox");
    newAssignment.setId("ass_" + subject);

    Text newTime = new Text(dueDate + " at " + dueTime);
    newTime.setId("time");

    Text newDesc = new Text(" - " + name);
    newAssignment.getChildren().addAll(new Text("Due "), newTime, newDesc);

    assignmentsPane.getChildren().add(newAssignment);
  }

  private static void assigmentOptions(HBox assignmentBox) {
    Text manage = new Text("Manage this assignment");
    manage.setId("h2");

    HBox manageHolder = new HBox(manage);
    manageHolder.setPadding(new Insets(0, 0, 17.5, 0));

    Button completed = new Button("Mark as completed");
    completed.setId("bigButton");

    Button delete = new Button("Delete this assignment");
    delete.setId("bigButton");
    delete.setStyle("-fx-background-color: red;");

    HBox buttonOptions = new HBox(completed, delete);
    buttonOptions.setSpacing(10);

    VBox assignmentOptions = new VBox(manageHolder, buttonOptions);
    assignmentOptions.setPadding(new Insets(10, 10, 10, 10));

    Scene dialogScene = new Scene(assignmentOptions, 500, 115);
    dialogScene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm());

    Stage dialogStage = new Stage();
    dialogStage.setScene(dialogScene);
    dialogStage.show();

    completed.setOnAction(e -> {
      assignmentBox.getStyleClass().add("completed");
      dialogStage.close();
    });

    delete.setOnAction(e -> {
      assignmentsPane.getChildren().remove(assignmentBox);
      dialogStage.close();
    });
  }

  private static void exitDialog() {
    Text exitHeader = new Text("Exit Crystal");
    exitHeader.setId("h2");

    HBox exitHeaderHolder = new HBox(exitHeader);
    exitHeaderHolder.setPadding(new Insets(0, 0, 17.5, 0));

    Text whenText =
        new Text("When you close Crystal, your changes will automatically be added to ");
    Text fileText = new Text("saved_state.json");
    Text loadText =
        new Text("You can load this file back into Crystal next time you run the application.");

    fileText.setId("mono");

    FlowPane textHolder = new FlowPane(whenText, fileText, loadText);
    textHolder.setPadding(new Insets(0, 0, 27.5, 0));
    textHolder.setPrefWrapLength(500);

    Button cancel = new Button("Cancel");
    cancel.setId("bigButton");

    Button close = new Button("Close Crystal");
    close.setId("bigButton");
    close.setStyle("-fx-background-color: red;");

    HBox buttonOptions = new HBox(cancel, close);
    buttonOptions.setAlignment(Pos.BOTTOM_LEFT);
    buttonOptions.setSpacing(10);

    VBox exitOptions = new VBox(exitHeaderHolder, textHolder, buttonOptions);
    exitOptions.setPadding(new Insets(10, 10, 10, 10));

    Scene dialogScene = new Scene(exitOptions, 600, 200);
    dialogScene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm());

    Stage dialogStage = new Stage();
    dialogStage.setScene(dialogScene);
    dialogStage.show();

    cancel.setOnAction(e -> {
      dialogStage.close();
    });

    close.setOnAction(e -> {
      updateSaveState();
      System.exit(0);
    });
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }

  public static JSONArray getJSONClasses() {
    return classesJSONArray;
  }

  public static JSONArray getJSONAssignments() {
    return assignmentsJSONArray;
  }

  public static void setJSONClasses(JSONArray newJSONArray) {
    classesJSONArray = newJSONArray;
  }

  public static void setJSONAssignments(JSONArray newJSONArray) {
    assignmentsJSONArray = newJSONArray;
  }

  public static void updateSaveState() {
    try (FileWriter file = new FileWriter("saved_state.json")) {

      file.write("{");
      file.write("\"classes\": ");
      file.write(classesJSONArray.toJSONString());
      file.write(", \"assignments\": ");
      file.write(assignmentsJSONArray.toJSONString());
      file.write("}");
      file.flush();

    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }
}
