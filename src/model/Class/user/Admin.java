package model.Class.user;

import model.Enum.UserType;

public class Admin extends User{
    int adminID;

    public Admin(int userID, String username, String name, String password, String phoneNumber, String email, UserType userType, int adminID) {
        super(userID, username, name, password, phoneNumber, email, userType);
        this.adminID = adminID;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }
}
