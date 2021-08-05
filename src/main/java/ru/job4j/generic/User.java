package ru.job4j.generic;

public class User extends Base {
    private final String userName;

    protected User(String id, String userName) {
        super(id);
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }
}
