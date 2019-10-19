package HACS.Interfaces;

import HACS.Person.UserInfoItem;

public interface FacadeManager {

    boolean login(String userName, String password);
    void addAssignment(String assignment);
    void viewAssignment();
    void gradeSolution();
    void reportSolutions();
    void submitSolution();
    void remind();
    void createUser(UserInfoItem userInfoItem) throws Exception;
    void createCourseList();
    void attachCourseToUser();
    void selectCourse();
    void courseOperation();
}
