package ru.job4j.ref;

public class User {
    private int id;
    private String name;

    public static User of(String name, int id) {
        User user = new User();
        user.name = name;
        user.id = id;
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}