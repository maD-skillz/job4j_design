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
        Map<String, Integer> mapPrev = new HashMap<>();
        Map<String, Integer> mapCur = new HashMap<>();
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
            mapPrev.put(i.getName(), i.getId());
        }

        for (User j : current) {
            mapCur.put(j.getName(), j.getId());
        }

        for (Map.Entry<String, Integer> ind : mapPrev.entrySet()) {
            for (Map.Entry<String, Integer> ind2 : mapCur.entrySet()) {
                if (!ind.getKey().equals(ind2.getKey())
                && ind.getValue().equals(ind2.getValue())) {
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
