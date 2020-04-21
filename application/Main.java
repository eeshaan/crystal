/**
 * 
 */
package application;

import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

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

  @Override
  public void start(Stage mainStage) throws Exception {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(0, 0, 0, 0));

    // Due Today Header
    Text dueTodayHeader = new Text("Due Today");
    dueTodayHeader.setId("h1"); // apply DM Serif Display font

    // Continue working on Header
    Text workHeader = new Text("Continue working on");
    workHeader.setId("workHeader"); 
    workHeader.setStyle("-fx-padding: 100px 0 0 0;");

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

    Text time1 = new Text("11:00 AM");
    time1.setId("time");

    Text desc1 = new Text(" - 11.6 little-o notation");

    assignmentBox1.getChildren().addAll(time1, desc1);

    assignmentsPane.getChildren().add(assignmentBox1);

    // task 2
    HBox assignmentBox2 = new HBox();
    assignmentBox2.getStyleClass().add("assignmentBox");
    assignmentBox2.setId("ass_CS400");

    Text time2 = new Text("11:59 PM");
    time2.setId("time");

    Text desc2 = new Text(" - p6");
    assignmentBox2.getChildren().addAll(time2, desc2);

    assignmentsPane.getChildren().add(assignmentBox2);

    ///// Add the rest of the tasks/the next few tasks. Also must be a for loop /////

    assignmentsPane.getChildren().add(workHeader);

    // task 3
    HBox assignmentBox3 = new HBox();
    assignmentBox3.getStyleClass().add("assignmentBox");
    assignmentBox3.setId("ass_CS252");

    Text time3 = new Text("Friday, April 17 at 11:59 PM");
    time3.setId("time");

    Text desc3 = new Text(" - Worksheet 12");
    assignmentBox3.getChildren().addAll(new Text("Due "), time3, desc3);

    assignmentsPane.getChildren().add(assignmentBox3);

    // task 4
    HBox assignmentBox4 = new HBox();
    assignmentBox4.getStyleClass().add("assignmentBox");
    assignmentBox4.setId("ass_PHILOS101");

    Text time4 = new Text("Sunday, April 19 at 8:00 PM");
    time4.setId("time");

    Text desc4 = new Text(" - Meaning of Life Essay");
    assignmentBox4.getChildren().addAll(new Text("Due "), time4, desc4);

    assignmentsPane.getChildren().add(assignmentBox4);



    // Setting up regions for spacing
    // Horizontal region for spacing
    Region hRegion = new Region();
    hRegion.setPrefWidth(120);
    HBox.setHgrow(hRegion, Priority.ALWAYS);


    ///// Setting Children for leftPaneHeader /////

    // date display
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMMM d, yyyy");
    Date now = new Date(System.currentTimeMillis());
    Text dateText = new Text(dateFormat.format(now));
    dateText.setId("dateText");

    // "What should I do now" button
    Button wtdBtn = new Button("What should I do now?");
    wtdBtn.setId("wtdBtn");
    wtdBtn.setOnAction(e->WhatToDoNowWindow.newWindow("What To Do Now"));


    // search button
    Image searchImage = new Image("/application/src/img/search-icon.png", 26, 26, false, true);
    ImageView search = new ImageView();
    search.setImage(searchImage);
    Button searchBtn = new Button("", search);
    searchBtn.getStyleClass().add("iconBtn");
    searchBtn.setOnAction(e->SearchWindow.newWindow("Search"));


    // window button
    Image layoutImage = new Image("/application/src/img/layout-icon.png", 26, 26, false, false);
    ImageView layout = new ImageView();
    layout.setImage(layoutImage);
    Button windowBtn = new Button("", layout);
    windowBtn.getStyleClass().add("iconBtn");
    windowBtn.setOnAction(e->LayoutWindow.newWindow("Subjects Manager"));
    

    // setting up add button
    Image addImage = new Image("/application/src/img/add-icon.png", 56, 56, false, false);
    ImageView add = new ImageView();
    add.setImage(addImage);
    Button addBtn = new Button("", add);
    addBtn.getStyleClass().add("iconBtn");
    addBtn.setId("addBtn");
    addBtn.setOnAction(e->AddAssignmentWindow.newWindow("Add Assignment"));

    // setting up header HBox
    HBox leftPaneHeader = new HBox(dateText, hRegion, wtdBtn, searchBtn, windowBtn);
    leftPaneHeader.setPrefHeight(106);
    leftPaneHeader.setPadding(new Insets(40, 65, 40, 65));
    leftPaneHeader.setAlignment(Pos.CENTER);
    leftPaneHeader.setId("leftPaneHeader");

    HBox leftPaneFooter = new HBox(addBtn);
    leftPaneFooter.setPrefSize(750, 106);
    leftPaneFooter.setPadding(new Insets(0, 65, 40, 65));
    leftPaneFooter.setAlignment(Pos.TOP_RIGHT);

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
        if(date.getDayOfYear() == date.now().getDayOfYear() && date.getYear() == date.now().getYear()) {
        	dueTodayHeader.setText("Due Today");
        }
        else {
        	SimpleDateFormat dueDateFormat = new SimpleDateFormat("EEEE, MMMMM d, yyyy");
        	Date due = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        	dueTodayHeader.setText("Due on " + dueDateFormat.format(due));
        	//dueTodayHeader.setText("Due on " + date);
        }
        										
    });
    

    FlowPane subjectsPane = new FlowPane();
    subjectsPane.setPrefHeight(346.41);
    subjectsPane.setPadding(new Insets(60, 50, 60, 50));
    rightPane.getChildren().add(subjectsPane);

    Text yourSubjects = new Text("Your subjects");
    yourSubjects.setId("yourSubjects");

    HBox subjectsHeader = new HBox();
    subjectsHeader.getChildren().add(yourSubjects);

    FlowPane subjects = new FlowPane();
    subjects.setPrefWidth(294);
    subjects.getStyleClass().add("subjects");

    Button subject1 = new Button("MATH 222");
    subject1.setId("MATH222");
    Button subject2 = new Button("CS 400");
    subject2.setId("CS400");
    Button subject3 = new Button("PHILOS 101");
    subject3.setId("PHILOS101");
    Button subject4 = new Button("CS 252");
    subject4.setId("CS252");
    
    subject1.setOnAction(e->CourseAssignmentsWindow.newWindow(subject1.getId()+" Assignments"));
    subject2.setOnAction(e->CourseAssignmentsWindow.newWindow(subject2.getId()+" Assignments"));
    subject3.setOnAction(e->CourseAssignmentsWindow.newWindow(subject3.getId()+" Assignments"));
    subject4.setOnAction(e->CourseAssignmentsWindow.newWindow(subject4.getId()+" Assignments"));



    subjects.getChildren().addAll(subject1, subject2, subject3, subject4);

    subjectsPane.getChildren().addAll(subjectsHeader, subjects);

    root.setRight(rightPane);

    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    mainScene.getStylesheets()
        .add(getClass().getResource("/application/src/css/style.css").toExternalForm());

    // Add the stuff and set the primary stage
    mainStage.setTitle(APP_TITLE);
    mainStage.setScene(mainScene);
    mainStage.show();
  }
  
  public static void createAssignmentBox(String name, String subject, String dueTime, String dueDate){
    HBox newAssignment = new HBox();
    newAssignment.getStyleClass().add("assignmentBox");
    newAssignment.setId("ass_" + subject);

    Text newTime = new Text("Friday, " + dueDate + " at " + dueTime);
    newTime.setId("time");

    Text newDesc = new Text(" - " + name);
    newAssignment.getChildren().addAll(new Text("Due "), newTime, newDesc);

    assignmentsPane.getChildren().add(newAssignment);
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }

}
