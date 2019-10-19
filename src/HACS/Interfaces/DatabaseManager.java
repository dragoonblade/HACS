package HACS.Interfaces;

import HACS.Course.Course;
import HACS.Database.DatabaseSystem;
import HACS.Person.UserInfoItem;

import java.util.List;

public interface DatabaseManager {

    DatabaseSystem.User verifyUser(String userName, String password);
    void createUser(UserInfoItem userInfoItem) throws Exception;
    List<Course> getCourseList();
}
