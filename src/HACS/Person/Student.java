package HACS.Person;

import HACS.Course.CourseMenu;
import HACS.Enums.CourseLevelType;
import HACS.Enums.UserType;
import HACS.Factories.CourseFactory;

public class Student extends Person {

    @Override
    public void showMenu() {
        showViewButton();
        showRadioButton();
        showComBoxes();
        showLabels();
    }

    @Override
    public CourseMenu createCourseMenu(CourseLevelType courseLevelType) {
        CourseMenu courseMenu = new CourseFactory().getCourseMenu(UserType.STUDENT, courseLevelType);
        updateCourseLevel(courseMenu);
        return courseMenu;
    }

}
