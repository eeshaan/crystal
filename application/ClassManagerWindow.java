package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClassManagerWindow {

  private static final int WINDOW_WIDTH = 580;
  private static final int WINDOW_HEIGHT = 600;

  static int i = 1;

  public static void newWindow(String title) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    VBox root = new VBox();
    root.setPadding(new Insets(20, 20, 20, 20));

    HBox headerBox = new HBox();
    Text header = new Text(title);
    header.setId("h2");
    headerBox.getChildren().add(header);

    root.getChildren().add(headerBox);


    GridPane pane = new GridPane();

    pane.setHgap(10);
    pane.setVgap(10);
    pane.setMaxWidth(WINDOW_WIDTH);

    TextField textField[] = new TextField[15];
    ColorPicker cp[] = new ColorPicker[15];
    Spinner<Integer> difficulty[] = new Spinner[15];

    textField[i] = new TextField();
    cp[i] = new ColorPicker();
    difficulty[i] = new Spinner<>(1, 5, 3, 1);
    difficulty[i].getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);

    pane.add(new Text("Class Name:"), 0, (i * 2) - 1);
    pane.add(textField[i], 0, i * 2);
    pane.add(new Text("Color:"), 5, (i * 2) - 1);
    pane.add(cp[i], 5, i * 2);
    pane.add(new Text("Difficulty (1-5):"), 7, (i * 2) - 1);
    pane.add(difficulty[i], 7, i * 2);

    i++;

    Image addImage = new Image("/application/src/img/add-icon.png", 25, 25, false, false);
    ImageView add = new ImageView();
    add.setImage(addImage);

    Button btn = new Button("Add another class", add);
    btn.setId("addAnother");

    btn.setOnAction(e -> {
      textField[i] = new TextField();
      cp[i] = new ColorPicker();
      difficulty[i] = new Spinner<>(1, 5, 3, 1);
      difficulty[i].getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);

      pane.add(new Text("Class Name:"), 0, (i * 2) - 1);
      pane.add(textField[i], 0, i * 2);
      pane.add(new Text("Color:"), 5, (i * 2) - 1);
      pane.add(cp[i], 5, i * 2);
      pane.add(new Text("Difficulty (1-5):"), 7, (i * 2) - 1);
      pane.add(difficulty[i], 7, i * 2);

      i++;
    });

    btn.setMaxWidth(WINDOW_WIDTH);

    HBox addAnother = new HBox();
    HBox.setHgrow(btn, Priority.ALWAYS);
    addAnother.setPadding(new Insets(12.5, 0, 20, 0));
    addAnother.getChildren().add(btn);


    root.getChildren().addAll(pane, addAnother);

    HBox bottom = new HBox();
    Button submit = new Button("Submit classes and launch");
    submit.setId("bigButton");

    bottom.getChildren().addAll(submit);

    submit.setOnAction(e -> {
      window.close();
    });

    root.getChildren().add(bottom);

    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    scene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS

    window.setScene(scene);
    window.setTitle(title);
    window.showAndWait();
  }
}
