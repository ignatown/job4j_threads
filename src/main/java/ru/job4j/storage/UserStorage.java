package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, Integer> userMap = new HashMap<Integer, Integer>();

    private synchronized boolean add(User user) {
        return userMap.putIfAbsent(user.getId(), user.getAmount()) == null;
    }

    private synchronized boolean update(User user) {
        return userMap.replace(userMap.get(user.getId()), userMap.get(user.getAmount()), user.getAmount());
    }

    public synchronized boolean delete(User user) {
        return userMap.remove(user.getId()) != null;
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
