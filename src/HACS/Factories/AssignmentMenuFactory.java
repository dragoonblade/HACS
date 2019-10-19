package HACS.Factories;

import HACS.Assignment.Assignment;
import HACS.Assignment.AssignmentMenu;
import HACS.Assignment.StudentAssignmentMenu;
import HACS.Enums.UserType;
import HACS.Assignment.InstructorAssignmentMenu;

public class AssignmentMenuFactory {

    private Assignment assignment;
    public AssignmentMenuFactory(Assignment assignment) {
        this.assignment = assignment;
    }

    public AssignmentMenu getAssignmentMenu(UserType userType) {
        AssignmentMenu assignmentMenu = null;
        switch (userType){
            case STUDENT:
                assignmentMenu = new StudentAssignmentMenu(this.assignment);
                break;
            case TEACHER:
                assignmentMenu = new InstructorAssignmentMenu(this.assignment);
                break;
        }
        return assignmentMenu;
    }

}
