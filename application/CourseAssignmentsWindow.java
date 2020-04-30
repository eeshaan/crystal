package application;

import java.util.Iterator;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CourseAssignmentsWindow {

  public static void newWindow(Class className) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    
    HashTable<Class, LinkedList> assignmentsByClass = Main.getAssignmentsByClass();
    VBox assignmentsPane = new VBox();
    
    Iterator<Assignment> today = assignmentsByClass.get(className).iterator();

    while (today != null && today.hasNext()) {
      Assignment assignment = today.next();
      String name = assignment.getAssignmentName();

      HBox assignmentBox = new HBox();
      assignmentBox.getStyleClass().add("assignmentBox");

      int[] classColor = assignment.getClassName().getClassColor();
      assignmentBox.setStyle("-fx-background-color: rgba(" + classColor[0] + ", " + classColor[1]
          + ", " + classColor[2] + ", 0.15); -fx-border-color: rgb(" + classColor[0] + ", "
          + classColor[1] + ", " + classColor[2] + ");");

      // assignmentBox.setId("ass_" + name);

      assignmentBox.setOnMouseClicked(e -> {
        Main.assignmentOptions(assignmentBox);
      });

      Text time = new Text(assignment.getDueTime());
      time.setId("time");

      Text desc = new Text(" - " + name);

      assignmentBox.getChildren().addAll(time, desc);

      assignmentsPane.getChildren().add(assignmentBox);
    }
    
    

    Scene scene = new Scene(assignmentsPane, 500, 500);
    scene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS

    window.setScene(scene);
    window.setTitle(className.getClassName() + " Assignments");
    window.showAndWait();
  }
}
