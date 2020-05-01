package application;

import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * CourseAssignmentsWindow - TODO Describe purpose of this user-defined type
 * 
 * @author Ayaz Franz, Benjamin Tan, Bryan Lin, Devin Demirlika, Eeshaan Pirani
 */
public class ClassAssignmentsWindow {

  /**
   * Creates the Course Assignments window
   * 
   * @param className - name of the class
   */
  public static void newWindow(Class className) {
    // Creates stage and formats it
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    // Gets HashTable of assignments
    HashTable<Class, LinkedList> assignmentsByClass = Main.getAssignmentsByClass();

    // Creates header of window
    Text header = new Text(className.getClassName() + " Assignments");
    header.setId("h2");

    // Creates vbox of assignments
    VBox assignmentsPane = new VBox(header);
    assignmentsPane.setSpacing(5);
    assignmentsPane.setPadding(new Insets(20, 20, 20, 20));

    Iterator<Assignment> today = assignmentsByClass.get(className).iterator();

    // Creates an assignment box for each assignment in the class
    while (today != null && today.hasNext()) {
      Assignment assignment = today.next(); // Gets assignments with nearest due date
      String name = assignment.getAssignmentName(); // Gets the assignment's name

      // Creates an hbox and formats it
      HBox assignmentBox = new HBox();
      assignmentBox.getStyleClass().add("assignmentBox");

      // Sets the color of the assignment to the class color
      int[] classColor = assignment.getClassName().getClassColor();
      assignmentBox.setStyle("-fx-background-color: rgba(" + classColor[0] + ", " + classColor[1]
          + ", " + classColor[2] + ", 0.15); -fx-border-color: rgb(" + classColor[0] + ", "
          + classColor[1] + ", " + classColor[2] + ");");

      // When clicked, the assignment information appears
      assignmentBox.setOnMouseClicked(e -> {
        Main.assignmentOptions(assignmentBox, assignment);
      });

      // Creates text for assignment due date
      Text due = new Text("Due ");

      // "Friday, April 17 at 11:59 PM" - intended format
      Text time =
          new Text(assignment.getDueDate().format(DateTimeFormatter.ofPattern("EEEE, MMMM d"))
              + " at " + assignment.getDueTime());
      time.setId("time");

      // Creates text for assignment description
      Text desc = new Text(" - " + name);
      assignmentBox.getChildren().addAll(due, time, desc);

      // Adds assignment into to display
      assignmentsPane.getChildren().add(assignmentBox);
      
      if (assignment.isCompleted())
        assignmentBox.getStyleClass().add("completed");
    }
    
    if (assignmentsByClass.get(className).toString().isEmpty()) {
      assignmentsPane.getChildren().add(new Text("There are no assignments in this class."));
    }

    // Creates scene and formats it
    Scene scene = new Scene(assignmentsPane, 600, 500);
    scene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS

    // Sets up window and makes it visible
    window.setScene(scene);
    window.setTitle(className.getClassName() + " Assignments");
    window.showAndWait();
  }
}
