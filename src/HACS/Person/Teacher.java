package HACS.Person;

import HACS.Course.CourseMenu;
import HACS.Enums.CourseLevelType;
import HACS.Enums.UserType;
import HACS.Factories.CourseFactory;

public class Teacher extends Person {

    @Override
    public void showMenu() {
        showAddButton();
        showViewButton();
        showRadioButton();
        showComBoxes();
        showLabels();
    }

    @Override
    public CourseMenu createCourseMenu(CourseLevelType courseLevelType) {
        CourseMenu courseMenu = new CourseFactory().getCourseMenu(UserType.TEACHER, courseLevelType);
        updateCourseLevel(courseMenu);
        return courseMenu;
    }

}
