package HACS.Assignment;

public class InstructorAssignmentMenu extends AssignmentMenu {

    private Assignment assignment;
    public InstructorAssignmentMenu(Assignment assignment) {
        super(assignment);
        this.assignment = assignment;
    }

    @Override
    public void showAssignmentMenu() {
        System.out.println(this.assignment.getAssignmentText() + " added by instructor");
    }

    @Override
    public void viewAssignment() {
        if (this.assignment.isAssignmentSubmitted()) {
            System.out.println(this.assignment.getAssignmentText() + " is submitted");
        } else {
            System.out.println(this.assignment.getAssignmentText() + " is due on " + this.assignment.getDueDate());
        }
    }
}
