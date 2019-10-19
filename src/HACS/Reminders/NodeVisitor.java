package HACS.Reminders;

import HACS.Assignment.Assignment;
import HACS.Course.Course;
import HACS.Facade;

public abstract class NodeVisitor {

    public abstract void visitFacade(Facade facade);
    public abstract void visitAssignment(Assignment assignment);
    public abstract void visitCourse(Course course);

}
