package application;

public class Class {
	private String className;
	private int[] classColor;
	private int difficulty;
	
	public Class(String className, int r, int g, int b, int difficulty) {
		this.className =className;
		classColor = new int[]{r, g, b};
		this.difficulty = difficulty;
	}

	public int[] getClassColor() {
		return classColor;
	}

	public void setClassColor(int[] classColor) {
		this.classColor = classColor;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
