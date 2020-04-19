/**
 * 
 */
package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author eeshaan
 *
 */
public class Main extends Application {

  private static final String APP_TITLE = "Crystal";
  private static final int WINDOW_WIDTH = 1144;
  private static final int WINDOW_HEIGHT = 880;
  
  @Override
  public void start(Stage mainStage) throws Exception {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(10, 10 , 10, 10));
    
    // Test Header
    Text testHeader = new Text("This is heading.");
    testHeader.setId("h1"); // apply DM Serif Display font
    
    // Test Body
    Text testBody = new Text("This is body.");
    
    // Create Text Box
    VBox testTextBox = new VBox();
    testTextBox.getChildren().addAll(testHeader, testBody);
    
    root.setTop(testTextBox);
    
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
