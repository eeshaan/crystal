package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClassManagerWindow {

  //Setting window dimensions
  private static final int WINDOW_WIDTH = 580;
  private static final int WINDOW_HEIGHT = 600;

  //setting index for vertical class input spacing
  static int i;

  /**
   * newWindow - launches the ClassManager GUI and fills window with any existing classes
   * 
   * @param title - title for the window (depending on if this is the first time classes are being added)
   */
  public static void newWindow(String title) {
    
    // reset i to 1 every time new window is opened
    i = 1;
    
	//initializing the Stage for the window
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    //Setting header for the window
    Text header = new Text(title);
    header.setId("h2");
    HBox headerBox = new HBox(header);

    //initialize GridPane for view
    GridPane pane = new GridPane();

    //initialize JSONArray for all of the JSON classes
    JSONArray classesJSONArrayToSet = Main.getJSONClasses();

    //setting spacing and width for the pane
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setMaxWidth(WINDOW_WIDTH);

    //initializing the arrays of input fields for the class variables
    TextField textField[] = new TextField[100]; 		//init class name textfield array
    ColorPicker cp[] = new ColorPicker[100]; 			//init class color colorpicker array
    Spinner<Integer> difficulty[] = new Spinner[100];	//init class difficulty spinner array

    // if starting application for the first time (not loading from JSON) first add class row is
    // already there
    if (title.equals("Add your classes!")) {
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
    } else {
      HashTable<String, Class> classTable = Main.getClasses();
      Iterator<String> classIterator = classTable.iterator();
      String className = "";
      while (classIterator.hasNext()) {
        // pull className from the class iterator
        className = classIterator.next();

        // use className to get the classObject from the hashtable
        Class classObj = classTable.get(className);

        // initialize textfield for class Name
        textField[i] = new TextField(); // initialize
        textField[i].setText(className); // set text as className
        textField[i].setEditable(false); // make textfield uneditable

        // setting class color
        int[] colorArr = classObj.getClassColor(); // get rgb values from class object
        Color color = Color.rgb(colorArr[0], colorArr[1], colorArr[2]); // use rgb values to
                                                                        // construct a color object
        cp[i] = new ColorPicker(color); // use color object to set colorpicker value
        cp[i].setDisable(true); // make colorpicker uneditable

        // set class difficulty
        difficulty[i] = new Spinner<>(1, 5, 3, 1); // initialize spinner
        difficulty[i].getValueFactory().setValue(classObj.getDifficulty()); // set existing
                                                                            // difficulty in the
                                                                            // spinner
        difficulty[i].setDisable(true); // make the spinner uneditable
        difficulty[i].getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL); // style
                                                                                        // spinner


        pane.add(new Text("Class Name:"), 0, (i * 2) - 1); 			//add class name header/prompt between input lines
        pane.add(textField[i], 0, i * 2); 							//add textfield for name on input line
        pane.add(new Text("Color:"), 5, (i * 2) - 1); 				//add class color header/prompt between input lines
        pane.add(cp[i], 5, i * 2); 									//add colorpicker for class color on input line
        pane.add(new Text("Difficulty (1-5):"), 7, (i * 2) - 1); 	//add difficulty header/prompt between input lines
        pane.add(difficulty[i], 7, i * 2); 							//add difficulty spinner on input line

        i++;	//increment i for placement of classes
      }
    }

    //adding image for plus sign on add class button
    Image addImage = new Image("/application/src/img/add-icon.png", 25, 25, false, false); 
    ImageView add = new ImageView();
    add.setImage(addImage);

    //initializing the add class button
    Button btn = new Button("Add another class", add);
    btn.setId("addAnother");

    //on "add class" button press, add another input line for a new class
    btn.setOnAction(e -> {
      textField[i] = new TextField(); 	//add text field
      cp[i] = new ColorPicker();		//add color picker
      difficulty[i] = new Spinner<>(1, 5, 3, 1);	//add difficulty spinner
      difficulty[i].getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL); //style difficulty spinner

      pane.add(new Text("Class Name:"), 0, (i * 2) - 1); 			//add class name header/prompt between input lines
      pane.add(textField[i], 0, i * 2); 							//add textfield for name on input line
      pane.add(new Text("Color:"), 5, (i * 2) - 1); 				//add class color header/prompt between input lines
      pane.add(cp[i], 5, i * 2); 									//add colorpicker for class color on input line
      pane.add(new Text("Difficulty (1-5):"), 7, (i * 2) - 1); 		//add difficulty header/prompt between input lines
      pane.add(difficulty[i], 7, i * 2); 							//add difficulty spinner on input line

      i++;	//increment i for placement of classes
    });

    btn.setMaxWidth(WINDOW_WIDTH);
    HBox addAnother = new HBox(btn); //addanother button
    HBox.setHgrow(btn, Priority.ALWAYS);
    addAnother.setPadding(new Insets(12.5, 0, 20, 0));

    Button submit = new Button("Submit classes and launch"); //submit button
    submit.setId("bigButton");
    HBox bottom = new HBox(submit); //adding submit button to bottom HBox

    HashTable<String, Class> classes = Main.getClasses(); //get hashtable of existing classes
    HashTable<Class, LinkedList> assignmentsByClass = Main.getAssignmentsByClass(); //get hashtable of existing assignments by class
    submit.setOnAction(e -> { // on submit button press
      for (int k = 1; k < i; k++) {
        JSONObject newJSONClass = new JSONObject(); //initialize a new JSONObject for every new class

        if (textField[k] != null) { //check if class field is valid
          if (classes.get(textField[k].getText()) == null) { //check if class already exists
        	  
        	  //add class variables to JSON class
            newJSONClass.put("className", textField[k].getText());
            newJSONClass.put("classColorRed", (int) (cp[k].getValue().getRed() * 255));
            newJSONClass.put("classColorGreen", (int) (cp[k].getValue().getGreen() * 255));
            newJSONClass.put("classColorBlue", (int) (cp[k].getValue().getBlue() * 255));
            newJSONClass.put("difficulty", difficulty[k].getValue());

            //make new class object
            Class newClass = new Class(textField[k].getText(),
                (int) (cp[k].getValue().getRed() * 255), (int) (cp[k].getValue().getGreen() * 255),
                (int) (cp[k].getValue().getBlue() * 255), difficulty[k].getValue());
            classesJSONArrayToSet.add(newJSONClass); //add JSON class to JSONArray
            classes.insert(textField[k].getText(), newClass); //add class object to Class Hashtable

            
            assignmentsByClass.insert(newClass, new LinkedList()); //add new class to the assignments by class datastructure
          }
        }
      }
      //Push the new class sets back to main
      Main.setJSONClasses(classesJSONArrayToSet);
      Main.setClasses(classes);
      //close the class manager window
      window.close();
    });
    //set Vbox for window and style
    VBox root = new VBox(headerBox, pane, addAnother, bottom);
    root.setPadding(new Insets(20, 20, 20, 20));

    //set scene for window and style
    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    scene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS

    //set the scene and the title for the window and display
    window.setScene(scene);
    window.setTitle(title);
    window.showAndWait();
  }
}
