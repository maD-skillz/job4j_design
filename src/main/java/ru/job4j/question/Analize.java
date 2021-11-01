package ru.job4j.question;

import java.util.Set;

public class Analize {

    public static int countAdded;
    public static int countChanged;
    public static int countDeleted;

    public static Info diff(Set<User> previous, Set<User> current) {
        Info inf = new Info(0, 0, 0);
        for (User prevUser : previous) {
            if (!current.contains(prevUser)) {
                inf.setDeleted(countDeleted++);
            }
        }

        for (User currUser : current) {
            if (!previous.contains(currUser)) {
                inf.setAdded(countAdded++);
            }
        }

        for (User userGetPrev : previous) {
            for (User userGetCur : current) {
                if (userGetPrev.getId() == userGetCur.getId()
                        && !userGetPrev.getName().equals(userGetCur.getName())) {
                    inf.setChanged(countChanged++);
                }
            }
        }
        return inf;
    }
}
