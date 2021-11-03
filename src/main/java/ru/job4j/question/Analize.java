package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static int countAdded;
    public static int countChanged;
    public static int countDeleted;

    public static Info diff(Set<User> previous, Set<User> current) {
        Info inf = new Info(0, 0, 0);
        Map<Integer, User> mapPrev = new HashMap<>();
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

        for (User i : previous) {
            mapPrev.put(i.getId(), i);
            for (User j : current) {
                mapPrev.put(j.getId(), j);
                if (!mapPrev.get(i.getId()).equals(mapPrev.get(j.getId())) && i.getId() == j.getId()) {
                    inf.setChanged(countChanged++);
                }
            }
        }

        return inf;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
