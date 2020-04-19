/**
 * 
 */
package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    root.setPadding(new Insets(0, 0 , 0, 10));
    
    // Test Header
    Text testHeader = new Text("This is heading.");
    testHeader.setId("h1"); // apply DM Serif Display font
    
    // Test Body
    Text testBody = new Text("This is body.");
    
    // Create Text Box
    VBox testTextBox = new VBox();
    testTextBox.getChildren().addAll(testHeader, testBody);
    
    root.setLeft(testTextBox);
    
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
