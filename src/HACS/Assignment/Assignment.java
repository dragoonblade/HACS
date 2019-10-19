package HACS.Assignment;

import HACS.Interfaces.Reminder;
import HACS.Iterators.Lists.SolutionList;
import HACS.Reminders.NodeVisitor;

import java.util.Date;

public class Assignment implements Reminder {
    private String assignmentText;
    private SolutionList solutionList;
    private Date dueDate;
    private Boolean isSubmitted = false;

    public Assignment(String assignmentText, Date dueDate) {
        this.assignmentText = assignmentText;
        this.dueDate = dueDate;
    }

    public SolutionList getSolutionList() {
        return solutionList;
    }

    public void setSolutionList(SolutionList solutionList) {
        this.solutionList = solutionList;
    }

    public String getAssignmentText() {
        return assignmentText;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void submitAssignment() { this.isSubmitted = true; }

    public Boolean isAssignmentSubmitted() { return this.isSubmitted; }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitAssignment(this);
    }
}
