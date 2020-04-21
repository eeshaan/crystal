package application;

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

  private static final int WINDOW_WIDTH = 640; // divide Figma by 2.039335664
  private static final int WINDOW_HEIGHT = 280;
  
  public static void newWindow(String title) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    VBox pane = new VBox();
    pane.setPadding(new Insets(40, 60, 40, 60));
    pane.setSpacing(20);

    HBox topText = new HBox();
    topText.setPrefWidth(WINDOW_WIDTH);
    
    Text crystal = new Text("Crystal");
    crystal.setStyle("-fx-font-weight: 700;");
    
    Text workOn = new Text(" says you should work on");
    
    topText.getChildren().addAll(crystal, workOn);
    
    // CS400 assignment example
    HBox assignmentBox2 = new HBox();
    assignmentBox2.getStyleClass().add("assignmentBox");
    assignmentBox2.setId("ass_CS400");

    Text time2 = new Text("11:59 PM");
    time2.setId("time");

    Text desc2 = new Text(" - p6");
    assignmentBox2.getChildren().addAll(time2, desc2);
    
    FlowPane bottomText = new FlowPane();    
    
    Label basedOn = new Label("based on the difficulty level of the class, the difficulty level of the assignment, and when the assignment is due.");
    basedOn.setStyle("-fx-font-weight: 100;");
    basedOn.setPrefWidth(WINDOW_WIDTH - 60 - 60);
    basedOn.setWrapText(true);
    // basedOn.setWrappingWidth(200);
    
    bottomText.getChildren().add(basedOn);
    
    pane.getChildren().addAll(topText, assignmentBox2, bottomText);
    
    Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
    scene.getStylesheets().add(AddAssignmentWindow.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS
    
    window.setScene(scene);
    window.setTitle(title);
    window.showAndWait();
  }
}
