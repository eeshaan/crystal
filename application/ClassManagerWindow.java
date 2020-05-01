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

  private static final int WINDOW_WIDTH = 580;
  private static final int WINDOW_HEIGHT = 600;

  static int i = 1;

  public static void newWindow(String title) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    Text header = new Text(title);
    header.setId("h2");
    HBox headerBox = new HBox(header);

    GridPane pane = new GridPane();

    JSONArray classesJSONArrayToSet = Main.getJSONClasses();


    pane.setHgap(10);
    pane.setVgap(10);
    pane.setMaxWidth(WINDOW_WIDTH);

    
    TextField textField[] = new TextField[100];
    ColorPicker cp[] = new ColorPicker[100];
    Spinner<Integer> difficulty[] = new Spinner[100];
    
    //if starting application for the first time (not loading from JSON) first add class row is already there
    if(title.equals("Add your classes!")){
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
    		//pull className from the class iterator
    		className = classIterator.next();
    		
    		//use className to get the classObject from the hashtable
    		Class classObj = classTable.get(className);
    		
    		//initialize textfield for class Name
    		textField[i] = new TextField(); 	//initialize
    		textField[i].setText(className);	//set text as className
    		textField[i].setEditable(false); 	//make textfield uneditable
    		
    		//setting class color
    		int[] colorArr = classObj.getClassColor(); //get rgb values from class object
    		Color color = Color.rgb(colorArr[0], colorArr[1], colorArr[2]); //use rgb values to construct a color object
    	    cp[i] = new ColorPicker(color); //use color object to set colorpicker value
    	    cp[i].setDisable(true); //make colorpicker uneditable
    	    
    	    //set class difficulty
    	    difficulty[i] = new Spinner<>(1, 5, 3, 1); //initialize spinner
    	    difficulty[i].getValueFactory().setValue(classObj.getDifficulty()); //set existing difficulty in the spinner
    	    difficulty[i].setDisable(true); //make the spinner uneditable
    	    difficulty[i].getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL); //style spinner
    	    
    	
    	    pane.add(new Text("Class Name:"), 0, (i * 2) - 1);
    	    pane.add(textField[i], 0, i * 2);
    	    pane.add(new Text("Color:"), 5, (i * 2) - 1);
    	    pane.add(cp[i], 5, i * 2);
    	    pane.add(new Text("Difficulty (1-5):"), 7, (i * 2) - 1);
    	    pane.add(difficulty[i], 7, i * 2);
    	
    	    i++;
    	}
    }
    
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
    HBox addAnother = new HBox(btn);
    HBox.setHgrow(btn, Priority.ALWAYS);
    addAnother.setPadding(new Insets(12.5, 0, 20, 0));

    Button submit = new Button("Submit classes and launch");
    submit.setId("bigButton");
    HBox bottom = new HBox(submit);

    HashTable<String, Class> classes = Main.getClasses();
    submit.setOnAction(e -> {
      for (int k = 1; k < i; k++) {
        JSONObject newJSONClass = new JSONObject();
        //List<Node> childList = (List<Node>) pane.getChildren();
        //System.out.println(childList.contains(textField[k]));
        if(textField[k] != null) {
        	if(classes.get(textField[k].getText()) == null) {
		        newJSONClass.put("className", textField[k].getText());
		        newJSONClass.put("classColorRed", (int) (cp[k].getValue().getRed() * 255));
		        newJSONClass.put("classColorGreen", (int) (cp[k].getValue().getGreen() * 255));
		        newJSONClass.put("classColorBlue", (int) (cp[k].getValue().getBlue() * 255));
		        newJSONClass.put("difficulty", difficulty[k].getValue());
		
		        Class newClass = new Class(textField[k].getText(), (int) (cp[k].getValue().getRed() * 255),
		            (int) (cp[k].getValue().getGreen() * 255), (int) (cp[k].getValue().getBlue() * 255),
		            difficulty[k].getValue());
		        classesJSONArrayToSet.add(newJSONClass);
		        classes.insert(textField[k].getText(), newClass);
        	}
        }
      }
      Main.setJSONClasses(classesJSONArrayToSet);
      Main.setClasses(classes);
      window.close();
    });

    VBox root = new VBox(headerBox, pane, addAnother, bottom);
    root.setPadding(new Insets(20, 20, 20, 20));

    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    scene.getStylesheets()
        .add(Main.class.getResource("/application/src/css/style.css").toExternalForm()); // link CSS

    window.setScene(scene);
    window.setTitle(title);
    window.showAndWait();
  }
}
