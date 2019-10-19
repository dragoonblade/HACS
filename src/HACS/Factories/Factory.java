package HACS.Factories;

import HACS.Course.CourseMenu;
import HACS.Enums.CourseLevelType;
import HACS.Enums.UserType;

public abstract class Factory {

     public abstract CourseMenu getCourseMenu(UserType userType, CourseLevelType courseLevelType);

}
