package application;

import java.time.format.DateTimeFormatter;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * WhatToDoNowWindow - creates the WhatToDoNow window
 * @author Ayaz Franz, Benjamin Tan, Bryan Lin, Devin Demirlika, Eeshaan Pirani
 */
public class WhatToDoNowWindow {

  private static final int WINDOW_WIDTH = 640; // Width of the window
  private static final int WINDOW_HEIGHT = 280; // Height of the window

  /**
   * Formats the window and makes it visible
   * @param title - title of the window
   */
  public static void newWindow(String title) {
    // Creates stage and formats it
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    // Creates first half of recommendation and formats it
    Text crystal = new Text("Crystal");
    crystal.setStyle("-fx-font-weight: 700;");

    // Creates second half of recommendation
    Text workOn = new Text(" says you should work on");

    // Formats recommendation
    HBox topText = new HBox(crystal, workOn);
    topText.setPrefWidth(WINDOW_WIDTH);

    // Gets assignment priority queue
    PriorityQueue whatToDoNow = Main.getWhatToDoNow();

    // Makes assignment display and formats it
    HBox assignmentBox = new HBox();
    assignmentBox.getStyleClass().add("assignmentBox");

    // Gets the assignment with the highest priority
    Assignment highestPriority = whatToDoNow.peekBest();
    VBox pane; // Makes pane


    if (highestPriority == null) { // If there are no assignments to be done and formats the message
      workOn.setText(" says you should relax. You have no upcoming assignments!");

      pane = new VBox(topText);
      pane.setPadding(new Insets(115, 60, 40, 60));
      pane.setSpacing(50);
    } else { // Recommends an assignment and formats it based on css, class info, and assignment info
      String name = highestPriority.getAssignmentName();

      int[] classColor = highestPriority.getClassName().getClassColor();
      assignmentBox.setStyle("-fx-background-color: rgba(" + classColor[0] + ", " + classColor[1]
          + ", " + classColor[2] + ", 0.15); -fx-border-color: rgb(" + classColor[0] + ", "
          + classColor[1] + ", " + classColor[2] + ");");

      // Sets up due time and date
      Text due = new Text("Due ");

      // Sets up due time and formats it
      Text time =
          new Text(highestPriority.getDueDate().format(DateTimeFormatter.ofPattern("EEEE, MMMM d"))
              + " at " + highestPriority.getDueTime());
      time.setId("time");

      // Adds description of assignment
      Text desc = new Text(" - " + name);
      assignmentBox.getChildren().addAll(due, time, desc);

      // Adds reasoning for recommendation and formats it
      Label basedOn = new Label(
          "based on the difficulty level of the class, the difficulty level of the assignment, and when the assignment is due.");
      basedOn.setStyle("-fx-font-family: \"Work Sans Light\";");
      basedOn.setPrefWidth(WINDOW_WIDTH - 60 - 60);
      basedOn.setWrapText(true);

      // Sets reasoning to be bottom of window
      FlowPane bottomText = new FlowPane(basedOn);

      // Adds components to the window and formats the margins and spacing
      pane = new VBox(topText, assignmentBox, bottomText);
      pane.setPadding(new Insets(40, 60, 40, 60));
      pane.setSpacing(20);
    }

    // Creates new scene and formats it based on css file
    Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
    scene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS

    // Set up window and make it visible
    window.setScene(scene);
    window.setTitle(title);
    window.showAndWait();
  }
}