package HACS.Database;

import HACS.Interfaces.DatabaseManager;

public class Login {

    private DatabaseManager databaseManager;

    public Login(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
    }
    public DatabaseSystem.User verifyUser(String userName, String password) {
        return databaseManager.verifyUser(userName, password);
    }
}
