package HACS.Reminders;

import HACS.Assignment.Assignment;
import HACS.Course.Course;
import HACS.Facade;
import HACS.Interfaces.Reminder;
import HACS.Iterators.CourseIterator;
import HACS.Iterators.Lists.ClassCourseList;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class ReminderVisitor extends NodeVisitor{

    @Override
    public void visitFacade(Facade facade) {
        ClassCourseList classCourseList = new ClassCourseList(facade.courseList);
        CourseIterator iterator = new CourseIterator(classCourseList);
        System.out.println("Course List:");
        while(iterator.hasNext()) {
            int index = iterator.getCurrentIndex();
            Course course = (Course) iterator.next();
            System.out.println(course.getCourseName());
            System.out.println();
        }
    }

    @Override
    public void visitAssignment(Assignment assignment) {
        LocalDateTime dueDate = LocalDateTime.ofInstant(assignment.getDueDate().toInstant(), ZoneId.systemDefault());
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, dueDate);
        Long hours = Math.abs(duration.toHours());
        System.out.println(hours + " hours left before assignment is due");
        System.out.println();

    }

    @Override
    public void visitCourse(Course course) {
        List<Assignment> assignments = course.getAssignments();
        System.out.println("Assignments:");
        if (assignments.size() > 0) {
            for (int index = 0; index < assignments.size(); index++) {
                System.out.println(assignments.get(index).getAssignmentText());
            }
        } else {
            System.out.println("No Assignment Available");
        }
        System.out.println();
    }
}
