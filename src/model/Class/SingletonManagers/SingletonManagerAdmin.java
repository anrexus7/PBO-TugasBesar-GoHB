package model.Class.SingletonManagers;

import model.Class.user.Admin;

public class SingletonManagerAdmin {
    private static SingletonManagerAdmin instance;
    private Admin user;

    SingletonManagerAdmin() {
    }

    public static SingletonManagerAdmin getInstance() {
        if (instance == null) {
            instance = new SingletonManagerAdmin();
        }
        return instance;
    }

    public Admin getAdmin() {
        return user;
    }

    public void setAdmin(Admin user) {
        this.user = user;
    }

    public void logout() {
        user = null;
        instance = null;
    }
}
