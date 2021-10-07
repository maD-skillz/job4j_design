package ru.job4j.collection;

import java.util.*;

public class User {

    private String name;

    private int children;

    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }



    public static void main(String[] args) {
        Calendar cal = new GregorianCalendar(1900, Calendar.DECEMBER, 31);
        User user1 = new User("Ash", 2, cal);
        User user2 = new User("Ash", 2, cal);
        Map<User, Object> userMap = new HashMap<>();
        userMap.put(user1, new Object());
        userMap.put(user2, new Object());
    //    if (!userMap.get(user1).equals(userMap.get(user2))) {
    //        System.out.println("false");
    //    }
    //    System.out.println(userMap.get(user1));
    //    System.out.println(userMap.get(user2));
        int x = 8;
        int y = 8 % 3;
        System.out.println(y);
        System.out.println("-------------------------------------------------");
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println("-------------------------------------------------");
        System.out.println(System.identityHashCode(userMap.get(user1)));
        System.out.println(System.identityHashCode(userMap.get(user2)));
        System.out.println("-------------------------------------------------");
    }
}

