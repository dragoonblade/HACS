package HACS.Course;

import HACS.Assignment.Assignment;
import HACS.Enums.CourseLevelType;
import HACS.Interfaces.Reminder;
import HACS.Reminders.NodeVisitor;

import java.util.ArrayList;
import java.util.List;

public class Course implements Reminder {
    private String courseName;
    private List<Assignment> assignments;
    private CourseLevelType courseLevelType;

    public Course(String courseName, CourseLevelType courseLevelType) {
        assignments = new ArrayList<>();
        this.courseName = courseName;
        this.courseLevelType = courseLevelType;
    }

    public String getCourseName() {
        return courseName;
    }

    public CourseLevelType getCourseLevelType() {
        return courseLevelType;
    }

    public void setAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitCourse(this);
    }
}
