package HACS.Assignment;

public abstract class AssignmentMenu {

    private Assignment assignment;
    public AssignmentMenu(Assignment assignment) {
        this.assignment = assignment;
    }

    public abstract void showAssignmentMenu();

    public abstract void viewAssignment();
}
