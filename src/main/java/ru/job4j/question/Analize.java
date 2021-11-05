package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int countAdded = 0;
        int countChanged = 0;
        Map<Integer, User> mapPrev = new HashMap<>();

        for (User i : previous) {
            mapPrev.put(i.getId(), i);
        }

        for (User j : current) {
            User previousUser = mapPrev.putIfAbsent(j.getId(), j);
            if (previousUser != null) {
                if (!j.equals(previousUser)) {
                    countChanged++;
                }
            } else {
                countAdded++;
            }
        }
        int countDeleted = previous.size() - current.size() + countAdded;
        return new Info(countAdded, countChanged, countDeleted);
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
