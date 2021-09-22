package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> userMap = new HashMap<>();

    private synchronized boolean add(User user) {
        return userMap.putIfAbsent(user.getId(), user) == null;
    }

    private synchronized boolean update(User user) {
        return userMap.replace(user.getId(), userMap.get(user), new User(user.getId(), user.getAmount()));
    }

    public synchronized boolean delete(User user) {
        return userMap.remove(user.getId()) != null;
    }

    private synchronized boolean transfer(int fromId, int toId, int amount) {
        if (userMap.containsKey(fromId) && userMap.containsKey(toId)) {
            userMap.put(fromId, new User(fromId, userMap.get(fromId).getAmount() - amount));
            userMap.put(toId, new User(toId, userMap.get(toId).getAmount() + amount));
            return true;
        }
        return false;
    }
}
