package HACS.Solutions;

public class Solution {
    private String solution;
    private int grade;
    private boolean isGraded = false;
    private boolean isReported = false;

    public Solution(String solution){
        this.solution = solution;
    }

    public String getSolution(){
        return solution;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
        this.isGraded = true;
    }

    public boolean isGraded() { return isGraded; }

    public boolean isReported() { return isReported; }

    public void reportSolution() { isReported = true; }
}
