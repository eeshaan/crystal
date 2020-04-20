/**
 * 
 */
package application;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
  
  @Override
  public void start(Stage mainStage) throws Exception {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(0, 0, 0, 0));
    
    // Due Today Header
    Text dueTodayHeader = new Text("Due Today");
    dueTodayHeader.setId("h1"); // apply DM Serif Display font
 
    // Continue working on Body
    Text workHeader = new Text("Continue working on");
    workHeader.setId("h1"); // apply DM Serif Display font
    
    // Create Text Box
    VBox testTextBox = new VBox();
    testTextBox.getChildren().add(dueTodayHeader);
    
//Add today's tasks. Will need to be put into a for loop eventually
    //task 1
    HBox assignmentBox1 = new HBox();
    assignmentBox1.setId("ass_MATH222");
    
    Text time1 = new Text("11:00 AM");
    time1.setId("time");
    
    Text desc1 = new Text("— 11.6 little-o notation");
    
    assignmentBox1.getChildren().addAll(time1, desc1);
    testTextBox.setSpacing(20.0);
    
    assignmentBox1.setMargin(time1, new Insets(20,5,20,20));
    assignmentBox1.setMargin(desc1, new Insets(20,5,20,0));
    
    testTextBox.getChildren().add(assignmentBox1);
    
    //task 2
    HBox assignmentBox2 = new HBox();
    assignmentBox2.setId("ass_CS400");
    
    Text time2 = new Text("11:59 PM");
    time2.setId("time");
    
    Text desc2 = new Text("— p6");
    assignmentBox2.getChildren().addAll(time2, desc2);
    testTextBox.setSpacing(5.0);
    assignmentBox2.setMargin(time2, new Insets(20,5,20,20));
    assignmentBox2.setMargin(desc2, new Insets(20,5,20,0));
    testTextBox.getChildren().add(assignmentBox2);
    
    testTextBox.getChildren().add(workHeader);
    
//Add the rest of the tasks/the next few tasks. Also must be a for loop
    //task 3
    HBox assignmentBox3 = new HBox();
    assignmentBox3.setId("ass_CS252");
    
    Text time3 = new Text("Due Friday, April 17 at 11:59 PM");
    time3.setId("time");
    
    Text desc3 = new Text("— Worksheet 12");
    assignmentBox3.getChildren().addAll(time3, desc3);
    testTextBox.setSpacing(20.0);
    assignmentBox3.setMargin(time3, new Insets(20,5,20,20));
    assignmentBox3.setMargin(desc3, new Insets(20,5,20,0));
    testTextBox.getChildren().add(assignmentBox3);
    
    //task 4
    HBox assignmentBox4 = new HBox();
    assignmentBox4.setId("ass_PHILOS101");
    
    Text time4 = new Text("Due Sunday, April 19 at 8:00 PM");
    time4.setId("time");
    
    Text desc4 = new Text("— Meaning of Life Essay");
    assignmentBox4.getChildren().addAll(time4, desc4);
    testTextBox.setSpacing(5.0);
    assignmentBox4.setMargin(time4, new Insets(20,5,20,20));
    assignmentBox4.setMargin(desc4, new Insets(20,5,20,0));
    testTextBox.getChildren().add(assignmentBox4);
    
    
    // Create leftPane
    VBox leftPane = new VBox();
    leftPane.setPrefWidth(750);
    leftPane.setId("leftPane");
    
//Setting up regions for spacing
    //Horizontal region for spacing
    Region hRegion = new Region();
    HBox.setHgrow(hRegion, Priority.ALWAYS);
    
    //Vertical region for spacing
    Region vRegion = new Region();
    VBox.setVgrow(vRegion, Priority.ALWAYS);
    
//Setting Children for leftPaneHeader
    
    //date display
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMMM d, yyyy");
    Date now = new Date(System.currentTimeMillis());
    Text dateText = new Text(dateFormat.format(now));
    dateText.setFont(new Font(26.0));
    
    
    
    //"What should I do now" button
    Button task = new Button("What should I do now?");
    task.setStyle("-fx-background-color: red; -fx-height: 26;"); //styling
    
    //search button
    Image searchImage = new Image("/application/src/img/search-icon.png", 26, 26, true, true);
    ImageView search = new ImageView();
    search.setImage(searchImage);
    Button searchBtn = new Button("",search);
    searchBtn.setStyle("-fx-background-color: transparent;");//styling
    
    
    //window button
    Image layoutImage = new Image("/application/src/img/layout-icon.png", 26, 26, true, true);
    ImageView layout = new ImageView();
    layout.setImage(layoutImage);
    Button windowBtn = new Button("",layout);
    windowBtn.setStyle("-fx-background-color: transparent;");//styling
    
//Setting HBox for add button
    HBox addBox = new HBox();
    Image addImage = new Image("/application/src/img/add-icon.png", 56, 56, true, true);
    ImageView add = new ImageView();
    add.setImage(addImage);
    Button addBtn = new Button("",add);
    addBtn.setStyle("-fx-background-color: transparent;");//styling
    addBox.getChildren().add(addBtn);
    addBtn.setAlignment(Pos.BOTTOM_RIGHT);
    addBox.setMargin(addBtn, new Insets(20,20,20,20));
    
    
//setting up header HBox
    HBox leftPaneHeader = new HBox(dateText, hRegion, task, searchBtn, windowBtn);
    leftPaneHeader.setPrefHeight(106);
    leftPaneHeader.setId("leftPaneHeader");
    leftPaneHeader.setSpacing(20);
    leftPaneHeader.setAlignment(Pos.CENTER);
    leftPaneHeader.setMargin(dateText, new Insets(0,0,0,80));

    leftPane.getChildren().addAll(leftPaneHeader, testTextBox, vRegion, addBox);
    addBox.setAlignment(Pos.BOTTOM_RIGHT);
    
    leftPane.setMargin(testTextBox, new Insets(20,80,20,80));
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
    
    root.setRight(rightPane);
    
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    mainScene.getStylesheets().add(getClass().getResource("/application/src/css/style.css").toExternalForm());

    // Add the stuff and set the primary stage
    mainStage.setTitle(APP_TITLE);
    mainStage.setScene(mainScene);
    mainStage.show();
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }

}
