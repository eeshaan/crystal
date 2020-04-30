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

public class SearchWindow {

  private static final int WINDOW_WIDTH = 500;
  private static final int WINDOW_HEIGHT = 220;
  
  public static void newWindow(String title) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    Text search = new Text("Search");
    search.setId("h2");
    
    Text comingSoon = new Text("Feature coming soon.");
    comingSoon.setId("subText");
    
    HBox comingSoonHolder = new HBox(comingSoon);
    comingSoonHolder.setPadding(new Insets(0, 0, 20, 0));
    
    Text findAssignments = new Text("For now you can find assignments by class by clicking in the bottom right panel.");
    findAssignments.setWrappingWidth(WINDOW_WIDTH - 40);
    
    FlowPane findAssignmentsHolder = new FlowPane(findAssignments);
    findAssignmentsHolder.setPrefWrapLength(WINDOW_WIDTH - 40);
    findAssignmentsHolder.setPadding(new Insets(0, 0, 30, 0));
    
    Button close = new Button("Close");
    close.setId("bigButton");
    close.setOnAction(e -> {
      window.close();
    });
    
    VBox pane = new VBox(search, comingSoonHolder, findAssignmentsHolder, close);
    pane.setPadding(new Insets(20, 20, 20, 20));
    
    Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
    scene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS

    window.setScene(scene);
    window.setTitle(title);
    window.showAndWait();
  }
}
