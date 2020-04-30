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

public class WhatToDoNowWindow {

  private static final int WINDOW_WIDTH = 640;
  private static final int WINDOW_HEIGHT = 280;

  public static void newWindow(String title) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    Text crystal = new Text("Crystal");
    crystal.setStyle("-fx-font-weight: 700;");

    Text workOn = new Text(" says you should work on");

    HBox topText = new HBox(crystal, workOn);
    topText.setPrefWidth(WINDOW_WIDTH);
    
    PriorityQueue whatToDoNow = Main.getWhatToDoNow();
    
    HBox assignmentBox = new HBox();
    assignmentBox.getStyleClass().add("assignmentBox");
    
    Assignment highestPriority = whatToDoNow.peekBest();
    String name = highestPriority.getAssignmentName();   
    
    int[] classColor = highestPriority.getClassName().getClassColor();
    assignmentBox.setStyle("-fx-background-color: rgba(" + classColor[0] + ", " + classColor[1]
        + ", " + classColor[2] + ", 0.15); -fx-border-color: rgb(" + classColor[0] + ", "
        + classColor[1] + ", " + classColor[2] + ");");

    Text due = new Text("Due ");
    
    Text time = new Text(highestPriority.getDueDate().format(DateTimeFormatter.ofPattern("EEEE, MMMM d"))
            + " at " + highestPriority.getDueTime());
    time.setId("time");

    Text desc = new Text(" - " + name);
    assignmentBox.getChildren().addAll(due, time, desc);
    
    Label basedOn = new Label(
        "based on the difficulty level of the class, the difficulty level of the assignment, and when the assignment is due.");
    basedOn.setStyle("-fx-font-weight: 100;");
    basedOn.setPrefWidth(WINDOW_WIDTH - 60 - 60);
    basedOn.setWrapText(true);

    FlowPane bottomText = new FlowPane(basedOn);
    
    VBox pane = new VBox(topText, assignmentBox, bottomText);
    pane.setPadding(new Insets(40, 60, 40, 60));
    pane.setSpacing(20);

    Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
    scene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS

    window.setScene(scene);
    window.setTitle(title);
    window.showAndWait();
  }
}
