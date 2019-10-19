package HACS.Database;

import HACS.Course.Course;
import HACS.Enums.CourseLevelType;
import HACS.Enums.UserType;
import HACS.Interfaces.DatabaseManager;
import HACS.Person.UserInfoItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseSystem implements DatabaseManager {

    private static DatabaseManager mDatabaseManager;
    private Map<String, User> userMap = new HashMap<>();
    private List<Course> courseList = new ArrayList<>();

    private DatabaseSystem(){
        userMap.put("pepe", new User(UserType.STUDENT, "1111"));
        userMap.put("yaya", new User(UserType.STUDENT, "2222"));
        userMap.put("yape", new User(UserType.STUDENT, "3333"));
        userMap.put("Inst1", new User(UserType.TEACHER, "1111"));

        courseList.add(new Course("CSE890", CourseLevelType.HIGH));
        courseList.add(new Course("CSE870", CourseLevelType.HIGH));
        courseList.add(new Course("CSE880", CourseLevelType.LOW));
    }

    public static DatabaseManager getInstance(){
        if(mDatabaseManager == null){
            mDatabaseManager = new DatabaseSystem();
        }
        return mDatabaseManager;
    }

    @Override
    public User verifyUser(String userName, String password) {
        if(userMap.containsKey(userName) && userMap.get(userName).getPassword().equals(password)){
            return userMap.get(userName);
        }else{
            return null;
        }
    }

    @Override
    public void createUser(UserInfoItem userInfoItem) throws Exception {
        if(userMap.containsKey(userInfoItem.getUserName())){
            throw new Exception("User Already Exists");
        }else{
            User user = new User(userInfoItem.getUserType(), userInfoItem.getPassword());
            userMap.put(userInfoItem.getUserName(), user);
        }
    }

    @Override
    public List<Course> getCourseList() {
        return courseList;
    }

    public static class User{
        private UserType userType;
        private String password;

        User(UserType userType, String password){
            this.userType = userType;
            this.password = password;
        }

        public UserType getUserType() {
            return userType;
        }

        String getPassword() {
            return password;
        }
    }
}
