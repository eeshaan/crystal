package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * SearchWindow - Details a new feature that will appear, simulating a real application
 *              - For now, gives a tip on where to find assignments
 * @author Bryan Lin (2020)
 */
public class SearchWindow {

  private static final int WINDOW_WIDTH = 500; // Width of the window
  private static final int WINDOW_HEIGHT = 220; // Height of the window

  /**
   * newWindow - Creates the search window and formats it
   * @param title - title of the window
   */
  public static void newWindow(String title) {
    // Creates the stage and formats it
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    // Creates title and formats it
    Text search = new Text("Search");
    search.setId("h2");

    // Adds text about new feature and formats it
    Text comingSoon = new Text("Feature coming soon.");
    comingSoon.setId("subText");

    // Creates divider between title and other components
    HBox comingSoonHolder = new HBox(comingSoon);
    comingSoonHolder.setPadding(new Insets(0, 0, 20, 0));

    // Creates tip text about navigating application and formats it
    Text findAssignments = new Text("For now you can find assignments by class by clicking in the bottom right panel.");
    findAssignments.setWrappingWidth(WINDOW_WIDTH - 40);

    // Creates pane and formats it
    FlowPane findAssignmentsHolder = new FlowPane(findAssignments);
    findAssignmentsHolder.setPrefWrapLength(WINDOW_WIDTH - 40);
    findAssignmentsHolder.setPadding(new Insets(0, 0, 30, 0));

    // Creates button and formats it
    Button close = new Button("Close");
    close.setId("bigButton");

    // Window closes when button is clicked
    close.setOnAction(e -> {
      window.close();
    });

    // Adds components of window to vbox and foramts margins
    VBox pane = new VBox(search, comingSoonHolder, findAssignmentsHolder, close);
    pane.setPadding(new Insets(20, 20, 20, 20));

    // Adds pane to scene and formats it according to css file
    Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
    scene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS

    // Sets up scene window and makes it visible 
    window.setScene(scene);
    window.setTitle(title);
    window.showAndWait();
  }
}