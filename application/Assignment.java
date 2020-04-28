package application;

import java.util.Date;

public class Assignment implements Comparable<Assignment> {

  private String assignmentName;
  private Class className;
  private int difficulty;
  private Date startDate;
  private Date dueDate;
  private String dueTime;
  private boolean completed;
  private int priority;

  public Assignment(String assignmentName, Class className, int difficulty, Date startDate,
      Date dueDate, String dueTime, boolean completed) {
    this.assignmentName = assignmentName;
    this.className = className;
    this.difficulty = difficulty;
    this.startDate = startDate;
    this.dueDate = dueDate;
    this.dueTime = dueTime;
    this.completed = completed;

    priority = difficulty * className.getDifficulty() * dueDate.compareTo(startDate);
  }

  public String getAssignmentName() {
    return assignmentName;
  }

  public void setAssignmentName(String assignmentName) {
    this.assignmentName = assignmentName;
  }

  public Class getClassName() {
    return className;
  }

  public void setClassName(Class className) {
    this.className = className;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public String getDueTime() {
    return dueTime;
  }

  public void setDueTime(String dueTime) {
    this.dueTime = dueTime;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  @Override
  public int compareTo(Assignment o) {
    return this.priority - o.priority;
  }

  @Override
  public String toString() {
    return this.assignmentName;
  }
}
