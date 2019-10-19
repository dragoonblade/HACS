package HACS.Person;

import HACS.Enums.UserType;

public class UserInfoItem {
    private UserType userType;
    private String userName;
    private String password;

    public UserInfoItem(UserType userType, String userName, String password){
        this.userType = userType;
        this.userName = userName;
        this.password = password;
    }

    public UserType getUserType(){
        return userType;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
