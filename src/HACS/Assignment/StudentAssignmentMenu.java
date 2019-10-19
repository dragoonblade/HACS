package HACS.Assignment;

public class StudentAssignmentMenu extends AssignmentMenu {

    private Assignment assignment;
    public StudentAssignmentMenu(Assignment assignment) {
        super(assignment);
        this.assignment = assignment;
    }

    @Override
    public void showAssignmentMenu() {
        System.out.println(this.assignment.getAssignmentText() + " added by student");
    }

    @Override
    public void viewAssignment() {
        if (this.assignment.isAssignmentSubmitted()) {
            System.out.println(this.assignment.getAssignmentText() + " submitted");
        } else {
            System.out.println(this.assignment.getAssignmentText() + " is due on " + this.assignment.getDueDate());
        }
    }
}
