package application;

import java.time.LocalDate;

/**
 * 
 * Assignment - Assignment data structure containing the information of the Assignment in Crystal
 * @author Ayaz Franz, Benjamin Tan, Bryan Lin, Devin Demirlika, Eeshaan Pirani
 */
public class Assignment implements Comparable<Assignment> {

  private String assignmentName; // name of the Assignment
  private Class className; // subject of the Assignment
  private int difficulty; // difficulty rating of the Assignment
  private LocalDate startDate; // start date of the Assignment
  private LocalDate dueDate; // due date of the Assignment
  private String dueTime; // due time of the Assignment
  private boolean completed; // if the Assignment is completed
  private int priority; // priority rating of the Assignment

  /**
   * Assignment - constructor of the Assignment, setting all the values of it to those provided
   * @param assignmentName - name of the Assignment
   * @param className - subject of the Assignment
   * @param difficulty - difficulty rating of the Assignment
   * @param startDate - start date of the Assignment
   * @param dueDate - due date of the Assignment
   * @param dueTime - due time of the Assignment
   * @param completed - if the Assignment is completed
   */
  public Assignment(String assignmentName, Class className, int difficulty, LocalDate startDate,
      LocalDate dueDate, String dueTime, boolean completed) {
    this.assignmentName = assignmentName;
    this.className = className;
    this.difficulty = difficulty;
    this.startDate = startDate;
    this.dueDate = dueDate;
    this.dueTime = dueTime;
    this.completed = completed;

    // Calculates the priority of the assignment based on its due date compared to today's date
    int dateDifference = dueDate.compareTo(LocalDate.now());
    if(dateDifference == 0) {
        priority = 100 * difficulty;
    }
    else {
        priority = difficulty * className.getDifficulty() - dateDifference;
    }   

  }

  /**
   * getAssignmentName - gets the name of the Assignment
   * @return name of the Assignment
   */
  public String getAssignmentName() {
    return assignmentName;
  }

  /**
   * setAssignmentName - sets the Assignment's name as the one provided
   * @param assignmentName - new name of the Assignment
   */
  public void setAssignmentName(String assignmentName) {
    this.assignmentName = assignmentName;
  }

  /**
   * getClassName - gets the class of the Assignment
   * @return the Class of the Assignment
   */
  public Class getClassName() {
    return className;
  }

  /**
   * setClassName - sets the Class of the Assignment as the one provided
   * @param className - new class of the Assignment
   */
  public void setClassName(Class className) {
    this.className = className;
  }

  /**
   * getDifficulty - gets the difficulty of the Assignment
   * @return the difficulty of the Assignment
   */
  public int getDifficulty() {
    return difficulty;
  }

  /**
   * setDifficulty - sets the difficulty of the Assignment as the one provided
   * @param difficulty - new difficulty of the Assignment
   */
  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  /**
   * getStartDate - gets the start date of the Assignment
   * @return the start date of the Assignment
   */
  public LocalDate getStartDate() {
    return startDate;
  }

  /**
   * setStartDate - sets the start date of the Assignment as the one provided
   * @param startDate - the new start date of the Assignment
   */
  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  /**
   * getDueDate - gets the due date of the Assignment
   * @return dueDate - the due date of the Assignment
   */
  public LocalDate getDueDate() {
    return dueDate;
  }

  /**
   * setDueDate - sets the due date of the Assignment as the one provided
   * @param dueDate - the new due date of the Assignment
   */
  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  /**
   * getDueTime - gets the due time of the Assignment
   * @return dueTime - the due time of the Assignment
   */
  public String getDueTime() {
    return dueTime;
  }

  /**
   * setDueTime - sets the due time of the Assignment as the one provided
   * @param dueTime - the new due time of the Assignment
   */
  public void setDueTime(String dueTime) {
    this.dueTime = dueTime;
  }

  /**
   * isCompleted - checks if the assignment is completed
   * @return if assignment is completed
   */
  public boolean isCompleted() {
    return completed;
  }

  /**
   * setCompleted - sets the assignment as either completed or not depending on 
   * the boolean provided
   * @param completed - if the Assignment is completed
   */
  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  /**
   * compareTo - compares the priority rating of this Assignment and the one provided
   * @return int representing the difference between this Assignment's priority and
   * the one given
   */
  @Override
  public int compareTo(Assignment o) {
    return this.priority - o.priority;
  }

  /**
   * toString - gets this Assignment's name
   * @return this Assignment's name
   */
  @Override
  public String toString() {
    return this.assignmentName;
  }
}