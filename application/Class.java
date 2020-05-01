package application;

/**
 * 
 * Class
 * @author Ayaz Franz, Benjamin Tan, Bryan Lin, Devin Demirlika, Eeshaan Pirani
 */
public class Class {
  private String className; // name of class
  private int[] classColor; // color format of class
  private int difficulty; // difficulty of class

  /**
   * Class - constructor for Class, setting the class values as the ones given
   * @param className - name of class
   * @param r - red value of class color
   * @param g - green value of class color
   * @param b - blue value of class color
   * @param difficulty - difficulty level of the class
   */
  public Class(String className, int r, int g, int b, int difficulty) {
    this.className = className;
    classColor = new int[] {r, g, b};
    this.difficulty = difficulty;
  }

  /**
   * getClassColor - gets the color of the class
   * @return an array containing the red, green, and blue values of the color
   */
  public int[] getClassColor() {
    return classColor;
  }

  /**
   * setClassColor - sets the class color as the one provided
   * @param classColor - array of rgb values for the class color
   */
  public void setClassColor(int[] classColor) {
    this.classColor = classColor;
  }

  /**
   * getClassName - gets the class name
   * @return the name of the class
   */
  public String getClassName() {
    return className;
  }

  /**
   * setClassName - sets the name of the Class as the one provided
   * @param className - new name of the class
   */
  public void setClassName(String className) {
    this.className = className;
  }

  /**
   * getDifficulty - gets the difficulty of the class
   * @return a number representing the difficulty of the class
   */
  public int getDifficulty() {
    return difficulty;
  }

  /**
   * setDifficulty - set the difficulty of the class as the one given
   * @param difficulty - the new difficulty rating for the class
   */
  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  /**
   * equals - checks if the class names are the same
   * @param other - another class name
   * @return true if the class names are the same and false otherwise
   */
  public boolean equals(Class other) {
      return this.className.equals(other.className);
  }
}