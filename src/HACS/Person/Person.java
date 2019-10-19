package HACS.Person;

import HACS.Course.Course;
import HACS.Course.CourseMenu;
import HACS.Enums.CourseLevelType;

import java.util.ArrayList;

public abstract class Person {

    private CourseMenu mCourseMenu;
    private ArrayList<Course> courseList = new ArrayList<>();

    Person() {

    }

    void showAddButton() {
        mCourseMenu.showAddButton();
    }

    void showViewButton() {
        mCourseMenu.showViewButton();
    }

    void showRadioButton() {
        mCourseMenu.showRadioButton();
    }

    void showLabels() {
        mCourseMenu.showLabels();
    }

    void showComBoxes() {
        mCourseMenu.showComBoxes();
    }

    void updateCourseLevel(CourseMenu courseMenu) {
        this.mCourseMenu = courseMenu;
    }

    public abstract void showMenu();

    public abstract CourseMenu createCourseMenu(CourseLevelType courseLevelType);

    public void addCourse(Course course){
        courseList.add(course);
    }
    public void removeCourse(Course course){
        courseList.remove(course);
    }

}
