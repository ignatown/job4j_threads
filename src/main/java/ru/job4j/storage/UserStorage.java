package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    Map<Integer, Integer> userMap = new HashMap<Integer, Integer>();

    private synchronized boolean add(User user) {
        if (!userMap.containsKey(user.getId())) {
            userMap.put(user.getId(), user.getAmount());
            return true;
        }
        return false;
    }

    private synchronized boolean update(User user) {
        if (userMap.containsKey(user.getId())) {
            userMap.put(user.getId(), user.getAmount());
            return true;
        }
        return false;
    }

    private synchronized boolean transfer(int fromId, int toId, int amount) {
        if (userMap.containsKey(fromId) && userMap.containsKey(toId)) {
            userMap.put(fromId, userMap.get(fromId) - amount);
            userMap.put(toId, userMap.get(toId) + amount);
            return true;
        }
        return false;
    }
}
