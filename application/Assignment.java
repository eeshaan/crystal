package application;

public class Assignment {
	
	private String assignmentName;
	private Class className;
	private int difficulty;
	private String startDate;
	private String dueDate;
	private String dueTime;
	private boolean completed;
	
	public Assignment(String assignmentName, Class className, int difficulty, String startDate, String dueDate, String dueTime) {
		this.assignmentName = assignmentName;
		this.className = className;
		this.difficulty = difficulty;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.dueTime = dueTime;
		completed = false;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
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
}
